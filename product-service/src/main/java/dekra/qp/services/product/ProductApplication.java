package dekra.qp.services.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import dekra.qp.services.product.model.Product;
import dekra.qp.services.product.repository.ProductRepository;

import java.util.List;
import java.util.Random;

/**
 * Main class for the product service
 */
@SpringBootApplication
@EnableCaching
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {

			Random random = new Random();

		List<Long> inventoryIds = List.of(1L, 2L);
		List<Long> categoryIds = List.of(1L, 1L, 2L, 3L, 4L);
		List<String> productNames = List.of("Nike Airforce", "Adidas Runners", "Adidas Lightspeed",
				"Reebok Runners", "Northface basic", "Basic fit",
				"CK SPECIAL", "Adidas MEGA", "Reebok Comfort",
				"Rolex Submarine", "CK Silver wristband");
		List<String> descriptions = List.of("Special edition for collectors", "Super light edition for runners",
				"Specific design fit", "Casual style", "Super comfortable",
				"Special edition Calvin Klein", "Easy to wear", "Rolex newest watch edition",
				"CK silver wristband");
		List<Double> prices = List.of(50.90, 60.50, 85.99, 39.15, 19.99, 14.15, 119.50, 34.55, 30.80, 4999.50, 230.10);

		// Populate the database with random products
		int numberOfProductsToPopulate = 10;

		for (int i = 0; i < numberOfProductsToPopulate; i++) {
			Long inventoryId = inventoryIds.get(random.nextInt(inventoryIds.size()));
			Long categoryId = categoryIds.get(random.nextInt(categoryIds.size()));
			String productName = productNames.get(random.nextInt(productNames.size()));
			String description = descriptions.get(random.nextInt(descriptions.size()));
			double price = prices.get(random.nextInt(prices.size()));

			repository.save(new Product(inventoryId, categoryId, productName, price, description));
		}
	};
	}
	
}
