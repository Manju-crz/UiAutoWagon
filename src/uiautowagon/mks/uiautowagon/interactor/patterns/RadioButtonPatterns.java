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
	private String divParentingToInputWithParentSiblingText = null;
	

	public RadioButtonPatterns(WebElement element) {
		System.out.println("Beginned to CheckboxPatterns");
		this.element = element;
	}
	

	enum InputTagPreference {
		SiblingedInputaAndLabels,
		LabelParentingToInput,
		DivParentingToInput,
		DivParentingToInputWithParentSiblingText;
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
	
	private boolean isDivParentingToInputWithParentSiblingText() {

		System.out.println("Radio button not yet found");
		if ((!isSiblingedInputAndLabels()) && (!isLabelParentingToInput()) && (!isDivParentingToInput())) {
			System.out.println("Radio button going to find...");
			List<WebElement> siblings = new TagsFinder().siblings(element);
			System.out.println("Element siblings are : " + siblings.size());
			if (siblings.size() > 1) {
				return false;
			} else {
				System.out.println("Entered else part to find ...");
				int ipcount = -1, finalIdx = -1;
				List<String> allTexts = new ArrayList<>();
				WebElement grandParent = new TagsFinder().parentDiv(new TagsFinder().parentDiv(siblings.get(0)));

				if (grandParent != null) {
					System.out.println("grandParent also found amd tht s " + grandParent.getTagName());
					List<WebElement> parentSiblings = new TagsFinder().childElements(grandParent);
					System.out.println("Parent elements size is : " + parentSiblings.size());
					String attributes = new SupportUtil().getAttributes(element);
					for (int i = 0; i < parentSiblings.size(); i++) {
						System.out.println(i + "Parent element tag is : " + parentSiblings.get(i).getTagName());
						List<WebElement> child = new TagsFinder().childElements(parentSiblings.get(i));
						System.out.println("Child elements size of parent " + child.size());
						if (child.size() == 1) {
							System.out.println("child.get(0).getTagName() : " + child.get(0).getTagName());
							if (child.get(0).getTagName().equalsIgnoreCase("span")) {
								allTexts.add(parentSiblings.get(i).getText());
							} else if (child.get(0).getTagName().equalsIgnoreCase("input")) {
								ipcount++;
								if (new SupportUtil().getAttributes(child.get(0)).equalsIgnoreCase(attributes)) {
									finalIdx = ipcount;
								}
								System.out.println("finalIdx is " + finalIdx);
							}
							System.out.println("allTexts inside if: " + allTexts);
						} else if (child.size() == 0) {
							System.out.println("Went inside zero child size");
							String txt = parentSiblings.get(i).getText();
							System.out.println("Text inside zero - " + txt);
							if ((txt != null) && txt.trim().length() > 0) {
								allTexts.add(parentSiblings.get(i).getText().trim());
							}
							System.out.println("allTexts inside else if: " + allTexts);
						}
					}
				} else {
					return false;
				}
				try {
					divParentingToInputWithParentSiblingText = allTexts.get(finalIdx);
				} catch (IndexOutOfBoundsException e) {

				}
				System.out.println("allTexts finally found and are : " + allTexts);
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
			isDivParentingToInputWithParentSiblingText();
			rdo.setSiblingLabel(siblingLabel);
			rdo.setParentLabelText(parentLabelText);
			rdo.setParentDivText(parentDivText);
			rdo.setDivParentingToInputWithParentSiblingText(divParentingToInputWithParentSiblingText);
			rdo.setElement(element);
			return rdo;
		}
		return null;
	}
	
}
