package bukalapak.cucumber.step_definitions;

import bukalapak.cucumber.CucumberTestRunner;
import bukalapak.cucumber.domain.User;
import bukalapak.utility.Credentials;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

/**
 * Created by Aldo Christian on 21/05/18.
 */

public class BaseSteps extends CucumberTestRunner {

    private User user;

    @Given("Valid user credential")
    public void valid_user_credential() {
        user = new User();
        user.setUsername("arkadiusreymond2");
        user.setPassword("qwerty");
    }

    @Given("Valid users credential")
    public void valid_users_credential(List<User> list) {
        for (User user : list) {
            String username = user.getUsername();
            String password = user.getPassword();
            bukalapak.homeDesktopPage().inputUsernameAndPassword(username, password);
        }
    }


    @When("User do login")
    public void user_do_login() {
        switch (moduleType) {
            case "DESKTOP":
                bukalapak.homeDesktopPage().goToUrl("https://www.bukalapak.com");
                bukalapak.homeDesktopPage().tapOnLoginButton();
                bukalapak.homeDesktopPage().login(user.getUsername(), user.getPassword());
                break;
            case "PWA":
                bukalapak.agentPage().goToUrl("https://agen.bukalapak.com");
                bukalapak.agentPage().goToLoginPage();
                bukalapak.agentPage().login(user.getUsername(), user.getPassword());
                break;
            case "APP":
                bukalapak.homePage().checkShowCaseButton();
                bukalapak.homePage().tapOnProfileBottomBar();
                bukalapak.loginPage().login(Credentials.VALID_PRODUCTION_CREDENTIALS);
                break;
            default:
                System.out.println("Can't find module type");
                break;
        }
    }

    @Then("User see homepage")
    public void user_see_homepage() {
        bukalapak.homeDesktopPage().goToPengaturan();
    }


    @When("Try login with several accounts")
    public void try_login_with_several_accounts(DataTable table) {
        bukalapak.homeDesktopPage().goToUrl("https://www.bukalapak.com");
        bukalapak.homeDesktopPage().tapOnLoginButton();
        for (Map<String, String> map : table.asMaps(String.class, String.class)) {
            String username = map.get("username");
            String password = map.get("password");
            bukalapak.homeDesktopPage().inputUsernameAndPassword(username, password);
        }
        bukalapak.homeDesktopPage().tapOnRealLoginButton();
    }
}
