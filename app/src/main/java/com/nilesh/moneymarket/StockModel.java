package com.nilesh.moneymarket;

public class StockModel {
    private String symbol;
    private String identifier;
    private String openPrice;
    private String dayHigh;

    public StockModel(String symbol, String identifier, String openPrice, String dayHigh) {
        this.symbol = symbol;
        this.identifier = identifier;
        this.openPrice = openPrice;
        this.dayHigh = dayHigh;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
    }

    public String getDayHigh() {
        return dayHigh;
    }

    public void setDayHigh(String dayHigh) {
        this.dayHigh = dayHigh;
    }
}
