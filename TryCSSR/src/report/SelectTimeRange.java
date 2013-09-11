package report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.testng.Assert;

public class SelectTimeRange extends Login{
	private static final String odipath = "/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/a"; 
	private static final String reportspath="/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/ul/li[2]/a";
	private static final String cssrpath="/html/body/div[5]/div[2]/div/div[2]/ul/li[4]/ul/li[2]/ul/li/a";
	public static void entertime() {
	try 
	{
	//wait for page to load after sign in
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//click On Demand Insight
	WebElement odi= (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(odipath)));
	odi.click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement report=(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(reportspath)));
	report.click();
	WebElement cssr= (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(cssrpath)));
	cssr.click();
	driver.findElement(By.id("PARAM_START_DATE")).clear();
	//to handle the alert that pops up 
	Alert alert = driver.switchTo().alert();
	alert.dismiss();
	driver.findElement(By.id("PARAM_START_DATE")).sendKeys("09/05/2013");
	driver.findElement(By.id("PARAM_END_DATE")).clear();
	alert.dismiss();
	driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/06/2013");
	driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	catch (Exception e)
	{
		System.out.print("trace: ");
		e.printStackTrace();
	}
	finally
	{
		driver.manage().timeouts().implicitlyWait(30000000, TimeUnit.SECONDS);	
		driver.quit();
	}
	
}
	private static boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
}
