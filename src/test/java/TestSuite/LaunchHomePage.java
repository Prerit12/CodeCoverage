package TestSuite;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Pages.HomePage;
import utility.BaseClass;

public class LaunchHomePage extends BaseClass {
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
