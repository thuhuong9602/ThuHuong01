package webdriver_api;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic04_Textbox_Textare {
	WebDriver driver;
	String userName="mngr239135";
	String passWord="zuhydav";
	String customerID;
	//Input values
	String customerName="Austin";
	String gender="female";
	String dateOfbirth="1989-01-15";
	String addDress="NewYork";
	String cityTextbox="NewYork";
	String stateTextbox="American";
	String pinTextbox="100000";
	String emailTexbox="auto_"+NunberRandom()+ "@gmail.com";
	String passWordTextbox="123456";
	String numberPhone="0987232345";
	
	String addresEdit="HaNoi";
	String cityEdit="HaNoi";
	String stateEdit="VietNam";
	String pinEdit="100001";
	String numberPhoneEdit="0356789087";
	String emailEdit="autotest@hotmai.com";

	public int NunberRandom() {
		Random rd= new Random();
		return rd.nextInt(100);
	}
	@BeforeTest
	public void beforeTest() {
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4");
	}


	@Test
	public void TC01() {
		sendKey("//input[@name='uid']", userName);
		sendKey("//input[@name='password']", passWord);
		click("//input[@name='btnLogin']");
		
		Assert.assertTrue(isDisplay("//marquee[@class='heading3']"));
		Assert.assertTrue(isDisplay("//td[contains(text(),'Manger Id : "+userName+"')]"));
		click("//a[text()='New Customer']");
		sendKey("//input[@name='name']", customerName);
		click("//input[@value='f']");
		Assert.assertTrue(isSelected("//input[@value='f']"));
		sendKey("//input[@name='dob']", dateOfbirth);
		sendKey("//textarea[@name='addr']", addDress);
		sendKey("//input[@name='city']", cityTextbox);
		sendKey("//input[@name='state']", stateTextbox);
		sendKey("//input[@name='pinno']", pinTextbox);
		sendKey(" //input[@name='telephoneno']", numberPhone);
		sendKey("//input[@name='emailid']", emailTexbox);
		sendKey("//input[@name='password']", passWordTextbox);
		click("//input[@name='sub']");
		
		Assert.assertTrue(isDisplay("//p[@class='heading3']"));
		customerID=driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		System.out.println(customerID);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(), dateOfbirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(), addDress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(), cityTextbox);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(), stateTextbox);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(), pinTextbox);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(), numberPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(), emailTexbox);
		
			
		
	}
	@Test
	public void TC02() {
		
		click("//a[text()='Edit Customer']");
		sendKey("//input[@name='cusid']", customerID);
		click("//input[@name='AccSubmit']");
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), addDress);
		
		Assert.assertTrue(isDisplay("//input[@name='name']"));
		Assert.assertTrue(isDisplay("//input[@name='gender']"));
		Assert.assertTrue(isDisplay("//input[@name='dob']"));
		
		clear("//textarea[@name='addr']");
		clear("//input[@name='city']");
		clear("//input[@name='state']");
		clear("//input[@name='pinno']");
		clear(" //input[@name='telephoneno']");
		clear("//input[@name='emailid']");
		sendKey("//textarea[@name='addr']", addresEdit);
		sendKey("//input[@name='city']", cityEdit);
		sendKey("//input[@name='state']", stateEdit);
		sendKey("//input[@name='pinno']", pinEdit);
		sendKey(" //input[@name='telephoneno']", numberPhoneEdit);
		sendKey("//input[@name='emailid']", emailEdit);
		click("//input[@name='sub']");
		
		Assert.assertTrue(isDisplay("//p[@class='heading3']"));
		String customerID=driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		System.out.println(customerID);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText(), dateOfbirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText(), addresEdit );
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText(), cityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText(), stateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText(), pinEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText(), numberPhoneEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText(), emailEdit);
		
		
	}
	public void clear(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		element.clear();
	}
	public void sendKey(String locator , String value) {
		WebElement element= driver.findElement(By.xpath(locator));
		element.sendKeys(value);
	}
	public void click(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		element.click();
	}
	public boolean isDisplay(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		if(element.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isSelected(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		if(element.isEnabled()) {
			return true;
		}
		else {
			return false;
		}
	}



	@AfterTest
	public void afterTest() {
		driver.quit();
		
	}


}
