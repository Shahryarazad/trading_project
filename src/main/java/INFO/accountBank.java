package INFO;

import com.example.trading_project.InputChecker;
import com.example.trading_project.mainCode;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class accountBank extends InputChecker {
//    public static ArrayList<account> accounts = new ArrayList<account>();
    public static account addAccount(String username , String password, String password1 , String firstname , String lastname, String email , String phoneNumber, Text text1 , Text text2, Text text3, Text text4, Text text5 ){
        boolean q1 = false,q2 = false,q3 = false,q4 = false,q5 = false;
        boolean[] pass = passAcceptable(password), number = phoneNumberAcceptable(phoneNumber),emailE = emailAcceptable(email);
        boolean name = signupUsernameAcceptable(username);
        if(!password.equals(password1)){
            text1.setText("Passwords do not match");
        }else if(!pass[0]){
            text1.setText("Password must be at least 8 characters long");
        }else if(!pass[1]){
            text1.setText("Password must contain a number");
        }else if(!pass[2]) {
            text1.setText("Password must contain a character");
        }else {
            text1.setText("");
            q1 = true;
        }
        if(!name){
            text2.setText("Username must contain a character");
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
        if(!number[0]){
            text5.setText("Phone number needed");
        }else if(!number[1]){
            text5.setText("Phone can only include numbers");
        }else{
            text5.setText("");
            q4 = true;
        }
            if(!emailE[0]){
            text4.setText("Email needed");
        }else if(!emailE[1]){
            text4.setText("Email does not look right");
        }else{
                text4.setText("");
                q5 = true;
            }
        if(q1 && q2 && q3 && q4 && q5){
            account account = new account(username, password, firstname, lastname, email, phoneNumber);
            return account;
        }
        return null;
    }
    public static account editAccount(account a, String username , String password, String password1 , String firstname , String lastname, String email , String phoneNumber, Text passErrorField , Text firstnameErrorField, Text lastnameErrorField, Text emailErrorField, Text phoneErrorField ){
        boolean q2 = false,q3 = false,q4 = false,q5 = false;
        boolean[] pass = passAcceptable(password) , number = phoneNumberAcceptable(phoneNumber),emailE = emailAcceptable(email);
        if(!password.equals(password1)){
            passErrorField.setText("Passwords do not match");
        } else if(!pass[0]){
            passErrorField.setText("Password must be at least 8 characters long");
        }else if(!pass[1]){
            passErrorField.setText("Password must contain a number");
        }else if(!pass[2]) {
            passErrorField.setText("Password must contain a character");
        }else {
            passErrorField.setText("");
            q2 = true;
        }
        if(firstname.isEmpty()){
            firstnameErrorField.setText("Firstname needed");
        }else if(lastname.isEmpty()){
            lastnameErrorField.setText("Lastname needed");
        }else {
            firstnameErrorField.setText("");
            lastnameErrorField.setText("");
            q3 = true;
        }
        if(!number[0]){
            phoneErrorField.setText("Phone number needed");
        }else if(!number[1]){
            phoneErrorField.setText("Phone can only include numbers");
        }else{
            phoneErrorField.setText("");
            q4 = true;
        }
        if(!emailE[0]){
            emailErrorField.setText("Email needed");
        }else if(!emailE[1]){
            emailErrorField.setText("Email does not look right");
        }else{
            emailErrorField.setText("");
            q5 = true;
        }
        if(q2 && q3 && q4 && q5){
//            account account = new account(username, password, firstname, lastname, email, phoneNumber);
            a.password = password;
            a.firstName = firstname;
            a.lastName = lastname;
            a.email = email;
            a.phoneNumber = phoneNumber;
            return a;
        }
        return null;
    }

    public static void logIn(account account,AnchorPane anchorPane) throws IOException {
        if(account == null)
            return;
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("dashboard.fxml")));
        newAnchorPane.setUserData(account);
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll(newAnchorPane);
    }
}
