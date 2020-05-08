package com.basicapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//any class useful for database or jdbc operation
public class DBUtil {
//get connection
	//create connecion with database and return the connection object
	public static Connection getConnection()
	{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@java2004raul.cancjc6pfcwp.us-east-2.rds.amazonaws.com:1521:ORCL");
		}
		catch (Exception e)
		{
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
