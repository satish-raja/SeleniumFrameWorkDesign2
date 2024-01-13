package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.BasePage;

public class OrderConfirmationPage extends BasePage{
	WebDriver driver ;
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage ;

	@FindBy(css = "label.ng-star-inserted")
	WebElement orderId ;
	
	@FindBy(css = ".fa.fa-sign-out")
	WebElement signOut ;	
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	public String getOrderSubmissionMessage( )
	{
		return confirmationMessage.getText();
		
	}
	public String getOrderId()
	{
		return orderId.getText().replace("| ", "").replace(" |", "");
	}
	
	public void SignOut() {
		signOut.click();
	}
	
	
}