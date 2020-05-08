package com.basicapp.app;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.basicapp.dao.ProductManagementDAO;
import com.basicapp.pojo.Product;

public class ProductManagementApplication {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ProductManagementDAO dao = new ProductManagementDAO();

	public static void main (String[] args) throws Exception{
			
			String option = "";
			
			do {
				System.out.println("A. View Products");
				System.out.println("B. Update Products");
				System.out.println("C Update Product");
				System.out.println("D Delete Product");
				System.out.println("E Search Product");
				System.out.println("F Exit");
				
				System.out.println("==========================================================================");
				System.out.println("Enter an option");
				System.out.println("==========================================================================");
				
				option = br.readLine();
				System.out.println("\n");
				
				switch (option.toUpperCase())
				{
				case "A":
					viewProducts();
					break;
				case:"B":
					addProduct();
					break;
				case: "C":
					updateProduct();
				break;
				case: "D":
					deleteProduct();
				case:"E":
					searchProduct();
					break;
				case: "F":
					System.out.println("*********************THANK YOU**************************");
				System.exit(0);
				break;
				default:
					System.out.println("Invalid Option!!.Please enter again");
					break;
				}
		}while(option != "F");

	public static void viewProducts() {
		System.out.println("------------------------------------");
		List<Product> productList = dao.getAllProducts();
		for (Product product : productList) {
			displayProduct(product);
		}
		System.out.println("--------------------------------------");
		System.out.println("\n");
	}

	public static void addProduct() throws Exception {
		System.out.println("------------------------------------");
		System.out.println("Enter Product ID:");
		System.out.println("------------------------------------");
		String productId = br.readLine();
		System.out.println("------------------------------------");
		System.out.println("Enter Product Name:");
		System.out.println("------------------------------------");
		String productName = br.readLine();
		System.out.println("------------------------------------");
		System.out.println("Enter Product Price:");
		System.out.println("------------------------------------");
		int productPrice = Integer.parseInt(br.readLine());
		Product product = new Product(productId, productName, productPrice);
		int status = dao.addProduct(product);
		if (status == 1) {
			System.out.println("Product added succesfulyy");
		} else {
			System.out.println("Error while adding product");
		}
		System.out.println("\n");

	}

	public static void updaPRoduct() throws Exception {
		System.out.println("------------------------------------");
		System.out.println("Enter Product ID:");
		System.out.println("------------------------------------");
		String productId = br.readLine();
		System.out.println("------------------------------------");
		System.out.println("Enter New Product Name:");
		System.out.println("------------------------------------");
		String productName = br.readLine();
		System.out.println("------------------------------------");
		System.out.println("Enter New Product Price:");
		System.out.println("------------------------------------");
		int productPrice = Integer.parseInt(br.readLine());
		Product product = new Product(productId, productName, productPrice);
		int status = dao.updateProduct(product);
		if (Status == 1) {
			System.out.println("Product updated succesfully");
		} else {
			System.out.println("Error while updating the product");
		}
		System.out.println("\n");
	}

	public static void deleteProduct() throws Exception {
		System.out.println("--------------------------");
		System.out.println("Enter product ID");
		System.out.println("--------------------------");
		String productId = br.readLine();
		int status = dao.deleteProduct(productId);
		if (status == 1) {
			System.out.println("Product delete succesfully");
		} else {
			System.out.println("Error while deleteing product");
		}
		System.out.println("\n");
	}

	public static void search

	Produc() throws Exception
			{
				System.out.println("------------------------------");
				System.out.println("Etner Product ID");
				System.out.println("------------------------------");
				String productID = br.readLine();
				Product product = dao.getProductByID(productId);
				displayProduct(product);
				System.out.println("\n");
			}

	public static void displayProduct(Product product) {
		System.out.println("Product ID: " + product.getProductId());
		System.out.println("Product Name: " + product.getProductName());
		System.out.println("Product Price: " + product.getProductPrice());
		System.out.println("\n");
	}

}
