package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import br.com.liandro.utils.enuns.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class CreateContactsPageObject extends PageObjectHelper {

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='First name']")
    @iOSXCUITFindBy(accessibility = "First name")
    private WebElement fieldFirstName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Last name']")
    @iOSXCUITFindBy(accessibility = "Last name")
    private WebElement fieldLastName;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Company']")
    @iOSXCUITFindBy(accessibility = "Company")
    private WebElement fieldCompany;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='add phone']")
    private WebElement btnAddPhone;

    @AndroidFindBy(xpath = "(//android.widget.ImageButton[@content-desc='Show dropdown menu'])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='Forward'])[1]")
    private WebElement btnSelectPhoneType;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Phone']")
    private WebElement fieldPhone;

    @AndroidFindBy(xpath = "(//android.widget.ImageButton[@content-desc='Show dropdown menu'])[2]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='Forward'])[2]")
    private WebElement btnSelectEmailType;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='add email']")
    private WebElement btnAddEmail;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Email']")
    private WebElement fieldEmail;

    @AndroidFindBy(xpath = "(//android.widget.ImageButton[@content-desc='Show dropdown menu'])[5]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='Forward'])[5]")
    private WebElement btnSelectAddressType;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='add address']")
    private WebElement btnAddAddress;

    @AndroidFindBy(xpath = "//android.widget.AutoCompleteTextView[@text='Address']")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeTextField[@name='Street'])[2]")
    private WebElement fieldAddress;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='City']")
    private WebElement fieldCity;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Save']")
    @iOSXCUITFindBy(accessibility = "Done")
    private WebElement btnSaveContact;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='State']")
    private WebElement fieldState;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='Postal Code']")
    private WebElement fieldPostalCode;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='More fields']")
    private WebElement btnMoreFields;

    public CreateContactsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void checkFieldFirstNameIsVisible(String step) throws IOException {
        fieldFirstName.isDisplayed();
        takeScreenshot(step + "___Field first name is displayed");
    }

    public void fillFieldFirstName(String step, String firstName) throws IOException {
        fieldFirstName.isDisplayed();
        fieldFirstName.sendKeys(firstName);
        takeScreenshot(step + "___Field first name is filled");
    }

    public void fillFieldLastName(String step, String lastName) throws IOException {
        fieldLastName.isDisplayed();
        fieldLastName.sendKeys(lastName);
        takeScreenshot(step + "___Field last name is filled");
    }

    public void fillFieldCompany(String step, String company) throws IOException {
        fieldCompany.isDisplayed();
        fieldCompany.sendKeys(company);
        takeScreenshot(step + "___Field company is filled");
    }

    public void clickOnButtonAddPhone() {
        btnAddPhone.isDisplayed();
        btnAddPhone.click();
    }

    @AndroidFindBy(accessibility = "Mobile Phone")
    WebElement spinner;

    public void clickOnButtonSelectPhoneTypeAndSelectAType(String phoneType) {
        btnSelectPhoneType.isDisplayed();
        btnSelectPhoneType.click();
        WebElement labelPhoneType;
        if("IOS".equals(getPlatformNameString())) {
            labelPhoneType = driver.findElement(By.xpath(String.format("//XCUIElementTypeStaticText[@name='%s']", phoneType.toLowerCase())));
            labelPhoneType.click();
        } else {
            labelPhoneType = driver.findElement(By.xpath(String.format("//android.widget.TextView[@text='%s']", phoneType)));
            labelPhoneType.click();
        }
    }

    public void fillFieldPhone(String step, String phone) throws IOException {
        fieldPhone.isDisplayed();
        fieldPhone.sendKeys(phone);
        takeScreenshot(step + "___Field phone is filled");
    }

    public void clickOnButtonAddEmail() {
        btnAddEmail.isDisplayed();
        btnAddEmail.click();
    }

    public void clickOnButtonSelectEmailTypeAndSelectAType(String emailType) {
        btnSelectEmailType.isDisplayed();
        btnSelectEmailType.click();
        WebElement labelEmailType;
        if("IOS".equals(getPlatformNameString())) {
            labelEmailType = driver.findElement(By.xpath(String.format("//XCUIElementTypeStaticText[@name='%s']", emailType.toLowerCase())));
            labelEmailType.click();
        } else {
            labelEmailType = driver.findElement(By.xpath(String.format("//*[@name='%s']", emailType)));
            labelEmailType.click();
        }
    }

    public void fillFieldEmail(String step, String email) throws IOException {
        if("ANDROID".equals(getPlatformNameString())) {
            swipe(SwipeDirection.SWIPE_DOWN);
        }
        fieldEmail.isDisplayed();
        fieldEmail.sendKeys(email);
        takeScreenshot(step + "___Field email is filled");
    }

    public void clickOnButtonAddAddress() {
        swipe(SwipeDirection.SWIPE_DOWN);
        if("IOS".equals(getPlatformNameString())) {
            btnAddAddress.isDisplayed();
            btnAddAddress.click();
        } else {
            btnMoreFields.isDisplayed();
            btnMoreFields.click();
        }
    }

    public void clickOnButtonSelectAddressTypeAndSelectAType(String addressType) {
        btnSelectAddressType.isDisplayed();
        btnSelectAddressType.click();
        WebElement labelAddressType;
        if("IOS".equals(getPlatformNameString())) {
            labelAddressType = driver.findElement(By.xpath(String.format("//XCUIElementTypeStaticText[@name='%s']", addressType.toLowerCase())));
            labelAddressType.click();
        } else {
            labelAddressType = driver.findElement(By.xpath(String.format("android.widget.TextView[@name='%s']", addressType)));
            labelAddressType.click();
        }
    }

    public void fillFieldAddress(String step, String address, String city, String state, String postalCode) throws IOException {
        fieldAddress.isDisplayed();
        if("IOS".equals(getPlatformNameString())) {
            fieldAddress.sendKeys(address);
            fieldCity.sendKeys(city);
            fieldState.sendKeys(state);
            fieldPostalCode.sendKeys(postalCode);
        }
        if("ANDROID".equals(getPlatformNameString())) {
            fieldAddress.sendKeys(String.format(
                    "%s\n" + "%s\n" + "%s\n" + "%s", address, city, state, postalCode
            ));
        }
        takeScreenshot(step + "___Fields from address section is filled");
    }

    public void clickOnButtonSaveContact() {
        btnSaveContact.isDisplayed();
        btnSaveContact.click();
    }

    public void clickOnButtonMoreFields() {
        swipe(SwipeDirection.SWIPE_DOWN);
        swipe(SwipeDirection.SWIPE_DOWN);
        btnMoreFields.isDisplayed();
        waitDriver.until(ExpectedConditions.elementToBeClickable(btnMoreFields));
        btnMoreFields.click();
        swipe(SwipeDirection.SWIPE_DOWN);
        swipe(SwipeDirection.SWIPE_DOWN);
    }

}
