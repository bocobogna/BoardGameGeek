package intern.BGGStart.tests;

import com.codeborne.selenide.*;
import intern.BGGStart.fragments.MainHeader;
import intern.BGGStart.fragments.SignInModal;
import intern.BGGStart.fragments.UserMenuDropDown;
import intern.BGGStart.pageObject.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static intern.BGGStart.enums.UserMenuOption.COLLECTION;
import static intern.BGGStart.enums.UserMenuOption.SIGN_OUT;

public class CheckLanguageDependenceTest {

    @BeforeEach
    public void setup(){
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

    @AfterEach
    public void teardown(){
        WebDriverRunner.driver().getWebDriver().close();
        WebDriverRunner.driver().getWebDriver().quit();
    }

//    @Disabled
    @DisplayName("Check Language Dependence")
    @ParameterizedTest
    @ValueSource(ints = {17})
    public void CheckLanguageDependence(int number){
        HomePage homepage = new HomePage();
        homepage.open();
        homepage.openSignInModal();

        MainHeader mainHeader = new MainHeader();
        mainHeader.checkIfUserIsLoggedOut();

        SignInModal loginForm = new SignInModal();
        loginForm.loginUser();

        mainHeader.checkIfUserIsLoggedIn();

        UserHomePage userHomePage = new UserHomePage();
        userHomePage.openUserDropDownMenu();

        UserMenuDropDown userMenuDropDown = new UserMenuDropDown();
        userMenuDropDown.returnsUserMenuDropDownSize().shouldHave(size(number));
        userMenuDropDown.menuAction(COLLECTION);

        CollectionPage collectionPage = new CollectionPage();
        collectionPage.checkBoardGameCollectionName();
        collectionPage.goToGamePageWithUsageOfCollectionsShuffle();

        GamePage gamePage = new GamePage();
        gamePage.checkIfGameNameIsEqualToGameNameFromUserCollection();
        gamePage.getGameID();
        gamePage.checkLanguageDependence();
        gamePage.checkIfMostVotedLanguageDependenceValueIsDisplayed();

        userHomePage.openUserDropDownMenu();
        userMenuDropDown.returnsUserMenuDropDownSize().shouldHave(size(17));
        userMenuDropDown.menuAction(SIGN_OUT);

        mainHeader.checkIfUserIsLoggedOut();
    }
}
