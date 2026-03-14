package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages="com")
@PropertySource("com/application.properties")
public class DataBaseConnections {
	//@Value("jdbc:mysql://localhost:3306/iem.employee_details?") //hard coded values
	//String interpolation-> used to read the value from .properties file 
	//but IOC will not able to fetch if we don't use  @PropertySource
	@Value("${mysqlurl}")   //primitive data
	private String url;
	@Value("${mysqlusername}")
	private String username;
	@Value("${mysqlpassword}")
	private String password;
	@Value("${mysqlDriverClass}")
	private String driverClassName;
	
	
	
	public Connection mySqlDbConnection() {
		try {
			Class.forName(driverClassName);
			try {
				return DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String toString() {
		return "DataBaseConnections [url=" + url + ", username=" + username + ", password=" + password
				+ ", driverClassName=" + driverClassName + "]";
	}
	
	
}
