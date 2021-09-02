package intern.BGGStart.pageObject.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SignInModal {

    private SelenideElement userNameInput = $("#inputUsername");
    private SelenideElement passwordInput = $("#inputPassword");
    private SelenideElement loginForm = $("div.modal-content");
    private SelenideElement signInButton = $(byXpath("//form[@name='loginform']//button[@type='submit']"));

    public SignInModal loginUser(String userName, String userPassword) {
        loginForm
                .shouldBe(visible)
                .shouldHave(matchText("Sign in"));
        userNameInput
                .shouldBe(visible)
                .val(userName)
                .shouldHave(value(userName));
        passwordInput
                .shouldBe(visible)
                .val(userPassword)
                .shouldNot(empty);
        return this;
    }

    public SignInModal clickSingIn() {
        signInButton
                .shouldBe(visible)
                .click();
        return this;
    }

}
