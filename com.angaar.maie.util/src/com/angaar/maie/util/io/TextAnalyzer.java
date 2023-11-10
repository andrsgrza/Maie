package com.angaar.maie.util.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {
	// Regular expression to validate a Roman numeral
    private static final String ROMAN_NUMERAL_PATTERN = "^\\b[IVXLCDM]+\\b.*$";
    private static final String IS_ANSWER_PATTERN = "^=.*$";
    private static final String IS_QUESTION_PATTERN = "^\\?.*$";


    // Function to validate a Roman numeral using a regular expression
    public static boolean isValidRomanNumeral(String str) {
        Pattern pattern = Pattern.compile(ROMAN_NUMERAL_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    public static boolean isQuestion(String str) {
    	Pattern pattern = Pattern.compile(IS_QUESTION_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    
    public static boolean isAnswer(String str) {
    	Pattern pattern = Pattern.compile(IS_ANSWER_PATTERN);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
