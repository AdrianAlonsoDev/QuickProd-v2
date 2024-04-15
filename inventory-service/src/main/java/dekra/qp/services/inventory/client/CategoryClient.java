package dekra.qp.services.inventory.client;

import java.util.List;

import dekra.qp.services.inventory.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dekra.qp.services.inventory.dto.CategoryDTO;

/**
 *  This interface is used to define the methods that will
 *  be used to communicate with the category-service
 */
@FeignClient(name = "category-service", configuration = { FeignClientConfig.class })
public interface CategoryClient {

	@GetMapping("/inventory/{inventoryId}")
    List<CategoryDTO> findByInventory(@PathVariable("inventoryId") Long inventoryId);
	
	@GetMapping("/inventory/{inventoryId}/with-products")
    List<CategoryDTO> findByInventoryWithProducts(@PathVariable("inventoryId") Long inventoryId);
	
}
