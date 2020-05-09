package com.basicapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//any class useful for database or jdbc operation
public class DBUtil {
//get connection
	//create connection with database and return the connection object
	public static Connection getConnection()
	{
		String JDBC_URL = "jdbc:oracle:thin:@java2004raul.cancjc6pfcwp.us-east-2.rds.amazonaws.com:1521:ORCL";
		Connection conn = null;
		String user = "ralocorvette";
		String password = "Papuchi2013";
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 conn = DriverManager.getConnection(JDBC_URL,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	
	}
	//takes connection as a a paramater and closes the connection
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
