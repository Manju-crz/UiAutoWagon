package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClickElementsStore {

	public static List<HashMap<String, Object>> clickElements = new ArrayList<>();
	
	public void add(String key, Object object) {
		
		System.out.println("Adding key - " + key);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(key, object);
		clickElements.add(map);
	}
	
}
