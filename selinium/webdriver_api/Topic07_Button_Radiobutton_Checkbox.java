package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic07_Button_Radiobutton_Checkbox {
	WebDriver driver;
	JavascriptExecutor javascript;
	@BeforeTest
	public void beforeTest() {
		driver= new FirefoxDriver();
		javascript= (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}

	//@BeforeMethod
	//public void beforeMethod() {
		//driver.get("");
		
	//}

	//@Test
	public void TC01_Button() {
		driver.get("http://live.guru99.com/");
		clickByJS(" //div[@class='footer']//a[text()='My Account']");
		String Url=driver.getCurrentUrl();
		Assert.assertEquals(Url, "http://live.demoguru99.com/index.php/customer/account/login/");
		clickByJS("//span[text()='Create an Account']");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	public void clickByJS(String locator) {
		javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}

	@Test
	public void TC02_Checkbox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		String locator="//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input";
				
		checktoCheckbox̣̣̣̣(locator);
		Assert.assertTrue(isSelected(locator));
		unchecktoCheckbox̣̣̣̣(locator);
		Assert.assertFalse(isSelected(locator));

	}

	//@Test
	public void TC03_Radiobutton() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		String locator="//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input";
		clickByJS(locator);
		Assert.assertTrue(isSelected(locator));
		

	}
	public boolean isSelected(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			return true;
		}
		else {
			
			return false;

		}
	}
	public boolean isSelectedRadio(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		if(element.isSelected()) {
			return true;
		}
		else {
			clickByJS("//label[contains(text(),'2.0 Petrol, 147kW')]");
			return false;

		}
	}

	public void checktoCheckbox̣̣̣̣(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			clickByJS(locator);;
		}

	}

	public void unchecktoCheckbox̣̣̣̣(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			clickByJS(locator);;
		}

	}
	@AfterTest
	public void afterTest() {
		driver.quit();
		
	}

}
