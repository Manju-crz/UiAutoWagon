package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.Checkbox;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class CheckboxPatterns {


	private String label = null;
	private List<String> spanTexts = new ArrayList<>();
	private boolean isAnchorParent = false;

	private String parentLabelText = null;
	private String grandParentLabelText = null;

	CurrentElement cElement = null;
	
	public CheckboxPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	

	enum InputTagPreference {
		InputTagWithSiblingedSpanText,
		InputTagWithSiblingedLabel;
	}
	
	
	enum AnchorTagPreference {
		InputTagAndSpanInsideAnchor;
	}

	enum ParentBased {
		InputTagwithParentLabel,
		InputTagwithGrandParentLabel;
	}
	
	private boolean isCheckbox() {
		String attributeType = cElement.getTypeAttribute();
		if ((attributeType != null) && (attributeType.equalsIgnoreCase("checkbox")))
			return true;
		return false;
	}
	
	private boolean isInputTagWithSiblingedLabel() {
		WebElement siblingLabel = new TagsFinder().siblingLabel(cElement.getElement());
		if (siblingLabel == null)
			return false;
		label = siblingLabel.getText();
		if (label == null) {
			return false;
		}
		return true;
	}
	
	private boolean isInputTagWithSiblingedSpanTexts() {
		spanTexts = new SupportUtil().getElementsText(new TagsFinder().siblingSpans(cElement.getElement()));
		return spanTexts.isEmpty();
	}
	
	private boolean isInputTagWithWithAnchorParent() {
		WebElement parentElement = new TagsFinder().parentElement(cElement.getElement());
		return parentElement.getTagName().equalsIgnoreCase("a");
	}

	private boolean isInputTagwithParentLabel() {
		WebElement parentElement = new TagsFinder().parentElement(cElement.getElement());
		if (parentElement.getTagName().equalsIgnoreCase("label")) {
			parentLabelText = parentElement.getText().trim();
			return true;
		}
		return false;
	}

	private boolean isInputTagwithGrandParentLabel() {
		WebElement parentElement = new TagsFinder().parentElement(cElement.getElement());
		WebElement grandParent = new TagsFinder().parentElement(parentElement);
		if (grandParent.getTagName().equalsIgnoreCase("label")) {
			grandParentLabelText = grandParent.getText().trim();
			return true;
		}
		return false;
	}
	
	public Checkbox findPattern() {
		if (isCheckbox()) {
			Checkbox chk = new Checkbox();
			isInputTagWithSiblingedLabel();
			isInputTagWithSiblingedSpanTexts();
			isInputTagWithWithAnchorParent();
			isInputTagwithParentLabel();
			isInputTagwithGrandParentLabel();
			chk.setCheckBoxLabel(label);
			chk.setSpanTexts(spanTexts);
			chk.setParentLabelText(parentLabelText);
			chk.setGrandParentLabelText(grandParentLabelText);
			chk.setParentElementAnchor(isAnchorParent);
			chk.setcElement(cElement);
			return chk;
		}
		return null;
	}
	
}
