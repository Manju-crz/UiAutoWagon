package mks.uiautowagon.interactor.patterns;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.patterns.objects.Frames;

public class FramePatterns {


	private WebElement element = null;
	
	public FramePatterns(WebElement element) {
		System.out.println("Beginned to CheckboxPatterns");
		this.element = element;
	}
	
	
	private boolean isFrame() {
		if (element.getTagName().trim().equalsIgnoreCase("iframe"))
			return true;
		return false;
	}
	
	

	public Frames findPattern() {
		System.out.println("Finding iframe pattern");
		if (isFrame()) {
			System.out.println("It is an iframe");
			Frames frame = new Frames();
			frame.setAttributeStr(new SupportUtil().getAttributes(element).trim());
			frame.setElement(element);
			return frame;
		}
		return null;
	}
	
}
