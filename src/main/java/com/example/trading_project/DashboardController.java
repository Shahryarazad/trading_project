package com.example.trading_project;

import INFO.account;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DashboardController {

    public AnchorPane anchorPane;
    public Pane profile_Anchor;
    public Text username;
    public ImageView profilePic;
    public Text lastname;
    public Text firstname;
    public ImageView profileButton;
    private account account;
    public int phase = -1;
    TranslateTransition profileSlider= new TranslateTransition(Duration.millis(750));
    public void Profile_Click(MouseEvent event) {
        if((profileSlider.getStatus() == Animation.Status.STOPPED)){
            setUpProfilePage();
            profileSlider.setByX(phase*anchorPane.getPrefWidth());
            profileSlider.setNode(profile_Anchor);
            profileSlider.setAutoReverse(false);
            profileSlider.setCycleCount(1);
            profileSlider.play();
            phase *= -1;
        }
    }
    public void setUpProfilePage(){
        account = ((account) anchorPane.getUserData());
        profilePic.setImage(account.profilePic);
        username.setText(account.username);
        firstname.setText(account.firstName);
        lastname.setText(account.lastName);
    }
}
