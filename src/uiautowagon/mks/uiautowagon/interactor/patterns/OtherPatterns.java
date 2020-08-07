package mks.uiautowagon.interactor.patterns;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.patterns.objects.Other;

public class OtherPatterns {

	CurrentElement cElement = null;
	
	
	public OtherPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	
	
	public Other findPattern() {

		Other other = new Other();
		other.setAttributesStr(cElement.getAttributes());
		other.setTagName(cElement.getTagName());
		other.setElement(cElement.getElement());
		return other;
	}
	
}
