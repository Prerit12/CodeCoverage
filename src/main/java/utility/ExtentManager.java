package utility;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager extends BaseClass {
	public ExtentReporter htmlReporter;

	public static ExtentReports getInstance() {
		if (report == null) {
			String path = "./target/ExtentReport/ExtentReport.html";
			String extentConfig = "./src/main/resources/extent-configCucumber.xml";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
			report = new ExtentReports();
			report.setSystemInfo("OS", "Windows");
			report.setSystemInfo("Host Name", "Prerit");
			report.setSystemInfo("Environment", "Live");
			report.setSystemInfo("UserName", "Prerit");
			htmlReporter.loadConfig(extentConfig);
			report.attachReporter(htmlReporter);
		}
		return report;
	}
}
