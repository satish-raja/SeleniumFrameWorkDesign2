package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.BasePage;

public class LandingPage extends BasePage	{
	WebDriver driver ;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".login-title")
	WebElement LoginSection ;
	
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;
	
	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='toast-error']")
	WebElement loginErrorMessage;
	
	@FindBy(css = ".fa.fa-sign-out")
	WebElement signOut ;	
	
	public ProductCatalogue LoginApplication(String email, String password) throws InterruptedException
	{
		waitForWebElementToAppear(LoginSection);
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		Thread.sleep(3000);
		submit.click();
		return new ProductCatalogue(driver);
	}
	
		public String getLoginErrorMessage()
	{
		waitForWebElementToAppear(loginErrorMessage);
		return loginErrorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void SignOut() {
		signOut.click();
	}
}
