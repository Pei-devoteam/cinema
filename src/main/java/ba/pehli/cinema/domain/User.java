package ba.pehli.cinema.domain;

import java.util.Date;

import org.springframework.core.io.Resource;

public class User {
	private String username;
	private String password;
	private String role;
	private Date birthDate;
	private String country;
	private CreditCard creditCard;
	private Resource image;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
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
