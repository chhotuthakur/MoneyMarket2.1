package com.nilesh.moneymarket;

public class StockDetailsModel {
    private String symbol;
    private String identifier;
    private String open;
    private String dayHigh;
    private String dayLow;
    private String lastPrice;
    private String previousClose;
    private String change;
    private String pChange;
    private String yearHigh;
    private String yearLow;
    private String totalTradedVolume;
    private String totalTradedValue;
    private String perChange365d;
    private String perChange30d;

    public StockDetailsModel() {
    }

    public StockDetailsModel(String symbol, String identifier, String open, String dayHigh, String dayLow,
                             String lastPrice, String previousClose, String change, String pChange,
                             String yearHigh, String yearLow, String totalTradedVolume, String totalTradedValue,
                             String perChange365d, String perChange30d) {
        this.symbol = symbol;
        this.identifier = identifier;
        this.open = open;
        this.dayHigh = dayHigh;
        this.dayLow = dayLow;
        this.lastPrice = lastPrice;
        this.previousClose = previousClose;
        this.change = change;
        this.pChange = pChange;
        this.yearHigh = yearHigh;
        this.yearLow = yearLow;
        this.totalTradedVolume = totalTradedVolume;
        this.totalTradedValue = totalTradedValue;
        this.perChange365d = perChange365d;
        this.perChange30d = perChange30d;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getOpen() {
        return open;
    }

    public String getDayHigh() {
        return dayHigh;
    }

    public String getDayLow() {
        return dayLow;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public String getPreviousClose() {
        return previousClose;
    }

    public String getChange() {
        return change;
    }

    public String getPChange() {
        return pChange;
    }

    public String getYearHigh() {
        return yearHigh;
    }

    public String getYearLow() {
        return yearLow;
    }

    public String getTotalTradedVolume() {
        return totalTradedVolume;
    }

    public String getTotalTradedValue() {
        return totalTradedValue;
    }

    public String getPerChange365d() {
        return perChange365d;
    }

    public String getPerChange30d() {
        return perChange30d;
    }
}
