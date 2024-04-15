package dekra.qp.services.product.tax.impl;

import dekra.qp.services.product.tax.TaxCalculator;

public class TaxCalculatorITBIS implements TaxCalculator {
    private static final double ITBIS_RATE = 0.18; // 18%

    @Override
    public double calculateTax(double price) {
        return price * ITBIS_RATE;
    }
}
