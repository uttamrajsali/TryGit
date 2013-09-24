package report;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
	private String content;
	private String contentValue;
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
		//	driver.quit(); i guess this is the one I have to replace because of error"  Main:51 - can't find home button on the page"
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
	
	//odi.618 Method for finding the lables of filters 
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

	
	//Method that I invoke in findlablesfilter() function in the above method
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
	//odi.614
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
	//odi.623.Method for DNIS value search
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
	//odi.620 Method that the report footer a/b filter should match all as it specified in left side panel
	public void abfilter(){
		String dmName="METROPCS";
		gotoreports(dmName);
		try{
			//WebElement ABvalue = driver.findElement(By.id("PARAM_APP_VARIANT_ID"));
			//Select select = new Select(ABvalue);
			//select.deselectAll();
			//select.selectByValue("NVP 5.0.0-NDF 6.1.0-0.0.0.1");
			//NVP 5.2.1-NDF 6.1.0-3.1.0.0rc1, NVP 5.2.1-NDF 6.1.0-3.1.0.0rc2, NVP 5.2.1-NDF 6.1.0-3.1.0.0rc2a, NVP 5.2.1-NDF 6.
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			WebElement tag=page.findElement(By.xpath("//*[contains(text(),'A/B')]"));
		
			if(tag != null)
			{
			System.out.println("A/B Combo ID: NVP 5.0.0-NDF 6.1.0-0.0.0.1 filter is present");
			}
		}catch (Exception e)
			{
				System.out.print("trace: ");
				e.printStackTrace();
			}
	}
	public void pickAvalidDate (){
		driver.findElement(By.id("PARAM_START_DATE")).clear();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		driver.findElement(By.id("PARAM_START_DATE")).sendKeys("08/01/2013");
		driver.findElement(By.id("PARAM_END_DATE")).clear();
		alert.dismiss();
		driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/01/2013");
		
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
	}
	//generate cssr with valid filters and check
	public void cssrNumbers (){
		int CallVolumeValue =0, TransfersValue = 0, PeakHourValue = 0;
		double roundOff =0, AverageCallValue =0;
		String dmName="US_AIRWAYS";
		gotoreports(dmName);
		pickAvalidDate();
		try{
			/*driver.findElement(By.id("PARAM_START_DATE")).clear();
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			driver.findElement(By.id("PARAM_START_DATE")).sendKeys("08/01/2013");
			driver.findElement(By.id("PARAM_END_DATE")).clear();
			alert.dismiss();
			driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/01/2013");
			
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			*/
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			List<WebElement> check= page.findElements(By.tagName("span"));
			for(int i=0;i<check.size();i++)
			{
				String content= (check.get(i).getText());
				
				//String[] kx = content.split("\\s");
				if (content.equals("Call Volume")){
					//System.out.println(kx[1]+kx[0]);
					String CallVolume = (check.get(i+1).getText());
					String[] CallVolumes = CallVolume.split("\\s");
				//	System.out.println(jx[1]+jx[0]);
					CallVolumeValue= Integer.parseInt(CallVolumes[1]);
					System.out.println(CallVolumeValue);
					
				}
				else if(content.equals("Transfers (All)")){
					
					String  TransfersAll= (check.get(i+1).getText());
					String[] Tvalues= TransfersAll.split("\\s");
					TransfersValue=Integer.parseInt(Tvalues[1]);
					System.out.println(TransfersValue);
					
				}
				else if(content.equals("Peak Hour Call Volume")){
					String PeakHourCallVolume = (check.get(i+1).getText());
					String[] PeakHourCallValues= PeakHourCallVolume.split("\\s");
					PeakHourValue = Integer.parseInt(PeakHourCallValues[1]);
				}
				else if (content.equals("Call Duration (minutes)")){
					String CallDuration= (check.get(i+1).getText());
					String[] CallDurationValues= CallDuration.split("\\s");
					double CallDurationValue=Double.parseDouble(CallDurationValues[1]);
					double x= CallDurationValue/CallVolumeValue;
				    roundOff =Math.round(x*100.0)/100.0;
				/*else if(content.equals("Peak Hour Call Volume")){
					String PeakHourCallValue = (check.get(i+21).getText());
					String[] PeakHourCallValues= PeakHourCallValue.split("\\s");
					int PeakHourCallValuesConvert=Integer.parseInt(PeakHourCallValues[1]);
					String AverageCallValue= (check.get(i+11).getText());
					String[] AverageCallValues= AverageCallValue.split("\\s");
					double AverageCallValuesConvert= Double.parseDouble(AverageCallValues[1]);
					String CallDurationValue= (check.get(i+9).getText());
					String[] CallDurationValues= CallDurationValue.split("\\s");
					double CallDurationValuesConvert=Double.parseDouble(CallDurationValues[1]);
					double x= CallDurationValuesConvert/jc;
					double roundOff =Math.round(x*100.0)/100.0;
					
					
					System.out.println(PeakHourCallValuesConvert);
					System.out.println(AverageCallValuesConvert);
					System.out.println(CallDurationValuesConvert);
					System.out.println(x);
					System.out.println(roundOff);
					*/
				 
					/*if(PeakHourCallValuesConvert <=jc ) {
						System.out.println("this is second true");
					}if (AverageCallValuesConvert == roundOff){
						System.out.println("this is third is true");
						
					}
					
					/*if (jx[1].equals("11")){
						System.out.println(jx[1]+jx[0]);
					}*/
					
					
				
				}
				else if (content.equals("Average Call Duration (minutes)")){
					String AverageCallDuration= (check.get(i+1).getText());
					String[] AverageCallDurationValues= AverageCallDuration.split("\\s");
					AverageCallValue= Double.parseDouble(AverageCallDurationValues[1]);					
							
				}
			
			
			
				/*else if (k.equals(" 11")){
					//System.out.println(jx[1]+jx[0]);
				}*/
				//String k= (check.get(i).getText()); // changes string to int
				//int j= Integer.parseInt(check.get(i+2).getText());
					//if (k >= j ){
				//		System.out.println("true");
					//}	 
					
						  
				//}
			//
			}
			if (CallVolumeValue >= TransfersValue){
				
				logger.info("the call volume is greater than the Transfer");
				System.out.println("True");
			}
			if (PeakHourValue <= CallVolumeValue){
				System.out.println("this is second true");
			}
			 if (AverageCallValue == roundOff){
					System.out.println("this is third is true");
			 }
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			driver.quit();	
			
		}
	//Number formatting
	
	public void numberFormat () {
		String dmName="US_AIRWAYS";
		gotoreports(dmName);
		pickAvalidDate();
		try{
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			List<WebElement> check= page.findElements(By.tagName("span"));
			for(int i=0;i<check.size();i++)
			{
				//System.out.println(i);
				content=check.get(i).getText();
				//System.out.println(content);
				//contentValue=check.get(i+1).getText();
				if(content.equals("Call Volume")){
					contentValue = check.get(i+1).getText();
					System.out.println(contentValue);
					decimalcheck(contentValue, "example", 0);
					System.out.println(contentValue);
				}
				/*if(content.equals("Call Duration (minutes)"))
						{
					contentValue=check.get(i+1).getText();
					int precision=2;
					decimalcheck(contentValue, "In call duration", precision);	
						}
				else if(content.equals("Average Call Duration (minutes)"))
				{
					contentValue=check.get(i+1).getText();
					decimalcheck(contentValue,"In average call duration",2 );
				}
				//decimalcheck("Call Duration (minutes)", contentValue);
				//decimalcheck("Average Call Duration (minutes)", contentValue);
				else if(content.equals("Average Call Duration for Transferred Calls (minutes)"))
				{
					contentValue=check.get(i+1).getText();
					decimalcheck(contentValue, "In average call duration for transfered calls", 2);	
				}
				else if(content.equals("Peak Hour Average Call Duration (minutes)"))
				{
					contentValue=check.get(i+1).getText();
					decimalcheck(contentValue, "In peak hour average call duration", 2);
				}
				else if (content.equals("Average Weekly Call Volume")){
					contentValue=check.get(i+1).getText();
					decimalcheck(contentValue, "Average Weekly call volume", 1);
					
				}*/

				if (content.equals("Transfer Rate (All)")){
					String percentage=check.get(i+1).getText();
					String[] contentValues= percentage.split("%"); 
					//System.out.println(contentValue);
					contentValue= contentValues[0];
					System.out.println(contentValue);
					decimalcheck(contentValue, "In Transfer Rate ", 1);
					/*for(int j=0; j<contentValues.length;j++){
					System.out.println(contentValues[j]);
					//System.out.println(contentValues[1]);
					 */
					}
				else if(content.equals("Containment Rate (All)")){
					String percentage=check.get(i+1).getText();
					String[] contentValues= percentage.split("%"); 
					System.out.println(contentValue);
					contentValue= contentValues[0];
					System.out.println(NumberFormat.getNumberInstance(Locale.US).format(contentValue));
					decimalcheck(contentValue, "In Containment Rate ", 1);
					
				}
				}
			
			/*{
			 k= (check.get(i).getText());
				//String[] kx = k.split("\\s");
			//all minutes should be decimal
				decimalcheck();
			if (k.equals("Call Duration (minutes)")){
				System.out.println(i);
				String j = (check.get(i+1).getText());
				String[] jx = j.split("\\s");
				double jxc= Double.parseDouble(jx[1]);
				//checkDecimalPlaces (jxc, 2);
				if (checkDecimalPlaces(jxc, 2) == true){
					logger.info("we have found the decimal with 2 precision");
				}
				//not yet executed
			} if (k.equals("Average Call Duration (minutes)")){
				System.out.println(i);
				String ACD = (check.get(i+1).getText());
				String[] ACDs=ACD.split("\\s");
				double ACDvalue=Double.parseDouble(ACDs[1]);
				if(checkDecimalPlaces (ACDvalue, 2)==true) {
					logger.info("this confirms Average call duration has 2 decimal points");
				}

			} if (k.equals("Average Call Duration for Transferred Calls (minutes)")){
				System.out.println(i);
				String TC = (check.get(i+1).getText());
				String[] TCs=TC.split("\\s");
				double TCsvalue=Double.parseDouble(TCs[1]);
				if(checkDecimalPlaces (TCsvalue, 2)==true) {
					logger.info("this confirms Average call duration has 2 decimal points");
				}
			} if (k.equals("Peak Hour Average Call Duration (minutes)")){
				System.out.println(i);
			}
			
			}*/
			//driver.quit();
		}catch(Exception e)
		{
			driver.quit();
			e.printStackTrace();
		}
		
	}
	//Method to invoke decimal checking in the above method
	private void decimalcheck(String x, String y, int z) {
		//if (content.equals(x)){
			//System.out.println(i);
			//String j = (check.get(i+1).getText());
			String[] contents = x.split("\\s");
			double contentsConvert= Double.parseDouble(contents[1]);
			//checkDecimalPlaces (jxc, 2);
			
			if (checkDecimalPlaces(contentsConvert, z) == true){
				logger.info(y+" "+ "we have found the decimals with "+ z +"precision");
			}
		// TODO Auto-generated method stub
		
		}
	

	//Method that checks for 2 decimal places in above function
	public boolean checkDecimalPlaces (double d, int decimalPlaces){
		if (d==0) return true;

	    double multiplier = Math.pow(10, decimalPlaces); 
	    double check  =  d * multiplier;
	    check = Math.round(check);  	
	    check = check/multiplier; 
		return (d==check);
		
	}
	
	
	
		
}
