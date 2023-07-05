package br.com.liandro.utils;

import br.com.liandro.utils.enuns.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {

    protected static AppiumDriver driver;
    protected static WebDriverWait waitDriver;
    protected final static int SHORT_TIMEOUT = 5;
    protected final static int LONG_TIMEOUT = 30;
    private static Platform platform = null;


    /**
     * You must always set PLATFORM environment variable
     * envPlatform: the PLATFORM environment variable should be selected between ("iOS" and "Android")
     */
    protected static String envPlatform = "iOS";


    public static Platform getPlatform() {
        if (platform != null) {
            return platform;
        }
        throw new RuntimeException("You must call DriverManager.startDriver() before using the platform");
    }

    public static AppiumDriver getDriver() {
        if ((platform == Platform.ANDROID && driver != null) || (platform == Platform.IOS && driver != null)) {
            return driver;
        }
        throw new RuntimeException("You must call DriverManager.startDriver() before using the driver");
    }

    public static WebDriverWait getWaitDriver() {
        if ((platform == Platform.ANDROID && driver != null) || (platform == Platform.IOS && driver != null)) {
            return waitDriver;
        }
        throw new RuntimeException("You must call DriverManager.startDriver() before using the waitDriver");
    }

    public static void startDriver() {

        if (envPlatform == null) {
            throw new RuntimeException("The PLATFORM environment variable is not set");
        } else if (envPlatform.equals("Android")) {
            platform = Platform.ANDROID;
        } else if (envPlatform.equals("iOS")) {
            platform = Platform.IOS;
        } else {
            throw new RuntimeException("Platform not supported: " + envPlatform);
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();

        URL driverUrl;
        try {
            driverUrl = new URL("http://127.0.0.1:4723/");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }

        switch (platform) {
            case ANDROID:
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator Pixel 4");
                capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.contacts");
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.contacts.activities.PeopleActivity");
                break;
            case IOS:
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Simulator iPhone 14 Pro");
                capabilities.setCapability(MobileCapabilityType.UDID, "ECD914E6-714A-46DC-9CF7-326B995C1D1A");
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.MobileAddressBook");
                capabilities.setCapability(IOSMobileCapabilityType.APP_NAME, "Contacts");
                break;
        }
        driver = new AppiumDriver(driverUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT));
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT));
    }

    public static void stopApp() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        waitDriver = null;
    }

}
