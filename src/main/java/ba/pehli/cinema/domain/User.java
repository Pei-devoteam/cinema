package ba.pehli.cinema.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name="User.findByVerificationCode",query="select u from User u where u.verificationCode = :verificationCode"),
	@NamedQuery(name="User.findAllNonVerified", query="select u from User u where u.enabled=false")
})
public class User {
	private int id;
	private String username;
	private String password;
	private String role;
	private Date birthDate;
	private String country;
	private boolean enabled;
	private String verificationCode;
	private CreditCard creditCard;
	private Date createdDate = new Date();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty(message="{validation.field.notempty}")
	@Email(message="{validation.field.email}")
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotEmpty(message="{validation.field.notempty}")
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="role")
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	//@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd.MM.yyyy")
	@Past(message="{validation.field.past}")
	@Column(name="birth_date")
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Column(name="country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="verification_code")
	public String getVerificationCode() {
		return verificationCode;
	}
	
	@Column(name="enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	@DateTimeFormat(pattern="dd.MM.yyyy")
	@Column(name="created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@NotNull
	@Valid
	@OneToOne(optional=false,cascade= {CascadeType.ALL})
	@JoinColumn(name="credit_card_id",unique=true,nullable=false)
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	public String toString(){
		return "[id:" + id + ",username:" + username + ",password:" + password +
				",enabled:" + enabled + ",verificationCode=" + verificationCode + ",creditCard:" + creditCard + "]";
	}
}
