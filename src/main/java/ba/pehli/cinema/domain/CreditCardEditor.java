package ba.pehli.cinema.domain;

import java.beans.PropertyEditorSupport;

/*
 * Kreditne kartice možemo unositi na slijedeći način: VISA,87368746494
 */
public class CreditCardEditor extends PropertyEditorSupport{

	@Override
	public String getAsText() {
		CreditCard card = (CreditCard)getValue();
		return card.getIssuer() + "," + card.getNumber();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] parts = text.split(",");
		CreditCard card = new CreditCard();
		card.setIssuer(parts[0]);
		card.setNumber(parts[1]);
		setValue(card);
	}
	
}
