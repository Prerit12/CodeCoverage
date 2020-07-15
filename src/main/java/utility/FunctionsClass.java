package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import utility.Globals;

public class FunctionsClass extends BaseClass {
	static String className = new Throwable().getStackTrace()[0].getClassName();
	public static final Logger logger = Logger.getLogger(className);
	static String failed = "Path/Element or Text is null";
	private static Workbook workbook;

	/** Reading Data from Excel code */
	public static List<String> readingFromExcel(String sheetName, String columnName) throws IOException {
		List<String> data = new ArrayList<String>();
		Workbook wb = null;
		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/TestData.xlsx");
			wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Iterator<Row> rowIterator = sh.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.toString().equalsIgnoreCase(columnName)) {
						int rowNum = cell.getRowIndex();
						for (int i = rowNum; i <= rowNum; i++) {
							for (int j = 1; j < sh.getRow(rowNum).getLastCellNum(); j++) {
								Row row1 = sh.getRow(i);
								Cell cell1 = row1.getCell(j);
								data.add(cell1.toString());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Something went wrong fetching data from Excel" + e);
		} finally {
			wb.close();
		}
		return data;
	}

	/** Method used to wait until element is found */
	public static void waitUntilFound(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			if (xpath != null) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to wait until element is Clickable */
	public static void waitUntilClickable(String xpath, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			if (xpath != null) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to click on element using xpath */
	public static void clickElement(String xpath) {
		try {
			if (xpath != null) {
				driver.findElement(By.xpath(xpath)).click();
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to click on element using id */
	public static void clickElementbyID(String id) {
		try {
			if (id != null) {
				driver.findElement(By.id(id)).click();
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to click on element using web element */
	public static void clickElement(WebElement element) {
		try {
			if (element != null) {
				element.click();
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to create an element */
	public static WebElement createElement(String xpath) {
		WebElement element = null;
		try {
			if (xpath != null) {
				element = driver.findElement(By.xpath(xpath));
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return element;
	}

	/** Method used to create list of elements */
	public static List<WebElement> createElements(String xpath) {
		List<WebElement> elements = null;
		try {
			if (xpath != null) {
				elements = driver.findElements(By.xpath(xpath));
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return elements;
	}

	/** Method used to send keys to element */
	public void sendText(WebElement element, String text) {
		try {
			sleep(1);
			if (element != null && text != null) {
				element.sendKeys(text);
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to send keys to element using xpath */
	public static void sendText(String xpath, String text) {
		try {
			sleep(1);
			if (xpath != null && text != null) {
				driver.findElement(By.xpath(xpath)).sendKeys(text);
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to send keys to element using id */
	public static void sendTextbyID(String id, String text) {
		try {
			sleep(1);
			if (id != null && text != null) {
				driver.findElement(By.id(id)).sendKeys(text);
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** Method used to check if element is visible using xpath */
	public static boolean elementVisible(String xpath) {
		boolean result = false;
		try {
			if (xpath != null) {
				result = driver.findElement(By.xpath(xpath)).isDisplayed();
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	/** Method used to check if element is visible using element */
	public boolean elementVisible(WebElement element) {
		boolean result = false;
		try {
			if (element != null) {
				result = element.isDisplayed();
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	/**
	 * Method used to check if text is present in pdf or not
	 * 
	 * @throws IOException
	 */
	public static boolean isPresentPDF(String url, String text) throws IOException {
		boolean flag = false;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;

		try {
			URL url1 = new URL(url);
			RandomAccessRead file = new RandomAccessBuffer(url1.openStream());
			PDFParser parser = new PDFParser(file);

			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);

			pdDoc = new PDDocument(cosDoc);
			int count = pdDoc.getNumberOfPages();
			pdfStripper.setEndPage(count);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			test.log(Status.ERROR, e2);
		} catch (IOException e) {
			logger.error(e);
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				logger.error(e);
			}
		}
		if (parsedText != null && parsedText.contains(text)) {
			flag = true;
			pdDoc.close();
			cosDoc.close();
		}
		return flag;
	}

	/** This function is used to clear the text from the textbox */
	public void clearText(String xpath) {
		try {
			if (xpath != null) {
				WebElement toClear = driver.findElement(By.xpath(xpath));
				sleep(1);
				for (int i = 0; i < 20; i++) {
					toClear.sendKeys(Keys.BACK_SPACE);
				}
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to clear the text from the textbox of given Length */
	public void clearText(String xpath, int length) {
		try {
			if (xpath != null) {
				WebElement toClear = driver.findElement(By.xpath(xpath));
				sleep(1);
				for (int i = 0; i < length; i++) {
					toClear.sendKeys(Keys.BACK_SPACE);
				}
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to clear the text from the textbox */
	public void clearText(WebElement element) {
		try {
			if (element != null) {
				sleep(2);
				element.sendKeys(Keys.CONTROL + "a");
				element.sendKeys(Keys.DELETE);
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to switch to frame using webelement */
	public static void switchFrame(WebElement element) {
		try {
			if (element != null) {
				driver.switchTo().frame(element);
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to switch to frame using name */
	public void switchFrame(String name) {
		try {
			if (name != null) {
				driver.switchTo().frame(name);
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to switch to default frame */
	public void switchDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to sleep for a given time in sec */
	public static void sleep(int time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to get the Text of Element */
	public static String getText(String xpath) {
		String txt = null;
		try {
			txt = driver.findElement(By.xpath(xpath)).getText();
		} catch (Exception e) {
			logger.error(e);
		}
		return txt;
	}

	/** This function is used to get the Attribute of Element */
	public String getAttribute(String xpath, String attName) {
		String txt = null;
		try {
			txt = driver.findElement(By.xpath(xpath)).getAttribute(attName);
		} catch (Exception e) {
			logger.error(e);
		}
		return txt;
	}

	/** This function is used to execute JS script to move to element */
	public void scrollToView(String xpath) {
		try {
			if (xpath != null) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", createElement(xpath));
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/** This function is used to capture pass screenshots */
	public static String passScreenCapture(String tcName) throws IOException {
		String dest = null;
		try {
			if (tcName != null) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				dest = Globals.PASS_FOLDER + sdf.format(d) + tcName + "-Verified.png";
				dest = dest.substring(1);
				FileUtils.copyFile(scrFile, new File(dest));
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return dest;
	}

	/** This function is used to capture failed screenshots */
	public static String failScreenCapture(String tcName) throws IOException {
		String dest = null;
		try {
			if (tcName != null) {
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				dest = Globals.ERROR_FOLDER + sdf.format(d) + tcName + "-Failed.png";
				dest = dest.substring(1);
				FileUtils.copyFile(scrFile, new File(dest));
			} else {
				logger.info(failed);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return dest;
	}

	/** This function is used to establish DB connection */
	public static Connection dbConnection() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Spares", "postgres", "root");
		} catch (Exception e) {
			test.log(Status.ERROR, e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		test.log(Status.INFO, "Opened database successfully");
		return c;
	}

	/** Method use to check downloaded file is present and deleting that file */
	public boolean isDownloadFilePresent(String fileName) {
		try {
			int x = 0;
			File dir = new File(Globals.FILE_PATH);
			File[] dirContents = dir.listFiles();
			for (int i = 0; i < dirContents.length; i++) {
				if (dirContents[i].getName().contains(fileName)) {
					if (dirContents[i].delete()) {
						test.log(Status.INFO, "File is deleted from the Folder");
						x = 1;
						break;
					} else {
						test.log(Status.INFO, "File is not deleted from the Folder");
					}
				}
			}
			if (x == 0) {
				result = false;
			} else {
				result = true;
			}
		} catch (Exception e) {
			result = false;
			logger.error(e);
		}
		return result;
	}

	/** Method use to set the zoom level of page */
	public void zoomInZoomOut(String value) {
		if (value != null) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.body.style.zoom='" + value + "'");
		} else {
			logger.info(failed);
		}
	}

	/** Method use doubleclick the element */
	public void dblClick(String value) {
		if (value != null) {
			Actions actions = new Actions(driver);
			WebElement elementLocator = driver.findElement(By.xpath(value));
			actions.doubleClick(elementLocator).perform();
		} else {
			logger.info(failed);
		}
	}

	/** Method use to hover the element */
	public void mouseHover(String value) {
		if (value != null) {
			Actions actions = new Actions(driver);
			WebElement elementLocator = driver.findElement(By.xpath(value));
			actions.moveToElement(elementLocator).build().perform();
		} else {
			logger.info(failed);
		}
	}

	/** Method use to get console error */
	@SuppressWarnings("deprecation")
	public void verifyConsoleErrors() {
		Logs logs = driver.manage().logs();
		LogEntries logEntries = logs.get(LogType.BROWSER);
		List<LogEntry> errorLogs = logEntries.filter(Level.SEVERE);

		if (!errorLogs.isEmpty()) {
			for (LogEntry logEntry : logEntries) {
				test.log(Status.ERROR, "Found error in logs: " + logEntry.getMessage());
			}
		}
	}

	/** Storing Excel data to Hashmap */
	public static Map<String, Map<String, String>> setMapData() throws IOException {
		String path = "./src/test/resources/TestData.xlsx";
		FileInputStream fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("TestCases");
		int lastRow = sheet.getLastRowNum();
		Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();
		Map<String, String> dataMap = new HashMap<String, String>();

		// Looping over entire row
		for (int i = 1; i <= lastRow; i++) {
			Row row = sheet.getRow(i);
			// 2snd Cell as Value
			Cell valueCell = row.getCell(2);
			// 0th Cell as Key
			Cell keyCell = row.getCell(0);
			String value = valueCell.getStringCellValue().trim();
			String key = keyCell.getStringCellValue().trim();
			// Putting key & value in dataMap
			dataMap.put(key, value);
			// Putting dataMap to excelFileMap
			excelFileMap.put("DataSheet", dataMap);
		}
		// Returning excelFileMap
		return excelFileMap;
	}

	/** Getting data based on Key from Excel */
	public static String getMapData(String key) throws IOException {
		Map<String, String> m = setMapData().get("DataSheet");
		String value = m.get(key);
		return value;
	}

	/** Method use to Switch browser tab */
	public static void switchTab(int tabNumber) {
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tabNumber));
	}

	/** Method used to create the Instance of Report */
	public static void initReport(String scenarioName) {
		report = ExtentManager.getInstance();
		scenario = report.createTest(scenarioName);
		scenario.log(Status.INFO, "Starting Scenario " + scenarioName);
	}
}
