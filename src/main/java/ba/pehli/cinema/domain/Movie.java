package ba.pehli.cinema.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.core.io.Resource;

@Entity
@Table(name="movie")
@NamedQueries({
	@NamedQuery(name="Movie.findById", query="select m from Movie m where m.id = :id"),
	@NamedQuery(name="Movie.findAllWithCast",query="select distinct m from Movie m left join fetch m.cast c")
})
public class Movie {
	private int id;
	private String name;
	private Date releaseDate;
	private String description;
	private byte[] image;
	private String trailerUrl;
	private int version;
	
	private List<Actor> cast = new ArrayList<Actor>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="release_date")
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@Column(name="image")
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	@Version
	@Column(name="version")
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	@ManyToMany
	@JoinTable(name="movie_actor", joinColumns=@JoinColumn(name="movie_id"), inverseJoinColumns=@JoinColumn(name="actor_id"))
	public List<Actor> getCast() {
		return cast;
	}
	public void setCast(List<Actor> cast) {
		this.cast = cast;
	}
	
	@Column(name="trailer_url")
	public String getTrailerUrl() {
		return trailerUrl;
	}
	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
	}
	public String toString() {
		return "[" + id + " " + name + "]"; 
	}
	
}
