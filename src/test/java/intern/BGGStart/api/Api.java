package intern.BGGStart.api;

import io.restassured.path.xml.XmlPath;

import static intern.BGGStart.pageObject.GamePage.itemID;
import static io.restassured.RestAssured.get;

public class Api {

    private String path = "https://www.boardgamegeek.com/xmlapi/boardgame/";
    public static String mostVotedLanguageDependence, bestNumberOfPlayers;

    public void getLanguageDependence() {
        XmlPath xmlPath = get(path + itemID).xmlPath();
        String pollPath = "boardgames.boardgame.poll.find{it.@name=='language_dependence'}.results.result";

        int maxVotes = xmlPath.getInt(pollPath + ".@numvotes.list()*.toInteger().max()");
        mostVotedLanguageDependence = maxVotes > 0 ?
                xmlPath.getString(pollPath + ".find{it.@numvotes=='" + maxVotes + "'}.@value")
                : "(no votes)";
    }

    public void getSuggestedNumberOfPlayers(){
        XmlPath xmlPath = get(path + itemID).xmlPath();
        String pollPath = "boardgames.boardgame.poll.find{it.@name=='suggested_numplayers'}.results.result";
        String totalVotesPath = "boardgames.boardgame.poll.find{it.@name=='suggested_numplayers'}";

        int totalVotes = xmlPath.getInt(totalVotesPath + ".@totalvotes.toInteger()");

        if (totalVotes != 0) {

            int maxVotes = xmlPath.getInt(pollPath + ".find{it.@value=='Best'}.@numvotes.list()*.toInteger().max()");

            XmlPath myXmlPath = new XmlPath(path + itemID + "boardgames.boardgame.poll.find{it.@name=='suggested_numplayers')");
            if (myXmlPath.getNode("results").getNode("result").getAttribute("numvotes").equals(maxVotes)) {
                bestNumberOfPlayers = myXmlPath.getNode("results").getAttribute("numplayers");
            }
        } else {bestNumberOfPlayers = "(no votes)";}
    }
}
