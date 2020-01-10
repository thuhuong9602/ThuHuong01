package webdriver_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic06_DropdownList_Custom {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;
	Actions action;
	Select select;
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		javascript= (JavascriptExecutor) driver;
		action= new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC01_Jquery() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDroplistCustorm("//span[@id='number-button']", "//ul[@id='number-menu']//li", "19");
		// Kiem tra so 19 chon thanh cong
		Assert.assertTrue(isDisplay("//span[@id='number-button']//span[text()='19']"));

		Thread.sleep(20);

	}

	public void selectDroplistCustorm(String parentdropdown, String allItemDroplist, String value) {
		// 1- click thẻ chứa droplist để sổ ra hết item trong droplist
		driver.findElement(By.xpath(parentdropdown)).click();

		// 2- khai báo list để chứa tất cả item bên trong List <WebElement>
		List<WebElement> allItems = driver.findElements(By.xpath(allItemDroplist));
		// 3- wait cho tất cả item xuất hiện
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemDroplist)));
		// 4- getText từng item rồi so sánh với item cần chọn
		for (WebElement item : allItems) {
			System.out.println(item.getText());
			// 5- kiểm tra item nào đúng với cái mình cần chọn thì click vào
			if (item.getText().equals(value)) {
				item.click();
				break;
			}

		}

	}

	public boolean isDisplay(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}
	public String getText(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.getText();
		return element.getText();
	}
	public String getTextbyJS(String locator) {
		return (String) javascript.executeScript("return document.querySelector('"+locator+"').text");
	}
	@Test
	public void TC02_Angular() throws InterruptedException {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectDroplistCustorm("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Football");
		Thread.sleep(20);
		String expectText= getTextbyJS("#games_hidden > option");
		Assert.assertEquals(expectText,"Football");
		Thread.sleep(20);

	}
	@Test
	public void TC03_ReactJS() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");
		selectDroplistCustorm("//div[@class='ui fluid selection dropdown']", "//div[@class='visible menu transition']//div//span", "Christian");
		Thread.sleep(20);
		boolean expectText=isDisplay("//div[@class='ui fluid selection upward dropdown']//div[text()='Christian']");
		Assert.assertTrue(expectText,"Christian");
		Thread.sleep(20);

	}
	@Test
	public void TC04_VueJS() throws InterruptedException {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectDroplistCustorm("//li[@class='dropdown-toggle']", "//li[@class='dropdown-toggle']/following-sibling::ul//li/a", "Third Option");
		Thread.sleep(20);
		boolean expectText=isDisplay("//div[@class='btn-group']//li[contains(text(),'Third Option')]");
		Assert.assertTrue(expectText,"Third Option");
		Thread.sleep(20);
		
	}
	public void editDropdownCustom(String parentdropdown, String locator, String value) {
		driver.findElement(By.xpath(parentdropdown)).click();
		driver.findElement(By.xpath(locator)).sendKeys(value);
		action.sendKeys(driver.findElement(By.xpath(locator)), Keys.ENTER).perform();
			
	}
	
	@Test
	public void TC05_Editable() throws InterruptedException {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		editDropdownCustom("//div[@id='default-place']", "//div[@id='default-place']//input", "Alfa Romeo");
		//Truyen phim Enter
		
		Thread.sleep(20);
		boolean expectText=isDisplay("//div[@id='default-place']//li[text()='Alfa Romeo']");
		Assert.assertTrue(expectText,"Alfa Romeo");
		Thread.sleep(20);
		
	}
	@Test
	public void TC06_Editable() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");
		editDropdownCustom("//div[@id='types-search-selection']//div[@role='combobox']", "//div[@id='types-search-selection']//div[@role='combobox']//input", "Algeria");
		//Truyen phim Enter
		
		Thread.sleep(20);
		boolean expectText=isDisplay("//div[@id='types-search-selection']//div[@role='combobox']");
		Assert.assertTrue(expectText,"Algeria");
		Thread.sleep(20);
		
	}

	
	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
