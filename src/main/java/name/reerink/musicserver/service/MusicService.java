package name.reerink.musicserver.service;

import name.reerink.musicserver.db.Artist;

public interface MusicService {

	int addArtist(Artist artist);
	
	int addArtists(Artist[] artists);
	
	long getArtistCount();
}
