package name.reerink.musicserver;

import name.reerink.musicserver.files.Traverser;
import name.reerink.musicserver.misc.ServiceLocator;
import name.reerink.musicserver.service.MusicService;

import org.apache.log4j.Logger;

public class MusicServer {

	static Logger log = Logger.getLogger(MusicServer.class);

	private Traverser traverser;

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
		getMusicService().deleteAll();
		log.info(getMusicService().toString());
		getTraverser().traverse();
		log.info("Traverse result for '" + getTraverser().getDirectory()
				+ "': " + getTraverser().getCallback());
		log.info(getMusicService().toString());
	}

	public MusicService getMusicService() {
		MusicCallbackImpl s = (MusicCallbackImpl) getTraverser().getCallback();
		return s.getMusicService();
	}

	public void setTraverser(Traverser traverser) {
		this.traverser = traverser;
	}

	public Traverser getTraverser() {
		return traverser;
	}

}
