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
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement odi= (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(odipath)));
	odi.click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	WebElement report=(new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(reportspath)));
	report.click();
	WebElement cssr= (new WebDriverWait(driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(cssrpath)));
	cssr.click();
	/*driver.findElement(By.id("date_range")).click();
    new Select(driver.findElement(By.id("date_range"))).selectByVisibleText("Yesterday");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    
    driver.findElement(By.id("iExport")).click();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.linkText("Export to PDF")).click();
    */
	driver.findElement(By.id("PARAM_START_DATE")).clear();
	Alert alert = driver.switchTo().alert();
	alert.dismiss();
//	WebElement enddate = driver.findElement(By.id("PARAM_END_DATE"));
//	String enddateValue = enddate.getAttribute(enddateValue);
	
	//Calendar cal = Calendar.getInstance(); //java.util.calendar
	//DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
//	c
	driver.findElement(By.id("PARAM_START_DATE")).sendKeys("09/05/2013");
	//driver.findElement(By.xpath("//a[contains(text(),'5')]")).click();
	driver.findElement(By.id("PARAM_END_DATE")).clear();
	alert.dismiss();
	
	driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/06/2013");
	//driver.findElement(By.xpath("//a[contains(text(),'6')]")).click();
	driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//	Assert.assertEquals("From 9/ 5/2013 12:00AM to 9/ 6/2013 12:00AM PT", driver.findElement(By.cssSelector("span.fc1378763318070-1")).getText());
	//Assert.assertEquals("Call Statistics Summary Report", driver.findElement(By.className("fc1378768758027-1")).getText());
	Assert.assertTrue(isElementPresent(By.xpath("//div[2]")));
	Assert.assertTrue(isElementPresent(By.xpath("//div[@id='CrystalViewercridreportpage']/div[23]/div/div/span")));
	
	}
	catch (Exception e)
	{
		System.out.print("trace: ");
		e.printStackTrace();
	}
	finally
	{
		//driver.manage().timeouts().implicitlyWait(3000000, TimeUnit.SECONDS);	
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
