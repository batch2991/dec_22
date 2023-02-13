package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import base.Base;

public class HomePage extends Base
{
   	public String getTitle()
	{
		String title=driver.getTitle();
		return title;
	}
}
