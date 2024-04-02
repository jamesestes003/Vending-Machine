package org.example;

import java.util.Scanner;

public class PurchaseProcess {
    private Inventory inventory;
    private Logger logger;
    private double currentBalance;

    public PurchaseProcess(Inventory inventory) {
        this.inventory = inventory;
        this.logger = new Logger();
        this.currentBalance = 0;
    }

    public void startPurchaseProcess() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCurrent Money Provided: $" + String.format("%.2f", currentBalance));
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> feedMoney(scanner);
                case 2 -> selectProduct(scanner);
                case 3 -> {
                    finishTransaction();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void feedMoney(Scanner scanner) {
        System.out.print("Enter amount to feed (whole dollars only): ");
        int amount = scanner.nextInt();
        if (amount > 0) {
            currentBalance += amount;
            logger.logTransaction("FEED MONEY", amount, currentBalance);
            System.out.println("Current Money Provided: $" + String.format("%.2f", currentBalance));
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }

    private void selectProduct(Scanner scanner) {
        inventory.displayInventory();
        System.out.print("Enter product slot ID: ");
        String slotId = scanner.next();
        Product product = inventory.getProduct(slotId);
        if (product == null) {
            System.out.println("Invalid product slot ID. Please try again.");
        } else if (product.isSoldOut()) {
            System.out.println("Product is sold out. Please try again.");
        } else if (currentBalance < product.getPrice()) {
            System.out.println("Insufficient funds. Please feed more money.");
        } else {
            dispenseProduct(product);
            inventory.updateQuantity(slotId);
            currentBalance -= product.getPrice();
            logger.logTransaction(product.getName(), product.getPrice(), currentBalance);
        }
    }

    private void dispenseProduct(Product product) {
        System.out.println("Dispensing " + product.getName() + " for $" + String.format("%.2f", product.getPrice()));
        switch (product.getType()) {
            case "Chip" -> System.out.println("Crunch Crunch, Yum!");
            case "Candy" -> System.out.println("Munch Munch, Yum!");
            case "Drink" -> System.out.println("Glug Glug, Yum!");
            case "Gum" -> System.out.println("Chew Chew, Yum!");
        }
        System.out.println("Remaining balance: $" + String.format("%.2f", currentBalance));
    }

    private void finishTransaction() {
        if (currentBalance > 0) {
            double changeAmount = currentBalance;
            currentBalance = 0;
            logger.logTransaction("GIVE CHANGE", changeAmount, currentBalance);
            giveChange(changeAmount);
        } else {
            System.out.println("No change due.");
        }
    }

    private void giveChange(double amount) {
        int quarters = (int) (amount / 0.25);
        amount -= quarters * 0.25;
        int dimes = (int) (amount / 0.10);
        amount -= dimes * 0.10;
        int nickels = (int) (amount / 0.05);

        System.out.println("Dispensing change: ");
        if (quarters > 0) {
            System.out.println(quarters + " quarters");
        }
        if (dimes > 0) {
            System.out.println(dimes + " dimes");
        }
        if (nickels > 0) {
            System.out.println(nickels + " nickels");
        }
    }
}
