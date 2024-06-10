package INFO;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Currency implements Serializable {

    //fields
    public String name;
    public double currentPrice;
    public double maxPrice = Double.MIN_VALUE;
    public double minPrice = Double.MAX_VALUE;
    public String showChange;
    public float change;
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
                showChange = "+" + change + "%";
            else if (newPrice < currentPrice)
                showChange = "-" + change + "%";
            else
                showChange = "0.00%";
        }
        currentPrice = newPrice;
        prices.add(currentPrice);
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public String getShowChange() {
        return showChange;
    }
}
