package dekra.qp.services.inventory;

import dekra.qp.services.inventory.model.Inventory;
import dekra.qp.services.inventory.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Main class for the inventory service
 */
@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(InventoryRepository repository) {
		return (args) -> {
			repository.save(new Inventory("Granada Warehouse", "Poligono Industrial Juncaril, Granada, Spain"));
			repository.save(new Inventory("Malaga Warehouse", "Casco Historico, Malaga, Spain"));
		};
	}
	
}
