package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import br.com.liandro.utils.enuns.SwipeDirection;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;

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

    @AndroidFindBy(accessibility = "Mobile Phone")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='Forward'])[1]")
    private WebElement btnSelectPhoneType;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Phone']")
    private WebElement fieldPhone;

    @AndroidFindBy(accessibility = "Home Email")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='Forward'])[2]")
    private WebElement btnSelectEmailType;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='add email']")
    private WebElement btnAddEmail;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@value='Email']")
    private WebElement fieldEmail;

    @AndroidFindBy(accessibility = "Home Address")
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
        checkElementIsVisible(fieldFirstName);
        takeScreenshot(step + "___Field first name is displayed");
    }

    public void fillFieldFirstName(String step, String firstName) throws IOException {
        checkElementIsVisible(fieldFirstName);
        fieldFirstName.sendKeys(firstName);
        takeScreenshot(step + "___Field first name is filled");
    }

    public void fillFieldLastName(String step, String lastName) throws IOException {
        checkElementIsVisible(fieldLastName);
        fieldLastName.sendKeys(lastName);
        takeScreenshot(step + "___Field last name is filled");
    }

    public void fillFieldCompany(String step, String company) throws IOException {
        checkElementIsVisible(fieldCompany);
        fieldCompany.sendKeys(company);
        takeScreenshot(step + "___Field company is filled");
    }

    public void clickOnButtonAddPhone() {
        checkElementIsVisible(btnAddPhone);
        clickOnElement(btnAddPhone);
    }

    public void clickOnButtonSelectPhoneTypeAndSelectAType(String phoneType) {
        checkElementIsVisible(btnSelectPhoneType);
        clickOnElement(btnSelectPhoneType);
        WebElement labelPhoneType;
        if (Platform.IOS.equals(getPlatform())) {
            checkElementIsVisible(labelPhoneType = driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"" + phoneType.toLowerCase() + "\"]")));
            clickOnElement(labelPhoneType);
        } else {
            checkElementIsVisible(labelPhoneType = driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@index=3]")));
            clickOnElement(labelPhoneType);
        }
    }

    public void fillFieldPhone(String step, String phone) throws IOException {
        checkElementIsVisible(fieldPhone);
        fieldPhone.sendKeys(phone);
        takeScreenshot(step + "___Field phone is filled");
    }

    public void clickOnButtonAddEmail() {
        checkElementIsVisible(btnAddEmail);
        clickOnElement(btnAddEmail);
    }

    public void clickOnButtonSelectEmailTypeAndSelectAType(String emailType) {
        checkElementIsVisible(btnSelectEmailType);
        clickOnElement(btnSelectEmailType);
        WebElement labelEmailType;
        if (Platform.IOS.equals(getPlatform())) {
            checkElementIsVisible(labelEmailType = driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"" + emailType.toLowerCase() + "\"]")));
            clickOnElement(labelEmailType);
        } else {
            checkElementIsVisible(labelEmailType = driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@index=0]")));
            clickOnElement(labelEmailType);
        }
    }

    public void fillFieldEmail(String step, String email) throws IOException {
        if (Platform.ANDROID.equals(getPlatform())) {
            swipe(SwipeDirection.SWIPE_DOWN);
            swipe(SwipeDirection.SWIPE_DOWN);
        }
        checkElementIsVisible(fieldEmail);
        fieldEmail.sendKeys(email);
        takeScreenshot(step + "___Field email is filled");
    }

    public void clickOnButtonAddAddress() {
        if (Platform.IOS.equals(getPlatform())) {
            swipe(SwipeDirection.SWIPE_DOWN);
            checkElementIsVisible(btnAddAddress);
            clickOnElement(btnAddAddress);
        } else {
            checkElementIsVisible(btnMoreFields);
            clickOnElement(btnMoreFields);
        }
    }

    public void clickOnButtonSelectAddressTypeAndSelectAType(String addressType) {
        checkElementIsVisible(btnSelectAddressType);
        clickOnElement(btnSelectAddressType);
        WebElement labelAddressType;
        if (Platform.IOS.equals(getPlatform())) {
            checkElementIsVisible(labelAddressType = driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"" + addressType.toLowerCase() + "\"]")));
            clickOnElement(labelAddressType);
        } else {
            checkElementIsVisible(labelAddressType = driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout[@index=0]")));
            clickOnElement(labelAddressType);
        }
    }

    public void fillFieldAddress(String step, String address, String city, String state, String postalCode) throws IOException {
        checkElementIsVisible(fieldAddress);
        if (Platform.IOS.equals(getPlatform())) {
            fieldAddress.sendKeys(address);
            fieldCity.sendKeys(city);
            fieldState.sendKeys(state);
            fieldPostalCode.sendKeys(postalCode);
        }
        if (Platform.ANDROID.equals(getPlatform())) {
            fieldAddress.sendKeys(String.format(
                    "%s\n" + "%s\n" + "%s\n" + "%s", address, city, state, postalCode
            ));
        }
        takeScreenshot(step + "___Fields from address section is filled");
    }

    public void clickOnButtonSaveContact() {
        checkElementIsVisible(btnSaveContact);
        clickOnElement(btnSaveContact);
    }

    public void clickOnButtonMoreFields() {
        swipe(SwipeDirection.SWIPE_DOWN);
        swipe(SwipeDirection.SWIPE_DOWN);
        checkElementIsVisible(btnMoreFields);
        clickOnElement(btnMoreFields);
        swipe(SwipeDirection.SWIPE_DOWN);
    }

}
