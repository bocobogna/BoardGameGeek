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
        userHomePage.openUserDropDownMenu();
        userMenuDropDown.userMenuDropDownList().shouldHave(size(number));
        userMenuDropDown.menuAction(COLLECTION);

        collectionPage.checkBoardGameCollectionName();
        collectionPage.goToGameWithNoVotes();

        gamePage.checkIfGameNameIsEqualToGameNameFromUserCollection();
        gamePage.getGameID();

        api.languageDependence();

        gamePage.checkIfMostVotedLanguageDependenceValueIsDisplayed();

        userHomePage.openUserDropDownMenu();
        userMenuDropDown.userMenuDropDownList().shouldHave(size(number));
        userMenuDropDown.menuAction(SIGN_OUT);

        mainHeader.checkIfUserIsLoggedOut();
    }
}