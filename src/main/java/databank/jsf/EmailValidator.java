/*****************************************************************
 * File: PersonPojo.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shariar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.jsf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator( "emailValidator")
public class EmailValidator implements Validator< String> {

	// really really (!) simple email pattern: at-least-1-letter, '@', at-least-1-letter
	private static final Pattern EMAIL_PATTERN = Pattern.compile( "^(.+)@(.+)$");
	private Matcher matcher;
	

	@Override
	public void validate( FacesContext context, UIComponent component, String value) throws ValidatorException {

		
		 matcher = EMAIL_PATTERN.matcher(value.toString());
		
		if (!matcher.matches() || value == null) {
			FacesMessage msg = new FacesMessage("Phone validation failed","Invalid email .");
			msg.setSeverity( FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException( msg);
		}

	}

}