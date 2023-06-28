package br.com.liandro.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeviceDimensions {

    public AppiumDriver driver;
    public WebDriverWait wait;
    public final Dimension dimension;
    public final int height;
    public final int width ;
    public final int middleHeight;
    public final int middleWidth;
    public final int initialHeight;
    public final int initialWidth;

    public DeviceDimensions(AppiumDriver driver) {
        this.driver = DriverManager.getDriver();
        wait = DriverManager.getWaitDriver();
        this.dimension = this.driver.manage().window().getSize();
        this.height = dimension.getHeight();
        this.width  = dimension.getWidth();
        this.middleHeight = height/2;
        this.middleWidth = width/2;
        this.initialHeight = 1;
        this.initialWidth = 1;
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
