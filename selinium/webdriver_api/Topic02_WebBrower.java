package webdriver_api;
 
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
 
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
 
public class Topic02_WebBrower {
	WebDriver driver;
	@BeforeTest
	public void beforeTest() {
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
 
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

	}

	@Test
	public void TC01() {

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC02() {

		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC03() {
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}
	@Test
	public void TC04() {
		driver.getPageSource().contains("Login or Create an Account");
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();

		driver.getPageSource().contains("Create an Account");


	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
 
}
 
 
 
 
 
