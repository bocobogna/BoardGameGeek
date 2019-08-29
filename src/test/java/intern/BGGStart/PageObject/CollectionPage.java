package intern.BGGStart.PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.List;
import java.util.Collections;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class CollectionPage {

    private Boolean result;
    public static String gameNameShuffleFromCollection;
    private List<String> userGamesCollection;
    private ElementsCollection gamesCollection = $$(byXpath("//div[contains(@id,'results_objectname')]/a"));
    private SelenideElement gameHeaderTitle = $(byXpath("//div[@class='game-primary ng-scope']//div[@class='game-header-title-container']"));
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
        gamesCollection.findBy(Condition.text(gameNameShuffleFromCollection)).click();
    }

    public SelenideElement returnsGameHeaderWithGameTitle(){
        return gameHeaderTitle
                .shouldBe(visible)
                .shouldHave(text(gameNameShuffleFromCollection));
    }

    public Boolean checkIfRandomGameShuffledFromAllBoardGamesIsPresentInUserCollection(){
        List<String> userGamesCollection = gamesCollection.texts();
        result = userGamesCollection.contains(AllGamesPage.randomGameNameShuffleFromAllBoardGames);
        return result;
    }
}