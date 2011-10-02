package name.reerink.musicserver.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "track", uniqueConstraints = { @UniqueConstraint(columnNames = { "album_id", "number" }) })
public class Track {
	Long id;
	int number;
	String name;
	String path;
	// Music file type, i.e. ogg, flac, mp3
	public enum Type {
		UNKNOWN, OGG, FLAC, MP3, WMA
	}
	
	private Type type;

	private Album album = new Album();

	public Track() {
	}

/*	public Track(int number, String name, String path) {
		this.number = number;
		this.name = new String(name);
		this.path = new String(path);
		this.type = new String(type);
	}
*/
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@ManyToOne
	@JoinColumn(name = "album_id", nullable = false)
	public Album getAlbum() {
		return album;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Type getType() {
		return type;
	}

}
