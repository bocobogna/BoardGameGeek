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

    HomePage homepage = new HomePage();
    MainHeader mainHeader = new MainHeader();
    SignInModal loginForm = new SignInModal();
    UserHomePage userHomePage = new UserHomePage();
    UserMenuDropDown userMenuDropDown = new UserMenuDropDown();
    UserProfilePage userProfilePage = new UserProfilePage();
    EditUserDetailsPage editUserDetailsPage = new EditUserDetailsPage();
    BrowseMenuDropDown browseMenuDropDown = new BrowseMenuDropDown();
    AllGamesPage allGamesPage = new AllGamesPage();
    GamePage gamePage = new GamePage();
    CollectionPage collectionPage = new CollectionPage();
    Api api = new Api();

    @BeforeAll
    public static void setup() {
        if (Configuration.browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("w3c", false);
            options.addArguments("start-maximized");
            WebDriverManager.chromedriver().version("76.0.3809.68").setup();
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
        homepage.open()
                .openSignInModal();

        mainHeader.checkIfUserIsLoggedOut();

        loginForm.loginUser();

        mainHeader.checkIfUserIsLoggedIn();
    }
}
