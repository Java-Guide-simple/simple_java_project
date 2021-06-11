package in.stack.eStore.service;

import java.util.List;

import in.stack.eStore.model.Product;

public interface ProductServiceInterface {
	public List<Product> getAllProducts();
	public Product addProduct(Product p);
	public Product updateProduct(Product p);
	Product getProductById(int id);
}
