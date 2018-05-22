package bukalapak.pageobject.android;

import bukalapak.pageobject.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class HomePage extends BasePage {

    public HomePage(AndroidDriver<AndroidElement> androidDriver) {
        super(androidDriver);
    }

    @AndroidFindBy(id = "bottomBar")
    private AndroidElement bottomBar;

    public boolean isOnHomePage() {
        return bottomBar.isDisplayed();
    }

    public void tapOnShowCaseButton() {
        tapViewWithId("button_showcase");
    }

    public void tapOnHomeBottomBar() {
        tapViewWithId("bottombartab_home");
    }

    public void tapOnSearch() {
        tapViewWithId("text_search");
    }

    public void searchItem(String text) {
        typeTextOnViewWithId("text_search", text);
        pressEnterButton();
    }

    public void tapOnFavoriteBottomBar() {
        tapViewWithId("bottombartab_favorite");
    }

    public void tapOnTransactionBottomBar() {
        tapViewWithId("bottombartab_transaction");
    }

    public void tapOnChatBottomBar() {
        tapViewWithId("bottombartab_chat");
    }

    public void tapOnProfileBottomBar() {
        tapViewWithId("bottombartab_profile");
    }

    public void tapOnBukalapakCredits() {
        tapViewContainsText("BUKALAPAK CREDITS");
    }

    public void tapOnPulsa() {
        tapViewContainsText("PULSA");
    }

    public void tapOnPaketData() {
        tapViewContainsText("PAKET DATA");
    }

    public void tapOnListrik() {
        tapViewContainsText("LISTRIK");
    }

    public void tapOnKereta() {
        tapViewContainsText("KERETA");
    }

    public void tapOnPesawat() {
        tapViewContainsText("PESAWAT");
    }

    public void tapOnBukaEmas() {
        tapViewContainsText("BUKAEMAS");
    }

    public void tapOnZakatProfesi() {
        tapViewContainsText("ZAKAT PROFESI");
    }

    public void tapOnBPJS() {
        tapViewContainsText("BPJS");
    }

    public void tapOnAirPDAM() {
        tapViewContainsText("AIR PDAM");
    }

    public int getSaldoBukaDompet() {
        return getNumberFromRp(getTextFromViewWithId("tv_saldo_bukadompet"));
    }
}
