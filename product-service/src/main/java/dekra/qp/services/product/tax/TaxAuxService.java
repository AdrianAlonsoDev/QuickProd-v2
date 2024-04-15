package dekra.qp.services.product.tax;

import dekra.qp.services.product.model.Product;
import dekra.qp.services.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class TaxAuxService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaxCalculator taxCalculator;

    //TO-DO Improve this (Events?)
    public String calculatePriceAfterTax(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        double tax = taxCalculator.calculateTax(product.getPrice());
        product.setPriceAfterTax(product.getPrice() + tax);
        return "Price after tax: " + product.getPriceAfterTax();
    }
}
