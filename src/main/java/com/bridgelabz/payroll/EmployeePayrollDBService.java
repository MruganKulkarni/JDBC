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

    // UC4 - PreparedStatement salary update
    public int updateEmployeeSalary(String name, double salary) {

        String sql = "UPDATE employee_payroll SET salary = ? WHERE name = ?";

        try {
            Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDouble(1, salary);
            preparedStatement.setString(2, name);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}