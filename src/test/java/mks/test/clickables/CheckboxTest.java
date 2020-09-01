package mks.test.clickables;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import mks.driver.base.DriverBase;
import mks.java.util.Sleep;
import mks.uiautowagon.interactor.WagonerFacade;

public class CheckboxTest extends DriverBase {

	@Test
	public void testMarketo() {
		String url = "https://retail.onlinesbi.com/retail/login.htm";
		driver.get(url);
		WagonerFacade wagoner = new WagonerFacade(driver);
		wagoner.link.get("Continue to Login").click();
		Sleep.for2Seconds();
		wagoner.getCount();
		wagoner.checkBox.get("Enable Virtual Keyboard").click();
		Sleep.for2Seconds();
		
		/*System.out.println("Tx tis : " + driver.findElement(By.xpath("(//div[@class='col-lg-6 col-md-6 col-sm-6 user_details']//p)[3]")).getText());
		driver.findElement(By.xpath("//input[@onclick='constructKeyboard();']")).click();
		Sleep.for2Seconds();*/
	}

	//@Test
	public void testMarketos() {
		String url = "https://www.sbicard.com/eapply/eapply-form.page/apply?path=personal/credit-cards/shopping/simplyclick-sbi-card.dcr";
		driver.get(url);
		String checkBoxTxt = "I have read Customer Declaration of SBI Card & understood all its terms and conditions. I con?rm that details given above belong to me and authorize SBICPSL & its a?liates or associates to contact me on the details provided.";
		String actTxt = driver.findElement(By.xpath("//div[@class='col-5 mob-pad-consent checkbox-style']//p/span")).getText().trim();
		System.out.println("actTxt s : " + actTxt);
		if(checkBoxTxt.equalsIgnoreCase(actTxt)) {
			System.out.println("Matched!");
		}
		else {
			System.out.println("Not Matched!");
		}
		
	}
}
