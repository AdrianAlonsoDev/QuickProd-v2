package dekra.qp.services.inventory.client;

import java.util.List;

import dekra.qp.services.inventory.config.FeignClientConfig;
import dekra.qp.services.inventory.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", configuration = { FeignClientConfig.class })
public interface ProductClient {

	@GetMapping("/inventory/{inventoryId}")
	List<ProductDTO> findByInventory(@PathVariable("inventoryId") Long inventoryId);
	
}
