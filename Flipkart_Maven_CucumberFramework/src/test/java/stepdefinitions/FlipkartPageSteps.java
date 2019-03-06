package stepdefinitions;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Hooks;
import helpers.Log;
import pageobjects.FlipkartPageObjects;

public class FlipkartPageSteps {
	public WebDriver driver;

	public FlipkartPageSteps() {
		driver = Hooks.driver;
		PageFactory.initElements(driver, FlipkartPageObjects.class);

	}
	
	private void waitForLoaderToClose() {
		try {
			//TODO once you have proper selector for loader then use below code instead of sleep
			//wait.until(ExpectedConditions.invisibilityOf(FlipkartPageObjects.loader));
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	}

	// Navigate to browser URL
	@Given("^the user navigates to Flipkart page$")
	public void the_user_navigates_to_Flipkart_page() throws Throwable {
		driver.get("https://www.flipkart.com/");
	}

	// Select ethnic wear from women's menu
	@When("^the user selects Ethnic Wear from women's section redirects to product page$")
	public void the_user_selects_Ethnic_Wear_from_women_s_section_redirects_to_product_page() throws Throwable {
		Log.info("close login popup");
		driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);

		Log.info(" Mouse over on the women's menu");
		Actions act = new Actions(driver);
		act.moveToElement(FlipkartPageObjects.women_Menu).build().perform();

		Log.info("click ethnic wear");
		FlipkartPageObjects.ethnic_wear.click();

	}

	// Selecting filters from left panel on product page
	@When("^the user selects product filters from the page$")
	public void the_user_selects_product_filters_from_the_page() throws Throwable {

		
		Log.info("Select minimum price from dropdown");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(FlipkartPageObjects.min_price));
		Select minPrice = new Select(FlipkartPageObjects.min_price);
		minPrice.selectByValue("1000");
		//Thread.sleep(2000);
		waitForLoaderToClose();
		Log.info("Select maximum price from dropdown");
		wait.until(ExpectedConditions.visibilityOf(FlipkartPageObjects.max_price));
		Select maxPrice = new Select(FlipkartPageObjects.max_price);
		maxPrice.selectByValue("1500");
		waitForLoaderToClose();
		
		Log.info("Select size filter");
		wait.until(ExpectedConditions.visibilityOf(FlipkartPageObjects.size));
		FlipkartPageObjects.size.click();
		waitForLoaderToClose();

		Log.info("Select brand filter");
		FlipkartPageObjects.brand_dropdown.click();
		FlipkartPageObjects.brand.click();
		waitForLoaderToClose();

		Log.info("Select color filter");
		FlipkartPageObjects.color.click();
		waitForLoaderToClose();

	}

	@Then("^the user selects one of the ethnic wear from the filtered list$")
	public void the_user_selects_one_of_the_ethnic_wear_from_the_filtered_list() throws Throwable {

		Log.info(" Select one of the items from the displayed products");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(FlipkartPageObjects.select_Dress));
		FlipkartPageObjects.select_Dress.click();
		waitForLoaderToClose();

		Log.info("Switch window");
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);			
		}
	}

	// Take screen shot of the selected product
	@Then("^the user takes screenshot of the selected item$")
	public void the_user_takes_screenshot_of_the_selected_item() throws Throwable {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		waitForLoaderToClose();
		FileUtils.copyFile(screenshot, new File("screenshots/test.png"));
	}

}
