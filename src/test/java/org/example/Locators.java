package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class Locators {
    protected static String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";

    Playwright playwright;
    BrowserType browserType;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeSuite
    public void startChromeBrowser() {
        playwright = Playwright.create();
        browserType = playwright.chromium();
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browserContext = browser.newContext(new Browser.NewContextOptions());

        page = browser.newPage();
        System.out.println("Browser version: " + browser.version());
    }

    //    @Test
    public void openURL() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(5000);
    }

    //    @Test
    public void locateByID() throws InterruptedException {
        page.navigate(url);

        ElementHandle firstname = page.querySelector("#fullname");
        firstname.fill("Foysal Ahmmed Limon");
        Thread.sleep(5000);
    }

    //    @Test
    public void locateByName() throws InterruptedException {
        page.navigate(url);

        ElementHandle email = page.querySelector("[name='email']");
        email.fill("Test@noemail.com");
        Thread.sleep(5000);
    }

    //    @Test
    public void locateByLink() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/links.php");

        ElementHandle link = page.querySelector("a:has-text(\"Home\")");
        link.click();
        Thread.sleep(5000);
    }

    //    @Test
    public void locateByLink2() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/links.php");

        ElementHandle link = page.querySelector("a:has-text(\"Created\")");
        link.click();
        Thread.sleep(5000);
    }

    @Test
    public void locateByTagName() throws InterruptedException {
        page.navigate("https://www.tutorialspoint.com/selenium/practice/links.php");

        List<ElementHandle> links = page.querySelectorAll("a");
        System.out.println("Number of anchor elements: " + links.size());
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeChromeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
