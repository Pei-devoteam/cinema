package ba.pehli.cinema.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar{
	private String datePattern = "dd.MM.yyyy";

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
			/*System.out.println("*** REGISTRACIJA");
			SimpleDateFormat format = new SimpleDateFormat(datePattern);
			registry.registerCustomEditor(Date.class, new CustomDateEditor(format, true));*/
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	
	
	
}
