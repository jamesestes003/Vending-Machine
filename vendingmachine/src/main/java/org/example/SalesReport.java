package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class SalesReport {
    private Inventory inventory;

    public SalesReport(Inventory inventory) {
        this.inventory = inventory;
    }

    public void generateReport(String fileName) throws IOException {
        Map<String, Double> productSales = new LinkedHashMap<>();
        for (Product product : inventory.getProducts()) {
            productSales.put(product.getName(), product.getTotalSales());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, Double> entry : productSales.entrySet()) {
                String productName = entry.getKey();
                double sales = entry.getValue();
                writer.println(productName + "|" + sales);
            }

            writer.println();
            double totalSales = inventory.getTotalSales();
            writer.println("**TOTAL SALES** $" + String.format("%.2f", totalSales));
        }
    }
}
