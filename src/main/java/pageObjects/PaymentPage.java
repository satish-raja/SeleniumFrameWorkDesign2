package pageObjects;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.BasePage;

public class PaymentPage extends BasePage{
	WebDriver driver ;
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement selCountry ;
	
	@FindBy(css = "*.ta-results button")
	List <WebElement> countries ;

	
	@FindBy(css = "a[class*='submit']")
	WebElement submit ;
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	public void selectCountry(String countryName)
	{
		selCountry.sendKeys("ind");
		
		for (WebElement country : countries) {
		if (country.getText().equalsIgnoreCase(countryName)) {
			country.click();
			break;
		}
	}
		

	}
	By orderConfirmationPage = By.cssSelector(".em-spacer-1");
	public OrderConfirmationPage submitPayment()
	{
		submit.click();
		waitForElementToAppear(orderConfirmationPage);
		return new OrderConfirmationPage(driver);
	}
}
