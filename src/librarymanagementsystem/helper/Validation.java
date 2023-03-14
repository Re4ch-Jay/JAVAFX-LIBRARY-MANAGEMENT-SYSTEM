package librarymanagementsystem.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    /**
     * @param password
     * @return If the regex patter is matched
     */
    public boolean isValidPassword(String password)
    {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
