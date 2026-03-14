package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner; // Import the Scanner class

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConnections.class);
        DataBaseConnections connections = context.getBean(DataBaseConnections.class);
        System.out.println(connections);
        EmployeeDataBaseOpeartions employeeDataBaseOpeartions = context.getBean(EmployeeDataBaseOpeartions.class);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nEmployee Database Operations Menu:");
            System.out.println("1. Insert Employee Details");
            System.out.println("2. View All Details");
            System.out.println("3. View Employee Details by ID");
            System.out.println("4. Update Salary by Department No");
            System.out.println("5. Delete Employee by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    employeeDataBaseOpeartions.insertEmployeeDetails();
                    break;
                case 2:
                    employeeDataBaseOpeartions.all_details();
                    break;
                case 3:
                    employeeDataBaseOpeartions.employee_details_using_id();
                    break;
                case 4:
                    employeeDataBaseOpeartions.update_salary_by_deptno();
                    break;
                case 5:
                    employeeDataBaseOpeartions.delete_employee_by_id();
                    break;
                case 6:
                    exit = true; 
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        scanner.close(); 
    }
}
