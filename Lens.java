package steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Lens {
    public ChromeDriver driver;
	
	@Given("Launch the website https://www.lenskart.com/")
	public void launchTheWebsiteHttpsWwwLenskartCom() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver 83.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver= new ChromeDriver(options);
		driver.get("https://www.lenskart.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();		   
	}

	@Given("Mouseover on Contact Lenses")
	public void mouseoverOnContactLenses() {
		WebElement elementHover = driver.findElementByLinkText("Contact Lenses");
		Actions builder = new Actions(driver);	
		builder.moveToElement(elementHover).perform();
	}

	@Given("Click on Monthly under Explore By Disposability")
	public void clickOnMonthlyUnderExploreByDisposability() {
		driver.findElementByXPath("//span[text()='Monthly']").click();   
	}

	@Given("Select brand as Aqualens")
	public void selectBrandAsAqualens() throws InterruptedException {
	     driver.findElementByXPath("//span[text()='Aqualens(10)']").click();
	     Thread.sleep(5000);
	}

	@Given("Click on the first product")
	public void clickOnTheFirstProduct() throws InterruptedException {
		 Thread.sleep(2000);
	     driver.findElementByXPath("(//div[contains(@class,'product-info')])[1]").click();	
	}

	@Given("Click Buy Now")
	public void clickBuyNow() throws InterruptedException {
		 Thread.sleep(4000);
	     WebElement elementHover = driver.findElementByXPath("//span[text()='Sign Up']");
		 Actions builder = new Actions(driver);	
		 builder.moveToElement(elementHover).perform();
	     driver.findElementByXPath("//button[text()='BUY NOW']").click();
	}

	@Given("Select No of boxes as {int} and Power as {int} for both eyes.")
	public void selectNoOfBoxesAsAndPowerAsForBothEyes(Integer int1, Integer int2) {
		//Right Eye 
		 Select RE = new Select(driver.findElementByXPath("(//select[@name='boxes'])[1]")); 
		 RE.selectByValue("2"); 
	   
		 driver.findElementByXPath("(//span[text()='Please Select'])[1]").click();
		 driver.findElementByXPath("//div[text()='-1.00']").click();
		 
		//Left Eye 
		 Select LE = new Select(driver.findElementByXPath("(//select[@name='boxes'])[2]")); 
		 LE.selectByValue("2"); 
		 
		 driver.findElementByXPath("(//span[text()='Please Select'])[1]").click();
		 driver.findElementByXPath("//div[text()='-1.00']").click();	 
	}

	@Given("Type your name in User's name")
	public void typeYourNameInUserSName() {
          driver.findElementById("example-text-input").sendKeys("Ajanthan");  
	}

	@When("And click Save and continue")
	public void andClickSaveAndContinue() {
		driver.findElementByXPath("//button[text()='SAVE & CONTINUE']").click();	
	}

	@Then("Print total amount and click Proceed to Checkout")
	public void printTotalAmountAndClickProceedToCheckout() throws InterruptedException {
		String total = driver.findElementByXPath("//span[text()='Order Total :']/..//span[2]").getText(); 
	    System.out.println("Total Amount for the Lenses: Rs." + total); 
	    
	    driver.findElementByXPath("//span[text()='Proceed To Checkout']").click(); 
	    Thread.sleep(3000);
	}
}
