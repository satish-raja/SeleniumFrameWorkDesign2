package pageObjects;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.BasePage;

public class OrdersPage extends BasePage{
	WebDriver driver ;
	@FindBy(css = "li.totalRow button")
	WebElement checkOut ;
	
	@FindBy(css = "tr td:nth-child(3)")
	private List <WebElement> productNames ;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyOrderdisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
}
