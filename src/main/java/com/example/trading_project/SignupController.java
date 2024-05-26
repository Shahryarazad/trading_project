package com.example.trading_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import INFO.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupController {
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
    public AnchorPane anchorPane;

    @FXML
    protected void SignUp_Click() {
        accountBank.addAccount(username.getText(),password.getText(),password1.getText(),firstname.getText(),lastname.getText(),email.getText(),phonenumber.getText(),errorField2 , errorField1, firstlastnameerror,emailerror,phoneerror);
    }
    @FXML
    protected void Login_Page_Click() throws IOException {
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("login.fxml")));
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll();
    }





}