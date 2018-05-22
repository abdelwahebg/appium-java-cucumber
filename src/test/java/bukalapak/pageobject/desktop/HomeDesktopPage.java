package bukalapak.pageobject.desktop;

import bukalapak.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class HomeDesktopPage extends BasePage {

    public HomeDesktopPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void goToPengaturan() {
        Assert.assertTrue(
                getWebElementWithXpathWithTimeOut(
                        "//*[@id=\"reskinned_page\"]/header/div[1]/div/div/div[2]/div/div[2]/div[2]/ul/li[4]/a/div/div", 10
                ).isDisplayed());
        tapWebViewWithXpath("//*[@id=\"js-staff-onb--avatar\"]/a/div/img");
        tapWebViewWithText("Logout");
    }

    public void tapOnLoginButton() {
        // tap web view using id
        tapWebViewWithId("login_link");
    }

    public void inputUsernameAndPassword(String username, String password) {
        // type on text field using Id
        typeTextOnWebViewWithId("user_session_username", username);
        typeTextOnWebViewWithId("user_session_password", password);
    }

    public void tapOnRealLoginButton() {
        tapWebViewWithXpath("//*[@id=\"new_user_session\"]/div[1]/div[5]/button[2]");
    }

    public void login(String username, String password) {
        // type on text field using Id
        typeTextOnWebViewWithId("user_session_username", username);
        typeTextOnWebViewWithId("user_session_password", password);
        // type on text field using Xpath
        typeTextOnWebViewWithXpath("//*[@id=\"user_session_username\"]", username);
        typeTextOnWebViewWithXpath("//*[@id=\"user_session_password\"]", password);
        tapWebViewWithXpath("//*[@id=\"new_user_session\"]/div[1]/div[5]/button[2]");
    }
}
