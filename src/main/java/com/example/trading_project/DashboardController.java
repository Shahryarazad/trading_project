package com.example.trading_project;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class DashboardController {

    public AnchorPane anchorPane;
    public ToolBar profile_Anchor;

    public void Profile_Click(ActionEvent event) {
        TranslateTransition profileSlider = new TranslateTransition(Duration.millis(1500));
        profileSlider.setByX(-anchorPane.getPrefWidth());
        profileSlider.setNode(profile_Anchor);
        profileSlider.setAutoReverse(false);
        profileSlider.setCycleCount(1);
        profileSlider.play();
    }
}
