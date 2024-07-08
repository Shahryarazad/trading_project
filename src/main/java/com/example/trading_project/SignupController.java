package com.example.trading_project;

import INFO.account;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        account userAccount = addAccount(username.getText(),password.getText(),password1.getText(),firstname.getText(),lastname.getText(),email.getText(),phonenumber.getText(),errorField2 , errorField1, firstlastnameerror,emailerror,phoneerror);
        if (userAccount != null) {
            mainCode.socketOut.println("sign up");
            mainCode.objOut.writeObject(userAccount);
            mainCode.objOut.flush();
            String s = mainCode.socketIn.readLine();
            if (s.equals("true")) {
                mainCode.account = userAccount;
//                logIn(userAccount, anchorPane);
                go_to_homePage(userAccount, anchorPane);
                System.out.println("signed up successfully");
            }
            else if (s.equals("false")) {
                emailerror.setText("this account does exit");
            }
        }

    }

    protected void go_to_homePage(account account, AnchorPane anchorPane) throws IOException {
        mainCode.startMainThread();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("home_page2.fxml")));
        Scene scene = new Scene(root);
        mainCode.mainStage.setScene(scene);
        mainCode.mainStage.setWidth(950);
        mainCode.mainStage.setHeight(651);
        mainCode.mainStage.show();
    }

    @FXML
    protected void Login_Page_Click() throws IOException {
        AnchorPane newAnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("login.fxml")));
        anchorPane.getChildren().removeAll();
        anchorPane.getChildren().setAll(newAnchorPane);
    }


//    public void file(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Image files", "*.jpg")
//        );
//        File file = fileChooser.showOpenDialog(((Stage) anchorPane.getUserData()));
//        image = new Image(file.toURI().toString());
//        preview.setImage(image);
//    }

    public void Debug(ActionEvent event) throws IOException {
        username.setText("a");
        firstname.setText("a");

        lastname.setText("a");

        password.setText("1234567a");

        password1.setText("1234567a");
        email.setText("s@g.h");
        phonenumber.setText("1");
    }
}