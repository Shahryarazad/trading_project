package com.example.trading_project;

import INFO.account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import static INFO.accountBank.editAccount;

public class ProfileController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    private Text userName;

    @FXML
    private Text repeatPass;

    @FXML
    private TextField email;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField password;

    @FXML
    private TextField password1;

    @FXML
    private Text firstNameError;

    @FXML
    private Text lastNameError;

    @FXML
    private Text phoneError;

    @FXML
    private Text emailError;

    @FXML
    private Text passwordError;

    @FXML
    private ImageView accept;

    @FXML
    private ImageView edit;

    @FXML
    private Text notif;

    String back = "home page";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userName.setText(mainCode.account.username);
        firstName.setText(mainCode.account.firstName);
        lastName.setText(mainCode.account.lastName);
        phoneNumber.setText(mainCode.account.phoneNumber);
        email.setText(mainCode.account.email);
        password.setText(mainCode.account.password);
    }

    @FXML
    void onBackButton(ActionEvent event) {
        if (back.equals("home page")) {
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home_page2.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Scene scene = new Scene(root);
            mainCode.mainStage.setScene(scene);
            mainCode.mainStage.setWidth(950);
            mainCode.mainStage.setHeight(651);
            mainCode.mainStage.show();
        }
        else if (back.equals("edit")) {
            edit.setVisible(true);
            accept.setVisible(false);
            firstName.setEditable(false);
            lastName.setEditable(false);
            phoneNumber.setEditable(false);
            email.setEditable(false);
            firstNameError.setText("");
            lastNameError.setText("");
            phoneError.setText("");
            emailError.setText("");
            passwordError.setText("");

            password1.setVisible(false);
            repeatPass.setVisible(false);

            back = "home page";
        }
    }

    @FXML
    void onEditClick(MouseEvent event) {
        firstName.setEditable(true);
        lastName.setEditable(true);
        phoneNumber.setEditable(true);
        email.setEditable(true);

        edit.setVisible(false);
        password1.setVisible(true);
        password.setEditable(true);
        accept.setVisible(true);
        repeatPass.setVisible(true);

        back = "edit";
    }

    @FXML
    void onAcceptClick(MouseEvent event) {
        edit();
    }

    private void edit() {
        account a = editAccount(mainCode.account.username, password.getText(), password1.getText(), firstName.getText(),
                lastName.getText(), email.getText(), phoneNumber.getText(),
                passwordError, firstNameError, lastNameError, emailError, phoneError);
        if (a != null) {
            mainCode.account = a;
            firstNameError.setText("");
            lastNameError.setText("");
            phoneError.setText("");
            emailError.setText("");
            passwordError.setText("");
            edit.setVisible(true);
            accept.setVisible(false);
            firstName.setEditable(false);
            lastName.setEditable(false);
            phoneNumber.setEditable(false);
            email.setEditable(false);
            password.setEditable(false);
            password1.setVisible(false);
            repeatPass.setVisible(false);
            notif.setText("info changed successfully");
            back = "home page";
        }
        else
            back = "edit";
    }

}

