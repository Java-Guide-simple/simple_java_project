package in.stack.eStore.service;

import java.util.List;

import in.stack.eStore.model.Product;
import in.stack.eStore.repository.ProductRepoInterFace;
import in.stack.eStore.repository.ProductRepository;

// Service class
public class ProductService implements ProductServiceInterface {

	private ProductRepoInterFace productRepo = new ProductRepository();

	// Show All products
	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepo.getAllProducts();
		return allProducts;
	}

	// Show only particular product according to product Id
	@Override
	public Product getProductById(int id) {
		Product product = productRepo.getProductById(id);

		return product;
	}

	// Add New product in Our Store

	@Override
	public Product addProduct(Product p) {
		Product addedProduct = productRepo.addProduct(p);
		return addedProduct;
	}

	// Update product details
	@Override
	public Product updateProduct(Product p) {
		Product updatedProduct = productRepo.updateProduct(p);
		return updatedProduct;
	}

}
