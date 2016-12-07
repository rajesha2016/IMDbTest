package com.fyber.frameworkutils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * @author RAJESHA KUMAR MOHARANA
 * All utilities methods
 */
public class Utilities {
	
	protected ExtentReports report=new ExtentReports(System.getProperty("user.dir")+"/target/Fyber_Automation_Report.html",true);
	protected ExtentTest logger;
	protected WebDriver driver;
	protected Map<String,String> data = null;
	private int dataIndex = 0;
	WebdriverFactory _driver=new WebdriverFactory(driver);
	
	
	@BeforeTest
	public void testSetUp() throws BiffException, IOException{
		logger=report.startTest("======== Assignment Start =======");
		getTestData(getTestName());
		driver=_driver.getDriver(data.get("Browser"),data.get("DriverPath"));
		logger.log(LogStatus.INFO, "Browser initilized, ready for launching website");
		
	}
	
	private void getTestData(String testName) throws BiffException, IOException {
		if (dataIndex == -1) {
			return;
		}
		
		data = new HashMap<String,String>();
		Sheet dataSheet = Workbook.getWorkbook(new File(System.getProperty("user.dir")+"/src/test/resources/TestData.xls")).getSheet("Test_Data");
		dataIndex=dataSheet.findCell(testName).getRow();
		for (int i=1; i<dataSheet.getColumns(); i++) {
			String key = dataSheet.getCell(i,0).getContents();
			String value = dataSheet.getCell(i,dataIndex).getContents();
			data.put(key, value);
		}
		
	}
	
	private String getTestName() {
		String[] klassNameSplit = this.getClass().getName().split("\\.");
		return klassNameSplit[klassNameSplit.length-1];
	}
	
	/**
	 * @param driver of current session
	 * @param element to highlight
	 */
	public void highLightElement(WebDriver driver, WebElement element){
		
		JavascriptExecutor js=(JavascriptExecutor)driver;

			js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;')", element);
	
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				
			}
			
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
	}
	
	/**
	 * takes screenshot of the present screen
	 * @param name of the saved screenshot
	 * @param driver for the current session
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void captureScreenshot(WebDriver driver,String name) throws Exception{

			Thread.sleep(1000);
			
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			File scrnshot=ts.getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(scrnshot,new File((System.getProperty("user.dir")+"\\target\\")+ name
							+ ".png"));
	}
	
	@AfterMethod
	public void tearDown(ITestResult result){
		
		if(result.getStatus()==ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Automation Test Fail");
		}
		
		report.endTest(logger);
		report.flush();
		driver.quit();
	}

}
