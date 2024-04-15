package dekra.qp.services.category.client;

import java.util.List;

import dekra.qp.services.category.config.FeignClientConfig;
import dekra.qp.services.category.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", configuration = { FeignClientConfig.class })
public interface ProductClient {

	@GetMapping("/category/{categoryId}")
	List<ProductDTO> findByCategory(@PathVariable("categoryId") Long categoryId);
	
}
