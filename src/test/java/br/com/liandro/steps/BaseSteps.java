package br.com.liandro.steps;

import br.com.liandro.page.PageObjectHelper;
import br.com.liandro.page.object.ContactPageObject;
import br.com.liandro.page.object.CreateContactsPageObject;
import br.com.liandro.page.object.HomeContactsPageObject;
import br.com.liandro.page.PageObjectFactory;
import br.com.liandro.steps.hooks.Hooks;
import br.com.liandro.utils.DriverManager;
import com.github.javafaker.Faker;
import io.appium.java_client.AppiumDriver;

import io.cucumber.java.Scenario;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSteps {

    Faker faker = new Faker();
    protected AppiumDriver driver;
    protected WebDriverWait waitDriver;
    protected Scenario scenario;
    protected String scenarioName;

    /**
     * Pages singleton
     */
    protected PageObjectFactory pageObjectFactory;
    protected PageObjectHelper pageObjectHelper;
    protected HomeContactsPageObject homeContactsPageObject;
    protected CreateContactsPageObject createContactsPageObject;
    protected ContactPageObject contactPageObject;

    public BaseSteps() {
        driver = DriverManager.getDriver();
        waitDriver = DriverManager.getWaitDriver();
        this.pageObjectFactory = new PageObjectFactory(driver);
        this.pageObjectHelper = pageObjectFactory.getPageObjectHelper();
        this.homeContactsPageObject = pageObjectFactory.getHomeContactsPageObject();
        this.createContactsPageObject = pageObjectFactory.getCreateContactsPageObject();
        this.contactPageObject = pageObjectFactory.getContactPageObject();
        this.scenario = Hooks.getRunningScenario();
        scenarioName = scenario.getName();
    }

}
