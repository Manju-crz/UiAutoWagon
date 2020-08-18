package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.TextArea;
import mks.uiautowagon.interactor.patterns.objects.TextField;
import mks.uiautowagon.interactor.store.TextFieldsStore;

public class TextAreaPatterns {
	
	private String placeholder = null;
	private String label = null;
	private String trParallelLabel = null;
	
	private String divNeighbourPlaceholder = null;
	private String ariaLabel = null;
	
	private String parentDivsSiblingLabel = null;
	private String parentDivsSiblingInnerLabel = null;
	
	CurrentElement cElement = null;
	
	private List<String> acceptedAttributeTypes = new ArrayList<>(Arrays.asList("textarea"));
	
	public TextAreaPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	
	enum WithinAParentTag {
		InputTagHavingPlaceholder,
		InputTagWithSiblingedLabel,
		InputTagWithSiblingedDivPlaceholder,
		InputTagHavingAriaLabelholder;
	}
	
	
	enum OusideParentTag {
		FirstTrHavingLabelsSecondTrHavingInput,
		DivParentsSiblingLabelUnderGrandParentDiv,
		DivParentsSiblingChildLabelUnderGrandParentDiv;
	}
	

	private boolean isTextArea() {
		if (cElement.getTagName().equalsIgnoreCase("textarea"))
			return true;
		if (cElement.getTagName().equalsIgnoreCase("input")) {
			String attributeType = cElement.getElement().getAttribute("type");
			if ((attributeType != null) && (acceptedAttributeTypes.contains(attributeType)))
				return true;
		}
		return false;
	}

	private boolean isLabelFound() {
		if (label == null) {
			return false;
		} else if (label.trim().length() == 0) {
			return false;
		}
		return true;
	}

	private boolean isPlaceholderFound() {
		if (placeholder == null) {
			return false;
		} else if (placeholder.trim().length() == 0) {
			return false;
		}
		return true;
	}

	private boolean isInputTagHavingPlaceholder() {
		placeholder = cElement.getElement().getAttribute("placeholder");
		System.out.println("placeholder is : " + placeholder);
		if (placeholder != null) {
			return true;
		}
		return false;
	}
	
	private boolean isInputTagWithSiblingedLabel() {

		WebElement siblingLabel = new TagsFinder().siblingLabel(cElement.getElement());
		if (siblingLabel != null) {
			label = siblingLabel.getText().trim();
			return true;
		}
		return false;
	}
	
	
	private boolean isFirstTrHavingLabelsSecondTrHavingInput() {

		if (label == null) {
			String elementAttribute = new SupportUtil().getAttributes(cElement.getElement());
			System.out.println("elementAttribute -- " + elementAttribute);
			WebElement tdParent = new TagsFinder().parentTD(cElement.getElement());
			if (tdParent != null) {
				WebElement trParent = new TagsFinder().parentTR(tdParent);
				if(trParent != null) {
					WebElement parentTbodyElement = new TagsFinder().parentTBody(trParent);
					if (parentTbodyElement != null) {
						List<WebElement> allInnerInputs = new TagsFinder().innerInputs_ChildToTD(parentTbodyElement);
						System.out.println("allInnerInputs found are: " + allInnerInputs.size());
						int i;
						for (i = 0; i < allInnerInputs.size(); i++) {
							System.out.println("new SupportUtil().getAttributes(allInnerInputs.get(i)) : " + new SupportUtil().getAttributes(allInnerInputs.get(i)));
							if (new SupportUtil().getAttributes(allInnerInputs.get(i)).equalsIgnoreCase(elementAttribute)) {
								break;
							}
						}
						List<WebElement> allInnerLabels = new TagsFinder().innerLabels_ChildToTD(parentTbodyElement);
						System.out.println("allInnerLabels found are: " + allInnerLabels.size());
						if (allInnerLabels.size() > i) {
							trParallelLabel = allInnerLabels.get(i).getText().trim();
							System.out.println("allInnerLabels.get(i).getText().trim() : " + trParallelLabel);
						}
					}
				}
			}
		}
		return false;
	}
	
	
	private boolean isInputTagWithSiblingedDivPlaceholder() {
		
		System.out.println("label at this posiiong is : " + label);
		System.out.println("placeholder at this posiiong is : " + placeholder);

		if ((!isLabelFound()) && (!isPlaceholderFound())) {
			List<WebElement> siblings = new TagsFinder().siblingDivs(cElement.getElement());
			System.out.println("Found siblings divs are : " + siblings.size());

			for (WebElement tempElement : siblings) {
				
				String placeHolderTxt = tempElement.getAttribute("placeholder");
				System.out.println("placeHolderTxt fnd is : " + placeHolderTxt);
				if ((placeHolderTxt != null) && placeHolderTxt.trim().length() > 0) {
					divNeighbourPlaceholder = placeHolderTxt.trim();
					System.out.println("divNeighbourPlaceholder s : " + divNeighbourPlaceholder);
					return true;
				}
				placeHolderTxt = tempElement.getAttribute("class");
				System.out.println("class fnd is : " + placeHolderTxt);
				if ((placeHolderTxt != null) && placeHolderTxt.trim().equalsIgnoreCase("placeholder")) {
					divNeighbourPlaceholder = tempElement.getText().trim();
					return true;
				}
				placeHolderTxt = tempElement.getAttribute("name");
				System.out.println("name fnd is : " + placeHolderTxt);
				if ((placeHolderTxt != null) && placeHolderTxt.trim().equalsIgnoreCase("placeholder")) {
					divNeighbourPlaceholder = tempElement.getText().trim();
					return true;
				}
				
				
			}
		}
		return false;
	}
	
	private boolean isInputTagHavingAriaLabelholder() {
		System.out.println("isInputTagHavingAriaLabelholder label : " + label);
		System.out.println("isInputTagHavingAriaLabelholder placeholder : " + placeholder);
		if ((label == null) && (!isPlaceholderFound())) {
			ariaLabel = cElement.getElement().getAttribute("aria-label");
			System.out.println("ariaLabel is :: " + ariaLabel);
			return true;
		}
		return false;
	}

	private boolean isDivParentsSiblingLabelUnderGrandParentDiv() {
		if ((label == null) && (trParallelLabel == null) && (ariaLabel == null)) {
			WebElement parentDiv = new TagsFinder().parentDiv(cElement.getElement());
			if (parentDiv != null) {
				WebElement parentSiblingLabel = new TagsFinder().siblingLabel(parentDiv);
				if (parentSiblingLabel != null) {
					parentDivsSiblingLabel = parentSiblingLabel.getText();
					return true;
				}
			}
		}
		return false;
	}
	

	private boolean isDivParentsSiblingChildLabelUnderGrandParentDiv() {
		if (parentDivsSiblingLabel == null) {
			System.out.println("TextFieldsStore.textFieldsList.size() : " + TextFieldsStore.textFieldsList.size());
			WebElement parentDiv = new TagsFinder().parentDiv(cElement.getElement());
			if (parentDiv != null) {
				List<WebElement> parentSiblingDivs = new TagsFinder().siblingDivs(parentDiv);
				System.out.println("parentSiblingDivs are : " + parentSiblingDivs.size());
				for (WebElement divElement : parentSiblingDivs) {
					List<WebElement> childLabels = new TagsFinder().childLabels(divElement);
					System.out.println("childLabels size : " + childLabels.size());
					for (WebElement label : childLabels) {
						parentDivsSiblingInnerLabel = label.getText();
						System.out.println("parentDivsSiblingInnerLabel is : " + parentDivsSiblingInnerLabel);
						if (parentDivsSiblingInnerLabel != null && parentDivsSiblingInnerLabel.trim().length() > 0) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public TextArea findPattern() {
		if (isTextArea()) {
			TextArea tf = new TextArea();
			isInputTagHavingPlaceholder();
			isInputTagWithSiblingedLabel();
			isFirstTrHavingLabelsSecondTrHavingInput();
			isInputTagWithSiblingedDivPlaceholder();
			isInputTagHavingAriaLabelholder();
			isDivParentsSiblingLabelUnderGrandParentDiv();
			isDivParentsSiblingChildLabelUnderGrandParentDiv();
			
			tf.setPlaceholder(placeholder);
			tf.setLabelText(label);
			tf.setTrParallelLabel(trParallelLabel);
			System.out.println("divNeighbourPlaceholder finally fnd is : " + divNeighbourPlaceholder);
			tf.setDivNeighbourPlaceholder(divNeighbourPlaceholder);
			tf.setAriaLabel(ariaLabel);
			tf.setParentDivsSiblingLabel(parentDivsSiblingLabel);
			tf.setParentDivsSiblingInnerLabel(parentDivsSiblingInnerLabel);
			tf.setcElement(cElement);
			return tf;
		}
		return null;
	}
	
	
	
	
}
