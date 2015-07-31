package ba.pehli.cinema.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Movie domain object.
 * 
 * @author almir
 */

@Entity
@Table(name="movie")
@NamedQueries({
	@NamedQuery(name="Movie.findById", query="select m from Movie m where m.id = :id"),
	@NamedQuery(name="Movie.findAllWithCast",query="select distinct m from Movie m left join fetch m.cast c order by m.id"),
	@NamedQuery(name="Movie.findAllWithCastAndRating",query="select distinct m from Movie m left join fetch m.cast c left join fetch m.ratings r"),
	@NamedQuery(name="Movie.findCount", query="select count(*) from Movie"),
})
public class Movie {
	private int id;
	private String name;
	private Date releaseDate;
	private String description;
	private byte[] image;
	private String trailerUrl;
	private int version;
	
	private Set<Actor> cast = new HashSet<Actor>();
	private Set<Rating> ratings = new HashSet<Rating>();
	
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
	@NotEmpty(message="{validation.field.notempty}")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="{validation.field.notempty}")
	@DateTimeFormat(pattern="dd.MM.yyyy")
	@Column(name="release_date")
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	@Column(name="description")
	@NotEmpty(message="{validation.field.notempty}")
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
	
	@Column(name="trailer_url")
	@NotEmpty(message="{validation.field.notempty}")
	public String getTrailerUrl() {
		return trailerUrl;
	}
	public void setTrailerUrl(String trailerUrl) {
		this.trailerUrl = trailerUrl;
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
	public Set<Actor> getCast() {
		return cast;
	}
	public void setCast(Set<Actor> cast) {
		this.cast = cast;
	}
	
	@OneToMany(mappedBy="movie")
	public Set<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}
	public String toString() {
		return "[" + id + " " + name + "]"; 
	}
	
}
