package mks.base.driver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import mks.base.mainComponents.TempCheckbox;
import mks.base.mainComponents.TempTextField;
import mks.driver.base.DriverBase;
import mks.java.util.Sleep;

public class Tries extends DriverBase{
	
	//@Test
	public void testMarketo() {
		String url = "https://app-sjqe.marketo.com/";
		driver.get(url);
		
		String source = driver.getPageSource();
		System.out.println("Complete page source is : " + source);
		
		List<String> lst = new ArrayList<>();
		
		String placeholder = driver.findElement(By.id("loginUsername")).getAttribute("placesholder");
		System.out.println("Placeholder : " + placeholder);
		
		WebElement formLogin = driver.findElement(By.xpath("//div[@id='login']"));
		List<WebElement> childElements = formLogin.findElements(By.xpath("./*"));
		for(WebElement element : childElements) {
			System.out.println("ID s : " + element.getAttribute("id"));
		}
		
		WebElement parentElement = formLogin.findElement(By.xpath(".."));
		System.out.println("parent id s : " + parentElement.getAttribute("id"));
		
		
	}
	
	@Test
	public void tesRadio() {
		
		
		String url = "https://apply.standardchartered.co.in/personal-loan?pid=IN_IP_PL_CR&_ga=2.124102095.492274700.1594548540-1994161994.1594548540";
		driver.get(url);
		String radioValues = driver.findElement(By.xpath("//div[@id='existing_scb_user']")).getText().trim();
		System.out.println("Radio texts are : " + radioValues);
		Sleep.for2Seconds();
		String str1 = radioValues.split("splitter")[0];
		//String str2 = radioValues.split("   ")[1];
		System.out.println("Str1 : " + str1);
		//System.out.println("Str2 : " + str2);
	}
	
	
}
