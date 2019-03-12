package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;

public class MyTest{
	private final String URL = "https://vacations-management.herokuapp.com/users/sign_in";
	private final String USERNAME = "gap-automation-test@mailinator.com";
	private final String PASSWORD = "12345678";
	private final String FIRST_NAME = "Hellen";
	private final String LAST_NAME = "Suárez";	
	private final String EMAIL = "hsuarez@growthaccelerationpartners.com";
	private final String ID = "106550907";
	private final String LEADER_NAME = "Daniel";
	private WebDriver webDriver;
	private WebDriverWait wait;

	@BeforeTest
	public void tearUp() {
		webDriver = new ChromeDriver();
		webDriver.get(URL);
	}

	@Test 
	public void myTest() {
		login(USERNAME,PASSWORD);
		updateMyAcctInfo(FIRST_NAME,LAST_NAME,PASSWORD);
		//createNewEmployee(FIRST_NAME,LAST_NAME,EMAIL,ID,LEADER_NAME);
		verifyEmployeeBeforeDeleting(ID);
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

	private void updateMyAcctInfo(String firstName, String lastName, String currentPassword){
	    find(By.linkText("My Account")).click();
	    find(By.id("user_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), firstName);
	    find(By.id("user_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), lastName);
	    find(By.id("user_current_password")).sendKeys(currentPassword);
	    find(By.name("commit")).click();
	 
	}

	/*private void createNewEmployee(String firstName, String lastName, String email, String identification, String leaderName){
		find(By.linkText("Create a new employee")).click();
		find(By.id("employee_first_name")).sendKeys(firstName);
		find(By.id("employee_last_name")).sendKeys(lastName);
		find(By.id("employee_email")).sendKeys(email);
		find(By.id("employee_identification")).sendKeys(String.valueOf(identification));
		find(By.id("employee_leader_name")).sendKeys(leaderName);
		find(By.name("commit")).click();
	}*/

	private void verifyEmployeeBeforeDeleting(String identification){
		String identifier = find(By.cssSelector("table tr:nth-child(2) td:nth-child(3)")).getText();
		Assert.assertEquals(identifier, identification);
		find(By.cssSelector("table tr:nth-child(2) td:nth-child(9) a")).click();
		wait = new WebDriverWait(webDriver, 5);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = webDriver.switchTo().alert();
		alert.accept();
	}

	/*private void deleteEmployee(){
		

	}*/


}