package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic09_User_Interactions {
	WebDriver driver;
	Actions actions;
	Alert alert;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\libraries\\chromedriver.exe");
		driver = new ChromeDriver();
		
		actions = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	//@Test
	public void TC01_Hover() {
		driver.get("http://www.myntra.com/");
		actions.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Discover']")))
				.perform();
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='American Eagle']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='American Eagle']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='title-container']")).isDisplayed());

	}

	//@Test
	public void TC02_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		// Khai báo list các element
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

		// Lấy ra số lượng các element trong list
		int beforenumber = numbers.size();

		// Click và giữ đến số cần chọn
		actions.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).release().perform();

		// Khai báo list chứa các số đã chọn
		List<WebElement> selectednumbers = driver
				.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
		int afternumber = numbers.size();

		// In ra text các số đã được chọn
		for (WebElement number : selectednumbers) {
			number.getText();
		}
		Assert.assertEquals(selectednumbers.size(), 4);

	}

	//@Test
	public void TC03_Click_And_Select_Random() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		// Khai báo list các element
		List<WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']//li"));

		// Lấy ra số lượng các element trong list
		int beforenumber = numbers.size();

		// Nhấn phím ctr
		actions.keyDown(Keys.CONTROL).perform();
		// click vào các số cần chọn
		actions.click(numbers.get(0)).click(numbers.get(2)).click(numbers.get(5)).click(numbers.get(10));
		// thả chuột
		actions.keyUp(Keys.CONTROL).perform();
		// Khai báo list chứa các số đã chọn
		List<WebElement> selectednumbers = driver
				.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
		int afternumber = numbers.size();

		// In ra text các số đã được chọn
		for (WebElement number : selectednumbers) {
			number.getText();
		}
		Assert.assertEquals(selectednumbers.size(), 4);

	}

	//@Test
	public void TC04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		actions.doubleClick(finElement("//button[contains(text(),'Double click me')]")).perform();
		isDisplay("//p[@id='demo']");

	}

	//@Test
	public void TC05_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		actions.contextClick(finElement("//span[text()='right click me']")).perform();
		actions.moveToElement(finElement("//span[text()='Quit']")).perform();
		Assert.assertTrue(isDisplay(
				"//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']"));
		actions.click(finElement("//span[text()='Quit']")).perform();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();

	}
	@Test 
	public void dragAndDrop() throws InterruptedException {
	driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	WebElement sourceCricle =driver.findElement(By.xpath("//div[@id='draggable']"));
	WebElement targretCricle = driver.findElement(By.xpath("//div[@id='droptarget']"));
	actions.dragAndDrop(sourceCricle, targretCricle).perform();
	Thread.sleep(3000);
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());

	}


	public WebElement finElement(String locator) {
		return driver.findElement(By.xpath(locator));

	}

	public boolean isDisplay(String locator) {
		return finElement(locator).isDisplayed();

	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
