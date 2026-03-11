package com.bridgelabz.payroll;

import java.time.LocalDate;
import java.util.List;

public class EmployeePayrollService {

    private EmployeePayrollDBService employeePayrollDBService;

    public EmployeePayrollService() {
        employeePayrollDBService = new EmployeePayrollDBService();
    }

    public List<EmployeePayrollData> readEmployeePayrollData() {
        return employeePayrollDBService.readData();
    }

    public void updateEmployeeSalary(String name, double salary) {
        employeePayrollDBService.updateEmployeeSalary(name, salary);
    }

    public List<EmployeePayrollData> getEmployeesByDateRange(LocalDate startDate, LocalDate endDate) {
        return employeePayrollDBService.getEmployeesByDateRange(startDate, endDate);
    }

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        List<EmployeePayrollData> employees =
                service.getEmployeesByDateRange(
                        LocalDate.of(2018, 1, 1),
                        LocalDate.now()
                );

        employees.forEach(System.out::println);
    }
}