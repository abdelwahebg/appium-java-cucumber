package bukalapak.testcases;

import bukalapak.TestInstrument;
import bukalapak.utility.Credentials;
import org.testng.annotations.Test;

public class AndroidTest extends TestInstrument {

    @Test
    public void loginTest() {
        bukalapak.homePage().checkShowCaseButton();
        bukalapak.homePage().tapOnProfileBottomBar();
        bukalapak.loginPage().login(Credentials.VALID_PRODUCTION_CREDENTIALS);
    }

}
