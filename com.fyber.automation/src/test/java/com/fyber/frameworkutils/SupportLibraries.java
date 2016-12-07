package com.fyber.frameworkutils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fyber.objectrepository.ObjectRepository;

/**
 * @author RAJESHA KUMAR MOHARANA
 * class to support test method execution
 */
public class SupportLibraries {
	
	private Utilities _utils=new Utilities();
	
	public boolean verifyMovieList(WebDriver _driver) throws Exception{
		
		List<WebElement> _ele=_driver.findElements(By.xpath(ObjectRepository._moviesList));
		
		WebDriverWait wait=new WebDriverWait(_driver,30);
		wait.until(ExpectedConditions.visibilityOf(_ele.get(0)));
		_utils.highLightElement(_driver, _ele.get(0));
		if(_ele.size()>=1)
			return true;
		else
			return false;

	}
	
	public boolean verifyGenreMovieList(WebDriver _driver) throws Exception{
		
		List<WebElement> _ele=_driver.findElements(By.xpath(ObjectRepository._genreMoviesList));
		
		WebDriverWait wait=new WebDriverWait(_driver,30);
		wait.until(ExpectedConditions.visibilityOf(_ele.get(0)));
		_utils.highLightElement(_driver, _ele.get(0));
		if(_ele.size()>=1)
			return true;
		else
			return false;

	}
	
	public void selectSortingOption(WebDriver _driver, String _sortingValue) throws Exception{
		WebElement _sortingDropDown=_driver.findElement(By.xpath(ObjectRepository._sortedDropdown));
		
		WebDriverWait wait=new WebDriverWait(_driver,30);
		wait.until(ExpectedConditions.visibilityOf(_sortingDropDown));
		_utils.highLightElement(_driver, _sortingDropDown);
		
		Select _sortValue= new Select(_sortingDropDown);
		_utils.captureScreenshot(_driver,"fyber_1");
		_sortValue.selectByVisibleText(_sortingValue);
	}
	
	public void selectWesternGenre(WebDriver _driver, String _genreValue) throws Exception{
		List<WebElement> _westerGenre=_driver.findElements(By.xpath(ObjectRepository._westergenre));
		
		for(int i=1;i<_westerGenre.size();i++){
			String _xpath=ObjectRepository._westergenre+"["+i+"]"+"/a["+i+"]";
			if(_driver.findElement(By.xpath(_xpath)).getText().trim().equals(_genreValue)){
				_utils.highLightElement(_driver, _driver.findElement(By.xpath(_xpath)));
				_driver.findElement(By.xpath(_xpath)).click();
				WebDriverWait wait=new WebDriverWait(_driver,30);
				wait.until(ExpectedConditions.visibilityOf(_driver.findElement(By.xpath(ObjectRepository._listerList))));
				break;
			}
		}
		_utils.captureScreenshot(_driver,"fyber_2");
	}

}
