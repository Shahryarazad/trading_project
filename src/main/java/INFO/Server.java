package INFO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static protected ArrayList<Thread> clients = new ArrayList<>();
    static CurrencyHandler currencyHandler;
    static Thread currencyThread;

    public static void main(String[] args) throws IOException {
        currencyHandler = new CurrencyHandler();
        currencyThread = new Thread(currencyHandler);
        currencyThread.start();
        ServerSocket serverSocket = new ServerSocket(5051);
        System.out.println("[SERVER] waiting for client ...");
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("[SERVER] connection established");
            Thread clientThread = new Thread(new ClientHandler(client));
            clients.add(clientThread);
            clientThread.start();
        }
    }

}
