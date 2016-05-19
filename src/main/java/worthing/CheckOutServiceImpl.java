package worthing;

import worthing.product.OfferType;
import worthing.product.Product;
import worthing.product.ProductType;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * Class responsible for implementing interface methods
 *  - Responsible for calculating product prices
 *  - Responsible for calculating any special offers
 */
public class CheckOutServiceImpl implements CheckOutService {

    // Default Value
    private static BigDecimal APPLE_PRICE = BigDecimal.valueOf(0.60);
    // Default Value
    private static BigDecimal ORANGE_PRICE = BigDecimal.valueOf(0.25);


    /**
     * Calculate the product prices and delegates the flow if special offer need to applied
     * @param items list of products in cart
     * @param offers Offers to be applied
     * @return the total amount for all the products
     *  @throws ProductNotFoundException exceptions
     */
    public BigDecimal checkOutAmount(final List<Product> items, final List<OfferType> offers) throws ProductNotFoundException {
        if(null == items || items.isEmpty()){
            throw new IllegalArgumentException(" Item list is empty or null ! ");
        }

        BigDecimal bigDecimal = BigDecimal.ZERO;
        Iterator<Product> itemList = items.iterator();
        int appleCount =0;
        int orangeCount=0;
        boolean applePriceSet = true;
        boolean orangePriceSet = true;

        while (itemList.hasNext()) {
            Product item = itemList.next();
            if (ProductType.APPLE.equals(item.getProductType())) {
                bigDecimal = bigDecimal.add(item.getPriceInPence());
                //Set the price once
                if(applePriceSet){
                    APPLE_PRICE = item.getPriceInPence();
                    applePriceSet = false;

                }
                appleCount++;

            } else if (ProductType.ORANGE.equals(item.getProductType())) {
                bigDecimal = bigDecimal.add(item.getPriceInPence());
                //Set the price once
                if(orangePriceSet){
                    ORANGE_PRICE = item.getPriceInPence();
                    orangePriceSet = false;
                }
                orangeCount++;
            } else {
               throw new ProductNotFoundException("Product " + item.getProductType() + " not found");
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

        Iterator<OfferType> offerTypeIterator = offerTypes.iterator();
        BigDecimal appleSum = BigDecimal.ZERO;
        BigDecimal orangeSum = BigDecimal.ZERO;

        while (offerTypeIterator.hasNext()) {
            OfferType offerType = offerTypeIterator.next();
            switch (offerType) {
                case APPLE_ONE_PLUS_ONE:
                    appleSum = getOnePlusOneOffer(appleCount);
                    break;
                case ORANGE_THREE_FOR_TWO:
                    orangeSum = getThreeForTwoOffer(orangeCount);
                    break;
            }
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
