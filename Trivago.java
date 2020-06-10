package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Trivago
{
	public ChromeDriver driver;
	public   JavascriptExecutor js;
	
	@Given("Go to https://www.trivago.com/")
	public void goToHttpsWwwTrivagoCom() throws InterruptedException 
	{
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver 83.exe"); 
	driver = new ChromeDriver();
	driver.get("https://www.trivago.com/");
    driver.manage().window().maximize(); 
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Thread.sleep(3000);
    //driver.findElementById("onetrust-accept-btn-handler").click();    
	}

	@Given("Type Agra in Destination and select Agra, Uttar Pradesh")
	public void typeAgraInDestinationAndSelectAgraUttarPradesh() throws InterruptedException 
	{
	Thread.sleep(3000);
	driver.findElementById("querytext").sendKeys("Agra"); 
	driver.findElementByXPath("//span[text()='City - Uttar Pradesh, India']").click();	    
	}

	@Given("Choose June {int} as check in and June {int} as check out")
	public void chooseMayAsCheckInAndJuneAsCheckOut(Integer int1, Integer int2) throws InterruptedException 
	{
	js =(JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(0,300)");
    Thread.sleep(5000);
	driver.findElementByXPath("//time[@datetime='2020-06-15']").click();
	Thread.sleep(2000);
	driver.findElementByXPath("//time[@datetime='2020-06-18']").click();
	}

	@Given("Select Room as Family Room")
	public void selectRoomAsFamilyRoom() 
	{
	driver.findElementByXPath("//span[text()='Family rooms']").click();  
	}

	@Given("Choose Number of Adults {int}, Childern {int} and set Child's Age as {int}")
	public void chooseNumberOfAdultsChildernAndSetChildSAgeAs(Integer int1, Integer int2, Integer int3) 
	{
    //For Adults
	WebElement ele = driver.findElementById("select-num-adults-2");
	Select dd= new Select(ele);
	dd.selectByVisibleText("2");   
	//For Adults
	WebElement ele2 = driver.findElementById("select-num-children-2");
	Select dd2= new Select(ele2);
	dd2.selectByVisibleText("1");
	//For Adults
	WebElement ele3 = driver.findElementById("select-ages-children-2-3");
	Select dd3= new Select(ele3);
	dd3.selectByVisibleText("4");
	}

	@Given("Click Confirm button and click Search")
	public void clickConfirmButtonAndClickSearch() 
	{
	driver.findElementByXPath("//span[text()='Confirm']").click(); 	  
	driver.findElementByXPath("//span[text()='Search']").click(); 	  	    
	}

	@Given("Select Accommodation type as Hotels only and choose {int} stars")
	public void selectAccommodationTypeAsHotelsOnlyAndChooseStars(Integer int1) throws InterruptedException 
	{
	driver.findElementByXPath("//span[text()='All types']").click(); 	     
	driver.findElementByXPath("(//input[@name='accommodation-type'])[2]").click();	
	Thread.sleep(3000);
	driver.findElementByXPath("//button[@title='4-star hotels']	").click(); 	 
	driver.findElementById("filter-popover-done-button").click();
	}

	@Given("Select Guest rating as Very Good")
	public void selectGuestRatingAsVeryGood() throws InterruptedException 
	{
	Thread.sleep(1500);
	WebElement elementHover = driver.findElementByXPath("//strong[text()='Guest rating']");
	Actions builder = new Actions(driver);	
	builder.moveToElement(elementHover).perform();
	driver.findElementByXPath("//span[text()='Very good']").click();    
	}

	@Given("Set Hotel Location as Agra Fort and click Done")
	public void setHotelLocationAsAgraFortAndClickDone() throws InterruptedException
	{
	Thread.sleep(1500);
	WebElement elementHover = driver.findElementByXPath("//span[text()='City center']");
	Actions builder = new Actions(driver);	
	builder.moveToElement(elementHover).perform();   
	WebElement ele4 = driver.findElementById("pois");
	Select dd4= new Select(ele4);
	dd4.selectByVisibleText("Agra Fort");    
	driver.findElementByXPath("//button[text()='Done']").click();
	}

	@Given("In more Filters, select Air conditioning, Restaurant and WiFi and click Done")
	public void inMoreFiltersSelectAirConditioningRestaurantAndWiFiAndClickDone() 
	{
	WebElement elementHover = driver.findElementByXPath("//strong[text()='More filters']");
	Actions builder = new Actions(driver);		
	builder.moveToElement(elementHover).perform(); 	
	driver.findElementByXPath("//label[text()='Air conditioning']").click();
	driver.findElementByXPath("//label[text()='Restaurant']").click();
	driver.findElementByXPath("//label[text()='WiFi']").click();
	driver.findElementByXPath("//button[text()='Done']").click();	   	    
	}

	@Given("Sort the result as Rating & Recommended")
	public void sortTheResultAsRatingRecommended()
	{
	WebElement ele3 = driver.findElementById("mf-select-sortby");
	Select dd3= new Select(ele3);
	dd3.selectByVisibleText("Rating & Recommended");       
	}

	@Given("Print the Hotel name, Rating, Number of Reviews and Click View Deal")
	public void printTheHotelNameRatingNumberOfReviewsAndClickViewDeal() 
	{
	System.out.println("Hotel Name is " +driver.findElementByXPath("(//span[@class='item-link name__copytext'])[1]").getText());
	System.out.println("Hote Rating is " +driver.findElementByXPath("(//span[@itemprop='ratingValue'])[1]").getText());
	System.out.println("Number of Review is " +driver.findElementByXPath("//span[contains(text(),'reviews')]").getText());
    driver.findElementByXPath("(//span[text()='View Deal'])[1]").click();
	}

	@Given("Print the URL of the Page")
	public void printTheURLOfThePage() 
	{
	Set<String> window = driver.getWindowHandles();  
	List<String> winList = new ArrayList<String>(window);
	driver.switchTo().window(winList.get(1));
	System.out.println("URL:"+driver.getCurrentUrl());
	}

	@Given("Print the Price of the Room and click Choose Your Room")
	public void printThePriceOfTheRoomAndClickChooseYourRoom()
	{
	       
	}

	@When("Click Reserve and I'll Reserve")
	public void clickReserveAndILlReserve()
	{
	   	    
	}			
	
	@Then("Close the browser")
	public void closeTheBrowser()
	{
	driver.quit();
	}

}