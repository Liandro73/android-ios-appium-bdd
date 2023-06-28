package br.com.liandro.page;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class PageObjectHelper extends PageObjectFactory {

    LocalDateTime dateTime = LocalDateTime.now();
    public static int value = 0;

    public Integer getCountTest() {
        return value = value + 1;
    }

    public PageObjectHelper(AppiumDriver driver) {
        super(driver);
    }

    protected String getPlatformName() {
        return driver.getCapabilities().getPlatformName().toString();
    }

    protected void takeScreenshot(String step) throws IOException {
        File evidence = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String platformName = getPlatformName();
        String stepNameToScreenshot = step
                .toLowerCase()
                .replace(" ", "_");
        FileUtils.moveFile(evidence,
                new File("target/screenshots/" + platformName + "/" + platformName + "__" + stepNameToScreenshot + "_" + dateTime + ".jpg"));
    }

}
