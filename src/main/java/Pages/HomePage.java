package Pages;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import utility.FunctionsClass;
import utility.Globals;

public class HomePage extends FunctionsClass {
	static String className = new Throwable().getStackTrace()[0].getFileName();
	public static final Logger logger = Logger.getLogger(className);

	public void verifyUserList() {
		setORprop();
		String tcName = new Throwable().getStackTrace()[0].getMethodName();
		result = false;
		int x = 0;
		try {
			waitUntilFound(Globals.TXT_USERLIST, 10);
			if (getText(Globals.TXT_USERLIST).contains("User list")) {
				test.log(Status.PASS, "Home Page is loaded" + test.addScreenCaptureFromPath(FunctionsClass.passScreenCapture(tcName)));
			} else {
				x = x + 1;
				test.log(Status.PASS, "Home Page is not loaded" + test.addScreenCaptureFromPath(FunctionsClass.failScreenCapture(tcName)));
			}
		} catch (Exception e) {

		}
		if (x == 0) {
			result = true;
		}
	}

	public void verifyLogin() {
		setORprop();
		String tcName = new Throwable().getStackTrace()[0].getMethodName();
		result = false;
		int x = 0;
		try {
			waitUntilClickable(Globals.BTN_LOGIN, 20);
			clickElement(Globals.BTN_LOGIN);
			waitUntilFound(Globals.TXT_EMAIL, 20);
			sendText(Globals.TXT_EMAIL, "testuser1292@gmail.com");
			sendText(Globals.TXT_PASSWORD, "test");
			clickElement(Globals.BTN_SUBMIT);
			if(getText(Globals.TXT_LOGOUT).contains("Logout")) {
				test.log(Status.PASS, "Login Successfull" + test.addScreenCaptureFromPath(FunctionsClass.passScreenCapture(tcName)));
			} else {
				x = x + 1;
				test.log(Status.PASS, "Login Failed" + test.addScreenCaptureFromPath(FunctionsClass.failScreenCapture(tcName)));
			}
			if (x == 0) {
				result = true;
			}
		} catch (Exception e) {

		}
	}

}
