package dekra.qp.services.category;

import dekra.qp.services.category.model.Category;
import dekra.qp.services.category.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class CategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(dekra.qp.services.category.CategoryApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(CategoryRepository repository) {
		return (args) -> {
			repository.save(new Category(1L, "Footwear"));
			repository.save(new Category(1L, "Apparel"));
			repository.save(new Category(2L, "Hoodies"));
			repository.save(new Category(2L, "Accessories"));
		};
	}
	
}
