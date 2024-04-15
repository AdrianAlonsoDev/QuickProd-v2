package dekra.qp.services.inventory.cache;

import dekra.qp.services.inventory.model.Inventory;
import dekra.qp.services.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Axuliary service for caching inventory details
    as ResponseEnttity cannot be  serialized and cached
 */
@Service
public class CacheAuxService {

    private final InventoryRepository repository;

    @Autowired
    public CacheAuxService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "inventoryDetails", key = "#id")
    public Inventory findCachedInventoryById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
