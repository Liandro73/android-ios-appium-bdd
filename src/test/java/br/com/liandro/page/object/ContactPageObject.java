package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import br.com.liandro.utils.enuns.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactPageObject extends PageObjectHelper {

    @AndroidFindBy(id = "com.google.android.contacts:id/large_title")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Jonh Parker Jr']")
    private WebElement labelFullNameContact;

    public ContactPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnButtonMoreFields() {
        waitDriver.until(ExpectedConditions.elementToBeClickable(labelFullNameContact));
        labelFullNameContact.isDisplayed();
    }


}
