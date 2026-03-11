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

    public List<EmployeePayrollData> getEmployeesByDateRange(LocalDate startDate, LocalDate endDate) {

        List<EmployeePayrollData> employeeList = new ArrayList<>();

        String sql = "SELECT * FROM employee_payroll WHERE start BETWEEN ? AND ?";

        try {

            Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));

            ResultSet resultSet = preparedStatement.executeQuery();

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

    // UC6
    public void getSalaryStatisticsByGender() {

        String sql =
                "SELECT gender, SUM(salary), AVG(salary), MIN(salary), MAX(salary), COUNT(*) " +
                        "FROM employee_payroll GROUP BY gender";

        try {

            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                String gender = resultSet.getString(1);
                double sum = resultSet.getDouble(2);
                double avg = resultSet.getDouble(3);
                double min = resultSet.getDouble(4);
                double max = resultSet.getDouble(5);
                int count = resultSet.getInt(6);

                System.out.println("Gender: " + gender);
                System.out.println("Sum Salary: " + sum);
                System.out.println("Average Salary: " + avg);
                System.out.println("Minimum Salary: " + min);
                System.out.println("Maximum Salary: " + max);
                System.out.println("Employee Count: " + count);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}