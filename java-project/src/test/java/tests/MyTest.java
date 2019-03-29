package tests;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import java.util.*;
import java.util.List;
import java.util.ListIterator;


public class MyTest{
	
// Octavio´s changes
	private final String URL = "https://vacations-management.herokuapp.com/users/sign_in";
	private final String USERNAME = "gap-automation-test@mailinator.com";
	private final String PASSWORD = "12345678";
    private final String IDENTIFICATION = "75105914";
	List <WebElement> admins;
	
// Hellen´s changes
	private final String FIRST_NAME = "Growth";
	private final String LAST_NAME = "Acceleration Partners";
	private final String EMPLOYEE_NAME = "Hellen";	
	private final String EMPLOYEE_EMAIL = "hsuarez@growthaccelerationpartners.com";
	private final String ID = "106550907";
	private final String LEADER_NAME = "Daniel";
	private final String ADMIN_FIRST = "Carlos";
	private final String ADMIN_LAST = "Charry";
	private final String ADMIN_EMAIL = "scharry@growthaccelerationpartners.com";
	private final String ADMIN_PASS = "123Queso";
	private WebDriver webDriver;
	private WebDriverWait wait;
	List<WebElement> users;
	List<WebElement> tabs;
	private WebElement foundEmployee;
	private WebElement foundAdmin;

	
	@BeforeTest
	public void tearUp() {
		webDriver = new ChromeDriver();
		webDriver.get(URL);
	}

	@Test 

	public void myTest() {
		login(USERNAME,PASSWORD);
		//tab_Navigation(USERNAME,PASSWORD);
		//destroy_Account(USERNAME,PASSWORD);
		//deleteEmployee(USERNAME,PASSWORD,IDENTIFICATION);
		//deleteAdmin(USERNAME,PASSWORD,LAST_NAME);
		navigateThroughTabs();
		updateMyAcctInfo(FIRST_NAME,LAST_NAME,PASSWORD);
		createNewEmployee(EMPLOYEE_NAME,LAST_NAME,EMPLOYEE_EMAIL,ID,LEADER_NAME);
		deleteEmployee(ID);
		createAdmin(ADMIN_FIRST, ADMIN_LAST, ADMIN_EMAIL, ADMIN_PASS);
		destroyAdminAccount(ADMIN_EMAIL);
		logout();

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
	
	
	//Method to Destroy first account on the list
	private void destroy_Account(String username, String password) throws InterruptedException {
		find(By.id("user_email")).sendKeys(username);
		find(By.id("user_password")).sendKeys(password);
		find(By.name("commit")).click();
		find(By.cssSelector("a[href='/users']")).click();
		Thread.sleep(3000);
		find(By.xpath(" //table/tbody/tr[2]/td[4]")).click();
		Thread.sleep(5000);
		Alert alert = webDriver.switchTo().alert();
		isAlertPresent();
				
	
	}
	
	//Method to Add Vacations
		private void addVacations(String username, String password,String identification) throws InterruptedException{
			find(By.id("user_email")).sendKeys(username);
			find(By.id("user_password")).sendKeys(password);
			find(By.name("commit")).click();
			Thread.sleep(3000);
			WebElement element = getEmployeeById(identification);
			element.findElement(By.cssSelector("[data-method='Add/Request Vacation']")).click();
			Thread.sleep(3000);
			
		}
		
   //Method to delete an Admin
	private void deleteAdmin(String username, String password,String last_name) throws InterruptedException{
			find(By.id("user_email")).sendKeys(username);
			find(By.id("user_password")).sendKeys(password);
			find(By.name("commit")).click();
			Thread.sleep(3000);
			find(By.cssSelector("a[href='/users']")).click();
			WebElement element = getAdminByLastName(last_name);
	        element.findElement(By.cssSelector("[data-method='delete']")).click();
			Thread.sleep(3000);
			isAlertPresent();
			Thread.sleep(3000);
				}		
	
	//Method to delete an employee
	private void deleteEmployee(String username, String password,String identification) throws InterruptedException{
		find(By.id("user_email")).sendKeys(username);
		find(By.id("user_password")).sendKeys(password);
		find(By.name("commit")).click();
		//find(By.linkText("Employees Information")).click();
		Thread.sleep(3000);
		WebElement element = getEmployeeById(identification);
		element.findElement(By.cssSelector("[data-method='delete']")).click();
		Thread.sleep(3000);
		isAlertPresent();
		Thread.sleep(3000);
	}
	
	public WebElement getAdminByLastName(String last_name) {
		users = webDriver.findElements(By.cssSelector("table tr"));
	    users.forEach(admin -> {
	        if (admin.getText().contains(last_name)) {
	            foundAdmin = admin;

	        	}
	    	});
	    	return foundAdmin;
		}
	
	public WebElement getEmployeeById(String employeeId) {
		users = webDriver.findElements(By.cssSelector("table tr"));
	    users.forEach(employee -> {
	        if (employee.getText().contains(employeeId)) {
	            foundEmployee = employee;

	        	}
	    	});
	    	return foundEmployee;
		}
	
	
	//Method to Navigate between tabs
	private void tab_Navigation(String username, String password) throws InterruptedException {
		find(By.id("user_email")).sendKeys(username);
		find(By.id("user_password")).sendKeys(password);
		find(By.name("commit")).click();
		find(By.cssSelector("a[href='/users']")).click();
		Thread.sleep(3000);
		find(By.cssSelector("a[href='/my_account']")).click();
		Thread.sleep(3000);
		find(By.cssSelector("a[href='/employees']")).click();
		Thread.sleep(3000);
	   
		
	}
	
	public boolean isAlertPresent() {
		  boolean presentFlag = false;

		  try {
		   // Check the presence of alert
		   Alert alert = webDriver.switchTo().alert();
		   // Alert present; set the flag
		   presentFlag = true;
		   // if present consume the alert
		   alert.accept();

		  } catch (NoAlertPresentException ex) {
		   // Alert not present
		   ex.printStackTrace();
		  }

		  return presentFlag;
	 }
	
	

	private void navigateThroughTabs(){
		tabs = webDriver.findElements(By.cssSelector("ul[id=menu] li a"));

		for( int i = 0; i < tabs.size(); i++)
		{  
		    tabs = webDriver.findElements(By.cssSelector("ul[id=menu] li a")); 
		    tabs.get(i).click();
		}

	}

	private WebElement find(By locator) {
		return webDriver.findElement(locator);
	}

// Hellen´s Methods
	private void updateMyAcctInfo(String firstName, String lastName, String currentPassword){
	    find(By.linkText("My Account")).click();
	    find(By.id("user_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), firstName);
	    find(By.id("user_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), lastName);
	    find(By.id("user_current_password")).sendKeys(currentPassword);
	    find(By.name("commit")).click();
	 
	}

	private void createNewEmployee(String firstName, String lastName, String email, String identification, String leaderName){
		find(By.linkText("Create a new employee")).click();
		find(By.id("employee_first_name")).sendKeys(firstName);
		find(By.id("employee_last_name")).sendKeys(lastName);
		find(By.id("employee_email")).sendKeys(email);
		find(By.id("employee_identification")).sendKeys(String.valueOf(identification));
		find(By.id("employee_leader_name")).sendKeys(leaderName);
		find(By.name("commit")).click();
	}

	private void deleteEmployee(String identification){
		find(By.linkText("Employees Information")).click();
		WebElement element = getEmployeeById(identification);
		element.findElement(By.cssSelector("[data-method='delete']")).click();
		isAlertPresent();
	}

	private void createAdminUser(String adminFirstName, String adminLastName, String adminEmail, String adminPass) {
		find(By.linkText("Administrative Users")).click();
		find(By.linkText("Create a new Admin user")).click();
		find(By.id("user_first_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), adminFirstName);
		find(By.id("user_last_name")).sendKeys(Keys.chord(Keys.CONTROL, "a"), adminLastName);
		find(By.id("user_email")).sendKeys(Keys.chord(Keys.CONTROL, "a"), adminEmail);
		find(By.id("user_password")).sendKeys(Keys.chord(Keys.CONTROL, "a"), adminPass);
		find(By.id("user_password_confirmation")).sendKeys(Keys.chord(Keys.CONTROL, "a"), adminPass);
		find(By.name("commit")).click();
	}

	private void destroyAdminAccount(String adminEmail){
		find(By.linkText("Administrative Users")).click();
		WebElement element = getAdminByEmail(adminEmail);
		element.findElement(By.cssSelector("[data-method='delete']")).click();
		System.out.println(element);
		isAlertPresent();
	}

	public void getUsers() {
    	users = webDriver.findElements(By.cssSelector("table tr"));
	}

// Sebastian's Methods

	// CREATE ADMIN
	private void createAdmin(String admin_first, String admin_last, String admin_email, String admin_pass) {
		find(By.linkText("Administrative Users")).click();
		find(By.linkText("Create a new Admin user")).click();
		
		find(By.id("user_first_name")).clear();
		find(By.id("user_first_name")).sendKeys(admin_first);
		find(By.id("user_last_name")).clear();
		find(By.id("user_last_name")).sendKeys(admin_last);
		find(By.id("user_email")).clear();
		find(By.id("user_email")).sendKeys(admin_email);
		find(By.id("user_password")).clear();
		find(By.id("user_password")).sendKeys(admin_pass);
		find(By.id("user_password_confirmation")).sendKeys(admin_pass);
		find(By.name("commit")).click();
	}

	// LOGOUT
	private void logout() {
		find(By.linkText("Logout")).click();
	}


	public WebElement getAdminByEmail(String adminEmail) {
    getUsers();
    users.forEach(admin -> {
        if (admin.getText().contains(adminEmail)) {
            foundAdmin = admin;
        	}
    	});
    	return foundAdmin;
	}	
}