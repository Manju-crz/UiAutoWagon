package mks.test.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class DemoExecutor extends DriverBase {

	//@Test
	public void testMarketo() {
		String url;
		url = "https://app-sjqe.marketo.com/homepage/login";
		driver.get(url);
		
		/*driver.findElement(By.xpath("(//input[@class='login-field'])[1]")).sendKeys("sadsadsddd");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("sadsadsddd");
		driver.findElement(By.xpath("//input[@name='rememberMe']")).click();
		driver.findElement(By.xpath("//input[@class='buttonSubmit']")).click();*/
		
		
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email").sendKeys("sadsadsddd");
		wagoner.textField.get(2).sendKeys("asdada");
		Sleep.for3Seconds();
	}
	
	@Test
	public void testFacebook() {
		String url;
		url = "https://www.facebook.com/";
		driver.get(url);
		
		WagonerFacade wagoner;
		wagoner = new WagonerFacade(driver);
		wagoner.textField.get("Email address or phone number").sendKeys("fsfdsfsdfs");
		Sleep.forASecond();
		wagoner.textField.get("Password").sendKeys("fsfdsfsdfs");
		Sleep.for2Seconds();
		wagoner.link.get("Create new account").click();
		Sleep.forASecond();
		wagoner.textField.get("First Name").sendKeys("fsfdsfsdfs");
		Sleep.forASecond();
		wagoner.textField.get("Surname").sendKeys("fsfdsfsdfs");
		Sleep.forASecond();
		wagoner.textField.get("Mobile number or email address").sendKeys("fsfdsfsdfs@gmail.com");
		Sleep.forASecond();
		wagoner.textField.get("Re-enter email address").sendKeys("fsfdsfsdfs@gmail.com");
		Sleep.forASecond();
		wagoner.textField.get("New password").sendKeys("sdfsdfdfdfsdf");
		Sleep.forASecond();
		wagoner.radioButton.get("Male").click();
		Sleep.forASecond();
		new Select(wagoner.selectBox.get(1)).selectByVisibleText("25");
		new Select(wagoner.selectBox.get(2)).selectByVisibleText("Jul");
		Sleep.forASecond();
		wagoner.button.get("Sign Up").click();
		Sleep.for5Seconds();
	}
	
	
}
