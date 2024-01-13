package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest2  implements ITestListener {
	ExtentTest test ;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal < ExtentTest > extentTest = new ThreadLocal< ExtentTest >();
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("ITestListener - onTestStart");
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	  }
	@Override
	  public void onTestSuccess(ITestResult result) {
		System.out.println("ITestListener - onTestSuccess");
		extentTest.get().log(Status.PASS, "Results are as expected.");
	  }
	@Override
	  public void onTestFailure(ITestResult result) {
		System.out.println("ITestListener - onTestFailure");		
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String filePath = null;
		try {
			filePath = getScreenshot(  result.getMethod().getMethodName() , driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }
	@Override
	  public void onTestSkipped(ITestResult result) {
		System.out.println("ITestListener - onTestSkipped");
	    // not implemented
	  }
	@Override
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("ITestListener - onTestFailedButWithinSuccessPercentage");
	    // not implemented
	  }
	@Override
	  public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("ITestListener - onTestFailedWithTimeout");
	    onTestFailure(result);
	  }
	@Override
	  public void onStart(ITestContext context) {
		System.out.println("ITestListener - onStart");
	    // not implemented
	  }
	@Override
	  public void onFinish(ITestContext context) {
		System.out.println("ITestListener - onFinish");
		extent.flush();
	  }

}
