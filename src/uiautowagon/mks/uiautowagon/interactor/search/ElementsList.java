package mks.uiautowagon.interactor.search;

import java.util.List;

public class ElementsList {
	
	static List<InputTypeText> inputFields = null;
	static List<InputTypeButton> inputButtons = null;
	
	public void addInputElements(InputTypes inputtype) {
		
		if(inputtype.getType().equals(InputTypeText.class)) {
			inputFields.add((InputTypeText) inputtype);
		}
		else if(inputtype.getType().equals(InputTypeButton.class)) {
			inputButtons.add((InputTypeButton) inputtype);
		}
		
	}
	
}
