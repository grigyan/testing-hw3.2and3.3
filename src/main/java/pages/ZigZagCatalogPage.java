package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static constants.locators.ZigZagCatalogPageLocators.*;

public class ZigZagCatalogPage extends BasePage {
    private NumberFormat format = NumberFormat.getInstance(Locale.US);
    private By emptyCatalogMessage = By.cssSelector(EMPTY_CATALOG);
    private By catalogItems = By.className(PRODUCT_BLOCK);
    private By catalogItemPrice = By.className(PRODUCT_PRICE);
    public ZigZagCatalogPage(WebDriver driver) {
        super(driver);
    }

    public int getCatalogSize() {
        return getElements(catalogItems).size();
    }

    public int getCatalogItemPrice(int index) throws ParseException {
        String price = getText(catalogItemPrice, index);
        return format.parse(price).intValue();
    }

    public ZigZagSingleProductPage clickCatalogItem(int itemIndex) {
        click(catalogItems, itemIndex);
        return new ZigZagSingleProductPage(driver);
    }

    public boolean isCatalogEmpty() {
        return isElementDisplayed(emptyCatalogMessage);
    }

}
