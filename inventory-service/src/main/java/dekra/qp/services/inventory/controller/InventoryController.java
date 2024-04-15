package dekra.qp.services.inventory.controller;

import java.util.List;

import dekra.qp.services.inventory.client.CategoryClient;
import dekra.qp.services.inventory.client.ProductClient;
import dekra.qp.services.inventory.dto.CategoryDTO;
import dekra.qp.services.inventory.cache.CacheAuxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dekra.qp.services.inventory.model.Inventory;
import dekra.qp.services.inventory.repository.InventoryRepository;

@RestController
public class InventoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired
	InventoryRepository repository;
	@Autowired
	CategoryClient categoryClient;
	@Autowired
	ProductClient productClient;
	@Autowired
	CacheAuxService cacheAuxService;
	
	@GetMapping("/")
	@Cacheable("allInventories")
	public List<Inventory> findAll() {
		LOGGER.info("Inventory find");
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Inventory> findById(@PathVariable Long id) {
		LOGGER.info("Inventory find: id={}", id);
		Inventory inventory = cacheAuxService.findCachedInventoryById(id);
		if (inventory != null) {
			return ResponseEntity.ok(inventory);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/{id}/with-categories")
	@Cacheable(value = "inventoryComplexDetails", key = "'categories-' + #id")
	public Inventory findByIdWithCategories(@PathVariable Long id) {
		LOGGER.info("Inventory find: id={}", id);
		Inventory inventory = repository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
		inventory.setCategories(categoryClient.findByInventory(inventory.getId()));
		return inventory;
	}

	@GetMapping("/{id}/with-categories-and-products")
	@Cacheable(value = "inventoryComplexDetails", key = "'categoriesProducts-' + #id")
	public Inventory findByIdWithCategoriesAndProducts(@PathVariable Long id) {
		LOGGER.info("Inventory find: id={}", id);
		Inventory inventory = repository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
		// Assuming categoryClient.findByInventoryWithProducts() retrieves categories with their products populated
		List<CategoryDTO> categories = categoryClient.findByInventoryWithProducts(inventory.getId());
		inventory.setCategories(categories);
		return inventory;
	}

	@GetMapping("/{id}/with-products")
	@Cacheable(value = "inventoryComplexDetails", key = "'products-' + #id")
	public Inventory findByIdWithProducts(@PathVariable Long id) {
		LOGGER.info("Inventory find: id={}", id);
		Inventory inventory = repository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
		inventory.setProducts(productClient.findByInventory(inventory.getId()));
		return inventory;
	}

	@PostMapping("/")
	@CacheEvict(value = {"allInventories", "inventoryDetails", "inventoryComplexDetails"}, allEntries = true)
	public Inventory add(@RequestBody Inventory inventory) {
		LOGGER.info("Inventory add: {}", inventory);
		return repository.save(inventory);
	}

	@PutMapping("/{id}")
	@CacheEvict(value = {"allInventories", "inventoryDetails", "inventoryComplexDetails"}, allEntries = true)
	public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory inventoryDetails) {
		LOGGER.info("Updating inventory with id: {}", id);
		return repository.findById(id)
				.map(inventory -> {
					inventory.setName(inventoryDetails.getName());
					inventory.setAddress(inventoryDetails.getAddress());
					return repository.save(inventory);
				})
				.orElseThrow(() -> new RuntimeException("Inventory not found"));
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = {"allInventories", "inventoryDetails", "inventoryComplexDetails"}, allEntries = true)
	public void deleteInventory(@PathVariable Long id) {
		LOGGER.info("Deleting inventory with id: {}", id);
		repository.deleteById(id);
	}
	
}
