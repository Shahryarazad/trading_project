package INFO;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import static INFO.accountBank.*;

public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectInputStream objInput;
    private ObjectOutputStream objOutput;


    public ClientHandler(Socket clientSocket) throws IOException {
        client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        objInput = new ObjectInputStream(client.getInputStream());
        objOutput = new ObjectOutputStream(client.getOutputStream());
    }

    @Override
    public void run() {
        try {
            String text;
            while (true) {
                text = in.readLine();
                if (text.equals("quit"))
                    break;

                switch (text) {
                    case "sign up":
                        System.out.println("[SERVER] a client wants to sign up");
                        try {
                            account a = (account) objInput.readObject();
                            System.out.println("[SERVER] account received");
                            boolean checkSignUp = account.signUp(a);
                            out.println(checkSignUp);
                            System.out.println("[SERVER] now there is " + account.accounts.size() + " account");
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "login":
                        System.out.println("[SERVER] a client wants to log in");
                        try {
                            String[] strings = (String[]) objInput.readObject();
                            account logInAccount = account.logIn(strings[0], strings[1]);
                            if (logInAccount != null) {
                                out.println("true");
                                objOutput.writeObject(logInAccount);
                                System.out.println("[SERVER] client logged in successfully");
                            }
                            else
                                out.println("false");
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "home page":
                        System.out.println("[SERVER] client wants data for home page");
                        while (true) {
                            objOutput.writeObject(Server.currencyHandler.USD);
                            objOutput.writeObject(Server.currencyHandler.EUR);
                            objOutput.writeObject(Server.currencyHandler.TOMAN);
                            objOutput.writeObject(Server.currencyHandler.YEN);
                            objOutput.writeObject(Server.currencyHandler.GBP);
                            objOutput.reset();
                            System.out.println("[SERVER] currencies sent");
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (in.readLine().equals("false"))
                                break;
                        }

                }
            }
            client.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}