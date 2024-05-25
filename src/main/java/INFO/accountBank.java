package INFO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.trading_project.HelloController;
import javafx.scene.text.Text;


public class accountBank {
    static account[] accounts = new account[10000];
    static int count =0;
    public static void addAccount(String username , String password, String password1 ,String firstname , String lastname,String email , String phoneNumber , Text text1 ,Text text2,Text text3,Text text4,Text text5 ){
        boolean q1 = false,q2 = false,q3 = false,q4 = false,q5 = false;
        if(!password.equals(password1)){
            text1.setText("Passwords do not match");
        }else if(!passAcceptable(password)[0]){
            text1.setText("Password must be at least 8 characters long");
        }else if(!passAcceptable(password)[1]){
            text1.setText("Password must contain a number");
        }else if(!passAcceptable(password)[2]) {
            text1.setText("Password must contain a character");
        }else q1 = true;
        if(!usernameAcceptable(username)[0]){
            text2.setText("Username must contain a character");
        }else if(!usernameAcceptable(username)[1]){
            text2.setText("Username is taken");
        }else q2 = true;
        if(firstname.isEmpty()){
            text3.setText("Firstname needed");
        }else if(lastname.isEmpty()){
            text3.setText("Lastname needed");
        }else q3 = true;
        if(phoneNumber.isEmpty()){
            text5.setText("Phone number is taken");
        }else q4 = true;
        if(email.isEmpty()){
            text4.setText("Email is taken");
        }else q5 = true;
        if(q1 && q2 && q3 && q4 && q5){
            text1.setText("");
            text2.setText("");
            account account = new account(username, password, firstname, lastname, email, phoneNumber);
            System.out.println("user " + username + " with pass " + password + " is added in slot " + count + " with tier " + account.tier.toString());
            accounts[count++] = account;
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
        for (int i = 0; i < count; i++){
             {
                 if(accounts[i].username.equals(username))
                isUnique = false;
            }
        }
        return new boolean[]{!username.isEmpty() , isUnique};
    }
}
