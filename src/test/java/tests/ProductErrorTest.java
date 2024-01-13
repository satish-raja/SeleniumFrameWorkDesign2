package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest2;

public class ProductErrorTest extends BaseTest2 {
	String productName = "ADIDAS ORIGINAL";
	@Test(groups = {"ErrorHandling"} , retryAnalyzer = testComponents.Retry.class)
	public void ProductErrorValidation() throws InterruptedException {
		Thread.sleep(15000);
		ProductCatalogue productCatalogue = landingPage.LoginApplication("anshika@gmail.com","Iamking@000");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.VerifyProductdisplay(productName);
		Assert.assertTrue(match);
		System.out.println("Product Error Validation Completed");
	}

}