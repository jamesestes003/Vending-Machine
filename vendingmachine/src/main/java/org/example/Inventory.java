package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
    private Map<String, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    public Collection<Product> getProducts() {
        return products.values();
    }

    public void loadFromFile(String fileName) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File(fileName));
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split("\\|");
            String slotId = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            String type = parts[3];
            Product product = new Product(name, price, type);
            products.put(slotId, product);
        }
        fileScanner.close();
    }

    public void displayInventory() {
        for (Map.Entry<String, Product> entry : products.entrySet()) {
            String slotId = entry.getKey();
            Product product = entry.getValue();
            System.out.printf("%-5s %-20s $%-6.2f %-8s %2d remaining\n", slotId, product.getName(), product.getPrice(), product.getType(), product.getQuantity());
        }
    }

    public Product getProduct(String slotId) {
        return products.get(slotId);
    }

    public void updateQuantity(String slotId) {
        Product product = products.get(slotId);
        if (product != null) {
            product.decrementQuantity();
        }
    }

    public double getTotalSales() {
        double totalSales = 0;
        for (Product product : products.values()) {
            totalSales += product.getTotalSales();
        }
        return totalSales;
    }
}