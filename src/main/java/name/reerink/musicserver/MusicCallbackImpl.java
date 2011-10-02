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

	public void onFile(File file) {
		String track = file.getName();
		log.info("Track: " + track);
		String trackParts[] = track.split(" ", 2);
		int trackNumber = Integer.parseInt(trackParts[0]);
		String title = trackParts[1];
		Type trackType = Type.UNKNOWN;
		for (Type type : Track.Type.values()) {
			// log.info(type.name() + "=" + type.ordinal());
			if (title.toLowerCase().endsWith(type.name().toLowerCase())) {
				trackType = type;
				title = title.substring(0, title.length() - 1
						- type.name().length());
			}
		}
		String albumName = file.getParentFile().getName();
		log.info("Album: " + albumName);
		String artistName = file.getParentFile().getParentFile().getName();
		log.info("Artist: " + artistName);
		getMusicService().addTrack(artistName, albumName, title, trackNumber,
				trackType);
	}

	public void onFolder(File dir) {
		// TODO Auto-generated method stub
	}

	public void onError(File thing) {
		// TODO Auto-generated method stub
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	public MusicService getMusicService() {
		return musicService;
	}

}
