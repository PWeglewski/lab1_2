package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

/**
 * Created by piotr on 06.04.2016.
 */
public class TaxFactory {
    public Tax getTax(Money net, Taxable item){
        BigDecimal ratio = null;
        String desc = null;

        switch (item.getType()) {
            case DRUG:
                ratio = BigDecimal.valueOf(0.05);
                desc = "5% (D)";
                break;
            case FOOD:
                ratio = BigDecimal.valueOf(0.07);
                desc = "7% (F)";
                break;
            case STANDARD:
                ratio = BigDecimal.valueOf(0.23);
                desc = "23%";
                break;

            default:
                throw new IllegalArgumentException(item.getType() + " not handled");
        }

        Money taxValue = net.multiplyBy(ratio);

        return new Tax(taxValue, desc);
    }
}
