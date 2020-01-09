package webdriver_api;
import java.util.Random;
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
 
public class Topic_01_CheckEnvironment {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	String firstName="Automation";
	String lastName="Tesing";
	String eMail="automation_"+RandomNumber()+"@gmail.com";
	String passWord="123123";
	String passWordConfirm="123123";
	
	public int RandomNumber() {
		Random rd= new Random();
		return rd.nextInt(100);
	}
	//Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();
		
		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Phóng to trình duyệt
		driver.manage().window().maximize();
		

	}
	@BeforeMethod
	public void beforMethod() {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
	}
	
	@Test
	public void TC_01() {
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}
 
	@Test
	public void TC_02() {
		driver.findElement(By.id("email")).sendKeys("123@2123");
		driver.findElement(By.id("pass")).sendKeys("123123");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
 
	@Test
	public void TC_03() {
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
 
	@Test
	public void TC_04() {
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).getText(), "Invalid login or password.");
	}
	@Test
	public void TC_05() throws InterruptedException{
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(eMail);
		driver.findElement(By.id("password")).sendKeys(passWord);
		driver.findElement(By.id("confirmation")).sendKeys(passWordConfirm);
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		
		// Check thông báo
		Assert.assertTrue(driver.findElement(By.xpath("//span[(text()='Thank you for registering with Main Website Store.')]")).isDisplayed());

		Thread.sleep(2000);
		// Logout
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[(text()='Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/logoutSuccess/");
		
	}
	@Test
	public void TC_06() {

		System.out.println(eMail);
		driver.findElement(By.id("email")).sendKeys(eMail);
		driver.findElement(By.id("pass")).sendKeys(passWord);
		
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//h1")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'"+firstName+" "+lastName+"')] ")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'"+eMail+"')]  ")).isDisplayed());
		

		
	}
	//Pro-conditon
	@AfterClass
	public void afterClass() {
		//Tắt trình duyệt
		driver.quit();
	}
 
}
 
 
 
