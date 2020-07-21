package mks.uiautowagon.interactor.store;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.patterns.objects.Button;
import mks.uiautowagon.interactor.patterns.objects.Frames;

public class FramesStore {

	public static List<Frames> framesList = new ArrayList<>();
	
	
	public void add(Frames frame) {
		framesList.add(frame);
	}

	public WebElement find(String elementText) {

		Frames tf = new Frames();
		for (int i = 0; i < framesList.size(); i++) {
			tf = framesList.get(i);
			WebElement foundElement = tf.compare(elementText);
			if (foundElement != null) {
				return foundElement;
			}
		}
		return null;
	}
	
}
