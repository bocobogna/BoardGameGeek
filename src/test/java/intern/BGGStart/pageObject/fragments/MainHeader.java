package intern.BGGStart.pageObject.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainHeader {

    private SelenideElement globalHeader = $("div.global-header-nav-primary-wrapper");
    private SelenideElement signInLink = $(byXpath("//button[@login-required]"));

    public MainHeader checkIfUserIsLoggedIn(){
        globalHeader
                .shouldBe(visible)
                .$("i.fi-megaphone")
                .shouldBe(visible);
        globalHeader
                .shouldBe(visible)
                .$("i.fi-mail")
                .shouldBe(visible);
        return  this;
    }

    public MainHeader checkIfUserIsLoggedOut(){
        signInLink
                .shouldBe(visible)
                .should(matchText(".*Sign.*In.*"));
        return this;
    }

}
