package utility;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;



public class Listeners extends BaseClass implements ITestListener, IInvokedMethodListener {

	// When Test case get failed, this method is called.
	public void onTestFailure(ITestResult result) {
		logger.info("Test Case Failed: " + result.getName());
		test.log(Status.FAIL, "Testcase failed is : " + result.getName());
	}

	// When Test case get Skipped, this method is called.
	public void onTestSkipped(ITestResult result) {
		logger.info("Test Case Skiped: " + result.getName());
		test.log(Status.SKIP, "The name of the testcase Skipped is : " + result.getName());
	}

	// When Test case get Started, this method is called.
	public void onTestStart(ITestResult result) {
		logger.info("Test Case Started: " + result.getName());
		test = report.createTest(result.getName());
		test.log(Status.INFO, result.getName() + " test case started");
	}

	// When Test case get passed, this method is called.
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Case Passed: " + result.getName());
		test.log(Status.PASS, "The name of the testcase passed is : " + result.getName());
	}

	public void onFinish(ITestContext arg0) {
		driver.quit();
		test = report.createTest("Code Coverage Report");
		test.log(Status.INFO,"Code Coverage Link : <a href='file:///D:/Softwares/Jacoco/index.html'>Code Coverage Link</a>");
		report.flush();
	}

	public void onStart(ITestContext arg0) {
		report = ExtentManager.getInstance();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

	}

	public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
		
	}
}
