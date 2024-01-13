package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest2;

public class LoginErrorMessageTest extends BaseTest2 {
	@Test(groups = {"ErrorHandling"} , retryAnalyzer = testComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		Thread.sleep(15000);
		landingPage.LoginApplication("satisraja@gmail.com", "Raja1234");
		Assert.assertEquals("Incorrect email or password.", landingPage.getLoginErrorMessage());
	}

}
