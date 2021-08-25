package intern.BGGStart.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static intern.BGGStart.enums.UserMenuOption.PROFILE;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;
import static intern.BGGStart.utils.DataResources.*;

//@Disabled
public class EditInfoAboutUserTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(ints = {17})
    public void editInfoAboutUser(int number) {
        logInUser();

        pages.mainHeader
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .openUserMenuDropDownList();
        pages.userMenuDropDown
                .selectMenuAction(PROFILE);

        pages.userProfilePage
                .checkEditProfileElementsAreVisible()
                .goToEditUserDetails();

        pages.editUserDetailsPage
                .checkEditUserDetailHeaderVisibility()
                .setUserDetails()
                .clickSubmit();

        pages.mainHeader
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .openUserMenuDropDownList();
        pages.userMenuDropDown
                .selectMenuAction(PROFILE);

        pages.userProfilePage
                .verifyUserFirstAndLastName(firstName, lastName)
                .verifyCountryAndCityName(countryName, cityName);

        pages.mainHeader
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .openUserMenuDropDownList();
        pages.userMenuDropDown
                .selectMenuAction(SIGN_OUT);

        pages.mainHeader
                .checkIfUserIsLoggedOut();
    }
}
