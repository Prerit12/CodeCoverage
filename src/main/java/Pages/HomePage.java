package Pages;

import com.aventstack.extentreports.Status;
import utility.FunctionsClass;
import utility.Globals;

public class HomePage extends FunctionsClass {

	public void verifyUserList() {
		result = true;
		int x = 0;
		try {
			setORprop();
			waitUntilFound(Globals.TXT_USERLIST, 10);
			if (getText(Globals.TXT_USERLIST).contains("User list")) {
				test.log(Status.PASS, "Home Page is loaded");
			} else {
				x = x + 1;
				test.log(Status.PASS, "Home Page is not loaded");
			}
		} catch (Exception e) {

		}
		if (x > 0) {
			result = false;
		}
	}

	public void verifyLogin() {
		result = true;
		int x = 0;
		try {
			setORprop();
			waitUntilClickable(Globals.BTN_LOGIN, 20);
			clickElement(Globals.BTN_LOGIN);
			waitUntilFound(Globals.TXT_EMAIL, 20);
			sendText(Globals.TXT_EMAIL, "testuser1292@gmail.com");
			sendText(Globals.TXT_PASSWORD, "test");
			clickElement(Globals.BTN_SUBMIT);
			if(getText(Globals.TXT_LOGOUT).contains("Logout")) {
				test.log(Status.PASS, "Login Successfull");
			} else {
				x = x + 1;
				test.log(Status.PASS, "Login Failed");
			}
			if (x > 0) {
				result = false;
			}
		} catch (Exception e) {

		}
	}

}
