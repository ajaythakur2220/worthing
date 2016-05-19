package worthing;

import worthing.product.OfferType;
import worthing.product.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface that defines the method for calculating the total amount for all the products purchased
 */
public interface CheckOutService {

    BigDecimal checkOutAmount(List<Product> items, List<OfferType> offers);

}
