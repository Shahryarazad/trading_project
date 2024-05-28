package com.example.trading_project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static INFO.accountBank.*;
public abstract class InputChecker {
    public static boolean[] passAcceptable(String password) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(password);
        Pattern pattern1 = Pattern.compile("[a-z]");
        Matcher matcher1 = pattern1.matcher(password);
        return new boolean[]{password.length() >= 8, matcher.find(), matcher1.find()};
    }
    public static boolean[] signupUsernameAcceptable(String username) {
        boolean isUnique = true;
        for (INFO.account account : accounts) {
            {
                if (account.username.equals(username))
                    isUnique = false;
            }
        }
        return new boolean[]{!username.isEmpty() , isUnique};
    }
    public static boolean[] phoneNumberAcceptable(String phoneNumber) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean numbered = matcher.find();
        return new boolean[]{!phoneNumber.isEmpty() , !numbered};
    }
    public static boolean[] emailAcceptable(String email) {
        return new boolean[]{!email.isEmpty() , email.lastIndexOf(".")<email.length()-1 && email.lastIndexOf(".")-email.lastIndexOf("@")>1 && email.lastIndexOf("@")!=0};
    }

}
