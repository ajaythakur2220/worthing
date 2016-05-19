package worthing;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import worthing.product.OfferType;
import worthing.product.Product;
import worthing.product.ProductType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Class for CheckOutServiceImpl
 */
public class CheckOutServiceImplTest{

    // Default Value
    private static final BigDecimal APPLE_PRICE = BigDecimal.valueOf(0.60);
    // Default Value
    private static final BigDecimal ORANGE_PRICE = BigDecimal.valueOf(0.25);

    private static CheckOutServiceImpl checkOut = null;

    @BeforeClass
    public static void oneTimeSetUp() {
        checkOut = new CheckOutServiceImpl();
    }
    /**
     *  Test calculates amount for apples without any offer
     * @throws Exception
     */
    @Test
    public void testApplesNoOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        Product product1 = new Product(ProductType.APPLE, APPLE_PRICE);

        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, null);
        Assert.assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(3.6), actualAmount);
    }

    @Test
    public void testEmptyList() throws Exception {
        List<Product> allApples = new ArrayList<Product>();

        try{
            checkOut.checkOutAmount(allApples, null);
            Assert.fail("This line should not be executed");
        }catch (IllegalArgumentException e){

        }

    }

    /**
     * Test calculates amount for apples with the offer
     * @throws Exception
     */
    @Test
    public void testApplesOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        List<OfferType> offerTypes = new ArrayList<OfferType>();
        offerTypes.add(OfferType.APPLE_ONE_PLUS_ONE);
        Product product1 = new Product(ProductType.APPLE, APPLE_PRICE);

        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, offerTypes);
        Assert.assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(1.8), actualAmount);

    }

    /**
     * Test calculates amount for apples and oranges without the offer
     * @throws Exception
     */
    @Test
    public void testApplesOrangesNoOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        Product product1 = new Product(ProductType.APPLE, APPLE_PRICE);
        Product product2 = new Product(ProductType.ORANGE, ORANGE_PRICE);

        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, null);
        Assert.assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(4.60).setScale(2), actualAmount);
    }

    /**
     * Test calculates amount for apples and oranges with the offer
     * @throws Exception
     */
    @Test
    public void testApplesOrangesOffer() throws Exception {

        List<Product> allApples = new ArrayList<Product>();
        List<OfferType> offerTypes = new ArrayList<OfferType>();
        offerTypes.add(OfferType.APPLE_ONE_PLUS_ONE);
        offerTypes.add(OfferType.ORANGE_THREE_FOR_TWO);

        Product product1 = new Product(ProductType.APPLE, APPLE_PRICE);
        Product product2 = new Product(ProductType.ORANGE, ORANGE_PRICE);

        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, offerTypes);
        Assert.assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(2.55), actualAmount);
    }

    /**
     * Test calculates amount for oranges without the offer
     * @throws Exception
     */
    @Test
    public void testOrangesNoOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        Product product2 = new Product(ProductType.ORANGE, ORANGE_PRICE);

        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, null);
        Assert.assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(1.00).setScale(2), actualAmount);
    }

    /**
     * Test calculates amount for oranges with the offer
     * @throws Exception
     */
    @Test
    public void testOrangesOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        List<OfferType> offerTypes = new ArrayList<OfferType>();
        offerTypes.add(OfferType.ORANGE_THREE_FOR_TWO);

        Product product2 = new Product(ProductType.ORANGE, ORANGE_PRICE);

        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, offerTypes);
        Assert.assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(0.75), actualAmount);

    }
}
