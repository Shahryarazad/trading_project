package INFO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class accountBank {
    static account[] accounts = new account[10000];
    static int count =0;
    public static void addAccount(String username , String password){
        if(passAcceptable(password)){account account = new account(username , password);
            System.out.println("user " + username + " with pass " + password + " is added in slot " + count + " with tier " + account.tier.toString());
            accounts[count++] = account;
        }

    }
    public static boolean passAcceptable(String password) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(password);
        Pattern pattern1 = Pattern.compile("[a-z]");
        Matcher matcher1 = pattern1.matcher(password);
        if(password.length() >= 8 && matcher.find()&&matcher1.find())
            return true;
            else return false;
    }

}
