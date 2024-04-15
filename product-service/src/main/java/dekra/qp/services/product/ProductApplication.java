package dekra.qp.services.product;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import dekra.qp.services.product.model.Product;
import dekra.qp.services.product.repository.ProductRepository;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServletBearerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Random;

@SpringBootApplication
@EnableCaching
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	private static final String[] NAMES = {"Nike Airforce", "Adidas Lightspeed", "Reebok Runners", "Northface Basic", "Basic Fit", "CK SPECIAL", "Adidas MEGA", "Reebok Comfort", "Rolex Submarine", "CK Silver Wristband"};
	private static final String[] DESCRIPTIONS = {"Special edition for collectors", "Super light edition for runners", "Specific design fit", "Casual style", "Super comfortable", "Special edition Calvin Klein", "Developer", "Manager", "Rolex newest watch edition", "CK silver wristband"};
	private static final Random random = new Random();
	BigDecimal price = new BigDecimal(0.0);

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
			repository.save(new Product(2L, 3L, "Adidas MEGA", 34.55, "Developer"));
			repository.save(new Product(2L, 3L, "Reebok Comfort", 30.80, "Manager"));
			repository.save(new Product(2L, 4L, "Rolex Submarine", 4999.50, "Rolex newest watch edition"));
			repository.save(new Product(2L, 4L, "CK Silver wristband", 230.10, "CK silver wristband"));

		};
	}
	
}
