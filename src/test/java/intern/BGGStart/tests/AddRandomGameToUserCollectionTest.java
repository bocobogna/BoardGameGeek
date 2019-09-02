package intern.BGGStart.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static intern.BGGStart.enums.BrowseMenuOption.*;
import static intern.BGGStart.enums.UserMenuOption.*;

//@Disabled
public class AddRandomGameToUserCollectionTest extends BaseTest{

    @ParameterizedTest(name = "{index} => size14={0}, size17={1}")
    @CsvSource({"14, 17"})
    void AddRandomGameToUserCollection(int size14, int size17){
        logInUser();
        userHomePage.openBrowseDropDownMenu();

        browseMenuDropDown.browserMenuList().shouldHave(size(size14));
        browseMenuDropDown.menuOption(ALL_BOARDGAMES);

        allGamesPage.checkIfAllGamesPageIsOpened();
        allGamesPage.openRandomPageWithGames();
        allGamesPage.goToRandomGamePage();

        gamePage.checkIfGameNameIsEqualToGameNameFromAllBoardGames();
        gamePage.addGameToCollection();

        userHomePage.openUserDropDownMenu();

        userMenuDropDown.userMenuDropDownList().shouldHave(size(size17));
        userMenuDropDown.menuAction(COLLECTION);

        collectionPage.checkBoardGameCollectionName();
        collectionPage.checkIfRandomGameShuffledFromAllBoardGamesIsPresentInUserCollection();

        userHomePage.openUserDropDownMenu();

        userMenuDropDown.userMenuDropDownList().shouldHave(size(size17));

        userMenuDropDown.menuAction(SIGN_OUT);
        mainHeader.checkIfUserIsLoggedOut();

    }
}
