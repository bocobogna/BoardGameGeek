package intern.BGGStart.pageObject;

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
    private Integer lastPageInt;
    private SelenideElement collectionTable = $("#collectionitems");
    private SelenideElement maxPageValue = $(byXpath("//div[@class='fr']//a[@title='last page']"));
    private ElementsCollection gamesCollection = $$(byXpath("//div[contains(@id, 'results_objectname')]/a"));

    public AllGamesPage checkIfAllGamesPageIsOpened() {
        collectionTable
                .shouldBe(visible)
                .$$("tr")
                .shouldHave(size(101));
        return this;
    }

    public AllGamesPage openRandomPageWithGames() {
        lastPageStr = maxPageValue.getText();
        lastPageStr = lastPageStr.substring(1, lastPageStr.length() - 1);
        lastPageInt = Integer.parseInt(lastPageStr);
        int helper = ((int) (Math.random() * lastPageInt) + 1);
        Selenide.open("https://boardgamegeek.com/browse/boardgame/page/" + helper);
        gamesCollection
                .shouldHave(size(100));
        return this;
    }

    public String getRandomGameTitle() {
        List<String> gamesAvailableOnChosenPage = gamesCollection.texts();
        Collections.shuffle(gamesAvailableOnChosenPage);
        return gamesAvailableOnChosenPage.get(0);
    }

    public AllGamesPage goToRandomGamePage(String gameTitle) {
        gamesCollection.findBy(text(gameTitle)).click();
        return this;
    }

}
