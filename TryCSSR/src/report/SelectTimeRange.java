package report;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectTimeRange extends Login{
	//private static WebDriver driver;
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
	driver.findElement(By.id("PARAM_START_DATE")).sendKeys("12/12/2013");
	driver.findElement(By.xpath("//a[contains(text(),'5')]")).click();
	driver.findElement(By.id("PARAM_END_DATE")).click();
	driver.findElement(By.xpath("//a[contains(text(),'6')]")).click();
	driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    
	}
	catch (Exception e)
	{
		System.out.print("trace: ");
		e.printStackTrace();
	}
	finally
	{
		driver.manage().timeouts().implicitlyWait(300000, TimeUnit.SECONDS);	
		driver.quit();
	}
}
}