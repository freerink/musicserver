package name.reerink.musicserver.service;

import java.util.List;

import name.reerink.musicserver.db.Artist;
import name.reerink.musicserver.db.Track;

public interface MusicService {

	int addArtist(Artist artist);

	int addArtists(Artist[] artists);

	long getArtistCount();

	/**
	 * Delete all Artists, Albums and Tracks.
	 */
	void deleteAll();

	List<Artist> findArtist(String name);

	void addTrack(String artist, String album, String title, int trackNumber, Track.Type type);

}
