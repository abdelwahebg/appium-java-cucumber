package bukalapak.testcases;

import bukalapak.TestInstrument;
import org.testng.annotations.Test;

public class PwaTest extends TestInstrument {

    @Test
    public void loginTest() {
        bukalapak.agentPage().goToUrl("https://agen.bukalapak.com");
        bukalapak.agentPage().goToLoginPage();
        bukalapak.agentPage().login("zuraqia", "untuktesting");
    }
}
