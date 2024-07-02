package INFO;

public class Wallet {

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

//    private Currency findCurrency(String name) {
//        switch (name) {
//            case "USD":
//                return Server.currencyHandler.USD;
//            case "EUR":
//                return Server.currencyHandler.EUR;
//            case "TOMAN":
//                return Server.currencyHandler.TOMAN;
//            case "YEN":
//                return Server.currencyHandler.YEN;
//            case "GBP":
//                return Server.currencyHandler.GBP;
//        }
//        return null;
//    }

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
}
