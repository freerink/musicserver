package name.reerink.musicserver.service;

import static org.junit.Assert.*;

import name.reerink.musicserver.MusicServer;
import name.reerink.musicserver.db.Artist;
import name.reerink.musicserver.misc.ServiceLocator;

import org.junit.Test;

public class MusicServiceImplTest {

	@Test
	public void testGetArtistCount() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		assertNotNull(musicServer);
		assertEquals(0, musicServer.getMusicService().getArtistCount());
	}

	@Test
	public void testAddArtist() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		assertNotNull(musicServer);
		Artist artist = new Artist("Waits, Tom");
		assertEquals(0, musicServer.getMusicService().addArtist(artist));
	}

	@Test
	public void testAddArtists() {
		MusicServer musicServer = (MusicServer) ServiceLocator.getService(
				"MusicServer", "musicServer");

		assertNotNull(musicServer);
		Artist[] artists = { new Artist("String"), new Artist("Police, The") };
		assertEquals(0, musicServer.getMusicService().addArtists(artists));
	}

}
