package intern.BGGStart.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class UserHomePage {

    private SelenideElement userNameButton = $("gg-my-geek button.dropdown-toggle.btn");
    private SelenideElement browseButton = $(byXpath("//button[contains(text(),'Browse')]"));

    public UserHomePage openUserDropDownMenu() {
        userNameButton
                .shouldBe(visible)
                .click();
        return this;
    }

    public UserHomePage openBrowseDropDownMenu() {
        browseButton
                .shouldBe(visible)
                .click();
        return this;
    }


}
