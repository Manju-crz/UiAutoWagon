package mks.uiautowagon.interactor.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.WagonerElements;

public class ClickElementComponent implements WagonerElements {

	WebDriver driver = null;
	public ClickElementComponent(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public WebElement get(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement get(String label, int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement get(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement get() {
		// TODO Auto-generated method stub
		return null;
	}

}
