package org.example;

public class Product {
    private String name;
    private double price;
    private String type;
    private int quantity;
    private double totalSales;

    public Product(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;
        this.totalSales = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
            totalSales += price;
        }
    }

    public double getTotalSales() {
        return totalSales;
    }

    public boolean isSoldOut() {
        return quantity == 0;
    }
}
