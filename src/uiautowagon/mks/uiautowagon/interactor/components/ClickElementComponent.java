package mks.uiautowagon.interactor.components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.MyDriver;
import mks.uiautowagon.interactor.WagonerElements;
import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.Link;
import mks.uiautowagon.interactor.store.CheckboxStore;
import mks.uiautowagon.interactor.store.ClickElementsStore;

public class ClickElementComponent implements WagonerElements {

	WebDriver driver = null;
	public ClickElementComponent() {
		this.driver = MyDriver.getDriver();
	}

	@Override
	public WebElement get(String label) {

		WebElement element = findElement(label);
		
		if(element == null) {
			new WagonerFacade().reload();
			element = findElement(label);
		}
		
		return element;
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
	
	
	private WebElement findElement(String label) {

		List<HashMap<String, Object>> elementsMappedList = ClickElementsStore.clickElements;
		for (HashMap<String, Object> map : elementsMappedList) {
			String key = "";
			for (String k : map.keySet()) {
				key = key + k;
			}
			if (key.toLowerCase().contains("button")) {
				Button btn = (Button) map.get(key);
				CurrentElement element = btn.compare(label);
				if (element != null) {
					return element.getElement();
				}

			} else if (key.toLowerCase().contains("link")) {
				Link lnk = (Link) map.get(key);
				WebElement element = lnk.compare(label);
				if (element != null) {
					return element;
				}
			} else {
				continue;
			}
		}
		return null;
	}
	
	
}
