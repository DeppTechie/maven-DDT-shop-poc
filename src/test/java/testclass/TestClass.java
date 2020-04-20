/* Requirement : As a client I would like to add two products to the shopping basket
 * TestClass contains two test cases as below
 * 1) addProductsToShoppingCart() : To add the products to the shopping cart
 * Test case reads data from DataProviderClass
 * 2) validateShoppingCartItems() : To validate the products in shopping cart
 */

package testclass;


import org.testng.annotations.Test;
import basicsetup.BaseClass;
import pageobjects.AddToShoppingCartPage;

import java.io.IOException;

public class TestClass extends BaseClass {


    @Test(priority = 1, dataProvider = "productData", dataProviderClass = DataProviderClass.class)
    public void addProductsToShoppingCart(String product) throws IOException {
        AddToShoppingCartPage.addProductToShoppingCart(driver, product);
    }


//    @Test(priority = 2)
//    public void validateShoppingCartItems() throws IOException {
//        AddToShoppingCartPage.validateShoppingCart(driver);
//    }

}

