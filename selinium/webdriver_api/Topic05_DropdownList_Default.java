package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic05_DropdownList_Default {
	WebDriver driver;
	Select select;
	
	String firtName="Automation";
	String lastName="Testing";
	String day="1";
	String month="May";
	String year="1980";
	String emailTextbox="autonmation_" +NumberRandom()+ "@gmail.com";
	String passWord="123456" ;
	String passWordConfirm="123456" ;
	
	public int NumberRandom() {
		Random rd= new Random();
		return rd.nextInt(50);
	}
	@BeforeTest
	public void beforeTest() {
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}


	@Test
	public void T01() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select= new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		//Kiểm tra droplist không hỗ trợ multiple
		Assert.assertFalse(select.isMultiple());
		//Chọn giá trị Mobile Testing
		select.selectByVisibleText("Mobile Testing");
		//Kiểm tra chọn đúng
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		//Chọn giá trị Manual Testing
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		//Chọn giá trị Functional UI Testing"
		select.selectByIndex(9);
		//Kiểm tra chọn đúng
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		//Kiểm tra có 10 giá trị trong droplist
		Assert.assertEquals(select.getOptions().size(), 10);
		
		//Kiểm tra droplist không hỗ trợ multiple
		select= new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());
	
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		
		List <WebElement> allOption=select.getAllSelectedOptions();
		Assert.assertEquals(allOption.size(), 3);
		List<String> arraySelected= new ArrayList<String>();
		for(WebElement select:allOption) {
			arraySelected.add(select.getText());
		}
		Assert.assertTrue(arraySelected.contains("Automation"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Desktop"));
		
		select.deselectAll();
		List <WebElement> deSelectAll=select.getAllSelectedOptions();
		Assert.assertEquals(deSelectAll.size(), 0);

		

	}

	@Test
	public void T02() {
		driver.get("https://demo.nopcommerce.com/register");
		click("//a[text()='Register']");
		click("//input[@id='gender-male']");
		senKey("//input[@id='FirstName']", firtName);
		senKey("//input[@id='LastName']", firtName);
		
		selectLocator("//select[@name='DateOfBirthDay']", day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"1");
		Assert.assertEquals(select.getOptions().size(),32);

		selectLocator("//select[@name='DateOfBirthMonth']", month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"May");
		Assert.assertEquals(select.getOptions().size(),13);


		selectLocator("//select[@name='DateOfBirthYear']", year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"1980");
		Assert.assertEquals(select.getOptions().size(),112);
			
		senKey("//input[@id='Email']", emailTextbox);
		senKey("//input[@id='Password']", passWord);
		senKey("//input[@id='ConfirmPassword']", passWordConfirm);
		
		click(" //input[@id='register-button']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='header-links']//a[text()='My account']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Log out']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		
	}
	public void senKey(String locator, String value) {
		WebElement element= driver.findElement(By.xpath(locator));
		element.sendKeys(value);
	}
	public void click(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		element.click();
	}
	public void selectLocator(String locator, String value) {
		select=new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(value);
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
		
	}

}
