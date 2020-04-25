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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Random;


public class AddToShoppingCartPage {

    WebDriver driver;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement serachBox;

    @FindBy(id = "wishListMainButton-announce")
    private WebElement addButton;

    @FindBy(id = "continue")
    private WebElement submitBtn;


    public AddToShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterProductToSearch(String product) {
        String pageName = driver.getTitle();
        Reporter.log("The Name of the Page Launched is -----" + pageName, true);
        Assert.assertEquals(pageName, "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", "The right page is launched");
        serachBox.sendKeys(product);
        serachBox.sendKeys(Keys.ENTER);

    }

    public void clickOnProceedCheckout() {
        String confirmedProductTitle = driver.findElement(By.id("productTitle")).getText();
        Reporter.log("The Name of the Product on Confirmation Page is : " + confirmedProductTitle, true);


        String buttonName = driver.findElement(By.id("wishListMainButton-announce")).getText();
        Reporter.log("The button to test is : " + buttonName, true);
        WebElement addButton = driver.findElement(By.id("wishListMainButton-announce"));


        // System Testing: Test if Add to Cart Button Exists on page
        Assert.assertEquals(true, addButton.isDisplayed());
        addButton.click();
        // driver.findElement(By.id("wishListMainButton-announce")).click();
    }

    //Product1: locator for Sony 55 inch TV
    public void clickOnProduct() {

        int searchCnt = driver.findElements(By.cssSelector("a.a-link-normal.a-text-normal > span")).size();
        Reporter.log(" The total Number of Products for the search is :  " + searchCnt, true);

        Random rand = new Random();
        int rand_int = rand.nextInt(searchCnt - 1) + 1;
        Reporter.log(" User is selecting the Product number :  " + rand_int, true);

        WebElement searchResult = driver.findElement(By.cssSelector(".s-result-list > div:nth-child(" + rand_int + ")"));
        WebElement productToBeSelected = searchResult.findElement(By.cssSelector("a.a-link-normal.a-text-normal > span"));

        productToBeSelected.click();

    }

    public void clickOnSignIn() {

        Assert.assertEquals(true, submitBtn.isDisplayed());
        Reporter.log("User has arrived on the Sign Page:", true);

    }
}
