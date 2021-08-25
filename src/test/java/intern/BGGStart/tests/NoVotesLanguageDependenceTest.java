package intern.BGGStart.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static intern.BGGStart.enums.UserMenuOption.COLLECTION;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;

@Disabled
public class NoVotesLanguageDependenceTest extends BaseTest{

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
        pages.collectionPage
                .goToGameWithNoVotes();

        pages.gamePage
                .checkIfGameNameIsEqualToGameNameFromUserCollection()
                .getGameID();

        pages.api
                .getLanguageDependence();

        pages.gamePage
                .checkIfMostVotedLanguageDependenceValueIsDisplayed();

        pages.userHomePage.openUserDropDownMenu();
        pages.userMenuDropDown
                .openUserMenuDropDownList();
        pages.userMenuDropDown
                .selectMenuAction(SIGN_OUT);

        pages.mainHeader
                .checkIfUserIsLoggedOut();
    }
}