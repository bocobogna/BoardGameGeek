package intern.BGGStart.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static intern.BGGStart.enums.UserMenuOption.PROFILE;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;
import static intern.BGGStart.utils.DataResources.*;

@Disabled
public class EditInfoAboutUserTest extends BaseTest{

    @ParameterizedTest
    @ValueSource(ints = {17})
    public void editInfoAboutUser(int number) {
        logInUser();
        pages.userHomePage
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .userMenuDropDownList()
                .shouldHave(size(number));
        pages.userMenuDropDown.menuAction(PROFILE);

        pages.userProfilePage.checkIfEditProfileElementsAreVisible()
                .goToEditUserDetails();

        pages.editUserDetailsPage
                .checkEditUserDetailHeaderVisibility()
                .editUserDetails();

        pages.userProfilePage.checkIfProfileWasUpdated()
                .checkUserFirstAndLastName(firstName, lastName)
                .checkIfCountryAndCityNameWasUpdated(countryName, cityName);

        pages.userHomePage
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .userMenuDropDownList()
                .shouldHave(size(number));
        pages.userMenuDropDown
                .menuAction(SIGN_OUT);

        pages.mainHeader
                .checkIfUserIsLoggedOut();
    }
}
