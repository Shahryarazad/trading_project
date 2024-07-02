package INFO;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static INFO.accountBank.*;

public class ClientHandler implements Runnable{

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ObjectInputStream objInput;
    private ObjectOutputStream objOutput;
    private account a;


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
                            a = (account) objInput.readObject();
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
                            a = account.logIn(strings[0], strings[1]);
                            if (a != null) {
                                out.println("true");
                                objOutput.writeObject(a);
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
                            if (in.readLine().equals("false"))
                                break;
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case "currency":
                        String name = in.readLine();
                        Currency currency = findCurrency(name);
                        while (true) {
                            objOutput.writeObject(currency);
                            objOutput.reset();
                            if (in.readLine().equals("false"))
                                break;
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                    case "exchange":
                        while (true) {
                            objOutput.writeObject(Server.buyRequests);
                            objOutput.writeObject(Server.sellRequests);
                            objOutput.writeObject(a);
                            objOutput.writeObject(Server.currencyHandler.USD);
                            objOutput.writeObject(Server.currencyHandler.EUR);
                            objOutput.writeObject(Server.currencyHandler.TOMAN);
                            objOutput.writeObject(Server.currencyHandler.YEN);
                            objOutput.writeObject(Server.currencyHandler.GBP);
                            objOutput.reset();
                            ArrayList<Request> newBuy = (ArrayList<Request>) objInput.readObject();
                            ArrayList<Request> newSell = (ArrayList<Request>) objInput.readObject();
                            addToBuy(newBuy);
                            addToSell(newSell);
                            System.out.println(Server.sellRequests);
                            if (in.readLine().equals("false"))
                                break;
                            try {
                                Thread.sleep(5000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        break;
                }
            }
            client.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Currency findCurrency(String name) {
        switch (name) {
            case "USD":
                return Server.currencyHandler.USD;
            case "EUR":
                return Server.currencyHandler.EUR;
            case "TOMAN":
                return Server.currencyHandler.TOMAN;
            case "YEN":
                return Server.currencyHandler.YEN;
            case "GBP":
                return Server.currencyHandler.GBP;
        }
        return null;
    }

    private void addToBuy(ArrayList<Request> buyList) {
        for (Request r : buyList)
            Server.buyRequests.add(r);
    }

    private void addToSell(ArrayList<Request> buyList) {
        for (Request r : buyList)
            Server.sellRequests.add(r);
    }
}