package worthing.product;

import java.math.BigDecimal;

/**
 * Entity Class for Product. This can be extended by all the products with more specific features
 */
public class Product {

    private ProductType productType;

    private BigDecimal priceInPence;

    /**
     *
     * @param productType
     * @param priceInPence
     */
    public Product(ProductType productType, BigDecimal priceInPence) {
        this.productType = productType;
        this.priceInPence = priceInPence;
    }

    /**
     *
     * @return
     */
    public ProductType getProductType() {
        return productType;
    }

    public BigDecimal getPriceInPence() {
        return priceInPence;
    }
}
