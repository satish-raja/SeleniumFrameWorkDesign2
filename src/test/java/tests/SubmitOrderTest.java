package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest2;

	public class SubmitOrderTest extends BaseTest2 {
				
			@Test(dataProvider = "getdata",groups= {"Purchase"})
			public void SubmitOrder(HashMap<String, String > input) throws IOException, InterruptedException
				{
					Thread.sleep(15000);
					ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("userId"),input.get("password"));
					productCatalogue.addProductToCart(input.get("productName"));
					CartPage cartPage = productCatalogue.goToCart();
					Boolean match = cartPage.VerifyProductdisplay(input.get("productName"));
					Assert.assertTrue(match);
					PaymentPage paymentPage = cartPage.goToCheckOut();
					paymentPage.selectCountry("India");		
					OrderConfirmationPage orderConfirmationPage = paymentPage.submitPayment();
					String confirmation = orderConfirmationPage.getOrderSubmissionMessage();
					Assert.assertTrue(confirmation.equalsIgnoreCase("THANKYOU FOR THE ORdER."));
					String orderId = orderConfirmationPage.getOrderId();
					System.out.println("Successfully Submitted Order. Order ID :"+orderId);
					orderConfirmationPage.SignOut();
				}
		
	}