package name.reerink.musicserver;

import java.io.File;

import name.reerink.musicserver.db.Track;
import name.reerink.musicserver.files.Traverser;
import name.reerink.musicserver.misc.ServiceLocator;
import name.reerink.musicserver.service.MusicService;

import org.apache.log4j.Logger;
import org.junit.Test;

public class MusicCallbackImplTest {

	static Logger log = Logger.getLogger(MusicCallbackImplTest.class);

	@Test
	public void testTypes() {
		for (Track.Type type : Track.Type.values()) {
			log.info(type.name() + "=" + type.ordinal());
		}
	}

	@Test
	public void testZ() {
		Traverser t = new Traverser();
		t.setDirectory(new File("/mnt/data/Music/Z"));
		MusicCallbackImpl callback = new MusicCallbackImpl();
		MusicService musicService = (MusicService) ServiceLocator.getService(
				"MusicServer", "musicService");
		callback.setMusicService(musicService);
		t.setCallback(callback);
		musicService.deleteAll();
		t.traverse();
	}
}
