package intern.BGGStart.pageObject.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import intern.BGGStart.enums.UserMenuOption;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UserMenuDropDown {

    private ElementsCollection userMenuDropDownElements = $$(By.xpath("//a[@class='dropdown-item']|//div[@uib-dropdown-menu and contains(@class,'dropdown-menu dropdown-menu-right')]//ul[@class='col-xs-6']//a"));
    private SelenideElement getUserMenuDropDown = $(By.xpath("//div[@x-placement='bottom-right']|//div[@uib-dropdown-menu and contains(@class,'dropdown-menu dropdown-menu-right')]"));

    public UserMenuDropDown openUserMenuDropDownList() {
        getUserMenuDropDown
                .shouldBe(visible)
                .should(matchesText(".*Profile.*Collection.*Account.*GeekExchanges.*"));
        return this;
    }

    public void selectMenuAction(UserMenuOption menuOption){
        userMenuDropDownElements
                .findBy(text(menuOption.getOption()))
                .click();
    }
}
