package com.nvestment;

public class Fund {

    public Fund() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getFundName() {
        return fundName;
    }

    public String getPrice() {
        return price;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String symbol;
    private String fundName;
    private String price;

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    private String todayDate;

    public String getPriceDate() {
        return priceDate;
    }

    public void setPriceDate(String priceDate) {
        this.priceDate = priceDate;
    }

    private String priceDate;

    @Override
    public String toString() {
        return "{" +
                "\"Symbol\":  \"" + this.symbol + "\", " +
                "\"Fund Name\":\"" + this.fundName + "\", " +
                "\"Price\": \"" + this.price + "\", " +
                "\"Price Date\": \"" + this.priceDate + "\", " +
                "\"Today Date\": \"" + this.todayDate + "\" " +
                "}";
    }
}
