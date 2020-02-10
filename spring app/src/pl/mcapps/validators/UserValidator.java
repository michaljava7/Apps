package pl.mcapps.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.mcapps.pojo.User;
@Component
public class UserValidator implements Validator{
@Override
public boolean supports(Class<?> clazz) {

	return User.class.isAssignableFrom(clazz);
}

@Override
public void validate(Object target, Errors errors) {

	User user = (User) target;
	
	validate(ValidatorEnum.USERNAME, user.getUserName(), "userName", errors);
	validate(ValidatorEnum.PASSWORD, user.getPassword(), "password", errors);
	validate(ValidatorEnum.EMAIL, user.getEmail(), "email", errors);
	
	String password = user.getPassword();
	String confirmPassword = user.getConfirmPassword();
	if(!password.equals(confirmPassword)) {
		
		errors.rejectValue("password", "err_code", "Passwords must be the same");
	}
}

private void validate(ValidatorEnum validator, String value, String fieldName, Errors errors) {

	String patternReg = validator.getPattern();
	Pattern pattern = Pattern.compile(patternReg);
	Matcher matcher = pattern.matcher(value);
	if (!matcher.matches()) {

		errors.rejectValue(fieldName, validator.getErrCode(), validator.getErrMessage());
	}
}

}
