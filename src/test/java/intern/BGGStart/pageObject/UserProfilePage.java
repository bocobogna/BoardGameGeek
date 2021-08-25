package intern.BGGStart.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byXpath;

public class UserProfilePage {

    private SelenideElement editProfileElements = $("td.menu_table");
    private SelenideElement editProfileDetailsHeader = $(byXpath("//div[contains(text(),'User Details for')]//a"));
    private SelenideElement profileUpdatedStatement = $("#maincontent h4");
    private SelenideElement countryField = $(byXpath("//b[contains(text(),'Country:')]/following::td[1]"));
    private SelenideElement cityField = $(byXpath("//b[contains(text(),'Town/City:')]/following::td[1]"));

    public UserProfilePage checkEditProfileElementsAreVisible() {
        editProfileElements
                .shouldBe(visible)
                .shouldHave(matchText(".*Games.*Contributions.*Gallery.*"));
        return this;
    }

    public UserProfilePage checkProfileWasUpdated() {
        profileUpdatedStatement
                .shouldBe(visible)
                .shouldHave(text("Updated Your Profile"));
        return this;
    }

    public UserProfilePage verifyUserFirstAndLastName(String firstName, String lastName) {
        $(byXpath("//div[@data-userid]/div[contains(text(),'" + firstName + " " + lastName + "')]")).shouldBe(visible);
        return this;
    }

    public UserProfilePage verifyCountryAndCityName(String countryName, String cityName) {
        countryField
                .shouldBe(visible)
                .should(matchText(countryName));
        cityField
                .shouldBe(visible)
                .should(matchText(cityName));
        return this;
    }

    public UserProfilePage goToEditUserDetails() {
        editProfileDetailsHeader
                .shouldBe(visible)
                .click();
        return this;
    }
}
