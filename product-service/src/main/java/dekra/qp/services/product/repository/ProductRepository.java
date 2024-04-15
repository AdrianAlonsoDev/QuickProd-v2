package dekra.qp.services.product.repository;

import java.util.ArrayList;
import java.util.List;

import dekra.qp.services.product.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dekra.qp.services.product.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategoryId(Long categoryId);
	List<Product> findByInventoryId(Long inventoryId);
}

