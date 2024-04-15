package dekra.qp.services.product.config;

import dekra.qp.services.product.tax.TaxCalculator;
import dekra.qp.services.product.tax.impl.TaxCalculatorITBIS;
import dekra.qp.services.product.tax.impl.TaxCalculatorIVA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class TaxConfig {

    @Value("${tax.calculator.type}")
    private String taxCalculatorType;

    @Bean
    public TaxCalculator taxCalculator() {
        if ("ITBIS".equalsIgnoreCase(taxCalculatorType)) {
            return new TaxCalculatorITBIS();
        } else {
            return new TaxCalculatorIVA(); // default
        }
    }
}
