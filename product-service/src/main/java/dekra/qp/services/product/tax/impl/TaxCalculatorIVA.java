package dekra.qp.services.product.tax.impl;

import dekra.qp.services.product.tax.TaxCalculator;

public class TaxCalculatorIVA implements TaxCalculator {
    private static final double IVA_RATE = 0.21; // 21%

    @Override
    public double calculateTax(double price) {
        return price * IVA_RATE;
    }
}