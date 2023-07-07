package br.com.liandro.utils;

import br.com.liandro.utils.enuns.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    protected static AndroidDriver androidDriver;
    protected static IOSDriver iosDriver;
    protected static WebDriverWait waitDriver;
    protected final static int SHORT_TIMEOUT = 5;
    protected final static int LONG_TIMEOUT = 30;
    private static Platform platform = null;


    /**
     * You must always set PLATFORM environment variable
     * envPlatform: the PLATFORM environment variable should be selected between ("iOS" and "Android")
     * This variable should be set in the command line using -Dplatform
     */
    protected static String envPlatform;


    public static AppiumDriver getDriver() {
        if (platform == Platform.ANDROID && androidDriver != null) {
            return androidDriver;
        }
        if (platform == Platform.IOS && iosDriver != null) {
            return iosDriver;
        }
        throw new RuntimeException("You must call DriverManager.startDriver() before using the driver");
    }

    public static WebDriverWait getWaitDriver() {
        if ((platform == Platform.ANDROID && androidDriver != null) || (platform == Platform.IOS && iosDriver != null)) {
            return waitDriver;
        }
        throw new RuntimeException("You must call DriverManager.startDriver() before using the waitDriver");
    }

    public static void startDriver() {

        stopApp();

        envPlatform = System.getProperty("platform").toUpperCase();

        if (envPlatform.equals("ANDROID")) {
            platform = Platform.ANDROID;
        } else if (envPlatform.equals("IOS")) {
            platform = Platform.IOS;
        } else {
            throw new RuntimeException("Platform not supported: " + envPlatform);
        }

        URL driverUrl;
        try {
            driverUrl = new URL("http://127.0.0.1:4723/");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }

        switch (platform) {
            case ANDROID:
                UiAutomator2Options optionsAndroid = new UiAutomator2Options()
                        .setUdid("emulator-5554")
                        .setPlatformName("Android")
                        .setDeviceName("Emulator Pixel 4")
                        .setAppPackage("com.google.android.contacts")
                        .setAppActivity("com.android.contacts.activities.PeopleActivity");
                androidDriver = new AndroidDriver(driverUrl, optionsAndroid);
                androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT));
                waitDriver = new WebDriverWait(androidDriver, Duration.ofSeconds(LONG_TIMEOUT));
                break;
            case IOS:
                XCUITestOptions optionsIos = new XCUITestOptions()
                        .setUdid("ECD914E6-714A-46DC-9CF7-326B995C1D1A")
                        .setPlatformName("iOS")
                        .setDeviceName("Simulator iPhone 14 Pro")
                        .setBundleId("com.apple.MobileAddressBook");
                iosDriver = new IOSDriver(driverUrl, optionsIos);
                iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT));
                waitDriver = new WebDriverWait(iosDriver, Duration.ofSeconds(LONG_TIMEOUT));
                break;
        }
    }

    public static void stopApp() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
        if (iosDriver != null) {
            iosDriver.quit();
            iosDriver = null;
        }
        waitDriver = null;
    }

}
