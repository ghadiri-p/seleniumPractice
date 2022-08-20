package service;

import enums.Locators;
import model.Goods;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;


public class Services {


    /**
     * Takes username, password and driver values as parameters and logs in to the desired site .
     *
     * @param username
     * @param password
     * @param driver
     */
    public void login(String username, String password, WebDriver driver) {

        try {
            driver.get(Locators.WEBSITE_URL.getValue());
            driver.findElement(By.xpath(Locators.ACCOUNT.getValue())).click();
            driver.findElement(By.xpath(Locators.LOGIN.getValue())).click();
            driver.findElement(By.xpath(Locators.USER_NAME.getValue())).sendKeys(username);
            driver.findElement(By.xpath(Locators.PASSWORD.getValue())).sendKeys(password);
            driver.findElement(By.xpath(Locators.LOGIN_BUTTON.getValue())).click();

        }catch (ElementNotInteractableException | NoSuchElementException e) {
            System.out.println(e.getMessage() + "\ncheck your Xpath code.");
        }
    }



    /**
     * First call login() method then call removeCartObject() method and, makes a purchase and puts product(s) on the cart
     * , then checks the cart and adds its inventory information to the list
     * and finally returns this list as the output value of the method.
     *
     * @param username
     * @param pass
     * @param driver
     * @return List of Goods object that contains purchased product and cart object
     */
    public List<Goods> addToCart(String username, String pass, WebDriver driver) {

        List<Goods> goodsList = new ArrayList<>();
        login(username, pass, driver);
        Goods goods = new Goods();
        Goods cartGoods = new Goods();
        WebElement element;

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);

        removeCartObjects(driver);
        try {
            element = driver.findElement(By.xpath(Locators.MENS_LABEL.getValue()));
            action.moveToElement(element).perform();

            driver.findElement(By.xpath(Locators.NEW_ARRIVALS.getValue())).click();

            element = driver.findElement(By.xpath(Locators.PRODUCTS.getValue()));
            js.executeScript("arguments[0].scrollIntoView();", element);

            driver.findElement(By.xpath(Locators.VIEW_DETAIL_BUTTON.getValue())).click();
            js.executeScript("window.scroll(0,200)");

            element = driver.findElement(By.xpath(Locators.KHAKI_BOWERY.getValue()));
            goods.setName(element.getText());

            element = driver.findElement(By.xpath(Locators.COLOR.getValue()));
            action.moveToElement(element).click().perform();
            goods.setColor(element.getAttribute("alt"));

            element = driver.findElement(By.xpath(Locators.SIZE.getValue()));
            action.moveToElement(element).click().perform();
            goods.setSize(element.getText());

            element = driver.findElement(By.xpath(Locators.QTY.getValue()));
            action.moveToElement(element).click().perform();
            goods.setCount(Integer.valueOf(element.getAttribute("value")));

            element = driver.findElement(By.xpath(Locators.PRICE.getValue()));
            goods.setPrice(Double.valueOf(element.getAttribute("innerHTML").replace("$", "")));

            element = driver.findElement(By.xpath(Locators.ADD_TO_CART.getValue()));
            action.moveToElement(element).click().perform();

            if (!driver.getCurrentUrl().contains("/checkout/cart/")) {
                driver.findElement(By.xpath(Locators.CART.getValue())).click();
                driver.findElement(By.name(Locators.VIEW_SHOPPING_CART.getValue())).click();
            }

            element = driver.findElement(By.xpath(Locators.PRODUCT_NAME.getValue()));
            cartGoods.setName(element.getText());

            element = driver.findElement(By.xpath(Locators.PRODUCT_COLOR.getValue()));
            cartGoods.setColor(element.getText());

            element = driver.findElement(By.xpath(Locators.PRODUCT_SIZE.getValue()));
            cartGoods.setSize(element.getText());

            element = driver.findElement(By.xpath(Locators.PRODUCT_PRICE.getValue()));
            cartGoods.setPrice(Double.valueOf(element.getAttribute("innerHTML").replace("$", "")));

            element = driver.findElement(By.xpath(Locators.PRODUCT_COUNT.getValue()));
            cartGoods.setCount(Integer.valueOf(element.getAttribute("value")));

            element = driver.findElement(By.xpath(Locators.PRODUCT_TOTAL_PRICE.getValue()));
            cartGoods.setTotalPrice(Double.valueOf(element.getAttribute("innerHTML").replace("$", "")));
            goodsList.add(goods);
            goodsList.add(cartGoods);
        }catch (Exception e){
            if (e instanceof ElementNotInteractableException
            || e instanceof NoSuchElementException){
                System.out.println(e.getMessage() + "\ncheck your Xpath code.");

            } else if (e instanceof InterruptedException){
                System.out.println(e.getMessage());
            }
        }

        return goodsList;
    }



    /**
     * Checks the cart, if its value is greater than zero
     * , this method removes all purchases it already had.
     *
     * @param driver
     */
    public void removeCartObjects(WebDriver driver) {

        try {
            driver.findElement(By.xpath(Locators.CART.getValue())).click();
            while (Integer.valueOf(driver.findElement(By.xpath(Locators.CART_COUNT.getValue())).getAttribute("innerHTML")) > 0) {
                driver.findElement(By.xpath(Locators.VIEW_SHOPPING_CARTS.getValue())).click();
                driver.findElement(By.xpath(Locators.EMPTY_CART.getValue())).click();

            }
        } catch (ElementNotInteractableException | NoSuchElementException e) {
            System.out.println(e.getMessage() + "\ncheck your Xpath code.");
        }
    }
}