package dekra.qp.services.product.tax.impl;

import dekra.qp.services.product.tax.TaxCalculator;

/**
 * Implementation of the TaxCalculator interface
 * that calculates the tax of a product using the ITBIS rate {21%}
 */
public class TaxCalculatorITBIS implements TaxCalculator {
    private static final double ITBIS_RATE = 0.18; // 18%

    @Override
    public double calculateTax(double price) {
        return price * ITBIS_RATE;
    }
}
