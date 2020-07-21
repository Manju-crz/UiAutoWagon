package mks.uiautowagon.interactor;

import org.openqa.selenium.WebElement;

public interface WagonerElements {
	
	public WebElement get(String label);
	
	public WebElement get(String label, int position);
	
	public WebElement get(int position);
	
	public WebElement get();
	
}
