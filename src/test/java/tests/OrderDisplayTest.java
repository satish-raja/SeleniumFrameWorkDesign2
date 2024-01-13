package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.OrdersPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest2;

public class OrderDisplayTest extends BaseTest2 {
	@Test(dataProvider = "getdata")
	public void VerifyOrderdisplay(HashMap<String, String > input) throws IOException, InterruptedException {
			Thread.sleep(15000);
			ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("userId"),input.get("password"));
			OrdersPage ordersPage = productCatalogue.goToOrdersPage();
			Assert.assertTrue(ordersPage.VerifyOrderdisplay(input.get("productName")));
			landingPage.SignOut();
			System.out.println("Order Display Verification Completed");
		}
}