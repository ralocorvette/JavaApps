package com.basicapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.athena.model.ResultSet;
import com.basicapp.dbutil.DBUtil;
import com.basicapp.pojo.Product;

public class ProductManagementDAO {
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<Product>();
		try {
			Connection conn = DBUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * From product");
			while(rs.next())
			{
				Product product = new
						Product(rs.getString("prod_in"),rs.getString("prod_name"),rs.getInt("prod_price"));
				productList.add(product);
			}
			DBUtil.closeConnection(conn);
			}
		catch (Exception e)
		{
			e.printStackTrace();
	}
		return productList;
		
}
	public Product getProductByID(String productId)
	{
		Product product = null;
		try
		{
			Connection connection = DBUtil.getCOnnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE prod_id = ?");
			ps.setString(1, productId);
			ResulSet rs = ps.executeQuery();
			while(rs.next())
			{
				product = new
						Product(rs.getString("prod_id"),rs.getString("prod_name"),rs.getInt("prod_price"));
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return product;
	}
	public int addProduct(Product product)
	{
		int status =0;
		try {
			Connection conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("Update product SET prod_name=?,prd_price=? WHERE prod_id=?");
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getProductPrice());
			ps.setString(3, product.getProductId());
			status = ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	public int deleteProduct(String productId) {
		int status = 0;
		try
		{
			Connection conn = DBUtil.getCOnnection();
			PreparedStatement ps = conn.prepareStatement("DELETE FROM product where prod_id = ?");
			ps.setString(1, productId);
			status = ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}