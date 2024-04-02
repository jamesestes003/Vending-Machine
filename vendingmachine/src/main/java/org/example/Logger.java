package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_FILE = "Log.txt";

    public void logTransaction(String action, double amount, double balance) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(LOG_FILE, true))) {
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a"));
            String logEntry = String.format("%s %s: $%.2f $%.2f", timestamp, action, amount, balance);
            writer.println(logEntry);
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }
}
