package bukalapak;

import bukalapak.utility.EmulateDevice;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class TestInstrument {

    private static AppiumDriverLocalService service;
    protected static AndroidDriver driver;
    protected static WebDriver webDriver;
    protected static Bukalapak bukalapak;
    protected static String moduleType;

    /**
     * Initialization
     */
    @Parameters({"deviceName", "platformVersion", "port", "module", "browser", "emulation"})
    @BeforeClass
    public void beforeClass(String deviceName, String platformVersion, int port, String module, String browser, String emulation) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();
        service = AppiumDriverLocalService.buildService(builder);
        service.stop();

        if (service == null || !service.isRunning()) {
            System.out.println("\"Starting appium server!\"");
            service.start();
        }

        File appDir = new File("/Users/bukalapak/Downloads/apk/");
        File app = new File(appDir, "app-release.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (module.equals("APP")) {
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            driver = new AndroidDriver<>(service.getUrl(), capabilities);
            bukalapak = new Bukalapak(driver);
        } else {
            switch (browser) {
                case "CHROME":
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.merge(capabilities);
                    webDriver = new ChromeDriver(chromeOptions);
                    break;
                case "FIREFOX":
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                    firefoxOptions.merge(capabilities);
                    webDriver = new FirefoxDriver(firefoxOptions);
                    break;
                case "OPERA":
                    // TODO: Not stable yet for desktop version, screen blocked by notification prompt
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.OPERA_BLINK);
                    OperaOptions operaOptions = new OperaOptions();
                    webDriver = new OperaDriver(operaOptions);
                    break;
                case "SAFARI":
                    capabilities.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
                    SafariOptions safariOptions = new SafariOptions();
                    safariOptions.merge(capabilities);
                    webDriver = new SafariDriver(safariOptions);
                    break;
                default:
                    System.out.println("Sorry, can't found browser settings!");
                    break;
            }

            if (module.equals("DESKTOP"))
                webDriver.manage().window().maximize();
            else
                webDriver.manage().window().setSize(EmulateDevice.emulateDevice(emulation));

            bukalapak = new Bukalapak(webDriver);
        }
    }

    /**
     * Finishing
     */
    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.closeApp();
        }

        if (webDriver != null) {
            webDriver.quit();
        }

        if (service != null) {
            service.stop();
        }
    }

    @Parameters({"deviceName", "port", "module"})
    @BeforeSuite
    public void beforeSuite(String deviceName, String port, String module) {
        moduleType = module;
        if (module.equals("APP")) {
            String sdkPath = "/Users/bukalapak/library/android/sdk/";
            String emulatorPath = sdkPath + "tools" + File.separator + "emulator";
            String[] command = new String[]{emulatorPath, "-avd", deviceName, "-no-snapshot-save", "-port", port};
            try {
                Process process = new ProcessBuilder(command).start();
                process.waitFor(10, TimeUnit.SECONDS);
                System.out.println("Emulator launched successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Parameters({"port", "module"})
    @AfterSuite
    public void afterSuite(String port, String module) {
        if (module.equals("APP")) {
            String sdkPath = "/Users/bukalapak/library/android/sdk/";
            String adbPath = sdkPath + "platform-tools" + File.separator + "adb";
            String[] command = new String[]{adbPath, "-s", "emulator-" + port, "emu", "kill"};
            try {
                Process process = new ProcessBuilder(command).start();
                process.waitFor(5, TimeUnit.SECONDS);
                System.out.println("Emulator closed successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
