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

    public boolean checkButtonSkipBackUpOptionIsVisibleAndClick(String step) throws IOException {
        try {
            btnSkipBackUpOption.isDisplayed();
            takeScreenshot(step + "___Button skip is displayed");
            btnSkipBackUpOption.click();
            return true;
        } catch (NullPointerException | NoSuchElementException | StaleElementReferenceException ex) {
            return false;
        }
    }

    public void checkSearchBarIsVisible(String step) throws IOException {
        fieldSearch.isDisplayed();
        takeScreenshot(step + "___Field search is displayed");
    }

    public void clickOnSearchBar() {
        fieldSearch.isDisplayed();
        fieldSearch.click();
    }

    public void clickOnAddContactButton() {
        btnAddContact.isDisplayed();
        btnAddContact.click();
    }

}
