package name.reerink.musicserver.service;

import static org.junit.Assert.*;

import java.util.List;

import name.reerink.musicserver.MusicCallbackImpl;
import name.reerink.musicserver.MusicServer;
import name.reerink.musicserver.db.Artist;
import name.reerink.musicserver.db.Track;
import name.reerink.musicserver.misc.ServiceLocator;

import org.junit.Test;

public class MusicServiceImplTest {

	@Test
	public void testDeleteAll() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		assertNotNull(musicServer);
		musicServer.getMusicService().deleteAll();
		assertEquals(0, musicServer.getMusicService().getArtistCount());
	}

	@Test
	public void testAddArtist() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		assertNotNull(musicServer);
		long count = musicServer.getMusicService().getArtistCount();
		Artist artist = new Artist("Waits, Tom");
		assertEquals(0, musicServer.getMusicService().addArtist(artist));
		assertEquals(count + 1, musicServer.getMusicService().getArtistCount());
	}

	@Test
	public void testAddArtists() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		assertNotNull(musicServer);
		long count = musicServer.getMusicService().getArtistCount();
		Artist[] artists = { new Artist("Sting"), new Artist("Police, The") };
		assertEquals(0, musicServer.getMusicService().addArtists(artists));
		assertEquals(count + 2, musicServer.getMusicService().getArtistCount());
	}

	@Test
	public void testGetArtist() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		assertNotNull(musicServer);
		musicServer.getMusicService().deleteAll();
		assertEquals(0, musicServer.getMusicService().getArtistCount());
		Artist[] artists = { new Artist("Sting"), new Artist("Police, The"),
				new Artist("Pink Floyd") };
		assertEquals(0, musicServer.getMusicService().addArtists(artists));
		assertEquals(3, musicServer.getMusicService().getArtistCount());
		List<Artist> result = musicServer.getMusicService().findArtist("Sting");
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Sting", result.get(0).getName());
	}

	@Test
	public void testAddTrack() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");
		assertNotNull(musicServer);
		musicServer.getMusicService().deleteAll();
		assertEquals(0, musicServer.getMusicService().getArtistCount());
		Track track = new Track();
		track.setName("Pink Floyd");
		musicServer.getMusicService().addTrack(track);
		assertEquals(1, musicServer.getMusicService().getArtistCount());
	}
}
