package com.example.trading_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class mainCode extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane BaseAnchor = new AnchorPane();
        Scene base = new Scene(BaseAnchor);
        stage.setHeight(820);
        stage.setWidth(480);
        stage.setResizable(false);
        stage.setTitle("signup");
        stage.setScene(base);
        AnchorPane AnchorPane = FXMLLoader.load(Objects.requireNonNull(mainCode.class.getResource("signup.fxml")));
        BaseAnchor.getChildren().setAll(AnchorPane);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}