package dekra.qp.services.product.controller;

import java.util.List;
import java.util.Optional;

import dekra.qp.services.product.model.Product;
import dekra.qp.services.product.repository.ProductRepository;
import dekra.qp.services.product.tax.TaxAuxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the product service
 * Must verify again the security configuration
 */
@RestController
@PreAuthorize("hasAuthority('SCOPE_manager')")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	private final ProductRepository repository;
	private final TaxAuxService productTaxService;

	@Autowired
	public ProductController(ProductRepository repository, TaxAuxService productTaxService, TaxAuxService productTaxService1) {
		this.repository = repository;
        this.productTaxService = productTaxService1;
    }

	@GetMapping("/")
	@Cacheable(value = "products")
	public List<Product> findAll() {
		LOGGER.info("Product find");
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
		LOGGER.info("Product find: id={}", id);
		Optional<Product> product = repository.findById(id);
        return product.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/category/{categoryId}")
	@Cacheable(value = "categoryProducts", key = "#categoryId")
	public List<Product> findByCategory(@PathVariable("categoryId") Long categoryId) {
		LOGGER.info("Product find: categoryId={}", categoryId);
		return repository.findByCategoryId(categoryId);
	}

	@GetMapping("/inventory/{inventoryId}")
	@Cacheable(value = "inventoryProducts", key = "#inventoryId")
	public List<Product> findByInventory(@PathVariable("inventoryId") Long inventoryId) {
		LOGGER.info("Product find: inventoryId={}", inventoryId);
		return repository.findByInventoryId(inventoryId);
	}

	@PostMapping("/")
	@CacheEvict(value = {"products", "categoryProducts", "inventoryProducts"}, allEntries = true)
	public Product add(@RequestBody Product product) {
		LOGGER.info("Product add: {}", product);
		return repository.save(product);
	}

	@PutMapping("/{id}")
	@CacheEvict(value = {"products", "categoryProducts", "inventoryProducts"}, allEntries = true)
	public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
		Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		product.setName(productDetails.getName());
		product.setDescription(productDetails.getDescription());
		product.setPrice(productDetails.getPrice());
		product.setInventoryId(productDetails.getInventoryId());
		product.setCategoryId(productDetails.getCategoryId());
		return repository.save(product);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = {"products", "categoryProducts", "inventoryProducts"}, allEntries = true)
	public void deleteProduct(@PathVariable Long id) {
		Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		repository.delete(product);
	}

	@GetMapping("/{id}/priceWithTax")
	public String getProductPriceWithTax(@PathVariable Long id) {
		Product product = repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		productTaxService.calculatePriceAfterTax(id);
		return String.format("The price of the product with tax is: %.2f", product.getPriceAfterTax());
	}
}
