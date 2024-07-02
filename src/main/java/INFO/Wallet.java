package INFO;

import java.io.Serializable;

public class Wallet implements Serializable {

    public account account;
    private double amountOfUSD;
    private double amountOfEUR;
    private double amountOfTOMAN;
    private double amountOfYEN;
    private double amountOfGBP;

    public Wallet() {
        amountOfUSD = 1000;
        amountOfEUR = 1000;
        amountOfTOMAN = 1000000;
        amountOfYEN = 100_000;
        amountOfGBP = 1000;
    }

    public double getAmountOfUSD() {
        return amountOfUSD;
    }

    public double getAmountOfEUR() {
        return amountOfEUR;
    }

    public double getAmountOfTOMAN() {
        return amountOfTOMAN;
    }

    public double getAmountOfYEN() {
        return amountOfYEN;
    }

    public double getAmountOfGBP() {
        return amountOfGBP;
    }

    public double getProperty(String currencyName) {
        switch (currencyName) {
            case "USD":
                return amountOfUSD;
            case "EUR":
                return amountOfEUR;
            case "TOMAN":
                return amountOfTOMAN;
            case "YEN":
                return amountOfYEN;
            case "GBP":
                return amountOfGBP;
        }
        return 0.0;
    }
}
