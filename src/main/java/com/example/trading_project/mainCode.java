package com.example.trading_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainCode extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainCode.class.getResource("signup.fxml"));
        Scene signup = fxmlLoader.load();
        stage.setTitle("Hello!");
        stage.setScene(signup);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}