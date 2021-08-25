package intern.BGGStart.pageObject.fragments;

import com.codeborne.selenide.SelenideElement;
import intern.BGGStart.pageObject.UserHomePage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainHeader {

    private SelenideElement globalHeader = $(byXpath("(//header[contains(@class,'header')])[1]"));
    private SelenideElement signInLink = $(byXpath("//a[@ggloginbutton]"));

    private SelenideElement userNameButton = $("span.mygeek-dropdown-username");
    private SelenideElement browseButton = $(byXpath("//button[contains(text(),'Browse')]"));


    public MainHeader openSignInModal() {
        signInLink
                .shouldBe(visible)
                .click();
        return this;
    }

    public MainHeader checkIfUserIsLoggedIn() {
        globalHeader
                .shouldBe(visible)
                .$("gg-subscription-notifications fa-icon")
                .shouldBe(visible);
        globalHeader
                .shouldBe(visible)
                .$("gg-geekmail-notifications fa-icon")
                .shouldBe(visible);
        return this;
    }

    public MainHeader checkIfUserIsLoggedOut() {
        signInLink
                .shouldBe(visible)
                .should(matchText(".*Sign.*In.*"));
        return this;
    }

    public MainHeader openUserDropDownMenu(){
        userNameButton
                .shouldBe(visible)
                .click();
        return this;
    }

    public MainHeader openBrowseDropDownMenu() {
        browseButton
                .shouldBe(visible)
                .click();
        return this;
    }

}
