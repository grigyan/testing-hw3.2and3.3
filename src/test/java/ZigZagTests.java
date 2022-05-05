import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ZigZagCatalogPage;
import pages.ZigZagMainPage;
import pages.ZigZagSingleProductPage;

import java.text.ParseException;

import static constants.messages.ErrorMessages.NO_SEARCH_RESULTS;
import static constants.messages.ErrorMessages.SUCCESSFULL_SEARCH;

public class ZigZagTests extends BaseTest {
    @Test
    void shouldNotFindSearch() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("shaurma");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        Assert.assertTrue(catalogPage.isCatalogEmpty(), NO_SEARCH_RESULTS);
    }
    
    @Test
    void shouldFindSearch() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        Assert.assertTrue(catalogPage.getCatalogSize() != 0, SUCCESSFULL_SEARCH);
    }
    
    @Test
    void shouldAddToTheCart() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        ZigZagSingleProductPage productPage = catalogPage.clickCatalogItem(0);
        productPage.addItemToCart();
        productPage.waitUntilItemIsInTheCart();
        
        Assert.assertTrue(productPage.getCartItemsCount() == 1);
    }
    
    @Test
    void shouldGetSamePrice() throws ParseException {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        var catalogItemPrice = catalogPage.getCatalogItemPrice(0);
        ZigZagSingleProductPage productPage = catalogPage.clickCatalogItem(0);
        var productPrice = productPage.getProductPrice();
        Assert.assertTrue(catalogItemPrice == productPrice);
    }
    
    @Test
    void shouldFail() {
        ZigZagMainPage mainPage = new ZigZagMainPage(driver);
        mainPage.searchItemWithName("iphone");
        ZigZagCatalogPage catalogPage = mainPage.clickSearchButton();
        ZigZagSingleProductPage productPage = catalogPage.clickCatalogItem(0);
        productPage.addItemToCart();
        productPage.waitUntilItemIsInTheCart();

        Assert.assertTrue(productPage.getCartItemsCount() == 2);
    }
    
    


}
