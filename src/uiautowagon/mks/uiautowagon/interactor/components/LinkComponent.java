package mks.uiautowagon.interactor.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.store.ButtonStore;
import mks.uiautowagon.interactor.store.LinkStore;

public class LinkComponent implements WagonerElements {

	WebDriver driver = null;
	public LinkComponent(WebDriver driver) {
		this.driver = driver;
	}
	@Override
	public WebElement get(String label) {
		return new LinkStore().find(label);
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
