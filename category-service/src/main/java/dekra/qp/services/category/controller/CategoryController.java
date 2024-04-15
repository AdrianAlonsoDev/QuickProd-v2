package dekra.qp.services.category.controller;

import dekra.qp.services.category.client.ProductClient;
import dekra.qp.services.category.model.Category;
import dekra.qp.services.category.repository.CategoryRepository;
import dekra.qp.services.category.cache.CacheAuxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('SCOPE_manager')")
public class CategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	private final CategoryRepository repository;

	private final ProductClient productClient;

	private final CacheAuxService cacheAuxService;

	@Autowired
	public CategoryController(CategoryRepository repository, ProductClient productClient, CacheAuxService cacheAuxService) {
		this.repository = repository;
		this.productClient = productClient;
		this.cacheAuxService = cacheAuxService;
	}

	@GetMapping("/")
	@Cacheable("allCategories")
	public List<Category> findAll() {
		LOGGER.info("Category find");
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category category = cacheAuxService.findCachedCategoryById(id);
		if (category != null) {
			return ResponseEntity.ok(category);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/inventory/{inventoryId}")
	@Cacheable(value = "categoriesByInventory", key = "#inventoryId")
	public List<Category> findByInventory(@PathVariable("inventoryId") Long inventoryId) {
		LOGGER.info("Category find: inventoryId={}", inventoryId);
		return repository.findByInventoryId(inventoryId);
	}
	
	@GetMapping("/inventory/{inventoryId}/with-products")
	@Cacheable(value = "categoriesWithProductsByInventory", key = "#inventoryId")
	public List<Category> findByInventoryWithProducts(@PathVariable("inventoryId") Long inventoryId) {
		LOGGER.info("Category find: inventoryId={}", inventoryId);
		List<Category> categories = repository.findByInventoryId(inventoryId);
		categories.forEach(category -> category.setProducts(productClient.findByCategory(category.getId())));
		return categories;
	}

	@PostMapping("/")
	@CacheEvict(value = {"allCategories", "categoryDetails", "categoriesByInventory", "categoriesWithProductsByInventory"}, allEntries = true)
	public Category add(@RequestBody Category category) {
		LOGGER.info("Category add: {}", category);
		return repository.save(category);
	}

	@PutMapping("/{id}")
	@CacheEvict(value = {"allCategories", "categoryDetails", "categoriesByInventory", "categoriesWithProductsByInventory"}, allEntries = true)
	public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
		LOGGER.info("Updating category with id: {}", id);
		return repository.findById(id)
				.map(category -> {
					category.setName(categoryDetails.getName());
					category.setInventoryId(categoryDetails.getInventoryId());
					return repository.save(category);
				})
				.orElseThrow(() -> new RuntimeException("Category not found"));
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = {"allCategories", "categoryDetails", "categoriesByInventory", "categoriesWithProductsByInventory"}, allEntries = true)
	public void deleteCategory(@PathVariable Long id) {
		LOGGER.info("Deleting category with id: {}", id);
		repository.deleteById(id);
	}
}
