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
    private SelenideElement addToCollectionButton = $(byXpath("//div[@class='toolbar-actions']//button[contains(@class, 'toolbar-action-full')]"));
    private SelenideElement addToCollectionSaveButton = $(byXpath("//button[contains(text(),'Save')]"));
    private SelenideElement gameAddedConfirmationNotify = $(byXpath("//span[contains(text(),'Item added to')]"));
    private SelenideElement gameHeaderTitle = $("div.game-primary.ng-scope div.game-header-title-info a");

    public GamePage checkIfGameNameIsEqualToGameNameFromUserCollection(){
        gameHeaderTitle
                .shouldBe(visible)
                .should(matchText(gameNameShuffleFromCollection));
        return this;
    }

    public GamePage checkIfGameNameIsEqualToGameNameFromAllBoardGames(){
        gameHeaderTitle
                .shouldBe(visible)
                .should(matchText(randomGameNameShuffleFromAllBoardGames));
        return this;
    }

    public GamePage getGameID() {
        itemID = itemIDValue.getText().substring(13);
        return this;
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
        return  this;
    }
}
