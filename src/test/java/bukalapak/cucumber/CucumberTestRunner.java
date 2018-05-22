package bukalapak.cucumber;

import bukalapak.TestInstrument;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.Test;

/**
 * Created by Aldo Christian on 21/05/18.
 */

@CucumberOptions(
        strict = true,
        monochrome = true,
        tags = "@PWA",
        features = "src/test/resources/features",
        plugin = {
                "pretty",
                "json:target/cucumber-json-report.json"
        }
)

public class CucumberTestRunner extends TestInstrument {

    @Test(groups = "TestNG", description = "TestNG cucumber")
    public void runCukes() {
        new TestNGCucumberRunner(getClass()).runCukes();
    }

}
