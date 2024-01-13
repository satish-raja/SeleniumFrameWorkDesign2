package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;

public class BaseTest2 {
	
	public WebDriver driver ;
	public LandingPage landingPage ;
	
	public WebDriver initializedriver() throws IOException, URISyntaxException	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream (System.getProperty("user.dir")+"\\src\\main\\java\\resources\\Globaldata.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		DesiredCapabilities dc = new DesiredCapabilities();
		if ( browserName.contains("chrome"))
		{
			dc.setBrowserName("chrome");
		}
		else if( browserName.equalsIgnoreCase("firefox")) 
		{
			dc.setBrowserName("firefox");
		}
		else if( browserName.equalsIgnoreCase("edge")) 
		{
			dc.setBrowserName("MicrosoftEdge");
		}
		WebDriver driver = new RemoteWebDriver(new URI("http://localhost:4444").toURL(), dc);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getJsondataToMap(String filePath) throws IOException
	{
		// Read json to string
	String jsonContent =	FileUtils.readFileToString(new File( filePath ), StandardCharsets.UTF_8);
		// String to HashTable Jackson dataBind
	ObjectMapper mapper = new ObjectMapper();
	List < HashMap < String, String>> data = mapper.readValue(   jsonContent, new TypeReference< List < HashMap< String, String>>>(){}   );
		// { map, map }
	return data;
	
	}
	
	@BeforeClass(alwaysRun = true)
	public LandingPage launchApplication() throws IOException, URISyntaxException
	{
			driver = initializedriver();
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		
	}
	
	@AfterClass(alwaysRun = true)
	public void teardown()
	{
		driver.quit();
	}
	
	public String getScreenshot( String testCaseName, WebDriver driver ) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver ;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File( System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png" ;
	}
	
	@DataProvider
	public Object[][] getdata() throws IOException
	{
		List < HashMap < String, String>> data = getJsondataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object [] [] { { data.get(0) }, { data.get(1) } } ;
	}
}