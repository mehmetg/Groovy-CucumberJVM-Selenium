package Pages

import groovy.transform.PackageScope;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;

/**
 * Created by mehmetgerceker on 9/20/15.
 */
class PageBase {

    @PackageScope
    static void setCheckCheckBoxState(WebElement checkBox, boolean checked)
            throws InvalidElementStateException, Exception{
        if (checkBox.getAttribute("type") != 'checkbox' || checkBox.getTagName() != 'input'){
            throw new Exception("This web element is not a checkbox!");
        }
        //we may wanna check if it is displayed and enabled, when performing actions.
        if ( checkBox.isDisplayed() && checkBox.isEnabled()){
            if (checkBox.isSelected() != checked) {
                checkBox.click();
            }
        } else {
            throw new InvalidElementStateException("Checkbox by "
                    + checkBox.getAttribute("id")
                    + " is disabled!");
        }

    }

    @PackageScope
    static void setTextInputValue(WebElement textInput, String value)
            throws InvalidElementStateException, Exception{
        if (textInput.getAttribute("type") != 'text' || textInput.getTagName() != 'input'){
            throw new Exception("This web element is not a text input!");
        }
        //we may wanna check if it is displaued and enabled, when performing actions.
        if (textInput.isDisplayed() && textInput.isEnabled()){
            textInput.click();
            textInput.clear();
            textInput.sendKeys(value);
        } else {
            throw new InvalidElementStateException("Text input by "
                    + textInput.getAttribute("id")
                    + " is disabled or not displayed!");
        }
    }

    @PackageScope
    static void setTextAreaInputValue(WebElement textArea, String value)
            throws InvalidElementStateException, Exception{
        if (textArea.getAttribute("type") != 'textarea' || textArea.getTagName() != 'input'){
            throw new Exception("This web element is not a text area input!");
        }
        //we may wanna check if it is displaued and enabled, when performing actions.
        if (textArea.isDisplayed() && textArea.isEnabled()){
            textArea.click();
            textArea.clear();
            textArea.sendKeys(value);
        } else {
            throw new InvalidElementStateException("Text area by "
                    + textArea.getAttribute("id")
                    + " is disabled or not displayed!");
        }
    }
    @PackageScope
    static void clickButton(WebElement button){
        if (button.getAttribute("type") != 'submit' || button.getTagName() != 'input'){
            throw new Exception("This web element is not a button input!");
        }
        //we may wanna check if it is displaued and enabled, when performing actions.
        if (button.isDisplayed() && button.isEnabled()){
            button.click();
        } else {
            throw new InvalidElementStateException("Button by "
                    + button.getAttribute("id")
                    + " is disabled or not displayed!");
        }
    }
}
