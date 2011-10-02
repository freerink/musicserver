package name.reerink.musicserver;

import java.io.File;

import name.reerink.musicserver.db.Track;
import name.reerink.musicserver.db.Track.Type;
import name.reerink.musicserver.files.Callback;
import name.reerink.musicserver.service.MusicService;

import org.apache.log4j.Logger;

public class MusicCallbackImpl implements Callback {

	static Logger log = Logger.getLogger(MusicCallbackImpl.class);

	private MusicService musicService;

	private long fileCount = 0;

	public String toString() {
		return "fileCount: " + fileCount;
	}

	public void onFile(File file) {
		String artistName = file.getParentFile().getParentFile().getName();
		log.debug("Artist: " + artistName);
		String albumName = file.getParentFile().getName();
		log.debug("Album: " + albumName);
		String track = file.getName();
		log.debug("Track: " + track);
		String trackParts[] = track.split(" ", 2);
		int trackNumber;
		try {
			trackNumber = Integer.parseInt(trackParts[0]);
		} catch (NumberFormatException e) {
			log.warn("NumberFormatException skipping track: " + track
					+ " for artist '" + artistName + "' and album '"
					+ albumName + "'");
			return;
		}
		String title = trackParts[1];
		Type trackType = Type.UNKNOWN;
		for (Type type : Track.Type.values()) {
			if (title.toLowerCase().endsWith(type.name().toLowerCase())) {
				trackType = type;
				title = title.substring(0, title.length() - 1
						- type.name().length());
			}
		}
		if (trackType != Type.UNKNOWN) {
			try {
				getMusicService().addTrack(artistName, albumName, title,
						trackNumber, trackType);
				fileCount++;
			} catch (RuntimeException e) {
				log.warn("Exception adding track '"
						+ title + "', for artist '" + artistName
						+ "' and album '" + albumName + "'");
			}
		} else {
			log.info("Skipping unknown file type: " + file.getPath());
		}
	}

	public void onFolder(File dir) {
	}

	public void onError(File thing) {
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	public MusicService getMusicService() {
		return musicService;
	}

}
