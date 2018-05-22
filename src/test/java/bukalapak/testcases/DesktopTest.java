package bukalapak.testcases;

import bukalapak.TestInstrument;
import org.testng.annotations.Test;

public class DesktopTest extends TestInstrument {

    @Test
    public void loginTest() {
        bukalapak.homeDesktopPage().goToUrl("https://www.bukalapak.com");
        bukalapak.homeDesktopPage().tapOnLoginButton();
        bukalapak.homeDesktopPage().login("zuraqia", "untuktesting");
    }
}
