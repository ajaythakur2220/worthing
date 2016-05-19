package worthing;

import worthing.product.OfferType;
import worthing.product.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface that defines Check Out services.
 */
public interface CheckOutService {

    /**
     * Calculates the checkout amount
     * @param items items for which sum to be calculated
     * @param offers offers available
     * @return the total amount
     * @throws ProductNotFoundException exception
     */
    BigDecimal checkOutAmount(final List<Product> items, final List<OfferType> offers) throws ProductNotFoundException;

}
