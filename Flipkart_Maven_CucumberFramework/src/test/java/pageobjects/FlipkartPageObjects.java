package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class FlipkartPageObjects extends BaseClass {

	public FlipkartPageObjects(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Women')]")
	public static WebElement women_Menu;
	
	@FindBy(how = How.LINK_TEXT, using = "Ethnic Wear")
	public static WebElement ethnic_wear;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Brand')]")
	public static WebElement brand_dropdown;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Libas')]/../div")
	public static WebElement brand;
	
	@FindBy(how = How.XPATH, using = "//select/option[text()='Min']/..")
	public static WebElement min_price;
	
	@FindBy(how = How.XPATH, using = "//select/option[text()='₹2500+']/..")
	public static WebElement max_price;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'M')]/../div")
	public static WebElement size;
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Blue')]/../div")
	public static WebElement color;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Libas')]/../a")
	public static WebElement select_Dress;
}


       