package ba.pehli.cinema.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class CreditCard {
	private String issuer;
	private String number;
	
	@NotEmpty(message="{validation.field.notempty}")
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	@NotEmpty(message="{validation.field.notempty}")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String toString() {
		return "[" + getIssuer() + " " + getNumber() + "]";
	}
}
