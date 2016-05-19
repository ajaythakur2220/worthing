package worthing;

import worthing.product.OfferType;
import worthing.product.Product;
import worthing.product.ProductType;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static worthing.product.OfferType.APPLE_ONE_PLUS_ONE;
import static worthing.product.OfferType.ORANGE_THREE_FOR_TWO;

/**
 * Class responsible for implementing interface methods
 *  - Responsible for calculating product prices
 *  - Responsible for calculating any special offers
 */
public class CheckOutServiceImpl implements CheckOutService {

    // this value should be configurable either in database or properties file
    private static final BigDecimal APPLE_PRICE = BigDecimal.valueOf(0.60);
    // this value should be configurable either in database or properties file
    private static final BigDecimal ORANGE_PRICE = BigDecimal.valueOf(0.25);


    /**
     * Calculate the product prices and delegates the flow if special offer need to applied
     * @param items list of products in cart
     * @param offers Offers to be applied
     * @return the total amount for all the products
     */
    public BigDecimal checkOutAmount(List<Product> items, List<OfferType> offers) {
        BigDecimal bigDecimal = BigDecimal.ZERO;
        Iterator<Product> itemList = items.iterator();
        int appleCount =0;
        int orangeCount=0;

        while (itemList.hasNext()) {
            Product item = itemList.next();
            if (item.getProductType().equals(ProductType.APPLE)) {
                bigDecimal = bigDecimal.add(item.getPriceInPence());
                appleCount++;
            }else if (item.getProductType().equals(ProductType.ORANGE)) {
                bigDecimal = bigDecimal.add(item.getPriceInPence());
                orangeCount++;
            } else {
               throw new ProuctNotFoundException("Product " + item.getProductType() + " not found");
            }
        }

        // apply any special offer
        if(offers != null && !offers.isEmpty()){
            bigDecimal = specialOffer(appleCount, orangeCount, offers);
        }

        return bigDecimal;
    }


    /* Apply special offers available  */
    private BigDecimal specialOffer(int appleCount, int orangeCount, List<OfferType> offerTypes) {

        BigDecimal appleSum = BigDecimal.ZERO;
        BigDecimal orangeSum = BigDecimal.ZERO;
        Iterator<OfferType> offerType = offerTypes.iterator();

        switch (offerType.next()){
            case APPLE_ONE_PLUS_ONE:
                appleSum = getOnePlusOneOffer(appleCount);
                break;
            case ORANGE_THREE_FOR_TWO:
                orangeSum = getThreeForTwoOffer(orangeCount);
                break;
        }

        return appleSum.add(orangeSum);
    }

    /** Get Three for Two price offer*/
    private BigDecimal getThreeForTwoOffer(int orangeCount) {
        return BigDecimal.valueOf(orangeCount % 3).multiply(ORANGE_PRICE)
                .add(BigDecimal.valueOf(orangeCount / 3).multiply(ORANGE_PRICE).multiply(BigDecimal.valueOf(2)));
    }

    /** Get One Plus one price Offer*/
    private BigDecimal getOnePlusOneOffer(int appleCount) {
        return BigDecimal.valueOf(appleCount % 2).multiply(APPLE_PRICE)
                .add(BigDecimal.valueOf(appleCount / 2).multiply(APPLE_PRICE));
    }
}
