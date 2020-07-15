package utility;

import utility.BaseClass;

public class Globals extends BaseClass {

	/** Directory Path */
	public static final String FILE_PATH = propor.getProperty("FilePath");
	public static final String PASS_FOLDER = propor.getProperty("PassFolder");
	public static final String ERROR_FOLDER = propor.getProperty("ErrorFolder");
	public static final String EXTENT_REPORT = propor.getProperty("ExtentReport");
	
	/** Search Page */
	public static final String TXT_USERLIST = propor.getProperty("txtUserList");
	public static final String BTN_LOGIN = propor.getProperty("btnLogin");
	public static final String TXT_EMAIL = propor.getProperty("txtEmail");
	public static final String TXT_PASSWORD = propor.getProperty("txtPassword");
	public static final String BTN_SUBMIT = propor.getProperty("btnSubmit");
	public static final String TXT_LOGOUT = propor.getProperty("txtLogout");
}
