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

    public void checkIfEditProfileElementsAreVisible(){
        editProfileElements
                .shouldBe(visible)
                .shouldHave(matchesText(".*Games.*Contributions.*Gallery.*"));
    }

    public void checkIfProfileWasUpdated (){
        profileUpdatedStatement
                .shouldBe(visible)
                .shouldHave(text("Updated Your Profile"));
    }

    public void checkUserFirstAndLastName(String firstName, String lastName){
        $(byXpath("//div[contains(text(),'" + firstName + " " + lastName + "')]")).shouldBe(visible);
    }

    public void checkIfCountryAndCityNameWasUpdated(String countryName, String cityName){
        countryField
                .shouldBe(visible)
                .should(matchText(countryName));
        cityField
                .shouldBe(visible)
                .should(matchText(cityName));
    }

    public void goToEditUserDetails(){
        editProfileDetailsHeader
                .shouldBe(visible)
                .click();
    }
}
