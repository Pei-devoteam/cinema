package ba.pehli.cinema.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class User {
	private String username;
	private String password;
	private String role;
	private Date birthDate;
	private String country;
	private CreditCard creditCard;
	private Resource image;
	
	@NotEmpty(message="{validation.field.notempty}")
	@Email(message="{validation.field.email}")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotEmpty(message="{validation.field.notempty}")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	//@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd.MM.yyyy")
	@Past(message="{validation.field.past}")
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Resource getImage() {
		return image;
	}

	public void setImage(Resource image) {
		this.image = image;
	}
	
	@NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	public String toString(){
		return "[" + username + "]";
	}
}
