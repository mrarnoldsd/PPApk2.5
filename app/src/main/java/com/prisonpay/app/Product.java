package com.prisonpay.app;

public class Product {
    private String id;
    private String name;
    private double cost;
    private double price;
    private int stock;

    public Product() {
        // Needed for Firebase
    }

    public Product(String id, String name, double cost, double price, int stock) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.stock = stock;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getCost() { return cost; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCost(double cost) { this.cost = cost; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }

    public double getROI() {
        if (cost == 0) return 0;
        return ((price - cost) / cost) * 100;
    }

    public double getRevenue() {
        return price * stock;
    }

    public double getInvestmentValue() {
        return cost * stock;
    }
}
