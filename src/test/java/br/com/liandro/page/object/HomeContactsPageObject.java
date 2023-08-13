package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class HomeContactsPageObject extends PageObjectHelper {

    @AndroidFindBy(id = "android:id/button2")
    private WebElement btnSkipBackUpOption;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Search contacts']")
    @iOSXCUITFindBy(accessibility = "Search")
    private WebElement fieldSearch;

    @AndroidFindBy(accessibility = "Create contact")
    @iOSXCUITFindBy(accessibility = "Add")
    private WebElement btnAddContact;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='1 contact deleted']")
    private WebElement toastContactDeleted;

    public HomeContactsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void checkButtonSkipBackUpOptionIsVisibleAndClick(String step) throws IOException {
        try {
            checkElementIsVisible(btnSkipBackUpOption);
            takeScreenshot(step + "___Button skip is displayed");
            clickOnElement(btnSkipBackUpOption);
        } catch (NullPointerException | NoSuchElementException | StaleElementReferenceException ex) {
        }
    }

    public void checkSearchBarIsVisible(String step) throws IOException {
        checkElementIsVisible(fieldSearch);
        takeScreenshot(step + "___Field search is displayed");
    }

    public void clickOnSearchBar() {
        checkElementIsVisible(fieldSearch);
        clickOnElement(fieldSearch);
    }

    public void clickOnAddContactButton() {
        checkElementIsVisible(btnAddContact);
        clickOnElement(btnAddContact);
    }

    public void checkThatContactIsInTheList(String fullName) {
        if (Platform.IOS.equals(getPlatform())) {
            clickOnSearchBar();
            fieldSearch.sendKeys(fullName);
        } else if (Platform.ANDROID.equals(getPlatform())) {
            checkElementIsVisible(fieldSearch);
            clickOnSearchBar();
            elementExistsAndIsDisplay(fieldSearch);
            fieldSearch.sendKeys(fullName);
        }
        if (Platform.IOS.equals(getPlatform())) {
            checkElementIsVisible(driver.findElement(AppiumBy.xpath("//XCUIElementTypeCell[@name=\"" + fullName + "\"]")));
        } else {
            checkElementIsVisible(driver.findElement(AppiumBy.accessibilityId(fullName)));
        }
    }

    public void checkThatContactIsntInTheList(String fullName) {
        if (Platform.IOS.equals(getPlatform())) {
            checkElementIsVisible(driver.findElement(AppiumBy.accessibilityId("No Results for \"" + fullName + "\"")));
        } else {
            checkElementIsVisible(driver.findElement(AppiumBy.accessibilityId("No results")));
        }
    }

    public void clickOnContactIsInTheList(String fullName, String email) {
        if (Platform.IOS.equals(getPlatform())) {
            clickOnElement(driver.findElement(AppiumBy.xpath("//XCUIElementTypeCell[@name=\"" + fullName + ", " + email + "\"]/XCUIElementTypeOther[2]")));
        } else {
            clickOnElement(driver.findElement(AppiumBy.accessibilityId(fullName)));
        }
    }

    public void checkToastContactDeletedIsVisible() {
        elementExistsAndIsDisplay(toastContactDeleted);
    }

}
