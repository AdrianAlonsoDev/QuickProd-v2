package dekra.qp.services.inventory.cache;

import dekra.qp.services.inventory.model.Inventory;
import dekra.qp.services.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheAuxService {

    @Autowired
    private InventoryRepository repository;

    @Cacheable(value = "inventoryDetails", key = "#id")
    public Inventory findCachedInventoryById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
