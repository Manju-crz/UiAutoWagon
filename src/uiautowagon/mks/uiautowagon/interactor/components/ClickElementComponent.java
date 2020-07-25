package mks.uiautowagon.interactor.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.ClickElementStore;

public class ClickElementComponent implements WagonerElements {

	WebDriver driver = null;
	public ClickElementComponent(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement get(String label) {
		return new ClickElementStore().find(label);
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
