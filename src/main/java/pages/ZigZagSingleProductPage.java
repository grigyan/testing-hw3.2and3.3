package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static constants.locators.ZigZagSingleProductPageLocators.*;

public class ZigZagSingleProductPage extends BasePage {
    private NumberFormat format = NumberFormat.getInstance(Locale.US);
    private By price = By.className(PRICE);
    private By addToCartButton = By.cssSelector(ADD_TO_CART);
    private By cartItems = By.cssSelector(CART_ITEMS);
    
    public ZigZagSingleProductPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemsCount() {
        String cartItemsCount = getText(cartItems);
        return cartItemsCount.isEmpty() ? 0
                : Integer.parseInt(cartItemsCount);
    }
    
    public int getProductPrice() throws ParseException {
        return format.parse(getElement(price).getText()).intValue();
    }

    public void addItemToCart() {
        click(addToCartButton);
    }

    public void waitUntilItemIsInTheCart() {
        waitForElementToBeClickable(addToCartButton, "Գնել");
    }


}
