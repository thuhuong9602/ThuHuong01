package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic03_WebElement {
	WebDriver driver;
	By emailBy = By.id("mail");
	By under18By = By.id("under_18");
	By eduBy = By.id("edu");
	By job1By = By.id("job1");
	By developmentBy = By.id("development");
	By slider1By = By.id("slider-1");

	
	By passwordBy = By.id("password");
	By radiodisabledBy = By.id("radio-disabled");
	By bioBy = By.id("bio");
	By job3By = By.id("job3");
	By checkdisbaledBy = By.id("check-disbaled");
	By slider2By = By.id("slider-2");

	String emailTextbox = "Automation Testing";
	String eduTextbox = "Automation Testing";

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

	}

	@Test
	public void TC01() {
		Assert.assertTrue(isDisplay(emailBy));
		Assert.assertTrue(isDisplay(under18By));
		Assert.assertTrue(isDisplay(eduBy));

		sendKey(emailBy, emailTextbox);
		clickElement(under18By);
		sendKey(eduBy, eduTextbox);

	}

	@Test
	public void TC02() {
		Assert.assertTrue(isEnable(emailBy));
		Assert.assertTrue(isEnable(under18By));
		Assert.assertTrue(isEnable(eduBy));
		Assert.assertTrue(isEnable(job1By));
		Assert.assertTrue(isEnable(developmentBy));
		Assert.assertTrue(isEnable(slider1By));

		Assert.assertFalse(isEnable(passwordBy));
		Assert.assertFalse(isEnable(radiodisabledBy));
		Assert.assertFalse(isEnable(bioBy));
		Assert.assertFalse(isEnable(job3By));
		Assert.assertFalse(isEnable(checkdisbaledBy));
		Assert.assertFalse(isEnable(slider2By));

	}
	@Test
	public void TC03() {
		clickElement(under18By);
		clickElement(developmentBy);
		Assert.assertTrue(isSelected(under18By));
		Assert.assertTrue(isSelected(developmentBy));
		clickElement(developmentBy);
		Assert.assertFalse(isSelected(developmentBy));


	}

	public boolean isDisplay(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isEnable(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element is enable");
			return true;
		} else {
			System.out.println("Element is disable");
			return false;
		}

	}
	public boolean isSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element is selected");
			return true;
		} else {
			System.out.println("Element is unselected");
			return false;
		}

	}
	public void sendKey(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}

	public void clickElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
