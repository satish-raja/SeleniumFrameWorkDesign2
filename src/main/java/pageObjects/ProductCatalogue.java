package pageObjects;        
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.BasePage;

public class ProductCatalogue extends BasePage
	{
	WebDriver driver ;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	By toastMessage = By.id("toast-container");
	By productCatalogue = By.tagName("app-dashboard");
	public void addProductToCart(String productName )
	{
		waitForElementToAppear(productCatalogue);
		driver.findElement(By.xpath("//b[text()='" + productName + "']/parent::h5/following-sibling::button[2]")).click();
		waitForElementToAppear(toastMessage);
		waitForElementTodisappear(toastMessage);
	}
}