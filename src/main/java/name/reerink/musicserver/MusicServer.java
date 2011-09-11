package name.reerink.musicserver;

import java.io.File;

import name.reerink.musicserver.files.Traverser;
import name.reerink.musicserver.misc.CallbackDefaultImpl;
import name.reerink.musicserver.misc.ServiceLocator;
import name.reerink.musicserver.service.MusicService;

import org.apache.log4j.Logger;

public class MusicServer {

	static Logger log = Logger.getLogger(MusicServer.class);
	
	private MusicService musicService;
	private String musicFolderPath;

	/**
	 * Load the MusicServer via the ServiceLocator and then run it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		musicServer.run();
	}

	/**
	 * Do the work and exit.
	 */
	private void run() {
		log.info("Music server");
		Traverser t = new Traverser(new File(getMusicFolderPath()));
		CallbackDefaultImpl callback = new CallbackDefaultImpl();
		t.traverse(callback);
		log.info("Traverse result for '" + getMusicFolderPath()
				+ "': " + callback);
		t.traverse(callback);
		log.debug("Traverse result for '" + getMusicFolderPath()
				+ "': " + callback);
		
	}

	public MusicService getMusicService() {
		return musicService;
	}

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	public String getMusicFolderPath() {
		return musicFolderPath;
	}

	public void setMusicFolderPath(String musicFolderPath) {
		this.musicFolderPath = musicFolderPath;
	}

}
