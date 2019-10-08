package intern.BGGStart.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static intern.BGGStart.enums.UserMenuOption.COLLECTION;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;

//@Disabled
public class CheckLanguageDependenceTest extends BaseTest{

    @ParameterizedTest
    @ValueSource(ints = {17})
    public void CheckLanguageDependence(int number){
        logInUser();

        pages.userHomePage
            .openUserDropDownMenu();

        pages.userMenuDropDown
                .userMenuDropDownList()
                .shouldHave(size(number));
        pages.userMenuDropDown
                .menuAction(COLLECTION);

        pages.collectionPage
                .checkBoardGameCollectionName();
        pages.collectionPage
                .goToGamePageWithUsageOfCollectionsShuffle();

        pages.gamePage
                .checkIfGameNameIsEqualToGameNameFromUserCollection()
                .getGameID();

        pages.api
                .getLanguageDependence();

        pages.gamePage
                .checkIfMostVotedLanguageDependenceValueIsDisplayed();

        pages.userHomePage
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .userMenuDropDownList()
                .shouldHave(size(number));
        pages.userMenuDropDown.menuAction(SIGN_OUT);

        pages.mainHeader.checkIfUserIsLoggedOut();
    }
}
