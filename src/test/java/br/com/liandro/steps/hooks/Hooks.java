package br.com.liandro.steps.hooks;

import br.com.liandro.utils.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks {

    protected AppiumDriver driver;
    protected Instant start;
    private static final Logger LOGGER;
    private static Scenario runningScenario;
    protected String platformName;
    public static int value = 0;

    public Integer getCountTest() {
        return value = value + 1;
    }

    static {
        LOGGER = Logger.getLogger(Hooks.class.getName());
        LOGGER.setLevel(Level.ALL);
    }

    public Hooks() {
    }

    @Before(order = 0)
    public void startScenario(Scenario scenario) {
        DriverManager.startDriver();
        this.driver = DriverManager.getDriver();
        runningScenario = scenario;
        start = Instant.now();

        platformName = driver.getCapabilities().getPlatformName().toString();

        LOGGER.info(" ------------- STARTING SCENARIO ------------- ");
        if ("IOS".equals(platformName)) {
            try {
                FileUtils.deleteDirectory(new File("target/screenshots/" + platformName));
            } catch (IOException e) {
            }
            LOGGER.info(" -------------- RUNNING  ON  IOS -------------- ");
        }
        if ("ANDROID".equals(platformName)) {
            try {
                FileUtils.deleteDirectory(new File("target/screenshots/" + platformName));
            } catch (IOException e) {
            }
            LOGGER.info(" ------------ RUNNING  ON  ANDROID ------------ ");
        }

        Integer scenarioCount = getCountTest();
        LOGGER.info("SCENARIO: " + scenario.getName().toUpperCase() + "  NUMBER: " + scenarioCount);
    }

    public static Scenario getRunningScenario() {
        return runningScenario;
    }

    @After
    public void tearDown() throws IOException {
        Instant end = Instant.now();
        long runtime = (end.toEpochMilli() - start.toEpochMilli()) / 1000;
        LOGGER.info(" ------------- FINISHING SCENARIO ------------- ");
        LOGGER.info(String.format("STATUS %s", runningScenario.getStatus()));
        LOGGER.info(String.format("RUNTIME: %s SECONDS", runtime));

        finalScreenshot();
        DriverManager.stopApp();
    }

    public void finalScreenshot() throws IOException {

        String scenarioName = runningScenario.getName();

        File evidence = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        if (runningScenario.isFailed()) {
            try {
                FileUtils.forceDelete(
                        new File("target/evidences/" + platformName + "__" + scenarioName + "__FAILED.png"));
            } catch (IOException e) {
            }
            FileUtils.moveFile(evidence,
                    new File("target/evidences/" + platformName + "__" + scenarioName + "__FAILED.png"));
        } else {
            try {
                FileUtils.forceDelete(
                        new File("target/evidences/" + platformName + "__" + scenarioName + "__PASSED.png"));
            } catch (IOException e) {
            }
            FileUtils.moveFile(evidence,
                    new File("target/evidences/" + platformName + "__" + scenarioName + "__PASSED.png"));
        }
    }

}
