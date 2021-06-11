package in.stack.eStore.controller;

import java.util.List;
import java.util.Scanner;
import in.stack.eStore.model.Product;
import in.stack.eStore.service.ProductService;
import in.stack.eStore.service.ProductServiceInterface;

public class ProductController {

	static ProductController pc;
	private Scanner scan = new Scanner(System.in);
	private ProductServiceInterface service = new ProductService();

	// Get All product Details
	public void getProduct() {

		List<Product> allProducts = service.getAllProducts();
		for (Product product : allProducts) {
			System.out.println(product);
		}
		allProducts.clear();
	}

	// Get Product By ID
	public void getProductById(int id) {
		Product productById = service.getProductById(id);
		if (productById != null) {
			System.out.println(productById.toString());
			productById = null;
		} else
			System.out.println("ID  " + id + " has no product");

	}

	// Add new product
	public void addProduct(Product p) {
		Product addProduct = service.addProduct(p);
		if (addProduct != null) {
			System.out.println("New added Product Details :");
			System.out.println(addProduct + "\n");
		} else {
			System.out.println("No any New product added");
		}

	}

	// Update product details
	public void updateProduct(Product p) {
		Product updatedProduct = service.updateProduct(p);
		System.out.println("Updated Product Details is :");
		System.out.println(updatedProduct.toString());
		updatedProduct = null;
	}

	// User InterFace Option
	public void allFunctionalities() {

		System.out.println("If You Want to See all products Press : 1");
		System.out.println("If You Want to See  product by Id Press : 2");
		System.out.println("If You Want to Update Quantity ,Availablity Or Price of Product by Id Press : 3");
		System.out.println("If You Want to add New  product Press : 4");

		int input = scan.nextInt();
		switch (input) {
		// If You Want to See all products
		case 1:
			pc.getProduct();
			break;
		// If You Want to See product by Id
		case 2:
			System.out.println("Enter Product Id");
			int id = scan.nextInt();
			pc.getProductById(id);
			break;
		// If You Want to Update Quantity ,Availablity Or Price of Product by Id
		case 3:

			System.out.println("Enter Product Id");
			int pid = scan.nextInt();
			if (service.getProductById(pid) != null)
				pc.updateProduct(service.getProductById(pid));
			else
				System.out.println("ID  " + pid + " has no product");
			break;
		// If You Want to add New product
		case 4:
			Product p = new Product();

			pc.addProduct(p);
			break;

		default:

			System.out.println("Please Enter Right Key ");
			main(null);
		}

	}

	// Execution Starting Our Application
	public static void main(String[] args) {
		pc = new ProductController();
		boolean condition = false;
		System.out.println("WELCOME TO OUR ESTORE ");
		do {
			pc.allFunctionalities();
			System.out.println("If You Want to Go Main menu Press : 0");
			System.out.println("If you want to place order Press number other than 0 Key ");
			int back = pc.scan.nextInt();
			if (back == 0)
				condition = true;
			else
				break;
		} while (condition);

	}

}
