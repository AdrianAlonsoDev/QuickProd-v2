package dekra.qp.services.inventory.client;

import java.util.List;

import dekra.qp.services.inventory.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dekra.qp.services.inventory.dto.CategoryDTO;

@FeignClient(name = "category-service", configuration = { FeignClientConfig.class })
public interface CategoryClient {

	@GetMapping("/inventory/{inventoryId}")
	public List<CategoryDTO> findByInventory(@PathVariable("inventoryId") Long inventoryId);
	
	@GetMapping("/inventory/{inventoryId}/with-products")
	public List<CategoryDTO> findByInventoryWithProducts(@PathVariable("inventoryId") Long inventoryId);
	
}
