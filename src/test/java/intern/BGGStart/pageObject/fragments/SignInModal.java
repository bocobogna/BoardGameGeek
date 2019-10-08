package intern.BGGStart.pageObject.fragments;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static intern.BGGStart.utils.DataResources.*;

public class SignInModal {

    private SelenideElement userNameInput = $("#inputUsername");
    private SelenideElement passwordInput = $("#inputPassword");
    private SelenideElement loginForm = $("div.modal-content");


    public SignInModal loginUser (){
        loginForm
                .shouldBe(visible)
                .shouldHave(matchText("Sign in"));
        userNameInput
                .shouldBe(visible)
                .val(userName)
                .shouldHave(value(userName));
        passwordInput
                .shouldBe(visible)
                .val(password)
                .shouldNot(empty)
                .submit();
        return this;
    }

}
