package br.com.liandro.utils;

import br.com.liandro.utils.enuns.Platform;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class DriverManager {

    protected static AndroidDriver androidDriver;
    protected static IOSDriver iosDriver;
    protected static WebDriverWait waitDriver;
    protected final static int SHORT_TIMEOUT = 5;
    protected final static int LONG_TIMEOUT = 30;
    private static Platform platform = null;
    protected static MutableCapabilities capabilities = new MutableCapabilities();
    protected static HashMap<String, Object> browserstackOptions = new HashMap<>();


    public static final String projectName = "Contacts";
    public static final String projectVersion = "1.0.0";
    public static final String sessionName = "Contacts Management";
    public static final String appiumVersion = "2.0.0";
    public static final String timeZone = "Sao_Paulo";


    /**
     * You must always set PLATFORM environment variable
     * envPlatform: the PLATFORM environment variable should be selected between:
     * ("Devicefarm_iOS", "Devicefarm_Android" "iOS", and "Android")
     * This variable should be set in the command line using -Dplatform
     */
    protected static String envPlatform;
    /**
     * You must set DEVICE NAME environment variable when you'll send this test to a Device Farm
     * Check the devices enabled to receive the tests
     * This variable should be set in the command line using -Ddevice
     */
    protected static String envDeviceName;
    /**
     * You must set OS VERSION environment variable when you'll send this test to a Device Farm
     * Check the os enabled to receive the tests
     * This variable should be set in the command line using -Dosversion
     */
    protected static String envOSVersion;
    /**
     * You must set USER NAME environment variable when you'll send this test to a Device Farm
     * This variable should be set in the command line using -Duser
     */
    public static String userName;
    /**
     * You must set ACCESS KEY environment variable when you'll send this test to a Device Farm
     * This variable should be set in the command line using -Dkey
     */
    public static String accessKey;


    public static AppiumDriver getDriver() {
        if (platform == Platform.ANDROID || platform == Platform.DEVICEFARM_ANDROID && androidDriver != null) {
            return androidDriver;
        }
        if (platform == Platform.IOS || platform == Platform.DEVICEFARM_IOS && iosDriver != null) {
            return iosDriver;
        }
        throw new RuntimeException("You must call DriverManager.startDriver() before using the driver");
    }

    public static WebDriverWait getWaitDriver() {
        if ((platform == Platform.ANDROID || platform == Platform.DEVICEFARM_ANDROID && androidDriver != null) ||
                (platform == Platform.IOS || platform == Platform.DEVICEFARM_IOS && iosDriver != null)) {
            return waitDriver;
        }
        throw new RuntimeException("You must call DriverManager.startDriver() before using the waitDriver");
    }

    public static void startDriver() {

        stopApp();

        envPlatform = System.getProperty("platform").toUpperCase();

        switch (envPlatform) {
            case "ANDROID":
                platform = Platform.ANDROID;
                break;
            case "IOS":
                platform = Platform.IOS;
                break;
            case "DEVICEFARM_ANDROID":
                platform = Platform.DEVICEFARM_ANDROID;
                envDeviceName = System.getProperty("device");
                envOSVersion = System.getProperty("osversion");
                userName = System.getProperty("user");
                accessKey = System.getProperty("key");
                break;
            case "DEVICEFARM_IOS":
                platform = Platform.DEVICEFARM_IOS;
                envDeviceName = System.getProperty("device");
                envOSVersion = System.getProperty("osversion");
                userName = System.getProperty("user");
                accessKey = System.getProperty("key");
                break;
            default:
                throw new RuntimeException("Platform not supported: " + envPlatform);
        }

        URL driverUrl;
        URL driverUrlDeviceFarm;
        try {
            driverUrl = new URL("http://127.0.0.1:4723/");
            driverUrlDeviceFarm = new URL("http://hub.browserstack.com/wd/hub");
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
            case DEVICEFARM_ANDROID:
                capabilities.setCapability("app", "bs://");
                browserstackOptions.put("userName", userName);
                browserstackOptions.put("accessKey", accessKey);
                browserstackOptions.put("osVersion", envOSVersion);
                browserstackOptions.put("deviceName", envDeviceName);
                browserstackOptions.put("projectName", projectName);
                browserstackOptions.put("buildName", projectVersion);
                browserstackOptions.put("sessionName", sessionName);
                browserstackOptions.put("appiumVersion", appiumVersion);
                browserstackOptions.put("local", "false");
                browserstackOptions.put("debug", "true");
                browserstackOptions.put("timezone", timeZone);
                capabilities.setCapability("bstack:options", browserstackOptions);
                androidDriver  = new AndroidDriver(driverUrlDeviceFarm, capabilities);
                androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT));
                waitDriver = new WebDriverWait(androidDriver, Duration.ofSeconds(LONG_TIMEOUT));
                break;
            case DEVICEFARM_IOS:
                capabilities.setCapability("app", "bs://");
                browserstackOptions.put("userName", userName);
                browserstackOptions.put("accessKey", accessKey);
                browserstackOptions.put("osVersion", envOSVersion);
                browserstackOptions.put("deviceName", envDeviceName);
                browserstackOptions.put("projectName", projectName);
                browserstackOptions.put("buildName", projectVersion);
                browserstackOptions.put("sessionName", sessionName);
                browserstackOptions.put("appiumVersion", appiumVersion);
                browserstackOptions.put("local", "false");
                browserstackOptions.put("debug", "true");
                browserstackOptions.put("timezone", timeZone);
                capabilities.setCapability("bstack:options", browserstackOptions);
                iosDriver = new IOSDriver(driverUrlDeviceFarm, capabilities);
                iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(SHORT_TIMEOUT));
                waitDriver = new WebDriverWait(iosDriver, Duration.ofSeconds(LONG_TIMEOUT));
                break;
        }
    }

    public static String getEnvPlatform() {
        return envPlatform = System.getProperty("platform").toUpperCase();
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
