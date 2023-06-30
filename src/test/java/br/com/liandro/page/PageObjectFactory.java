package br.com.liandro.page;

import br.com.liandro.page.object.ContactPageObject;
import br.com.liandro.page.object.CreateContactsPageObject;
import br.com.liandro.page.object.HomeContactsPageObject;
import br.com.liandro.utils.DriverManager;
import br.com.liandro.utils.enuns.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.ref.PhantomReference;

public class PageObjectFactory {

    protected AppiumDriver driver;
    protected WebDriverWait waitDriver;
    protected final boolean onAndroid;
    protected final boolean oniOS;

    private HomeContactsPageObject homeContactsPageObject;
    private CreateContactsPageObject createContactsPageObject;
    private ContactPageObject contactPageObject;

    public PageObjectFactory(AppiumDriver driver) {
        onAndroid = DriverManager.getPlatform() == Platform.ANDROID;
        oniOS = DriverManager.getPlatform() == Platform.IOS;
        this.driver = driver;
        waitDriver = DriverManager.getWaitDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HomeContactsPageObject getHomeContactsPageObject() {
        if (this.homeContactsPageObject == null) {
            this.homeContactsPageObject = new HomeContactsPageObject(driver);
        }
        return this.homeContactsPageObject;
    }

    public CreateContactsPageObject getCreateContactsPageObject() {
        if (this.createContactsPageObject == null) {
            this.createContactsPageObject = new CreateContactsPageObject(driver);
        }
        return this.createContactsPageObject;
    }

    public ContactPageObject getContactPageObject() {
        if (this.contactPageObject == null) {
            this.contactPageObject = new ContactPageObject(driver);
        }
        return this.contactPageObject;
    }

}
