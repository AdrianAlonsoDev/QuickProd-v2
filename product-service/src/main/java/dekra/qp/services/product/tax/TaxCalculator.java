package dekra.qp.services.product.tax;

@FunctionalInterface
public interface TaxCalculator {
    double calculateTax(double price);
}