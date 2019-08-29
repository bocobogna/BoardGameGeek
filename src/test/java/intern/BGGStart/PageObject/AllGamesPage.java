package intern.BGGStart.PageObject;

import com.codeborne.selenide.*;

import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AllGamesPage {

    private String lastPageStr;
    public static String randomGameNameShuffleFromAllBoardGames;
    private Integer lastPageInt;
    private SelenideElement collectionTable = $("#collectionitems");
    private SelenideElement maxPageValue = $(byXpath("//div[@class='fr']//a[@title='last page']"));
    private ElementsCollection gamesCollection = $$(byXpath("//div[contains(@id, 'results_objectname')]/a"));

    public void checkIfAllGamesPageIsOpened(){
        collectionTable
                .shouldBe(visible)
                .$$("tr")
                .shouldHaveSize(101);
    }

    public void openRandomPageWithGames() {
        lastPageStr = maxPageValue.getText();
        lastPageStr = lastPageStr.substring(1, lastPageStr.length() - 1);
        lastPageInt = Integer.parseInt(lastPageStr);
        int helper = ((int)(Math.random() * lastPageInt) + 1);
        Selenide.open("https://boardgamegeek.com/browse/boardgame/page/" + helper);
        gamesCollection
                .shouldHave(size(100));
    }

    public void goToRandomGamePage() {
        List<String> gamesAvailableOnChosenPage = gamesCollection.texts();
        Collections.shuffle(gamesAvailableOnChosenPage);
        randomGameNameShuffleFromAllBoardGames = gamesAvailableOnChosenPage.get(0);
        gamesCollection.findBy(text(randomGameNameShuffleFromAllBoardGames)).click();
    }

}
