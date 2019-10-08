package intern.BGGStart.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import intern.BGGStart.api.Api;
import intern.BGGStart.pageObject.*;
import intern.BGGStart.pageObject.fragments.BrowseMenuDropDown;
import intern.BGGStart.pageObject.fragments.MainHeader;
import intern.BGGStart.pageObject.fragments.SignInModal;
import intern.BGGStart.pageObject.fragments.UserMenuDropDown;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public abstract class BaseTest {

    Pages pages = new Pages();

    @BeforeAll
    public static void setup() {
        if (Configuration.browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            WebDriverManager.chromedriver().version("76.0.3809.126").setup();
            WebDriver webDriver = new ChromeDriver(options);
            setWebDriver(webDriver);
        }
        Configuration.timeout = 8000;
        Configuration.baseUrl = "https://boardgamegeek.com";
    }

    @AfterAll
    public static void teardown() {
        WebDriverRunner.driver().getWebDriver().close();
        WebDriverRunner.driver().getWebDriver().quit();
    }

    protected void logInUser(){
        pages.homePage.open()
                .openSignInModal();

        pages.mainHeader
                .checkIfUserIsLoggedOut();

        pages.loginForm
                .loginUser();

        pages.mainHeader
                .checkIfUserIsLoggedIn();
    }
}
