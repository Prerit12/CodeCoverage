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
		try {
			driver.quit();
			String[] command = { "cmd.exe", "/C", "Start", "tomcatstop.bat" };
			Runtime.getRuntime().exec(command);
			FunctionsClass.sleep(5);
			String[] commandAntBuild = { "cmd.exe", "/C", "Start", "antBuild.bat" };
			Runtime.getRuntime().exec(commandAntBuild);
			FunctionsClass.sleep(5);
			test = report.createTest("Code Coverage Report");
			test.log(Status.INFO,
					"Code Coverage Link : <a href='file:///D:/Softwares/Jacoco/JSPDiaryReport/index.html'>Code Coverage Link</a>");
			report.flush();
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void onStart(ITestContext arg0) {
		try {
			String[] command = { "cmd.exe", "/C", "Start", "tomcatstart.bat" };
			Runtime.getRuntime().exec(command);
			report = ExtentManager.getInstance();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

	}

	public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {

	}
}
