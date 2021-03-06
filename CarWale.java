package steps;


		import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
		import java.util.concurrent.TimeUnit;

		import org.openqa.selenium.By;
		import org.openqa.selenium.JavascriptExecutor;
		import org.openqa.selenium.Keys;
		import org.openqa.selenium.WebElement;
		import org.openqa.selenium.chrome.ChromeDriver;
		import org.openqa.selenium.chrome.ChromeOptions;
		import org.openqa.selenium.interactions.Actions;
		import org.openqa.selenium.support.ui.ExpectedConditions;
		import org.openqa.selenium.support.ui.Select;
		import org.openqa.selenium.support.ui.WebDriverWait;

		import cucumber.api.java.en.Given;
		import cucumber.api.java.en.Then;
		import cucumber.api.java.en.When;

		public class CarWale {
			
			public ChromeDriver driver;
			public   JavascriptExecutor js;
			@Given("Lauch the CarWale Application")
			public void lauchCarwaleCom() {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver 83.exe"); 
				driver = new ChromeDriver();
				driver.get("https://www.carwale.com");
				driver.manage().window().maximize(); 
			    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			   
			}
			
			
			
		
			@Given("Click on used")
			public void clickOnUsed() throws InterruptedException {
				Thread.sleep(10000);
				
			    driver.findElementByXPath("//li[contains(@data-tabs,'usedCars')]").click();
			}

			@Given("Select the city as Chennai")
			public void selectTheCityAsChennai() {
				driver.findElementById("usedCarsList").sendKeys("Chennai");
			    driver.findElementByXPath("//a[@cityname='chennai,tamilnadu']").click();
			}

			@Given("Select budget min 8L amd max 12L and Click search")
			public void selectBudgetMinLAmdMaxLAndClickSearch() {
			    driver.findElementByXPath("(//li[text()='8 Lakh'])[1]").click();
			    driver.findElementByXPath("(//li[text()='12 Lakh'])[2]").click();
			    driver.findElementByXPath("//button[@id='btnFindCar']").click();
			    
			}

			@Given("Select Cars with photos only show cars with")
			public void selectCarsWithPhotosOnlyShowCarsWith() throws InterruptedException {
				  Thread.sleep(10000);
				    driver.findElementByXPath("//span[text()='Cars with Photos']").click();
			}

			@Given("Select Manufacturer as Hyndai --> Creta")
			public void selectManufacturerAsHyndaiCreta() throws InterruptedException {
				 	Thread.sleep(10000);
				 	js = (JavascriptExecutor) driver;
				    js.executeScript("window.scrollBy(0,500)");
				    driver.findElementByXPath("(//li[@data-manufacture-en='Hyundai']//span)[1]").click();
				    WebElement creta=driver.findElementByXPath("//span[text()='Creta']");
				    creta.click();
			}

			@Given("Select Fuel type as Petrol")
			public void selectFuelTypeAsPetrol() throws InterruptedException {
				    Thread.sleep(10000);
				    js = (JavascriptExecutor) driver;
				    js.executeScript("window.scrollBy(0,250)");
				    driver.findElementByXPath("(//h3[@class='sub-values'])[6]").click();
				    driver.findElementByXPath("(//span[text()='Petrol'])[1]").click();
				    
			}

			@Given("Select BEst Match as KM: low to high")
			public void selectBEstMatchAsKMLowToHigh() throws InterruptedException {
					Thread.sleep(10000);
				    WebElement bestMatches=driver.findElementByXPath("//select[@id='sort']");
				    Select lowTOHigh=new Select(bestMatches);
				    lowTOHigh.selectByVisibleText("KM: Low to High");
			}

			@Given("Validate the cars are listed with Kms Low to High and Add the Least KM run car to WishList")
			public void validateTheCarsAreListedWithKmsLowToHigh() throws InterruptedException {
					List<WebElement> list=driver.findElementsByXPath("//tr[@data-role='click-tracking']/td[1]/span");
					List<Integer> lowToHighList=new ArrayList<Integer>();
					for (int i = 0; i < list.size(); i++) {
					   int km=Integer.parseInt((list.get(i).getText()).replaceAll("\\D",""));
					   System.out.println(km);
				    	lowToHighList.add(km);
					    	}  
					List<Integer> lowToHighListSorted=new ArrayList<Integer>();
					lowToHighListSorted.addAll(lowToHighList);
					Collections.sort(lowToHighListSorted);
					 
					if (lowToHighList.equals(lowToHighListSorted)) {
						System.out.println("Sorted successfully");
					} else {
						System.out.println("Sorted is not happened");
					}
					System.out.println("Least Km ran Car's kms:"+lowToHighListSorted.get(0));
					
					Thread.sleep(10000);
					driver.findElementByXPath("(//a[text()=\"Don't show anymore tips\"])[1]").click();
					for (int i = 0; i < lowToHighList.size(); i++) {
						if (lowToHighListSorted.get(0)==lowToHighList.get(i)) {
							Thread.sleep(10000);
							WebElement wishList=driver.findElementByXPath("(//span[@class='shortlist-icon--inactive shortlist'])["+(i+1)+"]");
						js.executeScript("arguments[0].click();",wishList);
						break;
						}}
					}
			

			@Given("Go to wishlist and Click on More Details")
			public void goToWishlistAndClickOnMoreDetails() throws InterruptedException {
				    Thread.sleep(10000);
				    driver.findElementByXPath("//li[@data-role='click-tracking']").click();
				    Thread.sleep(10000);
				    driver.findElementByXPath("//a[text()='More details �']").click();
				    Set<String> windowSet=driver.getWindowHandles();
			        List<String> windowList=new ArrayList<String>(windowSet);
			        driver.switchTo().window(windowList.get(1));
			}

			@When("Print all the details under overview in the sameway as displayed")
			public void printAllTheDetailsUnderOverviewInTheSamewayAsDisplayed() {
				 Map<String,String> overview=new LinkedHashMap<String,String>();
			   List<WebElement> key=driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width text-light-grey']");
		     List<WebElement> value=driver.findElementsByXPath("//div[@id='overview']//div[@class='equal-width dark-text']");
		     for (int i = 0; i < key.size(); i++) {
			     	String keys=key.get(i).getText();
		        String values=value.get(i).getText();
			     	overview.put(keys, values);
		     	}
			    for (Entry<String,String> i : overview.entrySet()) {
		          System.out.println(i.getKey()+"---------"+i.getValue());
					}

			}

			@Then("Close the browser")
			public void closeTheBrowser() {
						driver.quit();
			}
		}