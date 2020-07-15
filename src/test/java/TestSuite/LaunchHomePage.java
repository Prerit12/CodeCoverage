package TestSuite;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Pages.HomePage;
import utility.BaseClass;
import utility.FunctionsClass;

public class LaunchHomePage extends BaseClass {
	@BeforeSuite
	public void startTomcat() {
		try {
			String[] command = { "cmd.exe", "/C", "Start", "tomcatstart.bat" };
			Runtime.getRuntime().exec(command);
		} catch (Exception e) {

		}
	}

	@AfterSuite
	public void stopTomcat() {
		try {
			String[] command = { "cmd.exe", "/C", "Start", "tomcatstop.bat" };
			Runtime.getRuntime().exec(command);
			FunctionsClass.sleep(2);
			String[] commandAntBuild = { "cmd.exe", "/C", "Start", "antBuild.bat" };
			Runtime.getRuntime().exec(commandAntBuild);
			FunctionsClass.sleep(2);
			Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
		} catch (Exception e) {

		}
	}

	@BeforeMethod
	public void startup() {
		try {
			setup("URL", "Diary");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void verifyHomePage() {
		try {
			HomePage hp = new HomePage();
			hp.verifyUserList();
			Assert.assertTrue(hp.result);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Test
	public void verifyLogin() {
		try {
			HomePage hp = new HomePage();
			hp.verifyLogin();
			Assert.assertTrue(hp.result);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@AfterMethod
	public void tearDown() {
		try {
			driver.quit();
		} catch (Exception e) {
			logger.error(e);
		}
	}

}
