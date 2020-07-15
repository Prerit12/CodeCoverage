package utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class BaseClass {
	private static final String BROWSER = "Browser";
	public Boolean result;
	public static final Properties propor = new Properties();
	public static final String PROPFILENAME = "OR.properties";
	InputStream input;
	public static WebDriver driver;
	protected static ExtentTest test;
	protected static ExtentTest scenario;
	protected static ExtentReports report;
	static String className = new Throwable().getStackTrace()[0].getClassName();
	
	public static final Logger logger = Logger.getLogger(className);

	/** Setting up the Environment to start the Test */
	public void setup(String url, String env) throws IOException {
		String log4jConfPath ="./log4j.properties" ;
		PropertyConfigurator.configure(log4jConfPath);
		List<String> link;
		if (FunctionsClass.readingFromExcel(BROWSER, "Chrome").get(0).contentEquals("Yes")) {
			driver = new ChromeDriver();
		} else if (FunctionsClass.readingFromExcel(BROWSER, "Firefox").get(0).contentEquals("Yes")) {
			driver = new FirefoxDriver();
		} else if (FunctionsClass.readingFromExcel(BROWSER, "IE").get(0).contentEquals("Yes")) {
			System.setProperty("webdriver.ie.driver", "./IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		} else {
			logger.warn("No driver Specified in Excel");
		}

		String url1 = null;
		link = FunctionsClass.readingFromExcel(url, env);
		url1 = link.get(0);
		FunctionsClass.sleep(5);
		driver.navigate().to(url1);
		driver.manage().window().maximize();
	}

	/** Setting OR properties file code */
	public void setORprop() {
		try {
			result = false;

			input = getClass().getClassLoader().getResourceAsStream(PROPFILENAME);

			if (input != null) {
				propor.load(input);

			} else {
				throw new FileNotFoundException("Property File" + PROPFILENAME + "not found in the classpath");
			}
			result = true;
		} catch (Exception e) {
			Reporter.log("Exception" + e);
		}
	}
}
