package in.stack.eStore.repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import in.stack.eStore.connection.DBConnection;
import in.stack.eStore.model.Product;

public class ProductRepository implements ProductRepoInterFace {

	DBConnection db = new DBConnection();
	Statement stmnt = db.getConnection();
	List<Product> allProduct = new ArrayList<Product>();

	// Get All Products
	public List<Product> getAllProducts() {
		try {

			ResultSet allProducts = stmnt.executeQuery("SELECT * FROM products ");

			while (allProducts.next()) {
				Product p = new Product();
				p.setProdutId(allProducts.getInt("productId"));
				p.setProductName(allProducts.getString("productName"));
				p.setQuantity(allProducts.getInt("quantity"));
				p.setPrice(allProducts.getDouble("price"));
				p.setIsactive(allProducts.getBoolean("isactive"));

				allProduct.add(p);
			}

			return allProduct;
		} catch (Exception e) {
			return allProduct = null;
		}

	}

	// Get product by Id
	@Override
	public Product getProductById(int id) {
		try {
			Product p = null;
			ResultSet product = stmnt.executeQuery("SELECT * FROM products WHERE productId = '" + id + "'");

			while (product.next()) {
				System.out.println(product.getInt("productId"));
				if (product.getInt("productId") != 0) {
					p = new Product();
					p.setProdutId(product.getInt("productId"));
					p.setProductName(product.getString("productName"));
					p.setQuantity(product.getInt("quantity"));
					p.setPrice(product.getDouble("price"));
					p.setIsactive(product.getBoolean("isactive"));
				} else {

					return p = null;
				}

			}

			return p;

		} catch (Exception e) {
			return null;
		}

	}

	// add new product
	@Override
	public Product addProduct(Product p) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Product Id");
		int pid = scan.nextInt();
		if (getProductById(pid) == null) {
			System.out.println("Enter Product Name");
			String pName = scan.next();
			System.out.println("Enter Price ");
			double price = scan.nextDouble();
			System.out.println("Enter available Quantities");
			int quantities = scan.nextInt();

			System.out.println("Enter availablity : 'true for available or false for not available' ");
			boolean isActive = scan.nextBoolean();
			p.setProdutId(pid);
			p.setProductName(pName);
			p.setQuantity(quantities);
			p.setPrice(price);
			p.setIsactive(isActive);

			int i = p.isIsactive() ? 1 : p.isIsactive() ? 0 : 0;
			try {
				stmnt.executeUpdate("insert into products values('" + p.getProductId() + "','" + p.getProductName()
						+ "','" + p.getQuantity() + "','" + p.getPrice() + "','" + i + "')");

				return p;
			} catch (Exception e) {
				System.out.println(e);
				return p;
			}
		} else {
			System.out.println("Corresponding to pid: " + pid
					+ " already Some Product available if You want to update then go to Update Method");
			return p = null;
		}
	}

	// update product details
	@Override
	public Product updateProduct(Product p) {

		try {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter New Price ");
			double newPrice = scan.nextDouble();
			System.out.println("Enter available Quantities");
			int newQuantities = scan.nextInt();
			int isActive = p.isIsactive() ? 1 : p.isIsactive() ? 0 : 0;
			System.out.println("Enter availablity : '1 for avalable or 0 for not available' ");
			isActive = scan.nextInt();
			stmnt.executeUpdate("UPDATE products SET quantity = '" + newQuantities + "',price='" + newPrice
					+ "',isactive='" + isActive + "' WHERE productId='" + p.getProductId() + "'");
			return getProductById(p.getProductId());
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
