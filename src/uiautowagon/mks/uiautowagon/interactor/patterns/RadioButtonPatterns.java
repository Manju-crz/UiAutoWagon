package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.RadioButton;

public class RadioButtonPatterns {

	
	private WebElement element = null;
	
	private String siblingLabel = null;
	private String parentLabelText = null;
	private String parentDivText = null;
	

	public RadioButtonPatterns(WebElement element) {
		System.out.println("Beginned to CheckboxPatterns");
		this.element = element;
	}
	

	enum InputTagPreference {
		SiblingedInputaAndLabels,
		LabelParentingToInput,
		DivParentingToInput;
	}
	
	private boolean isRadioButton() {
		String attributeType = element.getAttribute("type");
		if ((attributeType != null) && (attributeType.equalsIgnoreCase("radio")))
			return true;
		return false;
	}
	
	private boolean isSiblingedInputAndLabels() {
		String attributes = new SupportUtil().getAttributes(element).trim();
		List<WebElement> siblingInputs = new TagsFinder().siblingInputs(element);
		List<WebElement> siblingLabels = new TagsFinder().siblingLabels(element);
		for (int i = 0; i < siblingInputs.size(); i++) {
			if (new SupportUtil().getAttributes(siblingInputs.get(i)).equalsIgnoreCase(attributes)) {
				if (i < siblingLabels.size()) {
					siblingLabel = siblingLabels.get(i).getText();
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}
	

	private boolean isLabelParentingToInput() {
		WebElement parentElement = new TagsFinder().parentElement(element);
		if (parentElement.getTagName().equalsIgnoreCase("label")) {
			parentLabelText = parentElement.getText().trim();
			return true;
		}
		return false;
	}
	
	private boolean isDivParentingToInput() {
		if ((!isSiblingedInputAndLabels()) && (!isLabelParentingToInput())) {
			WebElement parentDiv = new TagsFinder().parentDiv(element);
			List<String> divStrList = new ArrayList<>();
			if (parentDiv != null) {
				String divStr = parentDiv.getText();
				if ((divStr != null) && divStr.trim().length() > 0) {
					String[] divStrArr = divStr.trim().split("   ");
					for (String str : divStrArr) {
						divStrList.add(str);
					}
				}
				if (divStrList.size() > 0) {
					String attributes = new SupportUtil().getAttributes(element).trim();
					List<WebElement> siblingInputs = new TagsFinder().siblingInputs(element);
					for (int i = 0; i < siblingInputs.size(); i++) {
						if (attributes.equalsIgnoreCase(new SupportUtil().getAttributes(siblingInputs.get(i)))) {
							try {
								parentDivText = divStrList.get(i);
							} catch (IndexOutOfBoundsException e) {
							}
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	
	public RadioButton findPattern() {
		if (isRadioButton()) {
			RadioButton rdo = new RadioButton();
			isSiblingedInputAndLabels();
			isLabelParentingToInput();
			isDivParentingToInput();
			rdo.setSiblingLabel(siblingLabel);
			rdo.setParentLabelText(parentLabelText);
			rdo.setParentDivText(parentDivText);
			rdo.setElement(element);
			return rdo;
		}
		return null;
	}
	
}
