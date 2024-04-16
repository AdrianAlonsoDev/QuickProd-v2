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
			repository.save(new Product(1L, 1L, "Nike Airforce", 50.90,  "Special edition for collectors"));
			repository.save(new Product(1L, 1L, "Adidas Runners", 60.50, "Special edition for collectors"));
			repository.save(new Product(1L, 1L, "Adidas Lightspeed", 85.99, "Super light edition for runners"));
			repository.save(new Product(1L, 1L, "Reebok Runners", 39.15, "Specific design fit"));
			repository.save(new Product(1L, 2L, "Northface basic", 19.99, "Casual style"));
			repository.save(new Product(1L, 2L, "Basic fit", 14.15, "Super comfortable"));
			repository.save(new Product(2L, 3L, "CK SPECIAL", 119.50, "Special edition Calvin Klein"));
			repository.save(new Product(2L, 3L, "Adidas MEGA", 34.55, "Easy to wear"));
			repository.save(new Product(2L, 3L, "Reebok Comfort", 30.80, "Super light"));
			repository.save(new Product(2L, 4L, "Rolex Submarine", 4999.50, "Rolex newest watch edition"));
			repository.save(new Product(2L, 4L, "CK Silver wristband", 230.10, "CK silver wristband"));
		};
	}
	
}
