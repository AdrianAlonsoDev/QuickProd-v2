package dekra.qp.services.inventory.client;

import java.util.List;

import dekra.qp.services.inventory.config.FeignClientConfig;
import dekra.qp.services.inventory.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *  This interface is used to define the methods that will
 *  be used to communicate with the product-service
 */
@FeignClient(name = "product-service", configuration = { FeignClientConfig.class })
public interface ProductClient {

	@GetMapping("/inventory/{inventoryId}")
	List<ProductDTO> findByInventory(@PathVariable("inventoryId") Long inventoryId);
	
}
