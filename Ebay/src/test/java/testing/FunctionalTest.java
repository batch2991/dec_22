package testing;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.Base;
import pages.HomePage;
import pages.ResultsPage;

public class FunctionalTest extends Base
{
	HomePage homepage=new HomePage();
	ResultsPage resultspage=new ResultsPage();
	
	@BeforeMethod
	@Parameters({"browser"})
	public void initialize(String br)
	{
		homepage.setUp(br);
	}
	@Test
	public void validateTitle() throws Exception
	{	
		homepage.openURL("http://www.ebay.com");
		homepage.search("mobiles");
		String title=homepage.getTitle();
		if(title.contains("mobiles"))
		{
			test=report.createTest("Validate Title");			
			test.log(Status.PASS,"Title is correct :"+title);
			takescreenshot("homepage.png");
			
		}
		else
		{
			test=report.createTest("Validate Title");
			test.log(Status.FAIL,"Title NOT correct :"+title);
			takescreenshot("homepage.png");
		}
		driver.quit();
		
	}
	@Test
	public void validateProdandPrice()
	{		
		resultspage.openURL("http://www.ebay.com");
		resultspage.search("outdoor toys");
		String f[]=resultspage.clickProduct(5);
		String s[]=resultspage.getproddetails();
		if(f[0].matches(s[0]) && f[1].matches(s[1]))
		{
			test=report.createTest("Validate Product Details");
			test.log(Status.PASS,"Productname & Price are matched : "+f[0]+"  "+f[1]);
			takescreenshot("products.png");
			
		}
		else
		{
			test=report.createTest("Validate Product Details");
			test.log(Status.FAIL,"Productname & Price NOT matched : "+f[0]+"  "+f[1]);
			takescreenshot("products.png");
		}
		driver.quit();
	}
}
