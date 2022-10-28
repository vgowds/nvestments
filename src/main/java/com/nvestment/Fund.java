package com.nvestment;

public class Fund {

    public Fund() {
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPrice() {
        return price;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String symbol;
    private String companyName;
    private String price;

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
                "Symbol='" + symbol + '\'' +
                ", CompanyName='" + companyName + '\'' +
                ", Price='" + price + '\'' +
                ", Price Date='" + priceDate + '\'' +
                '}';
    }
}
