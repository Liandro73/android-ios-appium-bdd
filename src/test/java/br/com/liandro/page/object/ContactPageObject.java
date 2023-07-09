package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import br.com.liandro.utils.enuns.SwipeDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

public class ContactPageObject extends PageObjectHelper {

    @AndroidFindBy(accessibility = "Navigate up")
    @iOSXCUITFindBy(accessibility = "iPhone")
    private WebElement btnBackContact;

    @AndroidFindBy(accessibility = "Edit contact")
    @iOSXCUITFindBy(accessibility = "Edit")
    private WebElement btnEditContact;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Delete Contact']")
    private WebElement btnDeleteContact;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Delete']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Delete Contact']")
    private WebElement btnConfirmDeleteContact;

    @AndroidFindBy(accessibility = "More options")
    private WebElement btnMoreOptions;

    public ContactPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void checkLabelFullNameContactIsDisplayed(String fullName) {
        WebElement labelFullNameContact;
        if (Platform.ANDROID.equals(getPlatform())) {
            labelFullNameContact =
                    driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"" + fullName + "\"]"));
            checkElementIsVisible(labelFullNameContact);
        } else {
            labelFullNameContact =
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"" + fullName + "\"]"));
            checkElementIsVisible(labelFullNameContact);
        }
    }

    public void clickOnButtonBackContacts() {
        checkElementIsVisible(btnBackContact);
        clickOnElement(btnBackContact);
    }

    public void clickOnButtonEditContact() {
        checkElementIsVisible(btnEditContact);
        clickOnElement(btnEditContact);
    }

    public void clickOnButtonDeleteContact() {
        if (Platform.IOS.equals(getPlatform())) {
            swipeToElement(btnDeleteContact);
        }
        if (Platform.ANDROID.equals(getPlatform())) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            checkElementIsVisible(btnMoreOptions);
            clickOnElement(btnMoreOptions);
        }
        checkElementIsVisible(btnDeleteContact);
        clickOnElement(btnDeleteContact);
    }

    public void clickOnButtonConfirmDeleteContact() {
        checkElementIsVisible(btnConfirmDeleteContact);
        clickOnElement(btnConfirmDeleteContact);
    }

}
