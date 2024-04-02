package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachineTest {
    private Inventory inventory;
    private PurchaseProcess purchaseProcess;
    private Logger logger;
    private SalesReport salesReport;

    public VendingMachineTest() {
        inventory = new Inventory();
        purchaseProcess = new PurchaseProcess(inventory);
        logger = new Logger();
        salesReport = new SalesReport(inventory);
    }

    public void run() {
        loadInventory();
        displayMainMenu();
    }

    private void loadInventory() {
        try {
            inventory.loadFromFile("vendingmachine.csv");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Inventory file not found.");
        }
    }

    private void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayInventory();
                case 2 -> purchaseProcess.startPurchaseProcess();
                case 3 -> {
                    System.out.println("Thank you for using the vending machine!");
                    return;
                }
                case 4 -> generateSalesReport();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayInventory() {
        System.out.println("\nVending Machine Items:");
        inventory.displayInventory();
    }

    private void generateSalesReport() {
        String fileName = "SalesReport_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".txt";
        try {
            salesReport.generateReport(fileName);
            System.out.println("Sales report generated: " + fileName);
        } catch (IOException e) {
            System.out.println("Error generating sales report: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        VendingMachineTest vendingMachine = new VendingMachineTest();
        vendingMachine.run();
    }
}