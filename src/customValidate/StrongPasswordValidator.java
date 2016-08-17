package customValidate;

/**
 * @author Tri Phan
 *
 */

import java.util.regex.*;
import javax.validation.*;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

	private String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*]).{6,20})";

	@Override
	public void initialize(StrongPassword arg0) {
				
	}
	
	@Override
	public boolean isValid(String password, ConstraintValidatorContext cxt) {
		Matcher matcher;
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

	

}
