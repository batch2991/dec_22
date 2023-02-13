package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import base.Base;

public class ResultsPage extends Base
{
    By prods=By.xpath("//div[@class='s-item__title']");
	By cost=By.xpath("//span[@class='s-item__price']");
	
	By prod2=By.xpath("//h1[@class='x-item-title__mainTitle']");
	By cost2=By.xpath("//span[@itemprop='price']");
	
	String prodname1,price1,prodname2,price2;
	
	
	public String[] clickProduct(int index)
	{
		String first[]=new String[2];
		List<WebElement> products=driver.findElements(prods);
		List<WebElement> prices=driver.findElements(cost);
		
		first[0]=products.get(index-1).getText();
		first[1]=prices.get(index-1).getText().substring(1);
		
		products.get(index-1).click();
		return first;
	}
	public String[] getproddetails()
	{
		String second[]=new String[2];
		ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		second[0]=driver.findElement(prod2).getText();
		second[1]=driver.findElement(cost2).getAttribute("content");
		return second;
		
	}
	
}
