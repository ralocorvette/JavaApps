package com.revature.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import com.amazonaws.services.rds.model.DBInstance;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;

public class Main {
	
	public static void main(String[] args) {
		AmazonRDS awsRDS = AmazonRDSClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

		DescribeDBInstancesResult dbInstResult = awsRDS.describeDBInstances();
		List <DBInstance> dbInstances = dbInstResult.getDBInstances();
		
		for (DBInstance dbInst:dbInstances)
		{
			System.out.println("DB Instance::"+dbInst.getDBName());
		}
		
		//Lets connect to our database
	
		String JDBC_URL = "jdbc:oracle:thin:@java2004raul.cancjc6pfcwp.us-east-2.rds.amazonaws.com:1521:ORCL";
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection= DriverManager.getConnection(
					JDBC_URL,"ralocorvette","Papuchi2013");
			//verify the connection is successful
			Statement stmt1 = connection.createStatement();
			//STATEMENT METHOD
			//create employee with all paraemters id,name and salary
			//int n = stmt1.executeUpdate("INSERT INTO employee values(101,'employeeA',3000)");
			//create employee with just 2 parameters
			//int n = stmt1.executeUpdate("INSERT INTO employee(em_id,emp_name) values (104,'employeeD')");
			//this created the table comment out after table creation 
			//int n = stmt1.executeUpdate("CREATE TABLE employee(em_id int, emp_name varchar2(30),emp_salary int)");
			//updateing values 
			//int n = stmt1.executeUpdate("Update employee SET emp_salary = 1000 where em_id =104");
			//delete values
			//int n = stmt1.executeUpdate("DELETE FROM employee where em_id = 101");
			//delete all records from employee table
			//int n = stmt1.executeUpdate("DELETE FROM employee");
			//PREPARED STATMENT METHOD
			//Inserting records with prepared statement
			
//			PreparedStatement ps = connection.prepareStatement("INSERT INTO employee values(?,?,?)");
//			ps.setInt(1, 103);
//			ps.setString(2, "EmployeeC");
//			ps.setInt(3, 10000);
//			int n = ps.executeUpdate();
//			Updating Records using Prepared statement
//			PreparedStatement ps = connection.prepareStatement("UPDATE employee SET emp_Salary = ? where em_id = ?");
//			ps.setInt(1, 20000);
//			ps.setInt(2, 102);
//			int n = ps.executeUpdate();
			//delete using the prepared statement method
			//"DELETE FROM EMPLOYEE WHERE em_id = ?"
			PreparedStatement ps = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE emp_Salary >= ?");
			ps.setInt(1, 1000);
			int n = ps.executeUpdate();
			System.out.println("Number of rows affectd: "+n);
			ResultSet rs =stmt1.executeQuery("SELECT * FROM Employee ");
			while (rs.next()) {
				String id = rs.getString(1);
				System.out.println(id); //Should print something
				//+"-->"+rs.getInt("emp_salary")
				System.out.println(rs.getInt("em_id")+"-->"+rs.getString("emp_name")+"-->"+rs.getInt("emp_salary"));
			}
			//close the connection 
			stmt1.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}

}