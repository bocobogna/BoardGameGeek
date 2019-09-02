package intern.BGGStart.api;

import io.restassured.path.xml.XmlPath;

import static intern.BGGStart.pageObject.GamePage.itemID;
import static io.restassured.RestAssured.get;

public class Api {

    private String path = "https://www.boardgamegeek.com/xmlapi/boardgame/";
    public static String mostVotedLanguageDependence;

    public void languageDependence() {
        XmlPath xmlPath = get(path + itemID).xmlPath();
        String pollPath = "boardgames.boardgame.poll.find{it.@name=='language_dependence'}.results.result";

        int maxVotes = xmlPath.getInt(pollPath + ".@numvotes.list()*.toInteger().max()");
        mostVotedLanguageDependence = maxVotes > 0 ?
                xmlPath.getString(pollPath + ".find{it.@numvotes=='" + maxVotes + "'}.@value")
                : "(no votes)";
    }
}
