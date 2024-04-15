package dekra.qp.services.category.repository;

import dekra.qp.services.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByInventoryId(Long inventoryId);
}
