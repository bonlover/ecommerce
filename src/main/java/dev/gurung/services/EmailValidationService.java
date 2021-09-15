package dev.gurung.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidationService {
    public static  boolean IsValidEmail(String email){
        String regex = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            return true;
        }
        return false;
    }

}
