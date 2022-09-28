package com.ideas2it.validationutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static String namePattern = "^[a-zA-Z]{2,}([ ]?[a-zA-Z]{0,15}){0,}$";
    public static String phoneNumberPattern = "^[0|91]*[7-9]{1}[0-9]{9}$";
    public static String agePattern = "^[0-9]{2}$";
    public static String experiencePattern ="^[3-9][.][0-9]$";
    public static String emailIdPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
   
    public static boolean isValidInput(String pattern, String inputValues) {
        return Pattern.matches(pattern, inputValues);
    }
}

   