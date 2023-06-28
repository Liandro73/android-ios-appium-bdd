package br.com.liandro.steps;

import br.com.liandro.page.object.HomeContactsPageObject;
import br.com.liandro.page.PageObjectFactory;
import br.com.liandro.steps.hooks.Hooks;
import br.com.liandro.utils.DriverManager;
import br.com.liandro.utils.enuns.Platform;
import io.appium.java_client.AppiumDriver;

import io.cucumber.java.Scenario;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSteps {

    protected AppiumDriver driver;
    protected WebDriverWait waitDriver;
    protected boolean onAndroid;
    protected boolean oniOS;
    protected Scenario scenario;
    protected String scenarioName;

    /**
     * Pages singleton
     */
    PageObjectFactory pageObjectFactory;
    HomeContactsPageObject homeContactsPageObject;

    public BaseSteps() {
        onAndroid = DriverManager.getPlatform() == Platform.ANDROID;
        oniOS = DriverManager.getPlatform() == Platform.IOS;
        driver = DriverManager.getDriver();
        waitDriver = DriverManager.getWaitDriver();
        this.pageObjectFactory = new PageObjectFactory(driver);
        this.homeContactsPageObject = pageObjectFactory.getContactsPageObject();
        this.scenario = Hooks.getRunningScenario();
        scenarioName = scenario.getName();
    }

}
