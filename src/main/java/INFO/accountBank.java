package INFO;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.text.Text;


public class accountBank {
    static ArrayList<account> accounts = new ArrayList<account>();
    public static void addAccount(String username , String password, String password1 ,String firstname , String lastname,String email , String phoneNumber , Text text1 , Text text2, Text text3, Text text4, Text text5 ){
        boolean q1 = false,q2 = false,q3 = false,q4 = false,q5 = false;
        if(!password.equals(password1)){
            text1.setText("Passwords do not match");
        }else if(!passAcceptable(password)[0]){
            text1.setText("Password must be at least 8 characters long");
        }else if(!passAcceptable(password)[1]){
            text1.setText("Password must contain a number");
        }else if(!passAcceptable(password)[2]) {
            text1.setText("Password must contain a character");
        }else {
            text1.setText("");
            q1 = true;
        }
        if(!usernameAcceptable(username)[0]){
            text2.setText("Username must contain a character");
        }else if(!usernameAcceptable(username)[1]){
            text2.setText("Username is taken");
        }else {
            text2.setText("");
            q2 = true;
        }
        if(firstname.isEmpty()){
            text3.setText("Firstname needed");
        }else if(lastname.isEmpty()){
            text3.setText("Lastname needed");
        }else {
            text3.setText("");
            q3 = true;
        }
        if(!phoneNumberAcceptable(phoneNumber)[0]){
            text5.setText("Phone number needed");
        }else if(!phoneNumberAcceptable(phoneNumber)[1]){
            text5.setText("Phone can only include numbers");
        }else{
            text5.setText("");
            q4 = true;
        }
            if(!emailAcceptable(email)[0]){
            text4.setText("Email needed");
        }else if(!emailAcceptable(email)[1]){
            text4.setText("Email does not look right");
        }else{
                text4.setText("");
                q5 = true;
            }
        if(q1 && q2 && q3 && q4 && q5){
            account account = new account(username, password, firstname, lastname, email, phoneNumber);
            System.out.println("user " + username + " with pass " + password + " is added in slot " + accounts.size() + " with tier " + account.tier.toString());
            accounts.add(account);
        }
    }
    public static boolean[] passAcceptable(String password) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(password);
        Pattern pattern1 = Pattern.compile("[a-z]");
        Matcher matcher1 = pattern1.matcher(password);
            return new boolean[]{password.length() >= 8, matcher.find(), matcher1.find()};
    }
    public static boolean[] usernameAcceptable(String username) {
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
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean numbered = matcher.find();
        return new boolean[]{!phoneNumber.isEmpty() , numbered};
    }
    public static boolean[] emailAcceptable(String email) {
        return new boolean[]{!email.isEmpty() , email.lastIndexOf(".")<email.length()-1 && email.lastIndexOf(".")-email.lastIndexOf("@")>1 && email.lastIndexOf("@")!=0};
    }

}
