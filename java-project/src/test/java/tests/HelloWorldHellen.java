//package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


public class HelloWorldHellen {

	private final String URL = "https://www.google.com/";
	private final String NAME = "Hello World - Hellen";
	private WebDriver webDriver;

	@BeforeTest
	public void tearUp() {
		webDriver = new ChromeDriver();
		webDriver.get(URL);
	}

	@Test
	public void HelloWorldHellen() {
		search();
	}

	@AfterTest
	public void tearDown() {
		webDriver.quit();
	}

	private void search(){
		find(By.name("q")).sendKeys(NAME);
	}

	private WebElement find(By locator) {
		return webDriver.findElement(locator);
	}
}