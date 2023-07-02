package br.com.liandro.page.object;

import br.com.liandro.page.PageObjectHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ContactPageObject extends PageObjectHelper {

    public ContactPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void checkLabelFullNameContactIsDisplayed(String fullName) {
        WebElement labelFullNameContact;
        if ("ANDROID".equals(getPlatformNameString())) {
            labelFullNameContact =
                    driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"" + fullName + "\"]"));
            checkElementIsVisible(labelFullNameContact);
        } else {
            labelFullNameContact =
                    driver.findElement(AppiumBy.xpath("//XCUIElementTypeStaticText[@name=\"" + fullName + "\"]"));
            checkElementIsVisible(labelFullNameContact);
        }
    }

}
