package ir.tamin.incomeBank.util;

import javax.enterprise.context.ApplicationScoped;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author A_Jamali
 */


@ApplicationScoped
public class FarsiExtractor {


    public String extractFarsiMessage(String input) {
        String pattern = "ORA-20105: ([\u0600-\u06FF\\s]+)";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }
}
