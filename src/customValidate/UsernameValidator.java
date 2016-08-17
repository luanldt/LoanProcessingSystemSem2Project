package customValidate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class UsernameValidator implements ConstraintValidator<UsernameCustom, String> {

	private String USERNAME_PATTERN = "^\\d+";

	@Override
	public void initialize(UsernameCustom arg0) {
		
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext arg1) {
		Matcher matcher;
		Pattern pattern = Pattern.compile(USERNAME_PATTERN);
		matcher = pattern.matcher(username);
		return !matcher.matches();
	}

}