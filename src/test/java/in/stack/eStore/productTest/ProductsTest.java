package in.stack.eStore.productTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import in.stack.eStore.model.Product;
import in.stack.eStore.repository.ProductRepository;
import in.stack.eStore.service.ProductService;

@Tag("Primary")
@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class ProductsTest {

	// Creating Mock of the repository
	@Mock
	ProductRepository productRepo;

	// Injecting the depended mock
	@InjectMocks
	ProductService productService;

	// test case for method which implemented Get all Products
	@Test
	public void getProductsTest() throws Exception {
		Product pro1 = new Product(90, "unitTest1", 100, 2000, true);
		Product pro2 = new Product(100, "unitTest2", 200, 5000, true);
		List<Product> list = new ArrayList<Product>();
		list.add(pro1);
		list.add(pro2);

		when(productRepo.getAllProducts()).thenReturn(list);
		List<Product> allProducts = productService.getAllProducts();
		assertNotNull(allProducts);
		assertEquals(list.size(), productService.getAllProducts().size());
	}

	// test case for method which implemented Get Products By Id
	@ParameterizedTest
	@ValueSource(ints = 100)
	public void getProductByIdTest(int id) throws Exception {
		Product pro1 = new Product(90, "unitTest1", 100, 2000, true);
		Product pro2 = new Product(100, "unitTest2", 200, 5000, true);

		when(productRepo.getProductById(id)).thenReturn(pro2);
		Product p = productService.getProductById(id);
		assertNotNull(p);

		assertEquals(pro2.getProductName(), p.getProductName());
	}

	// test case for method which implemented update the Product's details
	@Test
	public void updateProductDetailsTest() throws Exception {
		Product pro1 = new Product(100, "unitTest1", 100, 2000, true);// old Product detail
		Product pro2 = new Product(100, "unitTest2", 200, 5000, true);// after updated Product Details
		when(productRepo.updateProduct(pro1)).thenReturn(pro2);
		Product p = productService.updateProduct(pro1);
		assertNotNull(p);
		assertEquals(200, p.getQuantity());
		assertEquals(5000, p.getPrice());
	}

	// test case for method which implemented Adding the New Product in inventry
	@Test
	public void addproductTest() throws Exception {
		Product pro1 = new Product(90, "unitTest1", 100, 2000, true);
		when(productRepo.addProduct(pro1)).thenReturn(pro1);
		Product addedProduct = productService.addProduct(pro1);
		assertNotNull(addedProduct);
		assertEquals(pro1.getProductId(), addedProduct.getProductId());
		assertEquals(pro1.getProductName(), addedProduct.getProductName());
		assertEquals(pro1.getQuantity(), addedProduct.getQuantity());
		assertEquals(pro1.isIsactive(), addedProduct.isIsactive());
		assertEquals(pro1.getPrice(), addedProduct.getPrice());
	}
}
