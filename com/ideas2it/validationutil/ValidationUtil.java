package com.ideas2it.validationutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static String namePattern = ("^[a-zA-Z]{2,20}$");
    public static String phoneNumberPattern = ("^[0|91]*[7-9]{1}[0-9]{9}$");
    public static String agePattern = ("^[0-9]{2}$");
    public static String emailIdPattern = ("^[a-zA-Z]{1,10}")
                                          + ("?[a-zA-Z0-9]{1,10}[@]")
                                          + ("[a-z]{1,10}[a-z0-9]{0,10}")
                                          + ("[.][a-z]{2,3}[.]")
                                          + ("?[a-z]{1,2}");
   
    public static boolean isValidInput(String pattern, String inputValues) {
        return Pattern.matches(pattern, inputValues);
    }
}

   