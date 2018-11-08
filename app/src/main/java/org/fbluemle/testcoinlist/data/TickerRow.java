package org.fbluemle.testcoinlist.data;

public class TickerRow {
    public int id;
    public String name;
    public String symbol;
    public int rank;
    public float price;

    public TickerRow() {
    }

    public TickerRow(int rank, String name, float price) {
        this.rank = rank;
        this.name = name;
        this.price = price;
    }
}
