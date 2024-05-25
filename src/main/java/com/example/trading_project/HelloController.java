package com.example.trading_project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import INFO.*;

import static com.example.trading_project.HelloApplication.*;

public class HelloController {
    public TextField firstname;
    public TextField lastname;
    public TextField password;
    public TextField password1;
    public TextField username;
    public Text errorField1;
    public Text errorField2;
    public TextField email;
    public TextField phonenumber;
    public Text firstlastnameerror;
    public Text phoneerror;
    public Text emailerror;


    @FXML
    protected void SignUp_Click() {
        accountBank.addAccount(username.getText(),password.getText(),password1.getText(),firstname.getText(),lastname.getText(),email.getText(),phonenumber.getText(),errorField2 , errorField1, firstlastnameerror,phoneerror,emailerror);
    }
    @FXML
    protected void Login_Page_Click() {
        System.out.println(1);
    }
}