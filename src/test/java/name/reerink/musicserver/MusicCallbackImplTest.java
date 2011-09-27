package name.reerink.musicserver;

import java.io.File;

import name.reerink.musicserver.files.Traverser;
import name.reerink.musicserver.misc.ServiceLocator;
import name.reerink.musicserver.service.MusicService;

import org.junit.Test;

public class MusicCallbackImplTest {

	@Test
	public void test() {
		Traverser t = new Traverser();
		t.setDirectory(new File("/mnt/data/Music/Z"));
		MusicCallbackImpl callback = new MusicCallbackImpl();
		MusicService musicService = (MusicService) ServiceLocator.getService(
				"MusicServer", "musicService");
		callback.setMusicService(musicService);
		t.setCallback(callback);
		t.traverse();
	}
}
