package intern.BGGStart.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class EditUserDetailsPage {

    private SelenideElement editUserDetailHeader = $("div.journalheader");
    private SelenideElement firstNameInput = $("#firstname");
    private SelenideElement lastNameInput = $("#lastname");
    private SelenideElement cityInput = $("#city");
    private SelenideElement countrySelect = $("#country");
    private SelenideElement submitButton = $(byXpath("//input[@type='submit']"));

    private static String firstNameValue, lastNameValue, cityValue, countryValue;

    public EditUserDetailsPage checkEditUserDetailHeaderVisibility() {
        editUserDetailHeader.shouldBe(visible).shouldHave(text("Contact Information"));
        return this;
    }

    public EditUserDetailsPage setUserFirstName(String firstName) {
        firstNameInput
                .shouldBe(visible)
                .val(firstName)
                .shouldHave(value(firstName));
        return this;
    }

    public EditUserDetailsPage setLastName(String lastName) {
        lastNameInput
                .shouldBe(visible)
                .val(lastName)
                .shouldHave(value(lastName));
        return this;
    }

    public EditUserDetailsPage setCity(String cityName) {
        cityInput
                .shouldBe(visible)
                .val(cityName)
                .shouldHave(value(cityName));
        return this;
    }

    public EditUserDetailsPage setCountry(String countryName) {
        countrySelect
                .shouldBe(visible)
                .selectOptionByValue(countryName);
        countrySelect
                .shouldHave(value(countryName));
        return this;
    }

    public String getFirstNameValue() {
        return firstNameInput.getValue();
    }

    public String getLastNameValue() {
        return lastNameInput.getValue();
    }

    public String getCityValue() {
        return cityInput.getValue();
    }

    public String getCountryValue() {
        return countrySelect.getValue();
    }

    public EditUserDetailsPage clickSubmit() {
        submitButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
