package com.example.trading_project;

import INFO.account;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket serverSocket = null;
    Socket socket = null;
    DataInputStream input = null;
    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("server online");
                socket = serverSocket.accept();
                System.out.println("the bluetooth device has been connected successfully");
                input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                String quitMSG = "";
                account account = null;
                while (!quitMSG.equals("quit")) {
                    try {
                        quitMSG = input.readUTF();
                        System.out.println(quitMSG);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            System.out.println("closing...");
            socket.close();
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Server server = new Server(69);
    }
}
