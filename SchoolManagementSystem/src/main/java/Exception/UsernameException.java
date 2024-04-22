package Exception;

import java.util.regex.*;

public class UsernameException extends Exception {
 public UsernameException(String message) {
     super(message);
 }
}

class VerifyUsernameData {
 String USERNAME_PATTERN = "^[A-Za-z0-9_]{3,15}$";

 public void setUsername(String username) throws UsernameException {
     Pattern pattern = Pattern.compile(USERNAME_PATTERN);
     Matcher matcher = pattern.matcher(username);
     if (!matcher.matches()) {
         throw new UsernameException("Invalid username. Enter a valid username...");
     }
 }
}