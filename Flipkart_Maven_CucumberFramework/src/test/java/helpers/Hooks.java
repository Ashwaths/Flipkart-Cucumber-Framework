package helpers;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	public static WebDriver driver;

	@Before
	/**
	 * Delete all cookies at the start of each scenario to avoid shared state
	 * between tests
	 */
	public void openBrowser() throws MalformedURLException {
		System.out.println("Called openBrowser");
		System.setProperty("webdriver.gecko.driver",
		  "/home/sanket/Music/Flipkart_Maven_CucumberFramework/Drivers/ChromeDriver/chromedriver"); 
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		DOMConfigurator.configure("log4j.xml");

	}

	
	  @After
	 /**
		 * Embed a screenshot in test report if test is marked as failed
		 */
			  public void embedScreenshot(Scenario scenario) {
			  
			  if (scenario.isFailed()) { try { scenario.write("Current Page URL is " +
			  driver.getCurrentUrl());
			  
			  byte[] screenshot = ((TakesScreenshot) driver)
			  .getScreenshotAs(OutputType.BYTES); scenario.embed(screenshot, "image/png");
			  } catch (WebDriverException e ) { System.out.println(e.getMessage());
			  
			  }
			  
			  } driver.quit();
			  
			  }
			 
}