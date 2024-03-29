package name.reerink.musicserver.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "album", uniqueConstraints = { @UniqueConstraint(columnNames = { "artist_id", "name" }) })
public class Album {
	Long id;
	
	String name;

	private Artist artist = new Artist();

	private Set<Track> tracks = new HashSet<Track>();

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@ManyToOne
	@JoinColumn(name = "artist_id", nullable = false)
	public Artist getArtist() {
		return artist;
	}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "album")
	public Set<Track> getTracks() {
		return tracks;
	}

}
