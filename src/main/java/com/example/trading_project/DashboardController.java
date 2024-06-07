package com.example.trading_project;

import INFO.account;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static INFO.accountBank.accounts;
import static INFO.accountBank.editAccount;

public class DashboardController {

    public AnchorPane anchorPane;
    public Pane profile_Pane;
    public TextField username;
    public ImageView profilePic;
    public TextField lastname;
    public TextField firstname;
    public ImageView profileButton;
    public TextField Email;
    public TextField password;
    public TextField phoneNumber;
    public Text firstname1;
    public Text lastname1;
    public Text Email1;
    public Text password1;
    public Text phoneNumber1;
    public ImageView menuButton;
    public Pane menuPane;
    private account account;
    public int phase = -1;
    public int phase1 = 1;
    TranslateTransition profileSlider = new TranslateTransition(Duration.millis(750));
    TranslateTransition menuSlider = new TranslateTransition(Duration.millis(500));


    public void Profile_Click(MouseEvent event) {
        if((profileSlider.getStatus() == Animation.Status.STOPPED)){
            Profile_Toggle(profileSlider);
        }
        if(phase1 == -1)
            Menu_Toggle(menuSlider);
    }
    public void setUpProfilePage(){
        account = ((account) anchorPane.getUserData());
        profilePic.setImage(account.profilePic);
        username.setText(account.username);
        firstname.setText(account.firstName);
        lastname.setText(account.lastName);
        password.setText(account.password);
        Email.setText(account.email);
        phoneNumber.setText(account.phoneNumber);
        username.setEditable(false);
    }

    public void Edit(ActionEvent event) {
        account newAccount = editAccount(username.getText(),password.getText(),firstname.getText(),lastname.getText(),Email.getText(),phoneNumber.getText(), ((account) anchorPane.getUserData()).profilePic,password1,firstname1,lastname1,Email1,phoneNumber1);
        if(!(newAccount == null))
            accounts.set(accounts.indexOf(((account) anchorPane.getUserData())),newAccount);
        anchorPane.setUserData(newAccount);
    }

    public void Menu_Click(MouseEvent mouseEvent) {
        if((menuSlider.getStatus() == Animation.Status.STOPPED)){
            Menu_Toggle(menuSlider);
        }
    }
    public void Menu_Toggle(TranslateTransition menuSlider) {
            menuSlider.setByX(phase1*150);
            menuSlider.setNode(menuPane);
            menuSlider.setAutoReverse(false);
            menuSlider.setCycleCount(1);
            menuSlider.play();
            phase1 *= -1;
    }
    public void Profile_Toggle(TranslateTransition profileSlider) {
        setUpProfilePage();
        profileSlider.setByX(phase*anchorPane.getPrefWidth());
        profileSlider.setNode(profile_Pane);
        profileSlider.setAutoReverse(false);
        profileSlider.setCycleCount(1);
        profileSlider.play();
        phase *= -1;
    }

}
