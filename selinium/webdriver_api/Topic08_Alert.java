package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic08_Alert {
	// Khai báo biến driver đại diện cho selenium webdriver
	WebDriver driver;
	Alert alert;

	// Pre-Condition
	@BeforeClass
	public void beforeClass() {
		// Khởi tạo trình duyệt firefox
		driver = new FirefoxDriver();

		// Chờ chơ element được hiển thị trước khi tương tác trong vòng 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Phóng to trình duyệt
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_AceptAlert() throws InterruptedException {

		// Mở ra 1 trang web
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
		alert=driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "I am a JS Alert");	
	    alert.accept();
	    Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");
		
	}

	@Test
	public void TC_02_DismisAlert() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Confirm')]")).click();
		alert = driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");

	}
	@Test
	public void TC_03_Prompt() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[contains(text(),'Click for JS Prompt')]")).click();
		alert = driver.switchTo().alert();
		Thread.sleep(2000);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("Chào mừng bạn đến với lớp học Autotest");;
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: Chào mừng bạn đến với lớp học Autotest");

	}
	//Cách 1: Truyền trực tiếp username, pass vào URL
	@Test
	public void TC_04_Authe_1() {
		String usernameandPass="admin";
		String url="http://the-internet.herokuapp.com/basic_auth";
		url="http://"+usernameandPass+":"+ usernameandPass+ "@the-internet.herokuapp.com/basic_auth";
		driver.get(url);

	}
	//Cách 2: Dùng hàm getAttribute
	@Test
	public void TC_04_Authe_2() {
		String username="admin";
		String password="admin";
		driver.get("http://the-internet.herokuapp.com/");
		
		WebElement basicAuthenlink= driver.findElement(By.xpath("//a[text()='Basic Auth']"));
		String url=basicAuthenlink.getAttribute("href");
		System.out.println(url);
		driver.get(byPassAuthenticationAlert(url, username, password));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		

	}

	public String byPassAuthenticationAlert(String url, String username, String password) {
		System.out.println("Old url" + url);
		String[] splitUrl = url.split("//");
		url = splitUrl[0] + "//" + username + ":" + password + "@" + splitUrl[1];
		System.out.println("New url=" + url);
		return url;
	}
	// Pro-conditon
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}
