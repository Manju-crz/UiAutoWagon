package mks.uiautowagon.interactor.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import mks.uiautowagon.interactor.CurrentElement;
import mks.uiautowagon.interactor.interutil.SupportUtil;
import mks.uiautowagon.interactor.interutil.TagsFinder;
import mks.uiautowagon.interactor.patterns.objects.TextField;

public class TextFieldPatterns {
	
	//private WebElement element = null;
	
	private String placeholder = null;
	private String label = null;
	private String trParallelLabel = null;
	
	private String divNeighbourPlaceholder = null;
	private String ariaLabel = null;
	
	CurrentElement cElement = null;
	
	private List<String> acceptedAttributeTypes = new ArrayList<>(Arrays.asList("password", "text", "email"));
	
	public TextFieldPatterns(CurrentElement cElement) {
		this.cElement = cElement;
	}
	
	enum WithinAParentTag {
		InputTagHavingPlaceholder,
		InputTagWithSiblingedLabel,
		InputTagWithSiblingedDivPlaceholder,
		InputTagHavingAriaLabelholder;
	}
	
	
	enum OusideParentTag {
		FirstTrHavingLabelsSecondTrHavingInput;
	}
	

	private boolean isTextField() {
		String attributeType = cElement.getElement().getAttribute("type");
		System.out.println("attributeType is : " + attributeType);
		if ((attributeType != null) && (acceptedAttributeTypes.contains(attributeType)))
			return true;
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
		if ((label == null) && (placeholder == null)) {
			ariaLabel = cElement.getElement().getAttribute("aria-label").trim();
			return true;
		}
		return false;
	}
	
	
	public TextField findPattern() {
		if (isTextField()) {
			TextField tf = new TextField();
			isInputTagHavingPlaceholder();
			isInputTagWithSiblingedLabel();
			isFirstTrHavingLabelsSecondTrHavingInput();
			isInputTagWithSiblingedDivPlaceholder();
			isInputTagHavingAriaLabelholder();
			
			tf.setPlaceholder(placeholder);
			tf.setLabelText(label);
			tf.setTrParallelLabel(trParallelLabel);
			System.out.println("divNeighbourPlaceholder finally fnd is : " + divNeighbourPlaceholder);
			tf.setDivNeighbourPlaceholder(divNeighbourPlaceholder);
			tf.setAriaLabel(ariaLabel);
			//tf.setElement(element);
			tf.setcElement(cElement);
			return tf;
		}
		return null;
	}
	
	
	
	
}
