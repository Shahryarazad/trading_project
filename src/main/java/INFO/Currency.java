package INFO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Currency implements Serializable {

    //fields
    public String name;
    public double currentPrice;
    public String currentPriceStr;
    public double maxPrice = Double.MIN_VALUE;
    public String maxPriceStr;
    public double minPrice = Double.MAX_VALUE;
    public String minPriceStr;
    public float change;
    public String changeStr;
    public ArrayList<Double> prices = new ArrayList<>();


    //constructor
    public Currency(String name) {
        this.name = name;
    }


    //methods
    public void setCurrentPrice(double newPrice) {
        if (newPrice > maxPrice)
            maxPrice = newPrice;
        else if (newPrice < minPrice)
            minPrice = newPrice;
        if (currentPrice != 0) {
            change = (float) (Math.round((newPrice / currentPrice) * 100.0) / 100.0);
            if (newPrice > currentPrice)
                changeStr = "+" + change + "%";
            else if (newPrice < currentPrice)
                changeStr = "-" + change + "%";
            else
                changeStr = "0.00%";
        }
        currentPrice = newPrice;
        setStr();
        prices.add(currentPrice);
    }

    public void setStr() {
        currentPriceStr = String.valueOf((float) (Math.round(currentPrice * 100.0) / 100.0));
        maxPriceStr = String.valueOf((float) (Math.round(maxPrice * 100.0) / 100.0));
        minPriceStr = String.valueOf((float) (Math.round(minPrice * 100.0) / 100.0));
    }

    public String getName() {
        return name;
    }

    public String getCurrentPriceStr() {
        return currentPriceStr;
    }

    public String getMaxPriceStr() {
        return maxPriceStr;
    }

    public String getMinPriceStr() {
        return minPriceStr;
    }

    public String getChangeStr() {
        return changeStr;
    }
}
