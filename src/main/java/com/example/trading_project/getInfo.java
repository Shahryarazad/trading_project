package com.example.trading_project;


import INFO.*;

import java.io.IOException;
import java.util.ArrayList;

public class getInfo implements Runnable{
    @Override
    public void run() {
        try {
            while (true) {
                mainCode.usd = (Currency) mainCode.objIn.readObject();
                mainCode.eur = (Currency) mainCode.objIn.readObject();
                mainCode.toman = (Currency) mainCode.objIn.readObject();
                mainCode.yen = (Currency) mainCode.objIn.readObject();
                mainCode.gbp = (Currency) mainCode.objIn.readObject();
                mainCode.buyRequests = (ArrayList<Request>) mainCode.objIn.readObject();
                mainCode.sellRequests = (ArrayList<Request>) mainCode.objIn.readObject();
                mainCode.account = (account) mainCode.objIn.readObject();
                Thread.sleep(5000);
                mainCode.objOut.writeObject(mainCode.account);
                mainCode.objOut.writeObject(mainCode.newBuys);
                mainCode.objOut.writeObject(mainCode.newSells);
                mainCode.objOut.reset();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
