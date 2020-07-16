package utility;

import java.io.File;
import org.apache.log4j.Logger;
import utility.FunctionsClass;

public class BusinessClass extends FunctionsClass {
	static String className = new Throwable().getStackTrace()[0].getClassName();
	public static final Logger logger = Logger.getLogger(className);

	/** Method use to delete all existing screenshots */
	public static void deleteScreenshots(String path) {
		File dir = new File(path);
		File[] dirContents = dir.listFiles();
		if (dirContents != null) {
			for (int i = 0; i < dirContents.length; i++) {
				if ((dirContents[i].getName().contains("Failed")) || (dirContents[i].getName().contains("Verified"))) {
					dirContents[i].delete();
				}
			}
		}
	}
}
