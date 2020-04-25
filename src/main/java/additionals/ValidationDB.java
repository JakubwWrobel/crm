package additionals;

import javafx.css.Match;
import model.Status;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationDB {
    private static String value;
    private static int valueInt;
    private static final String reg = "\'[a-zA-Z]+\'$";
    private static final String regMail = "^[0-9]{9}$";
    private static final HashSet<String> STATUSES = new HashSet<>();

    public static String validation(String val) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(val);

        if (matcher.find()) {
            value = matcher.group();
            value = value.substring(1, value.length() - 1);
            return value;
        }
        return null;
    }

    public static int validationPhone(String val) throws NumberFormatException {
        Pattern pattern = Pattern.compile(regMail);
        Matcher matcher = pattern.matcher(val);

        if (matcher.matches()) {
            valueInt = Integer.parseInt(val);
            return valueInt;
        }
        throw new NumberFormatException();
    }

    public static int validationStatus(String status){
        for(String s : STATUSES){
            if(s.equals(status)){
                return 1;
            }
        }
        return -1;
    }

}
