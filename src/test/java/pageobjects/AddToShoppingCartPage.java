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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import java.util.Random;

public class AddToShoppingCartPage {
    public static WebElement element = null;

    public static void enterProductToSearch(WebDriver driver, String product) {


     String pageName =  driver.getTitle();
        System.out.println("The Name of the Page is -----" + pageName);

        Assert.assertEquals(pageName,"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", "The right page is launched");


        element = driver.findElement(By.id("twotabsearchtextbox"));
        element.clear();
        element.sendKeys(product);
        element.sendKeys(Keys.ENTER);

    }

    public static void clickOnProceedCheckout(WebDriver driver) {



       String ProductTitle = driver.findElement(By.id("productTitle")).getText();
      System.out.println("The Name of the Product Chosen is : " + ProductTitle);

        String ButtonName = driver.findElement(By.id("wishListMainButton-announce")).getText();
        System.out.println("The button to test is : " + ButtonName);
        WebElement AddButton = driver.findElement(By.id("wishListMainButton-announce"));


       // System Testing: Test if Add to Cart Button Exists on page
      Assert.assertEquals(true, AddButton.isDisplayed());


        driver.findElement(By.id("wishListMainButton-announce")).click();
    }

    //Product1: locator for Sony 55 inch TV
    public static void clickOnProduct(WebDriver driver) {

        int searchCnt = driver.findElements(By.cssSelector("div.sg-col-inner > div.a-section.a-spacing-none > h2.a-size-mini.a-spacing-none.a-color-base.s-line-clamp-2 > a.a-link-normal.a-text-normal > span")).size();
        System.out.println(" The total Number of Products for the search is :  " + searchCnt);
        Random rand = new Random();
        int rand_int1 = rand.nextInt(searchCnt);
     //   driver.findElement(By.cssSelector("div.sg-col-inner > div.a-section.a-spacing-none > h2.a-size-mini.a-spacing-none.a-color-base.s-line-clamp-2 > a.a-link-normal.a-text-normal > span")).click();

        driver.findElement(By.cssSelector(" a.a-link-normal.a-text-normal > span")).click();


  //     driver.findElement(By.xpath("//div[@class='s-result-list s-search-results sg-row']/div[" + rand_int1 + "]//div[@class='a-section a-spacing-none'][1]")).click();
    }

    public static void clickOnSignIn(WebDriver driver)
    {
        WebElement submitBtn = driver.findElement(By.id("continue"));
        Assert.assertEquals(true, submitBtn.isDisplayed());

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






