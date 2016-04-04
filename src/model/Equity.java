package src.model;
// Author : Ian Taylor
// Equity class to represent equities in FPTS.

public class Equity{

    String name = "";
    String ticker = "";
    double price_per_share = 0.0;
    String index = "";
    String sector = ""; // Should we make this a class?

    public Equity(String name, String ticker, double price_per_share) {
	this.name = name;
	this.ticker = ticker;
	this.price_per_share = price_per_share;
    }

    public String get_name() { return this.name; }
    public String get_ticker() { return this.ticker; }
    public double get_price() { return this.price_per_share; }
    public String get_index() { return this.index; }
    public String get_sector() { return this.sector; }

    public void set_name(String name) { this.name = name; }
    public void set_ticker(String ticker) { this.ticker = ticker; }
    public void set_price(double price) { this.price_per_share = price; }
    public void set_index(String index) { this.index = index; }
    public void set_sector(String sector) { this.sector = sector; }

    // I don't think we need anything else here...am I wrong?

    // For you Tyler
    public void import_equity() {}
    public void export_equity() {}

}
