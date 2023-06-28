package br.com.liandro.steps.hooks;

import br.com.liandro.page.PageObjectHelper;
import br.com.liandro.utils.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hooks {

    protected AppiumDriver driver;
    protected Instant inicio;
    private static final Logger LOGGER;
    private static Scenario runningScenario;

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

    @Before
    public void startScenario(Scenario scenario) {
        DriverManager.startDriver();
        this.driver = DriverManager.getDriver();
        runningScenario = scenario;
        inicio = Instant.now();
        LOGGER.info(" ------------- STARTING SCENARIO ------------- ");

        Integer scenarioCount = getCountTest();
        LOGGER.info("SCENARIO: " + scenario.getName().toUpperCase() + "  NUMBER: " + scenarioCount);
    }

    public static Scenario getRunningScenario() {
        return runningScenario;
    }

    @After
    public void tearDown(Scenario scenario) {
        Instant fim = Instant.now();
        long runtime = (fim.toEpochMilli() - inicio.toEpochMilli()) / 1000;
        LOGGER.info(" ------------- FINISHING SCENARIO ------------- ");
        LOGGER.info(String.format("STATUS %s", runningScenario.getStatus()));
        LOGGER.info(String.format("RUNTIME: %s SECONDS", runtime));

        finalScreenshot();
        DriverManager.stopApp();
    }

    public void finalScreenshot() {

        String platformName = driver.getCapabilities().getPlatformName().toString();
        String scenarioName = runningScenario.getName();

        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        File filePath;
        if (runningScenario.isFailed()) {
            filePath = new File("target/evidences/" + platformName + "__" + scenarioName + "__FAILED.png");
        } else {
            filePath = new File("target/evidences/" + platformName + "__" + scenarioName + "__PASSED.png");
        }
        BufferedImage image;
        ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(screenshot);
        try {
            image = ImageIO.read(byteArrayIS);
            byteArrayIS.close();
            ImageIO.write(image, "png", filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
