package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;

/**
 * Created by piotr on 06.04.2016.
 */
public interface Taxable {
    public ProductType getType();
}
