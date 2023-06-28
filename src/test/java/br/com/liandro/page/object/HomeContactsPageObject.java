package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class HomeContactsPageObject extends PageObjectHelper {

    @AndroidFindBy(id = "com.google.android.contacts:id/open_search_bar")
    @iOSXCUITFindBy(accessibility = "Search")
    private WebElement fieldSearch;

    @AndroidFindBy(accessibility = "Create contact")
    @iOSXCUITFindBy(accessibility = "Add")
    private WebElement btnAddContact;

    public HomeContactsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void checkSearchBarIsVisible(String step) throws IOException {
        fieldSearch.isDisplayed();
        takeScreenshot(step + "___Field search is displayed");
    }

    public void clickOnSearchBar(String step) {
        fieldSearch.isDisplayed();
        fieldSearch.click();
    }

    public void clickOnAddContactButton(String step) {
        btnAddContact.isDisplayed();
        btnAddContact.click();
    }

}
