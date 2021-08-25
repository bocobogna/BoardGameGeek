package intern.BGGStart.pageObject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.Collections;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static intern.BGGStart.pageObject.AllGamesPage.*;

public class CollectionPage {

    private Boolean result;
    public static String gameNameShuffleFromCollection;
    private List<String> userGamesCollection;
    private ElementsCollection gamesCollection = $$(byXpath("//div[contains(@id,'results_objectname')]/a"));
    private SelenideElement userBoardGameCollectionName = $("div.fl.sf");

    public SelenideElement checkBoardGameCollectionName() {
        return userBoardGameCollectionName
                .shouldBe(visible)
                .shouldHave(matchText("Board Game Collection"));
    }

    public String getGameNameShuffleFromCollection() {
        userGamesCollection = gamesCollection.texts();
        Collections.shuffle(userGamesCollection);
        return userGamesCollection.get(0);
    }

    public CollectionPage goToGamePageWithUsageOfCollectionsShuffle(String gameName) {
        gamesCollection.findBy(text(gameName)).click();
        return this;
    }

    public CollectionPage goToGameWithNoVotes(String gameName) {
        userGamesCollection = gamesCollection.texts();
//        gameNameShuffleFromCollection = "328272"; // Bliss
        gamesCollection.findBy(text(gameName)).click();
        return this;
    }

    public Boolean checkIfRandomGameShuffledFromAllBoardGamesIsPresentInUserCollection(String gameTitle) {
        List<String> userGamesCollection = gamesCollection.texts();
        result = userGamesCollection.contains(gameTitle);
        return result;
    }
}