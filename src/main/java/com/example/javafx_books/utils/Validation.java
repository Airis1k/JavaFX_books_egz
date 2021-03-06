package com.example.javafx_books.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    // [] - kas yra leidziama; {} - ilgis nuo iki
    public static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]{5,13}$";
    public static final String PASSWORD_REGEX_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{5,16}$";
    public static final String EMAIL_REGEX_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isValidUsername(String username) {
        // Sukuriamos validacijos taisykles pagal auksciau aprasyta sablona
        Pattern pattern = Pattern.compile(USERNAME_REGEX_PATTERN);
        // Validacijos atitikmens sukurimas palyginant vartotojo ivestus duomenis su validacijos taisyklemis
        Matcher matcher = pattern.matcher(username);

        // Grazins true, jei vartotojo ivesti duomenys atitiks validacijos taisykles, priesingai - false
        return matcher.find();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.find();
    }
}
