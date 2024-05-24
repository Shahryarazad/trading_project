package com.example.trading_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene signup = fxmlLoader.load();
        stage.setTitle("Hello!");
        stage.setScene(signup);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}