package com.example.trading_project;

import INFO.account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static INFO.accountBank.addAccount;
import static INFO.accountBank.logIn;

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
    public ImageView preview;
    public Image image;

    @FXML
    protected void SignUp_Click() throws IOException {
        logIn(addAccount(username.getText(),password.getText(),password1.getText(),firstname.getText(),lastname.getText(),email.getText(),phonenumber.getText(),image,errorField2 , errorField1, firstlastnameerror,emailerror,phoneerror),anchorPane);
    }
    @FXML
    protected void Login_Page_Click() throws IOException {
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("login.fxml")));
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll(newAnchorPane);
    }


    public void file(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image files", "*.jpg")
        );
        File file = fileChooser.showOpenDialog(((Stage) anchorPane.getUserData()));
        image = new Image(file.toURI().toString());
        preview.setImage(image);
    }

    public void Debug(ActionEvent event) throws IOException {
        logIn(new account("a","1234567a","a","a","a","a",image),anchorPane);
    }
}