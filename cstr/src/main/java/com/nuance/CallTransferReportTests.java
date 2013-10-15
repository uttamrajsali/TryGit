package com.nuance;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CallTransferReportTests extends Mainpage{
	//constructor
	public CallTransferReportTests() {
		gotomainpage();
		}
	//686 transfercalls
	public void transferCall(){
		transferReport();
		try{
			driver.findElement(By.id("PARAM_START_DATE")).clear();
			removeAlert();
			driver.findElement(By.id("PARAM_START_DATE")).sendKeys("8/11/2013");
			driver.findElement(By.id("PARAM_END_DATE")).clear();
			removeAlert();
			driver.findElement(By.id("PARAM_END_DATE")).sendKeys("9/11/2013");
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewercridreportpage")));
			/*WebElement data=page.findElement(By.xpath("//*[contains(text(),'No')]"));
			// check if the no data string is available or not
			if(data.equals("No data was found for the selected criteria."))
			{
				logger.info("fail");
			
			}*/
			List<WebElement> check= page.findElements(By.tagName("span"));
			for(int i=0; i <check.size(); i++)
			{
				String content = (check.get(i).getText());//get the webelement first and then its gets its text
				if(content.equals("%")){
					String callcount = check.get(i-1).getText();
					String callcount1 = check.get(i-3).getText();
					System.out.println(callcount +"more"+ callcount1 );
					break;
				}
			}
			//WebElement content=page.findElement(By.xpath("//*[contains(text(),'Call Count')]"));
			//WebElement content1=page.findElement(By.xpath("//*[contains(text(),'Transfer')]"));
			//WebElement content2=page.findElement(By.xpath("//*[contains(text(),'%')]"));
			
		//still i need to add the method using for loop to find the call count index and % index and compare it in if block and say they have verified	
			//String y=content.getText();
			//System.out.println(y); 
		}catch(NoSuchElementException e)
		{
			e.printStackTrace();
		} 
	}
	//688
	public void noData(){
		transferReport();
		try{
			driver.findElement(By.id("PARAM_START_DATE")).clear();
			removeAlert();
			driver.findElement(By.id("PARAM_START_DATE")).sendKeys("10/07/2008");
			driver.findElement(By.id("PARAM_END_DATE")).clear();
			removeAlert();
			driver.findElement(By.id("PARAM_END_DATE")).sendKeys("10/08/2008");
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewercridreportpage")));
			WebElement content=page.findElement(By.xpath("//*[contains(text(),'No')]"));
			String y=content.getText();
			System.out.println(y);
		}catch (NoSuchElementException e)
		{
			e.printStackTrace();
		}
	}
//	689
	public void search(){
		transferReport();
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
		
	//694 just check for matching left and right hand side
	public void abfilter(){
		//String dmName="METROPCS";
		
		choosedomain("METROPCS");
		transferReport();
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
	//693 still write code forfilter check on leftside  filter is multi-selectable and has values all, filter a, filter b, unknown
	public void abfilterselectable(){
		choosedomain("METROPCS");
		transferReport();
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
	//697
	public void dnisfilter(){
		
		choosedomain("US_AIRWAYS");
		transferReport();
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
	public void pickAvalidDate (){
		driver.findElement(By.id("PARAM_START_DATE")).clear();
		removeAlert();
		driver.findElement(By.id("PARAM_START_DATE")).sendKeys("08/01/2013");
		driver.findElement(By.id("PARAM_END_DATE")).clear();
		removeAlert();
		driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/01/2013");
		submit.click();
	}
	//700 
	public void netTotal(){
		transferReport();
		try{
			//pre condition one application and 
		}catch(Exception e){
			
		}
	}
	//708
	public void timeRange(){
		choosedomain("US_AIRWAYS");
		transferReport();
		try{
			pickAvalidDate();
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
	//713 buffer
	public void transferPercentage(){
		choosedomain("US_AIRWAYS");
		transferReport();
		try{
			//i think i have to check for the tag td whether it has attribute align=right then i can confirm it has a column
			//pickAvalidDate();
			driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			WebElement percent= page.findElement(By.xpath("//span[contains(text(),'%')]"));
			String x = percent.getText();
			System.out.println(x);
		}
		catch (NoSuchElementException e)
		{
			ReportFile.addTestCase("ODI6.x-614:CSSR-Search", "ODI6.x-614:CSSR-Search => Fail");
			
		}
	}
	//716
	public void destinationGroup(){
		//choosedomain("US_AIRWAYS");
		//transferReport();
		try{
			/*driver.findElement(By.id("PARAM_START_DATE")).clear();
			removeAlert();
			driver.findElement(By.id("PARAM_START_DATE")).sendKeys("08/01/2013");
			driver.findElement(By.id("PARAM_END_DATE")).clear();
			removeAlert();
			driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/01/2013");
			WebElement x = driver.findElement(By.id("PARAM_HIDE_label"));
			if (x!=null)
			{
				logger.info("Element  is Present");
			}
			WebElement HideValue = driver.findElement(By.id("PARAM_HIDE"));
			Select select = new Select(HideValue);
			select.deselectAll();
			select.selectByVisibleText("Destination Group");
			submit.click();*/
			driver.switchTo().defaultContent();
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
			WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
			WebElement y = page.findElement(By.xpath("//span[contains(text(),'Destination')]"));
			boolean fb = y.getText().contains("Group");
			System.out.println(y.getText());
			Boolean HiddenElement= y.isDisplayed();
			System.out.println(fb);
			if (HiddenElement == false)
			{
				logger.info("Element  is not Present");
			}
			//driver.quit();
		}catch (Exception e)
		{
			e.printStackTrace();
			logger.info("Exception");
			
		}
	}
	//715
	public void destinationNumber(){
		//choosedomain("US_AIRWAYS");
		//transferReport();
				try{
					/*driver.findElement(By.id("PARAM_START_DATE")).clear();
					removeAlert();
					driver.findElement(By.id("PARAM_START_DATE")).sendKeys("08/01/2013");
					driver.findElement(By.id("PARAM_END_DATE")).clear();
					removeAlert();
					driver.findElement(By.id("PARAM_END_DATE")).sendKeys("09/01/2013");
					WebElement x = driver.findElement(By.id("PARAM_HIDE_label"));
					if (x!=null)
					{
						logger.info("Element  is Present");
					}
					//driver.switchTo().defaultContent();*/
					driver.switchTo().defaultContent();
					
					WebElement HideValue = driver.findElement(By.id("PARAM_HIDE"));
					Select select = new Select(HideValue);
					select.deselectAll();
					select.selectByVisibleText("Destination");
					driver.findElement(By.cssSelector("input[type=\"submit\"]")).sendKeys(Keys.ENTER);
					driver.findElement(By.cssSelector("#openLeftPane > a > img")).click();
					//submit.click();
					new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
					WebElement page =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
					WebElement y = page.findElement(By.xpath("//span[contains(text(),'Destination')]"));
					boolean fb = y.getText().contains("Number");
					System.out.println(y.getText());
					//Boolean HiddenElement= y.isDisplayed(); displaying of the text Total > Application Name
					System.out.println(fb);
					driver.switchTo().defaultContent();
					
					//new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("LeftPane"));
					select.deselectByVisibleText("Destination");;
					
					driver.findElement(By.cssSelector("input[type=\"submit\"]")).sendKeys(Keys.ENTER);
					//submit.click();
					new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("reportContent"));
					WebElement page2 =new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("CrystalViewer")));
					WebElement y1 = page2.findElement(By.xpath("//span[contains(text(),'Destination')]"));
					boolean fb2 = y1.getText().contains("Number");
					System.out.println(fb2);
					//if (HiddenElement == false)
					//{
					//	logger.info("Element  is not Present");
					//}
					//driver.quit();
				}catch (Exception e)
				{
					e.printStackTrace();
					logger.info("Exception");
					
				}
	}
}
