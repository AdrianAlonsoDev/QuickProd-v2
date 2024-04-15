package dekra.qp.services.product.repository;

import java.util.List;

import dekra.qp.services.product.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to manage the products
 *
 * @see Product
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	/**
	 * Find all products by category
	 *
	 * @param categoryId the category id
	 * @return the list of products
	 */
	List<Product> findByCategoryId(Long categoryId);

	/**
	 * Find all products by inventory
	 *
	 * @param inventoryId the inventory id
	 * @return the list of products
	 */
	List<Product> findByInventoryId(Long inventoryId);
}

