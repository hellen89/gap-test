package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

public class MyTest{
	private final String URL = "https://vacations-management.herokuapp.com/users/sign_in";
	private final String USERNAME = "gap-automation-test@mailinator.com";
	private final String PASSWORD = "12345678";
	private WebDriver webDriver;

	@BeforeTest
	public void tearUp() {
		webDriver = new ChromeDriver();
		webDriver.get(URL);
	}

	@Test 
	public void myTest() {
		login(USERNAME,PASSWORD);
	}

	@AfterTest
	public void tearDown() {
		webDriver.quit();
	}

	private void login(String username, String password) {
		find(By.id("user_email")).sendKeys(username);
		find(By.id("user_password")).sendKeys(password);
		find(By.name("commit")).click();
	}

	private WebElement find(By locator) {
		return webDriver.findElement(locator);
	}


}