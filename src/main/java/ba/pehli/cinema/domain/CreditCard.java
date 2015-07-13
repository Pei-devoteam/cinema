package ba.pehli.cinema.domain;

public class CreditCard {
	private String issuer;
	private String number;
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
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
