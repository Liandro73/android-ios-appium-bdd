package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class HomeContactsPageObject extends PageObjectHelper {

    @AndroidFindBy(id = "android:id/button2")
    private WebElement btnSkipBackUpOption;

    @AndroidFindBy(id = "com.google.android.contacts:id/open_search_bar")
    @iOSXCUITFindBy(accessibility = "Search")
    private WebElement fieldSearch;

    @AndroidFindBy(accessibility = "Create contact")
    @iOSXCUITFindBy(accessibility = "Add")
    private WebElement btnAddContact;

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

}
