package bukalapak;

import bukalapak.pageobject.android.HomePage;
import bukalapak.pageobject.android.LoginPage;
import bukalapak.pageobject.desktop.HomeDesktopPage;
import bukalapak.pageobject.pwa.AgentPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class Bukalapak {

    private AndroidDriver<AndroidElement> androidDriver;
    private IOSDriver<IOSElement> iosDriver;
    private WebDriver webDriver;

    public Bukalapak(AndroidDriver<AndroidElement> androidDriver) {
        this.androidDriver = androidDriver;
    }

    public Bukalapak(IOSDriver<IOSElement> iosDriver) {
        this.iosDriver = iosDriver;
    }

    public Bukalapak(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Android Page

    public HomePage homePage() {
        return new HomePage(androidDriver);
    }

    public LoginPage loginPage() {
        return new LoginPage(androidDriver);
    }

    // Agent Page

    public AgentPage agentPage() {
        return new AgentPage(webDriver);
    }


    // Desktop Page

    public HomeDesktopPage homeDesktopPage() {
        return new HomeDesktopPage(webDriver);
    }
}

