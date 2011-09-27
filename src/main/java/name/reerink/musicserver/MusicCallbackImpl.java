package name.reerink.musicserver;

import java.io.File;

import name.reerink.musicserver.db.Artist;
import name.reerink.musicserver.files.Callback;
import name.reerink.musicserver.service.MusicService;

import org.apache.log4j.Logger;

public class MusicCallbackImpl implements Callback {

	static Logger log = Logger.getLogger(MusicCallbackImpl.class);

	private MusicService musicService;

	public void onFile(File file) {
		log.info("File: " + file.getName());
		log.info("Album: " + file.getParentFile().getName());
		String artistName = file.getParentFile().getParentFile().getName();
		log.info("Artist: " + artistName);
		if (getMusicService().findArtist(artistName).size() == 0) {
			Artist artist = new Artist(artistName);
			getMusicService().addArtist(artist);
		}
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
