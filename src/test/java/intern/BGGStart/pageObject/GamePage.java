package intern.BGGStart.pageObject;

import com.codeborne.selenide.SelenideElement;
import intern.BGGStart.api.Api;
import io.restassured.path.xml.XmlPath;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static intern.BGGStart.api.Api.mostVotedLanguageDependence;
import static intern.BGGStart.pageObject.AllGamesPage.*;
import static intern.BGGStart.pageObject.CollectionPage.*;
import static io.restassured.RestAssured.get;

public class GamePage {

    public static String itemID;
    private SelenideElement itemIDValue = $("div.game-itemid");
    private SelenideElement languageDependenceValue = $("div.feature-description span.ng-binding");
    private SelenideElement addToCollectionButton = $(byXpath("//div[@class='toolbar-action']//button[contains(@class, 'toolbar-action-full')]"));
    private SelenideElement addToCollectionSaveButton = $(byXpath("//button[contains(text(),'Save')]"));
    private SelenideElement gameAddedConfirmationNotify = $(byXpath("//span[contains(text(),'Item added to')]"));
    private SelenideElement gameHeaderTitle = $("h1 a");

    public GamePage checkIfGameNameIsEqualToGameNameFromUserCollection(String gameName) {
        gameHeaderTitle
                .shouldBe(visible)
                .should(matchText(".*" + gameName + ".*"));
        return this;
    }

    public GamePage checkIfGameNameIsEqualToGameNameFromAllBoardGames(String gameName) {
        gameHeaderTitle
                .shouldBe(visible)
                .should(matchText(".*" + gameName + ".*"));
        return this;
    }

    public String getGameID() {
        return itemIDValue.getText().substring(13);
    }

    public SelenideElement checkIfMostVotedLanguageDependenceValueIsDisplayed() {
        return languageDependenceValue
                .shouldBe(visible)
                .shouldHave(text(mostVotedLanguageDependence));
    }

    public GamePage addGameToCollection() {
        addToCollectionButton
                .shouldBe(visible)
                .click();
        addToCollectionSaveButton
                .shouldBe(visible)
                .hover().pressEnter();
        gameAddedConfirmationNotify
                .should(appear);
        return this;
    }
}
