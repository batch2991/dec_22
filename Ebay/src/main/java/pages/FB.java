package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FB 
{
    public static ChromeDriver driver;
    By uid=By.id("email");
	By pwd=By.name("pass");
	By login=By.name("login");
	
	public void setUp()
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	public void openurl()
	{
		driver.get("http://www.facebook.com");
	}
	public void login(String username,String passwd)
	{
		driver.findElement(uid).sendKeys(username);
		driver.findElement(pwd).sendKeys(passwd);
		driver.findElement(login).click();
	}
	
	public void tearDown()
	{
		driver.quit();
	}
}
