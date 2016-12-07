package com.fyber.test;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.fyber.frameworkutils.SupportLibraries;
import com.fyber.frameworkutils.Utilities;
import com.fyber.frameworkutils.WebdriverFactory;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author RAJESHA KUMAR MOHARANA
 * Class to test IMDb functionalities. Execution starts from here
 */
public class FyberTest extends Utilities{
	
	WebdriverFactory _driver=new WebdriverFactory(driver);
	SupportLibraries _lib=new SupportLibraries();
	private int failCounter=1;
	
	
	@Test
	public void verifyMovieIMDB(){
		do
		{
			try{
				
				logger.log(LogStatus.INFO, "Open the Test URL "+data.get("TestURL"));
				_driver.open(driver,data.get("TestURL"));
				logger.log(LogStatus.PASS, "Test URL launched, ready for testing");
				
				logger.log(LogStatus.INFO, "Verify atleast a movie on page");
				Assert.assertTrue(_lib.verifyMovieList(driver));
				
				logger.log(LogStatus.PASS, "List of movies appear on page as expected");
				logger.log(LogStatus.INFO, "Select one of the sorting value from drop down");
				_lib.selectSortingOption(driver,data.get("SortingValue"));
				
				logger.log(LogStatus.PASS, "The given value selected from sorting drop down");
				Assert.assertTrue(_lib.verifyMovieList(driver));
				logger.log(LogStatus.PASS, "List of movies appear on page as expected");
				
				logger.log(LogStatus.INFO, "Select one western genre given in test data");
				_lib.selectWesternGenre(driver, data.get("Genre"));
				
				logger.log(LogStatus.PASS, "Given Western Genre selected from genre list");
				Assert.assertTrue(_lib.verifyGenreMovieList(driver));
				
				logger.log(LogStatus.PASS, "Test work as expected");
				failCounter+=2;
				
			}catch(Exception e){

				failCounter++;

				if(failCounter>2){

					logger.log(LogStatus.FAIL, "An Exception occurred after re-running this script."
							+ "Failing this test script!! "+e);
				}
				else{
					logger.log(LogStatus.FAIL, "An Exception occured for first time during this execution."
							+ "Re-running this script!! "+e);
				}
			}
		}while(failCounter==2);
			
	}

}
