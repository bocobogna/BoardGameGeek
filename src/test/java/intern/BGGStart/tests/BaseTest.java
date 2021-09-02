package intern.BGGStart.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import intern.BGGStart.pageObject.*;
import intern.BGGStart.utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public abstract class BaseTest {

    Pages pages = new Pages();

    String info = Utils.getPathToResourcesFiles("Users.json");
    JSONObject userInfo = (JSONObject) Utils.readJsonFromFile(info).get("ssj4User");
    String userName = (String) userInfo.get("userName");
    String userPassword = (String) userInfo.get("userPassword");

    @BeforeAll
    public static void setup() {
        if (Configuration.browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            WebDriverManager.chromedriver().setup();
            WebDriver webDriver = new ChromeDriver(options);
            setWebDriver(webDriver);
        }
        Configuration.timeout = 5000;
        Configuration.baseUrl = "https://boardgamegeek.com";
    }

    @AfterAll
    public static void teardown() {
        WebDriverRunner.driver().getWebDriver().close();
        WebDriverRunner.driver().getWebDriver().quit();
    }

    protected void logInUser() {
        pages.homePage
                .open();

        pages.mainHeader
                .openSignInModal()
                .checkIfUserIsLoggedOut();

        pages.loginForm
                .loginUser(userName, userPassword)
                .clickSingIn();

        pages.mainHeader
                .checkIfUserIsLoggedIn();
    }
}
