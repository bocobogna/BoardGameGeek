package intern.BGGStart.pageObject.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import intern.BGGStart.enums.UserMenuOption;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserMenuDropDown {

    private ElementsCollection userMenuDropDownElements = $$("div.dropdown-menu-right ul.col-xs-6 li");
    private SelenideElement userMenuDropDown = $("div.dropdown-menu-right");

    public ElementsCollection userMenuDropDownList() {
        return userMenuDropDown
                .shouldBe(visible)
                .should(matchesText(".*Profile.*Collection.*Account.*GeekExchanges.*"))
                .$$("ul.col-xs-6 li");
    }

    public void menuAction(UserMenuOption menuOption){
        userMenuDropDownElements
                .findBy(text(menuOption.getOption()))
                .click();
    }
}
