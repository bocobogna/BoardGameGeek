package intern.BGGStart.pageObject;

import com.codeborne.selenide.SelenideElement;
import intern.BGGStart.utils.DataResources;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byXpath;

public class EditUserDetailsPage {

    private SelenideElement editUserDetailHeader = $("div.journalheader");
    private SelenideElement firstNameInput = $(byXpath("//input[@name='firstname']"));
    private SelenideElement lastNameInput = $(byXpath("//input[@name='lastname']"));
    private SelenideElement cityInput = $(byXpath("//input[@name='city']"));
    private SelenideElement countrySelect = $("#country");
    private SelenideElement submitButton = $(byXpath("//input[contains(@value,'Submit')]"));

    private static String firstNameValue, lastNameValue, cityValue, countryValue;

    public void checkEditUserDetailHeaderVisibility(){
        editUserDetailHeader.shouldBe(visible).shouldHave(text("Contact Information"));
    }

    public void editUserDetails(){
        firstNameInput
                .shouldBe(visible)
                .val(DataResources.firstName)
                .shouldHave(value(DataResources.firstName));
        lastNameInput
                .shouldBe(visible)
                .val(DataResources.lastName)
                .shouldHave(value(DataResources.lastName));
        cityInput
                .shouldBe(visible)
                .val(DataResources.cityName)
                .shouldHave(value(DataResources.cityName));
        countrySelect
                .shouldBe(visible)
                .selectOptionByValue(DataResources.countryName);
        countrySelect
                .shouldHave(value(DataResources.countryName));

        firstNameValue = firstNameInput.getValue();
        lastNameValue = lastNameInput.getValue();
        cityValue = cityInput.getValue();
        countryValue = countrySelect.getValue();

        submitButton
                .shouldBe(visible)
                .click();
    }
}
