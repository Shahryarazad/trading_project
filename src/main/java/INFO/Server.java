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
    static TradeHandler tradeHandler;
    static Thread tradeThread;
    public static account admin = new account("admin");
    public static ArrayList<Request> buyRequests = new ArrayList<>() {
        @Override
        public String toString() {
             String s = "";
            for (Request r : buyRequests)
                s += (r.price + " ");
            return s;
        }
    };
    public static ArrayList<Request> sellRequests = new ArrayList<>();
    public static ArrayList<Trade> trades = new ArrayList<>() {
        @Override
        public String toString() {
            String s = "";
            for (Trade t : trades)
                s += (t.price + " ");
            return s;
        }
    };

    public static ArrayList<account> accounts = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        currencyHandler = new CurrencyHandler();
        currencyThread = new Thread(currencyHandler);
        currencyThread.start();
        tradeHandler = new TradeHandler();
        tradeThread = new Thread(tradeHandler);
        tradeThread.start();
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

    public static void setTrade(account a1, account a2, Trade trade) {
        for (account a : accounts) {
            if (a.equals(a1))
                a.trades.add(trade);
            else if (a.equals(a2))
                a.trades.add(trade);
        }
    }



    //getter and setter
    public synchronized static ArrayList<Request> getBuyRequests() {
        return buyRequests;
    }

    public synchronized static void setBuyRequests(ArrayList<Request> buyRequests) {
        Server.buyRequests = buyRequests;
    }

    public synchronized static ArrayList<Request> getSellRequests() {
        return sellRequests;
    }

    public synchronized static void setSellRequests(ArrayList<Request> sellRequests) {
        Server.sellRequests = sellRequests;
    }

    public synchronized static ArrayList<Trade> getTrades() {
        return trades;
    }

    public synchronized static void setTrades(ArrayList<Trade> trades) {
        Server.trades = trades;
    }

    public synchronized static ArrayList<account> getAccounts() {
        return accounts;
    }

    public synchronized static void setAccounts(ArrayList<account> accounts) {
        Server.accounts = accounts;
    }

    public synchronized static CurrencyHandler getCurrencyHandler() {
        return currencyHandler;
    }

    public synchronized static void setCurrencyHandler(CurrencyHandler currencyHandler) {
        Server.currencyHandler = currencyHandler;
    }

    public synchronized static TradeHandler getTradeHandler() {
        return tradeHandler;
    }

    public synchronized static void setTradeHandler(TradeHandler tradeHandler) {
        Server.tradeHandler = tradeHandler;
    }
}
