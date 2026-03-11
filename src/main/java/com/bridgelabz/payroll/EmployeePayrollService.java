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

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        List<EmployeePayrollData> employees =
                service.readEmployeePayrollData();

        employees.forEach(System.out::println);
    }
}