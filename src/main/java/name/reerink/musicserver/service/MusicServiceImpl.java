package name.reerink.musicserver.service;

import java.util.List;

import name.reerink.musicserver.db.Album;
import name.reerink.musicserver.db.Artist;
import name.reerink.musicserver.db.Track;
import name.reerink.musicserver.db.Track.Type;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class MusicServiceImpl implements MusicService {

	static Logger log = Logger.getLogger(MusicServiceImpl.class);

	public SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get our injected sessionFactory.
	 * 
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		if (this.sessionFactory == null) {
			throw new IllegalStateException(
					"No sessionFactory found, please inject it.");
		}
		return this.sessionFactory;
	}

	public long getArtistCount() {
		Session session = getSessionFactory().getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Long> count = session.createQuery("select count(id) from Artist")
				.list();
		return count.get(0);
	}

	public int addArtist(Artist artist) {
		Artist[] artists = { artist };
		return addArtists(artists);
	}

	public int addArtists(Artist[] artists) {
		Session session = getSessionFactory().getCurrentSession();
		for (Artist a : artists) {
			session.save(a);
		}
		return 0;
	}

	/**
	 * Delete all artists, albums and tracks.
	 */
	public void deleteAll() {
		Session session = getSessionFactory().getCurrentSession();
		int deleted = session.createQuery("delete from Track").executeUpdate();
		log.info("Deleted (" + deleted + ") tracks");
		deleted = session.createQuery("delete from Album").executeUpdate();
		log.info("Deleted (" + deleted + ") albums");
		deleted = session.createQuery("delete from Artist").executeUpdate();
		log.info("Deleted (" + deleted + ") artists");
	}

	@SuppressWarnings("unchecked")
	public List<Artist> findArtist(String name) {
		Session session = getSessionFactory().getCurrentSession();
		return (List<Artist>) session.createQuery("from Artist where name = ?")
				.setString(0, name).list();
	}

	public void addTrack(String artistName, String albumName,
			String trackTitle, int trackNumber, Type type) {
		Session session = getSessionFactory().getCurrentSession();
		Artist artist;
		@SuppressWarnings("unchecked")
		List<Artist> artists = session
				.createQuery("from Artist where name = ?")
				.setString(0, artistName).list();
		if (artists.size() > 0) {
			artist = artists.get(0);
		} else {
			artist = new Artist();
			artist.setName(artistName);
			session.save(artist);
		}
		Album album;
		@SuppressWarnings("unchecked")
		List<Album> albums = session.createQuery("from Album where name = ?")
				.setString(0, albumName).list();
		if (albums.size() > 0) {
			album = albums.get(0);
		} else {
			album = new Album();
			album.setArtist(artist);
			album.setName(albumName);
			session.save(album);
		}
		Track track = new Track();
		track.setName(trackTitle);
		track.setNumber(trackNumber);
		track.setAlbum(album);
		track.setType(type);
		album.getTracks().add(track);
		session.save(artist);
	}

}
