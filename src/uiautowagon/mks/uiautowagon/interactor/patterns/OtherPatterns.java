package mks.uiautowagon.interactor.patterns;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.WagonerFacade;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.Other;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class OtherPatterns {
	
	private WebElement element = null;
	
	private String tagName = null;
	private String attributesStr = null;
	
	
	public OtherPatterns(WebElement element) {
		System.out.println("Beginned to TextFieldPatterns");
		this.element = element;
	}
	
	
	public Other findPattern() {

		Other other = new Other();
		other.setAttributesStr(new SupportUtil(WagonerFacade.getDriver()).getAttributes(element));
		other.setTagName(element.getTagName());
		other.setElement(element);
		return other;
	}
	
}
