package com.bridgelabz.payroll;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollDBService {

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(jdbcURL, username, password);
    }

    public List<EmployeePayrollData> readData() {

        List<EmployeePayrollData> employeeList = new ArrayList<>();

        String sql = "SELECT * FROM employee_payroll";

        try {
            Connection connection = this.getConnection();
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

    public int updateEmployeeSalary(String name, double salary) {

        String sql = String.format(
                "UPDATE employee_payroll SET salary = %.2f WHERE name = '%s';",
                salary, name
        );

        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();

            return statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}