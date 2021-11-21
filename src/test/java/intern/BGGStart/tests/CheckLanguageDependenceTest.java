package intern.BGGStart.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static intern.BGGStart.enums.UserMenuOption.COLLECTION;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;

public class CheckLanguageDependenceTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(ints = {17})
    public void CheckLanguageDependence(int number) {
        logInUser();

        pages.userHomePage
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .openUserMenuDropDownList();
        pages.userMenuDropDown
                .selectMenuAction(COLLECTION);

        pages.collectionPage
                .checkBoardGameCollectionName();

        String gameName = pages.collectionPage
                .getGameNameShuffleFromCollection();

        pages.collectionPage
                .goToGamePageWithUsageOfCollectionsShuffle(gameName);

        pages.gamePage
                .checkIfGameNameIsEqualToGameNameFromUserCollection(gameName);

        String gameID = pages.gamePage
                .getGameID();

        pages.api
                .getLanguageDependence(gameID);

        pages.gamePage
                .checkIfMostVotedLanguageDependenceValueIsDisplayed();

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
