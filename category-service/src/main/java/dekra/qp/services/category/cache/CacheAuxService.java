package dekra.qp.services.category.cache;

import dekra.qp.services.category.model.Category;
import dekra.qp.services.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Axuliary service for caching category details
 * as ResponseEnttity cannot be  serialized and cached
 */
@Service
public class CacheAuxService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CacheAuxService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Cacheable(value = "categoryDetails", key = "#id")
    public Category findCachedCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
