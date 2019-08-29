package intern.BGGStart.Fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import intern.BGGStart.enums.BrowseMenuOption;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class BrowseMenuDropDown {

    private SelenideElement browseMenuDropDown = $(
            byXpath("//button[normalize-space(text())='Browse']/following-sibling::div")
    );

    public void menuOption(BrowseMenuOption menuOption) {
        browseMenuDropDown
                .$$("li")
                .findBy(text(menuOption.getOption()))
                .click();
    }

    public ElementsCollection returnsBrowseMenuSize(){
        return browseMenuDropDown
                .shouldBe(visible)
                .$$("li");
    }
}
