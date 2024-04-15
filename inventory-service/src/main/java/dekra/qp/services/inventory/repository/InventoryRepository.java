package dekra.qp.services.inventory.repository;

import dekra.qp.services.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
