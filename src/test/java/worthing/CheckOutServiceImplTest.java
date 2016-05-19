package worthing;

import junit.framework.TestCase;
import worthing.product.Product;
import worthing.product.ProductType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Class for CheckOutServiceImpl
 */
public class CheckOutServiceImplTest extends TestCase {

    private CheckOutServiceImpl checkOut = new CheckOutServiceImpl();

    /**
     *  Test calculates amount for apples without any offer
     * @throws Exception
     */
    public void testApplesNoOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        Product product1 = new Product(ProductType.APPLE, BigDecimal.valueOf(0.60));

        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);
        allApples.add(product1);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, null);
        assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(3.6), actualAmount);
    }

    /**
     * Test calculates amount for apples with the offer
     * @throws Exception
     */
    public void testApplesOffer() throws Exception {

    }

    /**
     * Test calculates amount for apples and oranges without the offer
     * @throws Exception
     */
    public void testApplesOrangesNoOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        Product product1 = new Product(ProductType.APPLE, BigDecimal.valueOf(0.60));
        Product product2 = new Product(ProductType.ORANGE, BigDecimal.valueOf(0.25));

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
        assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(4.60).setScale(2), actualAmount);
    }

    /**
     * Test calculates amount for apples and oranges with the offer
     * @throws Exception
     */
    public void testApplesOrangesOffer() throws Exception {

    }

    /**
     * Test calculates amount for oranges without the offer
     * @throws Exception
     */
    public void testOrangesNoOffer() throws Exception {
        List<Product> allApples = new ArrayList<Product>();
        Product product2 = new Product(ProductType.ORANGE, BigDecimal.valueOf(0.25));

        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);
        allApples.add(product2);

        BigDecimal actualAmount = checkOut.checkOutAmount(allApples, null);
        assertEquals("The actual amount is not matching with expected amount", BigDecimal.valueOf(1.00).setScale(2), actualAmount);
    }

    /**
     * Test calculates amount for oranges with the offer
     * @throws Exception
     */
    public void testOrangesOffer() throws Exception {

    }
}
