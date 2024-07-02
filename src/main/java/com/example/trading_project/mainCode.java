package com.example.trading_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

import INFO.*;

public class mainCode extends Application{

    public static Socket socket;
    public static BufferedReader socketIn;
    public static PrintWriter socketOut;
    public static ObjectInputStream objIn;
    public static ObjectOutputStream objOut;

    //fields
    public static account account;
    public static Stage mainStage;


    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
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

    public static void main(String[] args) throws IOException {
        socket = new Socket("localhost", 5051);
        socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        socketOut = new PrintWriter(socket.getOutputStream(), true);
        objOut = new ObjectOutputStream(socket.getOutputStream());
        objOut.flush();
        objIn = new ObjectInputStream(socket.getInputStream());

        launch();
    }

}