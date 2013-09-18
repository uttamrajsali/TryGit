package report;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectTimeRange extends Login{
	
	public static Logger logger = LoggerFactory.getLogger(Main.class);
	private static final String odipath = "//a[contains(text(),'On Demand Insight')]"; 
	private static final String reportspath="//a[contains(text(),'Reports')]";
	private static final String cssrpath="//a[contains(text(),'Call Statistics Summary Report')]";
	private static String Element1 = "TimeRange";
	private static String Element2 = "StartTime";
	private static String Element3 = "EndTime";
	private static String Element4 = "TimeZone";
	private static String Element5 = "Application";
	private static String Element6 = "DNIS";
	private static String Element7 = "Language";
	public static WriteXmlFile ReportFile;
	
	public SelectTimeRange(){
	execute();//here it calls login.execute method
	}
	
	//Method for going to home page evertime after submitting and for a new testcase ex. after getting the report is for
	//default range Iam again checking for DNIS filter value if its present so for that i have to go back to home page and and come
	//to submit page...because directly i cant select the dnis filter value for some reason
	public void gotohomepage(){
		try{
			WebElement home = driver.findElement(By.linkText("Home"));
			home.click();
		}
		catch(NoSuchElementException e)
		{
			logger.info("can't find home button on the page");
			driver.switchTo().defaultContent();
			gotohomepage();
		}
	}
	
	//Method for getting to reports page
	public void gotoreports(String domainName) {
		gotohomepage();
		choosedomain(domainName);
	try 
	{
	//wait for page to load after sign in
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//click On Demand Insight
	WebElement odi= (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(By.xpath(odipath)));
	odi.click();
	WebElement report=(new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(By.xpath(reportspath)));
	report.click();
	WebElement cssr= (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(By.xpath(cssrpath)));
	cssr.click();
	}
	catch (Exception e)
	{
		System.out.print("trace: ");
		e.printStackTrace();
	}
	}
	//Method for finding the lables of filters
	public void findthelables(){
	String dmname="US_AIRWAYS";
	gotoreports(dmname);
	try{	
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	isElementpresent(Element1, By.id("date_range_label"));
	isElementpresent(Element2, By.id("PARAM_START_DATE_label"));
	isElementpresent(Element3, By.id("PARAM_END_DATE_label"));
	isElementpresent(Element4, By.id("PARAM_TIME_ZONE_label"));
	isElementpresent(Element5, By.id("PARAM_APP_ID_label"));
	isElementpresent(Element6, By.id("PARAM_DNIS_label"));
	isElementpresent(Element7, By.id("PARAM_LOCALE_label"));
	}
	catch (Exception e)
	{
		System.out.print("trace: ");
		e.printStackTrace();
	}
	}
	
	public void search(){
	String dmName="US_AIRWAYS";
	gotoreports(dmName);
	try{
	driver.findElement(By.id("PARAM_START_DATE")).sendKeys("value");
	driver.findElement(By.id("PARAM_END_DATE")).sendKeys("value");
	driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
	WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
	WebElement rangeStart=page.findElement(By.xpath("//*[contains(text(),'9/17/2013')]"));
	WebElement rangeEnd=page.findElement(By.xpath("//*[contains(text(),'9/17/2013')]"));
	if(rangeStart!=null&&rangeEnd!=null)
	{
	System.out.println("Report is for default range");
	}
	ReportFile = new WriteXmlFile();
	driver.switchTo().defaultContent();
	ReportFile.addTestCase("Test to verify labeling ", true);
	ReportFile.WriteToFile();
	//driver.quit();
	}
	catch (Exception e)
	{
		System.out.print("trace: ");
		e.printStackTrace();
	}
}
	//Method for DNIS value search
	public void dnisfilter(){
	String dmName="US_AIRWAYS";
	gotoreports(dmName);
	try{
	WebElement DNISvalue = driver.findElement(By.id("PARAM_DNIS"));
	Select select = new Select(DNISvalue);
	select.deselectAll();
	select.selectByValue("2404953077");
	driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
	WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
	WebElement tag=page.findElement(By.xpath("//*[contains(text(),'DNIS')]"));
	if(tag != null)
	{
	System.out.println("DNIS:2404953077 filter is present");
	}
	}catch (Exception e)
	{
		System.out.print("trace: ");
		e.printStackTrace();
	}
	//driver.quit();
}
	public void abfilter(){
		String dmName="METROPCS";
		gotoreports(dmName);
		try{
			
			
		}catch(Exception e)
		{
			
		}
	}
	//Method that I invoke in findlablesfilter() function
	private void isElementpresent(String name, By by){
		try
		{
		if(driver.findElement(by)!= null)
			{
			System.out.println(name+" "+"Element  is Present");
			}else
			{
			//System.out.println("Element is Absent");
				throw new Exception(name +" "+"Element is absent");
			}	
		} catch (Exception e) 
			{
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
	}
		
}
