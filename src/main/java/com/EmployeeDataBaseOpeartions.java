package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDataBaseOpeartions {
	@Autowired
	DataBaseConnections databaseconnections;

	@Autowired
	@Qualifier("scannerBean1")
	Scanner sc;

	public void insertEmployeeDetails() {
		Connection con = databaseconnections.mySqlDbConnection();
		System.out.println("Connection Established");
		String insert = "Insert into employeedb.employeedetails(employee_name, employee_salary, employee_deptno) values (?,?,?)";
		System.out.println("Enter the name: ");
		String name = sc.next();
		System.out.println("Enter the salary: ");
		double salary = sc.nextDouble();
		System.out.println("Enter the deptno: ");
		int deptno = sc.nextInt();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(insert);
			ps.setString(1, name);
			ps.setDouble(2, salary);
			ps.setInt(3, deptno);
			int result = ps.executeUpdate();
			if (result == 0) {
				System.out.println("Data insertion failed...");
				return;
			} else {
				System.out.println("Data insertion done...");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Create a method to get all the details of the employee
	public void all_details() {
		Connection con = databaseconnections.mySqlDbConnection();
		System.out.println("Connection Established");
		String select = "Select * from employeedb.employeedetails";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(select);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst()) {
				while (result.next()) {
					System.out.println("Employee ID: " + result.getInt("employee_id"));
					System.out.println("Employee Name: " + result.getString("employee_name"));
					System.out.println("Employee Salary: " + result.getDouble("employee_salary"));
					System.out.println("Employee Dept No: " + result.getInt("employee_deptno"));
					System.out.println("------------------------------------------");
				}
			} else {
				System.out.println("No employee exists in the database.");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Create a method to get employee by using id
	public void employee_details_using_id() {
		Connection con = databaseconnections.mySqlDbConnection();
		System.out.println("Connection Established");
		String select = "Select * from employeedb.employeedetails where employee_id=?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(select);
			System.out.println("Enter the employee ID: ");
			int id = sc.nextInt();
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.isBeforeFirst()) {
				while (result.next()) {
					System.out.println("Employee ID: " + result.getInt("employee_id"));
					System.out.println("Employee Name: " + result.getString("employee_name"));
					System.out.println("Employee Salary: " + result.getDouble("employee_salary"));
					System.out.println("Employee Dept No: " + result.getInt("employee_deptno"));
					System.out.println("------------------------------------------");
				}
			} else {
				System.out.println("No employee exists with the given ID.");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Create a method to update the salary by using deptno
	public void update_salary_by_deptno() {
		Connection con = databaseconnections.mySqlDbConnection();
		System.out.println("Connection Established");
		String update = "UPDATE employeedb.employeedetails SET employee_salary = ? WHERE employee_deptno = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(update);
			System.out.println("Enter the new salary: ");
			double newSalary = sc.nextDouble();
			System.out.println("Enter the department number to update salaries for: ");
			int deptNo = sc.nextInt();
			ps.setDouble(1, newSalary);
			ps.setInt(2, deptNo);
			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println(res + " records updated successfully in department " + deptNo);
			} else {
				System.out.println("No employees found in department " + deptNo);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Create a method to delete the details of the employee by using id
	public void delete_employee_by_id() {
		Connection con = databaseconnections.mySqlDbConnection();
		System.out.println("Connection Established");
		String delete = "DELETE FROM employeedb.employeedetails WHERE employee_id = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(delete);
			System.out.println("Enter the employee ID to delete: ");
			int id = sc.nextInt();
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if (res > 0) {
				System.out.println("Employee with ID " + id + " deleted successfully.");
			} else {
				System.out.println("No employee found with ID " + id);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
