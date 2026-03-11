package com.bridgelabz.payroll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

    public List<EmployeePayrollData> readData() {

        List<EmployeePayrollData> employeeList = new ArrayList<>();

        String sql = "SELECT * FROM employee_payroll";

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/payroll_service",
                    "root",
                    "root"
            );

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salary = resultSet.getDouble("salary");
                LocalDate start = resultSet.getDate("start").toLocalDate();

                employeeList.add(
                        new EmployeePayrollData(id, name, salary, start)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }
}