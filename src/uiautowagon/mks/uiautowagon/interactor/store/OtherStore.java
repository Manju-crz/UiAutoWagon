package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import mks.uiautowagon.interactor.patterns.objects.Other;

public class OtherStore {

	public static List<Other> otherElementsList = new ArrayList<>();
	
	
	public void add(Other button) {
		otherElementsList.add(button);
	}
	
}
