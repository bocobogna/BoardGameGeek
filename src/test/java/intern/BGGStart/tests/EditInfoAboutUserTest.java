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
        userHomePage.openUserDropDownMenu();

        userMenuDropDown.userMenuDropDownList().shouldHave(size(number));
        userMenuDropDown.menuAction(PROFILE);

        userProfilePage.checkIfEditProfileElementsAreVisible();
        userProfilePage.goToEditUserDetails();

        editUserDetailsPage.checkEditUserDetailHeaderVisibility();
        editUserDetailsPage.editUserDetails();

        userProfilePage.checkIfProfileWasUpdated();
        userProfilePage.checkUserFirstAndLastName(firstName, lastName);
        userProfilePage.checkIfCountryAndCityNameWasUpdated(countryName, cityName);

        userHomePage.openUserDropDownMenu();

        userMenuDropDown.userMenuDropDownList().shouldHave(size(number));
        userMenuDropDown.menuAction(SIGN_OUT);

        mainHeader.checkIfUserIsLoggedOut();
    }
}
