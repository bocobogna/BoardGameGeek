package intern.BGGStart.Fragments;

import com.codeborne.selenide.SelenideElement;
import intern.BGGStart.Resources.DataResources;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class SignInModal {

    private final String password = "qweasdzxc";
    private SelenideElement userNameInput = $("#inputUsername");
    private SelenideElement passwordInput = $("#inputPassword");
    private SelenideElement loginForm = $("div.modal-content");


    public void loginUser (){
        loginForm
                .shouldBe(visible)
                .shouldHave(matchText("Sign in"));
        userNameInput
                .shouldBe(visible)
                .val(DataResources.userName)
                .shouldHave(value(DataResources.userName));
        passwordInput
                .shouldBe(visible)
                .val(password)
                .shouldNot(empty).submit();
    }

}
