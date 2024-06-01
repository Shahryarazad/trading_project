package com.example.trading_project;

import INFO.account;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

import static INFO.accountBank.accounts;
import static INFO.accountBank.logIn;

public class LoginController {

    public TextField password;
    public TextField username;
    public Text PasswordError;
    public Text UsernameError;
    public AnchorPane anchorPane;
    public TextField code;
    public static String tempEmail;
    public TextField Email;

    public void LogIn_Click(ActionEvent event) throws IOException {
        for (account account : accounts) {
            if (account.username.equals(username.getText()) && account.password.equals(password.getText()))
                logIn(account,anchorPane);
        }
    }

    public void SignUpClick(MouseEvent mouseEvent) throws IOException {
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("signup.fxml")));
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll(newAnchorPane);
    }

    public void ForgotPassword(MouseEvent mouseEvent) throws IOException {
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("forgot password.fxml")));
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll(newAnchorPane);
    }

    public void Send_Click(ActionEvent event) throws IOException {
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("forgot password 2.fxml")));
        tempEmail = Email.getText();
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll(newAnchorPane);
    }

    public void Confirm_Click(ActionEvent event) throws IOException {
        //TODO: add code if statement
        System.out.println(tempEmail);
        for (account account: accounts) {
            if(account.email.equals(tempEmail))
                logIn(account,anchorPane);
        }
    }
}
