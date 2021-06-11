package in.stack.eStore.repository;

import java.util.List;

import in.stack.eStore.model.Product;

public interface ProductRepoInterFace {
	public List<Product> getAllProducts();
	public Product getProductById(int id);
	public Product addProduct(Product p);
	public Product updateProduct(Product p);
	
	
	
}
