package br.com.liandro.page;

import br.com.liandro.utils.DeviceDimensions;
import br.com.liandro.utils.enuns.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;

public class PageObjectHelper extends PageObjectFactory {

    public static int value = 0;

    DeviceDimensions deviceDimensions = new DeviceDimensions(driver);

    public Integer getCountTest() {
        return value = value + 1;
    }

    public PageObjectHelper(AppiumDriver driver) {
        super(driver);
    }

    protected String getPlatformNameString() {
        return driver.getCapabilities().getPlatformName().toString();
    }

    protected void takeScreenshot(String step) throws IOException {
        File evidence = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String platformName = getPlatformNameString();
        String stepNameToScreenshot = step
                .toLowerCase()
                .replace(" ", "_");
        FileUtils.moveFile(evidence,
                new File("target/screenshots/" + platformName + "/" + getCountTest() + "_" + platformName + "__" + stepNameToScreenshot + ".jpg"));
    }

    private PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

    public void swipe(SwipeDirection direction) {

        Sequence dragNDrop = new Sequence(finger, 1);
        int startX, startY, endX, endY;
        switch (direction) {
            case SWIPE_RIGHT:
                startX = deviceDimensions.getMiddleWidth();
                startY = deviceDimensions.getMiddleHeight();
                endX = deviceDimensions.getInitialWidth();
                endY = deviceDimensions.getMiddleHeight();
                break;
            case SWIPE_LEFT:
                startX = deviceDimensions.getInitialWidth();
                startY = deviceDimensions.getMiddleHeight();
                endX = deviceDimensions.getMiddleWidth();
                endY = deviceDimensions.getMiddleHeight();
                break;
            case SWIPE_DOWN:
                startX = deviceDimensions.getMiddleWidth();
                startY = deviceDimensions.getMiddleHeight();
                endX = deviceDimensions.getMiddleWidth();
                endY = deviceDimensions.getInitialHeight();
                break;
            case SWIPE_UP:
                startX = deviceDimensions.getMiddleWidth();
                startY = deviceDimensions.getInitialHeight();
                endX = deviceDimensions.getMiddleWidth();
                endY = deviceDimensions.getMiddleHeight();
                break;
            default:
                throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }
        dragNDrop.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                PointerInput.Origin.viewport(), startX, startY));
        dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragNDrop.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), endX, endY));
        dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(dragNDrop));
    }

    public void swipeDeviceDimensionsUp() {
        System.out.println("Inicio - Swipe");
        (new TouchAction((PerformsTouchActions) driver))
                .press(PointOption.point(deviceDimensions.getMiddleWidth(), deviceDimensions.getMiddleHeight()))
                .moveTo(PointOption.point(deviceDimensions.getMiddleWidth(), deviceDimensions.getInitialHeight()))
                .release()
                .perform();
        System.out.println("Fim - Swipe");
    }

}
