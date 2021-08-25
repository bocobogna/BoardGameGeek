package intern.BGGStart.pageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byXpath;
import static intern.BGGStart.utils.DataResources.*;

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

    public EditUserDetailsPage setUserDetails() {
        firstNameInput
                .shouldBe(visible)
                .val(firstName)
                .shouldHave(value(firstName));
        lastNameInput
                .shouldBe(visible)
                .val(lastName)
                .shouldHave(value(lastName));
        cityInput
                .shouldBe(visible)
                .val(cityName)
                .shouldHave(value(cityName));
        countrySelect
                .shouldBe(visible)
                .selectOptionByValue(countryName);
        countrySelect
                .shouldHave(value(countryName));

        firstNameValue = firstNameInput.getValue();
        lastNameValue = lastNameInput.getValue();
        cityValue = cityInput.getValue();
        countryValue = countrySelect.getValue();
        return this;
    }

    public EditUserDetailsPage clickSubmit() {
        submitButton
                .shouldBe(visible)
                .click();
        return this;
    }
}
