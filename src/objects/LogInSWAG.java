package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import constants.LogInPage;

public class LogInSWAG {

	public static void logIn(WebDriver driver, String username, String password) {

		driver.get(LogInPage.URL);

		driver.findElement(By.id(LogInPage.USERNAME_ID)).sendKeys(username);

		driver.findElement(By.id(LogInPage.PASSWORD_ID)).sendKeys(password);

		driver.findElement(By.id(LogInPage.LOGIN_ID)).click();

	}

	
	public static void sortPrice(WebDriver driver) {
		
		driver.findElement(By.xpath(LogInPage.SORT_XPATH)).click();
		
		
		
		
		
	}
	
	
	
	
}
