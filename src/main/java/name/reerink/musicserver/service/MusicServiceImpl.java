package name.reerink.musicserver.service;

import java.util.List;

import name.reerink.musicserver.db.Artist;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class MusicServiceImpl implements MusicService {

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
		List<Long> count = session.createQuery("select count(id) from Artist").list();
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

}
