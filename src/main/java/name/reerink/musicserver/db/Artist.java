package name.reerink.musicserver.db;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "artist")
public class Artist {
	Long id;
	String name;

	private Set<Album> albums = new HashSet<Album>();

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	/*
	 * @OneToMany(cascade=ALL, mappedBy="customer") public Set<Order>
	 * getOrders() { return orders; }
	 * 
	 * In Order class:
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="CUST_ID", nullable=false) public Customer getCustomer()
	 * { return customer; }
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artist")
	public Set<Album> getAlbums() {
		return albums;
	}

	public Artist() {
	}

	public Artist(String name) {
		this.name = new String(name);
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

}
