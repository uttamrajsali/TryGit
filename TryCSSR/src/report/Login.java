package report;

import java.io.IOException;
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
import report.UserInfo;


public class Login {

	public static WebDriver driver;
	public static String url = null;
	public static String username = null;
	public static String password = null;
	public static String browser = null;
	public static UserInfo user;
	public static final String projectPath = System.getProperty("user.dir");

	private static final String userName = "id('username')";
	private static final String passwordXPath = "//*[@id='password']";
	private static final String submitXPath = "html/body/div[2]/form/div/table/tbody/tr[4]/td[2]/div/input[3]";
	private static final String odipath = "/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/a"; 
	private static final String reportspath="/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/ul/li[2]/a";
	private static final String cssrpath="/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/ul/li[2]/ul/li/a";
	public static  void execute() {
		try
		{
			createNewWebDriver();
			
			getuserinfo();
			openURL(driver);
			WebElement usrname = findElement(driver, userName);
			WebElement pwd = findElement(driver, passwordXPath);
			WebElement submit = findElement(driver, submitXPath);
			//filling the username and password fields
			usrname.sendKeys(username);
			pwd.sendKeys(password);
			submit.submit();
			
		}
		catch (Exception e)
		{
			System.out.print("trace: ");
			e.printStackTrace();
		}
		
	}

	//selecting the webdriver
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

//Method to find the Element using xpath
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
//opening the webpage
	private static void openURL(WebDriver driver) {
		driver.get(url);
		
	}
	public static void getuserinfo() 
	{
		try {
			user =  new UserInfo("userinfo.xml");
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		url=user.getProperties("weburl");		
		username = user.getProperties("username");		
		password = user.getProperties("password");
		
		if (username ==null || username.isEmpty())
		{
			throw new NullPointerException("null username found");
		}
		else if (password == null|| password.isEmpty())
		{
			throw new NullPointerException("null password found");
		}
		
		
	}

}
