package com.bridgelabz.payroll;

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

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        service.updateEmployeeSalary("Terisa", 3000000.00);

        List<EmployeePayrollData> employees = service.readEmployeePayrollData();

        employees.forEach(System.out::println);
    }
}