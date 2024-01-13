package pageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.BasePage;

public class CartPage extends BasePage{
	WebDriver driver ;
	@FindBy(css = "li.totalRow button")
	WebElement checkOut ;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	By paymentPage = By.cssSelector(".payment");
	
	public PaymentPage goToCheckOut()
	{
		checkOut.click();
		waitForElementToAppear(paymentPage);
		return new PaymentPage(driver);
	}
	
	public Boolean VerifyProductdisplay(String productName)
	{
		Boolean match = driver.findElement(By.cssSelector("div.cartSection h3")).getText().equalsIgnoreCase(productName);
		return match;
	}
	
}
