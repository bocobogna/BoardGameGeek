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

    public SelenideElement checkBoardGameCollectionName(){
        return userBoardGameCollectionName
                .shouldBe(visible)
                .shouldHave(matchText("Board Game Collection"));
    }

    public void goToGamePageWithUsageOfCollectionsShuffle(){
        userGamesCollection = gamesCollection.texts();
        Collections.shuffle(userGamesCollection);
        gameNameShuffleFromCollection = userGamesCollection.get(0);
        gamesCollection.findBy(text(gameNameShuffleFromCollection)).click();
    }

    public void goToGameWithNoVotes(){
        userGamesCollection = gamesCollection.texts();
        gameNameShuffleFromCollection = "5211"; // Age of Steam
        gamesCollection.findBy(text(gameNameShuffleFromCollection)).click();
    }

    public Boolean checkIfRandomGameShuffledFromAllBoardGamesIsPresentInUserCollection(){
        List<String> userGamesCollection = gamesCollection.texts();
        result = userGamesCollection.contains(randomGameNameShuffleFromAllBoardGames);
        return result;
    }
}