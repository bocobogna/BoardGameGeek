package intern.BGGStart.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public abstract class BaseTestFree {

    @BeforeAll
    public static void setup() {

        Configuration.browser.equals("chrome");
        Configuration.timeout = 8000;
        Configuration.baseUrl = "https://www.google.com/";

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver(options);
        setWebDriver(webDriver);
    }

    @AfterAll
    public static void teardown() {
        Selenide.closeWebDriver();
    }
}
