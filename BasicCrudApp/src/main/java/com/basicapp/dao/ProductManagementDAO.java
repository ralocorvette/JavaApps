package com.basicapp.dao;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.basicapp.dbutil.DBUtil;
import com.basicapp.pojo.Product;

public class ProductManagementDAO {
	public List<Product> getAllProducts() {
		//create array list
		List<Product> productList = new ArrayList<Product>();
		try {
			//open connection
			Connection conn = DBUtil.getConnection();
			//create statement
			Statement st = conn.createStatement();
			//create ResultSet Object and execute the SQL Query
																	 
			
			ResultSet rs = st.executeQuery("SELECT * FROM product");
			while (rs.next()) {
				Product product = new Product(rs.getString("prod_id"), rs.getString("prod_name"),
						rs.getInt("prod_price"));
				productList.add(product);
			}
			//close connection 
			DBUtil.closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return product list 
		return productList;
	}

	public Product getProductById(String productId) {
		Product product = null;
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE prod_id = ?");
			ps.setString(1, productId); 
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getString("prod_id"), rs.getString("prod_name"), rs.getInt("prod_price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	public int addProduct(Product product) {
		int status = 0;
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO product VALUES(?,?,?)");
			ps.setString(1, product.getProductId()) ;
			ps.setString(2, product.getProductName());
			ps.setInt(3, product.getProductPrice());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int updateProduct(Product product) {
		int status = 0;
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("UPDATE product SET prod_name=?,prod_price=? WHERE prod_id=?");
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getProductPrice());
			ps.setString(3, product.getProductId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int deleteProduct(String productId) {
		int status = 0;
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM product where prod_id = ?");
			ps.setString(1, productId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}