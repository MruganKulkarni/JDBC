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

    public void getSalaryStatisticsByGender() {
        employeePayrollDBService.getSalaryStatisticsByGender();
    }

    public static void main(String[] args) {

        EmployeePayrollService service = new EmployeePayrollService();

        service.getSalaryStatisticsByGender();
    }
}