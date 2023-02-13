package base;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
    public static WebDriver driver;
    
    public static ExtentSparkReporter spark;
    public static ExtentReports report;
    public static ExtentTest test;
    
    By searchbox=By.xpath("//input[@placeholder='Search for anything']");
    By searchbtn=By.xpath("//input[@value='Search']");
    
    @BeforeSuite
    public void initializeReport()
    {
    	spark=new ExtentSparkReporter("./Reports/ebay.html");  	    	
    	spark.config().setTheme(Theme.STANDARD);
    	spark.config().setDocumentTitle("Ebay Functional Test");
    	spark.config().setReportName("EBay");
        
    	report=new ExtentReports();
    	report.attachReporter(spark);
    }
    
    public void setUp(String b)
    {
    	if(b.matches("firefox"))
    	{
    		WebDriverManager.firefoxdriver().setup();
    	   	  driver=new FirefoxDriver();
    	}
    	if(b.matches("chrome"))
    	{  
    		WebDriverManager.chromedriver().setup();
    		driver=new ChromeDriver();
    	}
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    public void openURL(String url)
    {
    	driver.get(url);
    }
    public void search(String prodname)
	{
		driver.findElement(searchbox).sendKeys(prodname);
		driver.findElement(searchbtn).click();		
	}
    
    public void takescreenshot(String imagename)
    {
    	try {
    	File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File("./Reports/images/"+imagename));
		test.addScreenCaptureFromPath("./Reports/images/"+imagename);
		
    	}
    	catch(Exception e) {}
    }
    @AfterSuite
    public void saveReport()
    {
    	report.flush();
    }
}
