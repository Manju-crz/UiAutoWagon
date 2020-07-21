package mks.uiautowagon.interactor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

public class ElementsStorage {
	
	public static List<HashMap<String, Object>> allElements = new ArrayList<>();
	
	
	public void add(String key, Object object) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(key, object);
		allElements.add(map);
	}
	
	
}
