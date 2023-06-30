package br.com.liandro.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeviceDimensions {

    protected AppiumDriver driver;
    protected WebDriverWait wait;
    protected final Dimension dimension;
    protected final int height;
    protected final int width;
    protected final int middleHeight;
    protected final int middleWidth;
    protected final int initialHeight;
    protected final int initialWidth;

    public DeviceDimensions(AppiumDriver driver) {
        this.driver = DriverManager.getDriver();
        wait = DriverManager.getWaitDriver();
        this.dimension = this.driver.manage().window().getSize();
        this.height = dimension.getHeight();
        this.width = dimension.getWidth();
        this.middleHeight = height / 2;
        this.middleWidth = width / 2;
        this.initialHeight = width / 4;
        this.initialWidth = width / 4;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMiddleHeight() {
        return middleHeight;
    }

    public int getMiddleWidth() {
        return middleWidth;
    }

    public int getInitialHeight() {
        return initialHeight;
    }

    public int getInitialWidth() {
        return initialWidth;
    }

}
