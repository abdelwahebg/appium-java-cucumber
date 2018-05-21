package bukalapak.pageobject.android;

import bukalapak.pageobject.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBys;

import java.util.List;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    @AndroidFindBys({
            @AndroidBy(uiAutomator = "new UiSelector().resourceId(\"com.bukalapak.android:id/et\")"),
            @AndroidBy(className = "android.widget.EditText")
    })
    private List<AndroidElement> loginForm;

    private void checkOnSmartLogin() {
        if (isElementPresentById("com.google.android.gms:id/credential_picker_layout", 2))
            pressBackButton();
    }

    private void checkOnSmartLock() {
        if (isElementPresentById("com.google.android.gms:id/credential_save_confirmation", 2))
            pressBackButton();
    }

    private void setEmail(String email) {
        loginForm.get(0).setValue(email);
    }

    private void setPassword(String password) {
        loginForm.get(1).setValue(password);
    }

    public void login(String[] credentials) {
        if (isElementPresentById("bottombartab_profile", 2))
            tapViewWithId("bottombartab_profile");

        tapViewContainsText("Login");
        checkOnSmartLogin();
        setEmail(credentials[0]);
        setPassword(credentials[1]);
        hideKeyboard();
        tapViewWithId("button_login_normal");
        checkOnSmartLock();
    }

    public void tapOnLoginFacebookButton() {
        tapViewWithId("button_login_facebook_wrapper");
    }

    public void tapOnLoginGoogleButton() {
        tapViewWithId("button_login_google");
    }
}