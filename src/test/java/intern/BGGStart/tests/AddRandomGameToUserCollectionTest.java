package intern.BGGStart.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.CollectionCondition.size;
import static intern.BGGStart.enums.BrowseMenuOption.ALL_BOARDGAMES;
import static intern.BGGStart.enums.UserMenuOption.COLLECTION;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;

public class AddRandomGameToUserCollectionTest extends BaseTest {

    @ParameterizedTest(name = "{index} => size15={0}")
    @CsvSource({"15"})
    void AddRandomGameToUserCollection(int size15) {
        logInUser();
        pages.mainHeader
                .openBrowseDropDownMenu();

        pages.browseMenuDropDown
                .browserMenuList()
                .shouldHave(size(size15));
        pages.browseMenuDropDown
                .menuOption(ALL_BOARDGAMES);

        pages.allGamesPage.checkIfAllGamesPageIsOpened()
                .openRandomPageWithGames();

        String gameTitle = pages.allGamesPage.getRandomGameTitle();

        pages.allGamesPage
                .goToRandomGamePage(gameTitle);

        pages.gamePage
                .checkIfGameNameIsEqualToGameNameFromAllBoardGames(gameTitle)
                .addGameToCollection();

        pages.mainHeader
                .openUserDropDownMenu();

        pages.userMenuDropDown
                .openUserMenuDropDownList();
        pages.userMenuDropDown
                .selectMenuAction(COLLECTION);

        pages.collectionPage
                .checkBoardGameCollectionName();
        pages.collectionPage
                .checkIfRandomGameShuffledFromAllBoardGamesIsPresentInUserCollection(gameTitle);

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
