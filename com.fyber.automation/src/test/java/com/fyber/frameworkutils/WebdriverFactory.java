package com.fyber.frameworkutils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author RAJESHA KUMAR MOHARANA
 * class to initiate browser session
 */
public class WebdriverFactory {
	
	private WebDriver driver;
	
	public WebdriverFactory(WebDriver driver){
		this.driver=driver;
	}
	
	public WebDriver getDriver(String _browser,String _externalDriver){
		
		switch(_browser) {
		case "Chrome":			
			System.setProperty("webdriver.chrome.driver",_externalDriver);
			driver = new ChromeDriver();
			break;
			
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", _externalDriver);
			driver = new FirefoxDriver();
			break;
			
		case "InternetExplorer":			
			System.setProperty("webdriver.ie.driver",_externalDriver);					
			driver = new InternetExplorerDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	

	/**
	 * navigates to the given url
	 * @param url to be navigated to
	 */
	public void open(WebDriver driver,String url){

		driver.get(url);

	}
}
