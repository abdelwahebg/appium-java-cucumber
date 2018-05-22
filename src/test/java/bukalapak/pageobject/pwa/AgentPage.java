package bukalapak.pageobject.pwa;

import bukalapak.pageobject.BasePage;
import org.openqa.selenium.WebDriver;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class AgentPage extends BasePage {

    public AgentPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void goToLoginPage() {
        tapWebViewWithText("Login");
    }

    public void login(String username, String password) {
        typeTextOnWebViewWithId("user_session_username", username);
        typeTextOnWebViewWithXpath("//*[@id=\"user_session_password\"]", password);
        tapWebViewWithText("Masuk");
        waitUntilWebElementDisplayedByXpath("//*[@id=\"agenlite-app\"]/div/div[2]/div/div[2]/strong", 20);
    }

}
