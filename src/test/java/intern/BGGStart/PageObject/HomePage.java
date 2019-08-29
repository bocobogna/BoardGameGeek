package intern.BGGStart.PageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private SelenideElement signInLink = $(byXpath("//button[@login-required]"));

    public void open() {
        Selenide.open("");
    }

    public void openSignInModal() {
        signInLink
                .shouldBe(visible).click();
    }

}
