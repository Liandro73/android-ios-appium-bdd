package br.com.liandro.page;

import br.com.liandro.page.object.HomeContactsPageObject;
import br.com.liandro.utils.DriverManager;
import br.com.liandro.utils.enuns.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectFactory {

    protected AppiumDriver driver;
    protected WebDriverWait waitDriver;
    protected final boolean onAndroid;
    protected final boolean oniOS;
    protected final static int SHORT_TIMEOUT = 5;
    protected final static int LONG_TIMEOUT = 30;

    private HomeContactsPageObject homeContactsPageObject;

    public PageObjectFactory(AppiumDriver driver) {
        onAndroid = DriverManager.getPlatform() == Platform.ANDROID;
        oniOS = DriverManager.getPlatform() == Platform.IOS;
        this.driver = driver;
        waitDriver = DriverManager.getWaitDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HomeContactsPageObject getContactsPageObject() {
        if(this.homeContactsPageObject == null) {
            this.homeContactsPageObject = new HomeContactsPageObject(driver);
        }
        return this.homeContactsPageObject;
    }

}
