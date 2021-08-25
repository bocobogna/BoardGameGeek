package intern.BGGStart.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static intern.BGGStart.enums.BrowseMenuOption.*;
import static intern.BGGStart.enums.UserMenuOption.*;

//@Disabled
public class AddRandomGameToUserCollectionTest extends BaseTest{

    @ParameterizedTest(name = "{index} => size15={0}, size17={1}")
    @CsvSource({"15, 17"})
    void AddRandomGameToUserCollection(int size15, int size17){
        logInUser();
        pages.userHomePage
                .openBrowseDropDownMenu();

        pages.browseMenuDropDown
                .browserMenuList()
                .shouldHave(size(size15));
        pages.browseMenuDropDown
                .menuOption(ALL_BOARDGAMES);

        pages.allGamesPage.checkIfAllGamesPageIsOpened()
                .openRandomPageWithGames()
                .goToRandomGamePage();

        pages.gamePage
                .checkIfGameNameIsEqualToGameNameFromAllBoardGames()
                .addGameToCollection();

        pages.userHomePage
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .openUserMenuDropDownList();
        pages.userMenuDropDown
                .selectMenuAction(COLLECTION);

        pages.collectionPage
                .checkBoardGameCollectionName();
        pages.collectionPage
                .checkIfRandomGameShuffledFromAllBoardGamesIsPresentInUserCollection();

        pages.userHomePage
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .openUserMenuDropDownList();

        pages.userMenuDropDown
                .selectMenuAction(SIGN_OUT);
        pages.mainHeader
                .checkIfUserIsLoggedOut();

    }
}
