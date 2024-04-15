package dekra.qp.services.product.tax;

/**
 * Interface to calculate the tax of a product
 */
@FunctionalInterface
public interface TaxCalculator {
    double calculateTax(double price);
}