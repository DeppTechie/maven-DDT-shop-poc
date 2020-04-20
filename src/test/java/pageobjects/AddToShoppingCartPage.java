/*This is the Page Object class, here all the methods are
 * defined to search for different products and add to the shopping cart
 * Even Methods are defined to validate the added items in the shopping cart
 * These methods can be re-used to avoid the code duplication and speed up the automation testing.
 */

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;

public class AddToShoppingCartPage {
    public static WebElement element = null;

    public static void enterProductToSearch(WebDriver driver, String product) {
        element = driver.findElement(By.id("twotabsearchtextbox"));
        element.clear();
        element.sendKeys(product);
    }

    public static void clickSearchButton(WebDriver driver) {
        driver.findElement(By.xpath("//input[@class='nav-input']")).click();
    }
//
//    public static void addToShoppingCart(WebDriver driver) {
//        driver.findElement(By.id("add-to-cart-button")).click();
//    }
//
//    public static void clickOnShoppingCart(WebDriver driver) {
//        driver.findElement(By.id("nav-cart-count")).click();
//    }

    public static void clickOnProceedCheckout(WebDriver driver) {
    //    driver.findElement(By.id("wishListMainButton-announce")).click();

         driver.findElement(By.xpath("//a[@id='wishListMainButton-announce']")).click();

      //  driver.findElement(By.xpath("//input[@name='proceedToCheckout']")).click();
    }


    //Product1: locator for Head first java book search
    public static void clickOnProduct(WebDriver driver) {
       List<WebElement> resultsList = driver.findElements(By.xpath(".//*[starts-with(@class, 'result_')]"));


        for (WebElement result:resultsList) {
            System.out.println(result.getText());

        }




    }


    //	Method to search and add products to shopping cart
    public static void addProductToShoppingCart(WebDriver driver, String product) {
//		To add product : Sony 55-inch TV Set
        Reporter.log("Searching for Sony 55-inch TV Set", true);
        enterProductToSearch(driver, product);
        clickSearchButton(driver);
        clickOnProduct(driver);
      //  addToShoppingCart(driver);
        Reporter.log("Searched Sony 55-inch TV and added to the shopping cart", true);

    }

}






