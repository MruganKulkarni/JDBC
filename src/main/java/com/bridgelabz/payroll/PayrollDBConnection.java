package com.bridgelabz.payroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Driver;
import java.util.Enumeration;

public class PayrollDBConnection {

    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String username = "root";
        String password = "root";   // change if your mysql password is different

        Connection connection;

        try {

            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        // List all registered drivers
        listDrivers();

        try {

            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("Connection established successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listDrivers() {

        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println("Driver: " + driver.getClass().getName());
        }
    }
}