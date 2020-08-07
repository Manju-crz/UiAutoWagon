package mks.uiautowagon.interactor.patterns;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.patterns.objects.Frames;

public class FramePatterns {

	CurrentElement cElement = null;
	
	public FramePatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	
	
	private boolean isFrame() {
		if (cElement.getTagName().equalsIgnoreCase("iframe"))
			return true;
		return false;
	}
	
	public Frames findPattern() {
		System.out.println("Finding iframe pattern");
		if (isFrame()) {
			System.out.println("It is an iframe");
			Frames frame = new Frames();
			frame.setAttributeStr(cElement.getAttributes());
			frame.setcElement(cElement);
			return frame;
		}
		return null;
	}
	
}
