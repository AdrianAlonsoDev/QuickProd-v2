package dekra.qp.services.category.cache;

import dekra.qp.services.category.model.Category;
import dekra.qp.services.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheAuxService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Cacheable(value = "categoryDetails", key = "#id")
    public Category findCachedCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
