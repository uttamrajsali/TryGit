package report;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login {

	public static WebDriver driver;
	public static String url = "https://sun-qa-ncp03clone.engca.bevocal.com:8443/np";
	public static String username = "yujie_xu";
	public static String password = "nuance1";
	public static String browser = null;
	public static final String projectPath = System.getProperty("user.dir");

	private static final String userName = "id('username')";
	private static final String passwordXPath = "//*[@id='password']";
	private static final String submitXPath = "html/body/div[2]/form/div/table/tbody/tr[4]/td[2]/div/input[3]";
	private static final String odipath = "/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/a"; 
	private static final String reportspath="/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/ul/li[2]/a";
	private static final String cssrpath="/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/ul/li[2]/ul/li/a";
	public static void execute() {
		try
		{
			createNewWebDriver();
			openURL(driver);
			WebElement usrname = findElement(driver, userName);
			WebElement pwd = findElement(driver, passwordXPath);
			WebElement submit = findElement(driver, submitXPath);
			usrname.sendKeys(username);
			pwd.sendKeys(password);
			submit.submit();
			/*WebElement odi= (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(odipath)));
			odi.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement report=findElement(driver, reportspath);
			report.click();
			WebElement cssr= (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(cssrpath)));
			cssr.click();
			driver.findElement(By.id("PARAM_START_DATE")).clear();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    
			driver.findElement(By.id("PARAM_START_DATE")).sendKeys("09/11/2013");
//driver.findElement(By.cssSelector("#PARAM_START_DATE")).sendKeys("09/11/2013");
			driver.findElement(By.id("PARAM_END_DATE")).clear();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    
			driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/11/2013");
			//driver.findElement(By.id("date_range")).click();
		    //new Select(driver.findElement(By.id("date_range"))).selectByVisibleText("Yesterday");
		    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    
		    driver.findElement(By.id("iExport")).click();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    //driver.findElement(By.xpath("/html/body/div[12]/div/ul/li/a")).click();
		    driver.findElement(By.linkText("Export to PDF")).click();
		    
		  // java.lang.Runtime.getRuntime().exec("autoit/test3.exe"); //when i use ie webdriver it is not able to invoke test2.exe
		  */
		}
		catch (Exception e)
		{
			System.out.print("trace: ");
			e.printStackTrace();
		}
		/*finally
		{
			driver.manage().timeouts().implicitlyWait(30000000, TimeUnit.SECONDS);	
			//driver.quit();
		}*/
	}

	
	public static void createNewWebDriver() {
		switch(browser.toLowerCase()){
		case "chrome":
        	System.setProperty("webdriver.chrome.driver", projectPath+"\\chromedriver\\chromedriver.exe");
        	driver = new ChromeDriver();
        	break;
    	case "firefox":
    		driver = new FirefoxDriver();
    		break;
    	case "ie":
    		System.setProperty("webdriver.ie.driver", projectPath+"\\iedriver\\IEDriverServer.exe");
    		driver = new InternetExplorerDriver();
    		break;
    	default:
    		break;
		
		}
		
	}


	private static  WebElement findElement(WebDriver driver, String xpath) {
		try
		{
			WebElement element = driver.findElement(By.xpath(xpath));
			return element;
		}
		catch(Exception ex)
		{
			throw new NoSuchElementException("no such element");
		}
	}

	private static void openURL(WebDriver driver) {
		driver.get(url);
		
	}

}
