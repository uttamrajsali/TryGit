package com.nuance;

import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectTimeRange extends Mainpage{
	
	public static Logger logger = LoggerFactory.getLogger(Main.class);
	private static String Element1 = "TimeRange";
	private static String Element2 = "StartTime";
	private static String Element3 = "EndTime";
	private static String Element4 = "TimeZone";
	private static String Element5 = "Application";
	private static String Element6 = "DNIS";
	private static String Element7 = "Language";
	private String content;
	private String contentValue;
	public SelectTimeRange(){
	gotomainpage();
	}
		
	//odi.618 Method for finding the lables of filters 
	public void findthelables(){
		cssrReport();
		try{	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		isElementpresent(Element1, By.id("date_range_label"));
		isElementpresent(Element2, By.id("PARAM_START_DATE_label"));
		isElementpresent(Element3, By.id("PARAM_END_DATE_label"));
		isElementpresent(Element4, By.id("PARAM_TIME_ZONE_label"));
		isElementpresent(Element5, By.id("PARAM_APP_ID_label"));
		isElementpresent(Element6, By.id("PARAM_DNIS_label"));
		isElementpresent(Element7, By.id("PARAM_LOCALE_label"));
		ReportFile.addTestCase("ODI6.x-618:Labeling of Call Stat Summary filters", "ODI6.x-618:Labeling of Call Stat Summary filters => Pass");
		}
		catch (Exception e)
		{
			System.out.print("trace: ");
			e.printStackTrace();
		}
		ReportFile.WriteToFile();
		driver.switchTo().defaultContent();
		gotomainpage();
	}

	
	//Method that I invoke in findlablesfilter() function in the above method
	private void isElementpresent(String name, By by){
		try
		{
			if(driver.findElement(by)!= null)
			{
			logger.info(name+" "+"Element  is Present");
			}else
			{
			logger.info(name +" "+"Element is absent");
			}	
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//odi.614 Able to generate reports for time range
	public void search(){
	cssrReport();
		try{
		driver.findElement(By.id("PARAM_START_DATE")).clear();
		removeAlert();
		driver.findElement(By.id("PARAM_START_DATE")).sendKeys("8/11/2013");
		driver.findElement(By.id("PARAM_END_DATE")).clear();
		removeAlert();
		driver.findElement(By.id("PARAM_END_DATE")).sendKeys("9/11/2013");
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
		WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
		WebElement rangeStart=page.findElement(By.xpath("//*[contains(text(),'8/11/2013')]"));
		WebElement rangeEnd=page.findElement(By.xpath("//*[contains(text(),'9/11/2013')]"));
			if(rangeStart!=null&&rangeEnd!=null)
			{
			logger.info("Report for selected range is showed");
			ReportFile.addTestCase("ODI6.x-614:CSSR-Search", "ODI6.x-614:CSSR-Search => Pass");
			}
		ReportFile = new WriteXmlFile();
		}
		catch (NoSuchElementException e)
		{
			ReportFile.addTestCase("ODI6.x-614:CSSR-Search", "ODI6.x-614:CSSR-Search => Fail");
			
		}
	
	driver.switchTo().defaultContent();
	gotomainpage();
	}
	
	//odi.623.Method for DNIS value search
	public void dnisfilter(){
	
		choosedomain("US_AIRWAYS");
		cssrReport();
		try{
		WebElement DNISvalue = driver.findElement(By.id("PARAM_DNIS"));
		Select select = new Select(DNISvalue);
		select.deselectAll();
		select.selectByValue("2404953077");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);//submit should be clickable if delay is there in selecting the DNIS
		submit.click();
		new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
		WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
		WebElement tag=page.findElement(By.xpath("//*[contains(text(),'2404953077')]"));
			if(tag != null)
			{
			logger.info("DNIS:2404953077 filter is present");
			ReportFile.addTestCase("ODI6.x-623:DNIS filter: selected DNIS filter should be shown in report result", "ODI6.x-623:DNIS filter: selected DNIS filter should be shown in report result => Pass");
			}
	
		}catch (NoSuchElementException e)
		{
			ReportFile.addTestCase("ODI6.x-623:DNIS filter: selected DNIS filter should be shown in report result", "ODI6.x-623:DNIS filter: selected DNIS filter should be shown in report result => Fail");
			
		}

		ReportFile.WriteToFile();
		driver.switchTo().defaultContent();
		gotomainpage();
	}
	
	//odi.620 Method that the report footer a/b filter	
	public void abfilter(){
		//String dmName="METROPCS";
		
		choosedomain("METROPCS");
		cssrReport();
		try{
			WebElement ABvalue = driver.findElement(By.id("PARAM_APP_VARIANT_ID"));
			Select select = new Select(ABvalue);
			select.deselectAll();
			select.selectByValue("NVP 5.0.0-NDF 6.1.0-0.0.0.1");
			submit.click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			WebElement tag=page.findElement(By.xpath("//*[contains(text(),'A/B')]"));
				if(tag != null)
				{
				logger.info("A/B Combo ID: NVP 5.0.0-NDF 6.1.0-0.0.0.1 filter is present");
				ReportFile.addTestCase("ODI6.x-620:A/B filter: should be in the report footer", "ODI6.x-620:A/B filter: should be in the report footer => Pass");
				}
		}catch (NoSuchElementException e)
		{
			ReportFile.addTestCase("ODI6.x-620:A/B filter: should be in the report footer", "ODI6.x-620:A/B filter: should be in the report footer => Fail");
			
		}
		driver.switchTo().defaultContent();
		ReportFile.WriteToFile();
		gotomainpage();
		
	}
	public void abfilterselectable(){
		choosedomain("METROPCS");
		cssrReport();
		boolean result;
		try{
			WebElement ABvalue = driver.findElement(By.id("PARAM_APP_VARIANT_ID"));
			Select select = new Select(ABvalue);
		//	List<WebElement> ABvalues = select.getOptions();
				result= select.isMultiple();
				if(result)
					ReportFile.addTestCase("ODI6.x-620:A/B filter: should be in the report footer", "ODI6.x-62x:A/B filter: should be in the report footer => Pass");
				else ReportFile.addTestCase("ODI6.x-620:A/B filter: should be in the report footer", "ODI6.x-62x:A/B filter: should be in the report footer => Fail");
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		driver.switchTo().defaultContent();
		ReportFile.WriteToFile();
		gotomainpage();
	}
	
	//Method for selecting a date
	public void pickAvalidDate (){
		driver.findElement(By.id("PARAM_START_DATE")).clear();
		removeAlert();
		driver.findElement(By.id("PARAM_START_DATE")).sendKeys("08/01/2013");
		driver.findElement(By.id("PARAM_END_DATE")).clear();
		removeAlert();
		driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/01/2013");
		submit.click();
	}
	
	//ODI6.x-627:generate cssr report with valid filters and check the number if they are reasonable
	public void cssrNumbers (){
		int CallVolumeValue =0, TransfersValue = 0, PeakHourValue = 0;
		double roundOff =0, AverageCallValue =0;
		choosedomain("US_AIRWAYS");
		cssrReport();
		pickAvalidDate();
		try{
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			List<WebElement> check= page.findElements(By.tagName("span"));
				for(int i=0;i<check.size();i++)
				{
					String content= (check.get(i).getText());
					if (content.equals("Call Volume")){
						String CallVolume = (check.get(i+1).getText());
						String[] CallVolumes = CallVolume.split("\\s");
						CallVolumeValue= Integer.parseInt(CallVolumes[1]);
					}
					else if(content.equals("Transfers (All)")){
						String  TransfersAll= (check.get(i+1).getText());
						String[] Tvalues= TransfersAll.split("\\s");
						TransfersValue=Integer.parseInt(Tvalues[1]);
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
					}
					else if (content.equals("Average Call Duration (minutes)")){
						String AverageCallDuration= (check.get(i+1).getText());
						String[] AverageCallDurationValues= AverageCallDuration.split("\\s");
						AverageCallValue= Double.parseDouble(AverageCallDurationValues[1]);					
								
					}
				}
				try{
					if (CallVolumeValue >= TransfersValue){
						logger.info("the call volume is greater than the Transfer");
					}
					if (PeakHourValue <= CallVolumeValue){
						logger.info("PeakHourValue <= CallVolumeValue");
					}
					 if (AverageCallValue == roundOff){
						 logger.info("Average Call Duration =Call Duration (minutes)/Call Volume");
					 }
					 
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				ReportFile.addTestCase("ODI6.x-627:CSSR numbers must be reasonable", "ODI6.x-627:CSSR numbers must be reasonable => Pass");
			}catch(Exception e)
			{
				ReportFile.addTestCase("ODI6.x-627:CSSR numbers must be reasonable", "ODI6.x-627:CSSR numbers must be reasonable => Fail");
				e.printStackTrace();
			}
			ReportFile.WriteToFile();
			driver.switchTo().defaultContent();
			gotomainpage();
			
		}
	
	//ODI6.x-628:Number formatting
	public void numberFormat () {
		cssrReport();
		pickAvalidDate();
		try{
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			List<WebElement> check= page.findElements(By.tagName("span"));
			for(int i=0;i<check.size();i++)
			{
				content=check.get(i).getText();
				if(content.equals("Call Volume")){
					contentValue = check.get(i+1).getText();
					decimalcheck(contentValue, "example", 0);
				}
				if(content.equals("Call Duration (minutes)"))
				{
					contentValue=check.get(i+1).getText();
					decimalcheck(contentValue, "In call duration", 2);	
				}
				else if(content.equals("Average Call Duration (minutes)"))
				{
					contentValue=check.get(i+1).getText();
					decimalcheck(contentValue,"In average call duration",2 );
				}				
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
				}

				if (content.equals("Transfer Rate (All)")){
					String percentage=check.get(i+1).getText();
					String[] contentValues= percentage.split("%"); 
					contentValue= contentValues[0];
					decimalcheck(contentValue, "In Transfer Rate ", 1);
					}
				else if(content.equals("Containment Rate (All)")){
					String percentage=check.get(i+1).getText();
					String[] contentValues= percentage.split("%"); 
					contentValue= contentValues[0];
					decimalcheck(contentValue, "In Containment Rate ", 1);
					
				}
				}
			ReportFile.addTestCase("ODI6.x-628:CSSR numbers Formatting", "ODI6.x-628:CSSR numbers Formatting => Pass");
		}catch(Exception e)
		{
			ReportFile.addTestCase("ODI6.x-628:CSSR numbers Formatting", "ODI6.x-628:CSSR numbers Formatting => Fail");
			e.printStackTrace();
		}
		driver.switchTo().defaultContent();
		gotomainpage();
		//driver.quit();
	}

	//Method to invoke decimal checking in the above method
	private void decimalcheck(String x, String y, int z) {
		String[] contents = x.split("\\s");
		double contentsConvert= Double.parseDouble(contents[1]);
		if (checkDecimalPlaces(contentsConvert, z) == true){
			logger.info(y+" "+ "we have found the decimals with "+ z +" precision");
		}
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
