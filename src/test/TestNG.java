package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import constants.LogInPage;
import objects.LogInSWAG;

public class TestNG {

	WebDriver driver;

	@BeforeClass
	public void createDriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\spaso\\Desktop\\chromedriver\\chromedriver.exe");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);

	}

	@Test
	public void logIn() {

		File f = new File("username.xlsx");

		SoftAssert sa = new SoftAssert();

		try {
			InputStream is = new FileInputStream(f);

			XSSFWorkbook wb = new XSSFWorkbook(is);

			Sheet sheet = wb.getSheetAt(0);

			for (int i = 0; i < 2; i++) {

				Row row = sheet.getRow(i);

				Cell cell0 = row.getCell(0);
				Cell cell1 = row.getCell(1);
				Cell cell2 = row.getCell(2);

				String username = cell0.toString();
				String password = cell1.toString();
				String expected = cell2.toString();

				LogInSWAG.logIn(driver, username, password);

				String currentURL = driver.getCurrentUrl();

				sa.assertEquals(currentURL, expected);

			}

			sa.assertAll();

			wb.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Test
	public void sort() {
		
		LogInSWAG.logIn(driver, "standard_user", "secret_sauce");
		
		LogInSWAG.sortPrice(driver);
		
		String current = driver.getCurrentUrl();
		
		Assert.assertEquals(current, LogInPage.EXPECTED_URL);
		
		driver.close();
		
	}
	
	
	
}
