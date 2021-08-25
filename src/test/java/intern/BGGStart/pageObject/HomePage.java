package intern.BGGStart.pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement signInLink = $(byXpath("//a[@ggloginbutton]"));

    public HomePage open() {
        Selenide.open("");
        return this;
    }

}
