package intern.BGGStart.PageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.restassured.path.xml.XmlPath;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.get;

public class GamePage {

    private String itemID, mostVotedLanguageDependence;
    private String path = "https://www.boardgamegeek.com/xmlapi/boardgame/";
    private SelenideElement itemIDValue = $("div.game-itemid.ng-binding");
    private SelenideElement languageDependenceValue = $("div.feature-description span.ng-binding");
    private SelenideElement addToCollectionButton = $(byXpath("//div[@class='toolbar-actions']//button[contains(@class, 'toolbar-action-full')]"));
    private SelenideElement addToCollectionSaveButton = $(byXpath("//button[contains(text(),'Save')]"));
    private SelenideElement gameAddedConfirmationNotify = $(byXpath("//span[contains(text(),'Item added to')]"));
    private SelenideElement gameHeaderTitle = $("div.game-primary.ng-scope div.game-header-title-info a");

    public void checkIfGameNameIsEqualToGameNameFromUserCollection(){
        gameHeaderTitle
                .shouldBe(visible)
                .should(matchText(CollectionPage.gameNameShuffleFromCollection));
    }

    public void checkIfGameNameIsEqualToGameNameFromAllBoardGames(){
        gameHeaderTitle
                .shouldBe(visible)
                .should(matchText(AllGamesPage.randomGameNameShuffleFromAllBoardGames));
    }

    public void getGameID() {
        itemID = itemIDValue.getText().substring(13);
    }

    public void checkLanguageDependence() {
        XmlPath xmlPath = get(path + itemID).xmlPath();
        String pollPath = "boardgames.boardgame.poll.find{it.@name=='language_dependence'}.results.result";

        int totalVotes = xmlPath
                .getInt("boardgames.boardgame.poll.find{it.@name=='language_dependence'}.@totalvotes");

        mostVotedLanguageDependence = (totalVotes > 0) ?
                xmlPath.getString(pollPath + ".find{it.@numvotes=='" + xmlPath.getInt(pollPath + ".@numvotes.list()*.toInteger().max()") + "'}.@value")
                : "(no votes)";

/*   !! Can be wtire like this but looks like crap :P
        mostVotedLanguageDependence = (xmlPath.getInt("boardgames.boardgame.poll.find{it.@name=='language_dependence'}.@totalvotes") > 0) ?
                xmlPath.getString(pollPath + ".find{it.@numvotes=='" + xmlPath.getInt(pollPath + ".@numvotes.list()*.toInteger().max()") + "'}.@value")
                : "(no votes)";
    !! Very long way of writing the same things
/*        if (totalVotes > 0) {
            maxVotes = xmlPath
                    .getInt(pollPath + ".@numvotes.list()*.toInteger().max()");
            System.out.println("Max votes: " + maxVotes);

            mostVotedLanguageDependence = xmlPath
                    .getString(pollPath + ".find{it.@numvotes=='" + maxVotes + "'}.@value");
            System.out.println("Category with max voices: " + mostVotedLanguageDependence);
        } else {
            mostVotedLanguageDependence = "(no votes)";
            System.out.println("--> :( <-- Nobody votes for this game Language Dependence.");
        }

        XmlPath xmlPath = get(path + itemID).xmlPath();
        String pollPath = "boardgames.boardgame.poll.find{it.@name=='language_dependence'}.results.result";

        int maxVotes = xmlPath.getInt(pollPath + ".@numvotes.list()*.toInteger().max()");
    !! Null pointer when there is no voices
        mostVotedLanguageDependence = maxVotes > 0 ?
                xmlPath.getString(pollPath + ".find{it.@numvotes=='" + maxVotes + "'}.@value")
                : "(no votes)";*/
    }

    public SelenideElement checkIfMostVotedLanguageDependenceValueIsDisplayed() {
        return languageDependenceValue
                .shouldBe(visible)
                .shouldHave(text(mostVotedLanguageDependence));
    }

    public void addGameToCollection() {
        addToCollectionButton
                .shouldBe(visible)
                .click();
        addToCollectionSaveButton
                .shouldBe(visible)
                .click();
        gameAddedConfirmationNotify
                .should(appear);
    }
}
