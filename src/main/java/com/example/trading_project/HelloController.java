package com.example.trading_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import INFO.*;

import static com.example.trading_project.HelloApplication.*;

public class HelloController {

    public Text passwordField;
    public Text usernameField;
    public TextField username;
    public TextField password;

    @FXML
    protected void SignUp_Click() {
        accountBank.addAccount(username.getText(),password.getText());
    }
    @FXML
    protected void Username_TextBox_Selected() {
        passwordField.setVisible(password.getCharacters().isEmpty()&&!password.isFocused());
        usernameField.setVisible(username.getCharacters().isEmpty()&&!username.isFocused());
    }
    @FXML
    protected void Password_TextBox_Selected() {
        passwordField.setVisible(password.getCharacters().isEmpty()&&!password.isFocused());
        usernameField.setVisible(username.getCharacters().isEmpty()&&!username.isFocused());
    }
}