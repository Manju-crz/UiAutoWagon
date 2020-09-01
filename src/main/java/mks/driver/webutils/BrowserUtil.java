package mks.driver.webutils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import mks.java.util.Sleep;

public class BrowserUtil {

	String fileSep = File.separator;
    String chromeExe = System.getProperty("user.dir") + fileSep + "ExeDrivers" + fileSep + "chromedriver.exe";
	
	
	public WebDriver launchChrome() {
		System.setProperty("webdriver.chrome.driver", chromeExe);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Sleep.forASecond();
		return driver;
	}
	

	
	public WebDriver launchEdge() {
		System.setProperty("webdriver.chrome.driver", chromeExe);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		Sleep.forASecond();
		return driver;
	}
}
