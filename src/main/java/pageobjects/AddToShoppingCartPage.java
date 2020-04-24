/*This is the Page Object class, here all the methods are
 * defined to search for different products and add to the shopping cart
 * Even Methods are defined to validate the added items in the shopping cart
 * These methods can be re-used to avoid the code duplication and speed up the automation testing.
 */

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Random;


public class AddToShoppingCartPage {
    public static WebElement element = null;

    public static void enterProductToSearch(WebDriver driver, String product) {


        String pageName = driver.getTitle();
        Reporter.log("The Name of the Page Launched is -----" + pageName, true);

        Assert.assertEquals(pageName, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", "The right page is launched");

        element = driver.findElement(By.id("twotabsearchtextbox"));
        element.clear();
        element.sendKeys(product);
        element.sendKeys(Keys.ENTER);

    }

    public static void clickOnProceedCheckout(WebDriver driver) {


        String confirmedProductTitle = driver.findElement(By.id("productTitle")).getText();
        Reporter.log("The Name of the Product on Confirmation Page is : " + confirmedProductTitle, true);


        String buttonName = driver.findElement(By.id("wishListMainButton-announce")).getText();
        Reporter.log("The button to test is : " + buttonName, true);
        WebElement addButton = driver.findElement(By.id("wishListMainButton-announce"));


        // System Testing: Test if Add to Cart Button Exists on page
        Assert.assertEquals(true, addButton.isDisplayed());
        driver.findElement(By.id("wishListMainButton-announce")).click();
    }

    //Product1: locator for Sony 55 inch TV
    public static void clickOnProduct(WebDriver driver) {

        int searchCnt = driver.findElements(By.cssSelector("a.a-link-normal.a-text-normal > span")).size();
        Reporter.log(" The total Number of Products for the search is :  " + searchCnt, true);

        Random rand = new Random();
        int rand_int1 = rand.nextInt(searchCnt);
        Reporter.log(" User is selecting the Product number :  " + rand_int1, true);

        WebElement searchResult = driver.findElement(By.cssSelector(".s-result-list > div:nth-child(" + rand_int1 + ")"));
        WebElement productToBeSelected = searchResult.findElement(By.cssSelector("a.a-link-normal.a-text-normal > span"));

        productToBeSelected.click();


    }

    public static void clickOnSignIn(WebDriver driver) {
        WebElement submitBtn = driver.findElement(By.id("continue"));
        Assert.assertEquals(true, submitBtn.isDisplayed());
        Reporter.log("User has arrived on the Sign Page:" ,true);

    }


    //	Method to search and add products to shopping cart
    public static void addProductToShoppingCart(WebDriver driver, String product) {

//		To add product : Sony 55-inch TV Set
        Reporter.log("Searching for Product Interested to Shop", true);
        enterProductToSearch(driver, product);
        clickOnProduct(driver);
        Reporter.log("Searched Product Interested to Shop and added to the shopping cart", true);
        clickOnProceedCheckout(driver);

        clickOnSignIn(driver);
    }

}






