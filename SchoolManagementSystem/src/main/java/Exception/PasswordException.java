package Exception;

import java.util.regex.*;

public class PasswordException extends Exception {
	public PasswordException(String message) {
		super(message);
	}
}

class VerifyPasswordData {
	String PASSWORD_PATTERN = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,20}$";
	public void setPassword(String password) throws PasswordException {
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			throw new PasswordException("Invalid password. Enter a correct password...");
		}
	}
}