package intern.BGGStart.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static intern.BGGStart.enums.UserMenuOption.PROFILE;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;
import static org.assertj.core.api.Assertions.assertThat;

//@Disabled
public class EditInfoAboutUserTest extends BaseTest {

    public final String firstName = "Master";
    public final String lastName = "Roshi";
    public final String cityName = "Lublin";
    public final String countryName = "Poland";

    List<String> userDetails = Arrays.asList(firstName, lastName, cityName, countryName);

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
                .setUserFirstName(firstName)
                .setLastName(lastName)
                .setCity(cityName)
                .setCountry(countryName);

        List<String> infoFromPage = Arrays.asList(pages.editUserDetailsPage.getFirstNameValue(), pages.editUserDetailsPage.getLastNameValue(), pages.editUserDetailsPage.getCityValue(), pages.editUserDetailsPage.getCountryValue());

        assertThat(userDetails).hasSameElementsAs(infoFromPage);

        pages.editUserDetailsPage
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
