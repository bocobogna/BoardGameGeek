package intern.BGGStart.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import intern.BGGStart.pageObject.Pages;
import intern.BGGStart.utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@ExtendWith({ScreenShooterExtension.class})
@ExtendWith({TextReportExtension.class})
public abstract class BaseTest {

    @RegisterExtension
    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true);

    //logger
    Logger log = LoggerFactory.getLogger(BaseTest.class);

    Pages pages = new Pages();

    String info = Utils.getPathToResourcesFiles("Users.json");
    JSONObject userInfo = (JSONObject) Utils.readJsonFromFile(info).get("ssj4User");
    String userName = (String) userInfo.get("userName");
    String userPassword = (String) userInfo.get("userPassword");

    @BeforeAll
    public static void setup() {
        if (Configuration.browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--test-type", "--start-maximized");
            WebDriverManager.chromedriver().setup();
            WebDriver webDriver = new ChromeDriver(options);
            setWebDriver(webDriver);
        }
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://boardgamegeek.com";
    }

    @AfterAll
    public static void teardown() {
        Selenide.closeWebDriver();
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
