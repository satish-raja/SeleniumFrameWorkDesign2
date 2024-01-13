package stepDefinitions;

import java.io.IOException;
import java.net.URISyntaxException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.LandingPage;
import pageObjects.OrderConfirmationPage;
import pageObjects.PaymentPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest2;

public class StepDefinitionImplmnt extends BaseTest2 {
	public LandingPage landingPage ;
	public ProductCatalogue productCatalogue ;
	public OrderConfirmationPage orderConfirmationPage ;
	public String confirmation ;
	public CartPage cartPage ;
	public PaymentPage paymentPage ;
	
	@Given( "I landed on Ecommerce Page" )
	public void I_Landed_On_Ecomeerce_Page() throws IOException, URISyntaxException {
		landingPage = launchApplication();
	}
	
	@Given ( "^Logged in with UserName (.+) and Password (.+)$" )
	public void Logged_In_UserName_Password( String userName , String userPassword ) throws InterruptedException {
		productCatalogue = landingPage.LoginApplication(userName, userPassword);
	}
	
    @When ( "^I add product (.+) to cart$" )
    public void I_Add_Product_To_Cart( String productName ) {
    	productCatalogue.addProductToCart(productName);
    }
	    
	@When ( "^Checkout (.+) and submit the order$" )
	public void Checkout_Added_Product_From_Cart_Submit_Order( String productName ) {
		cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.VerifyProductdisplay(productName);
		Assert.assertTrue(match);
		paymentPage = cartPage.goToCheckOut();
		paymentPage.selectCountry("India");		
		orderConfirmationPage = paymentPage.submitPayment();
	}
	
	@Then ( "{string} message is displayed on the Order Confirmation Page" )
	public void Message_displayed_OrderConfirmationPage( String string1) {
		String confirmation = orderConfirmationPage.getOrderSubmissionMessage();
		Assert.assertTrue(confirmation.equalsIgnoreCase(string1));
		driver.close();
	}

	@Then ( "{string} message is displayed on the Login Page" )
	public void Message_displayed_LoginPage( String string2) {
		Assert.assertEquals(string2, landingPage.getLoginErrorMessage());
		driver.close();
	}
	
}