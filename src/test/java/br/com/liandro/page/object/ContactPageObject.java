package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactPageObject extends PageObjectHelper {

    public ContactPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnButtonMoreFields(String fullName) {
        WebElement labelFullNameContact;
        if ("ANDROID".equals(getPlatformNameString())) {
            labelFullNameContact =
                    driver.findElement(By.xpath("//android.widget.TextView[@text='" + fullName + "']"));
            waitDriver.until(ExpectedConditions.elementToBeClickable(labelFullNameContact));
            labelFullNameContact.isDisplayed();
        } else {
            labelFullNameContact =
                    driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='" + fullName + "']"));
            waitDriver.until(ExpectedConditions.elementToBeClickable(labelFullNameContact));
            labelFullNameContact.isDisplayed();
        }
    }

}
