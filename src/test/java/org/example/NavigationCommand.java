package org.example;

import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class NavigationCommand {
    private static final Logger log = LoggerFactory.getLogger(NavigationCommand.class);
    protected String url = "https://www.tutorialspoint.com/selenium/practice/register.php";

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

    @Test
    public void navigation() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        ElementHandle logo = page.querySelector(".logo-desktop");
        logo.click();
        Thread.sleep(3000);

//        go back
        page.goBack();
        Thread.sleep(3000);

//        go forward
        page.goForward();
        Thread.sleep(3000);

        page.goBack();
        Thread.sleep(3000);

//        reload
        page.reload();
        Thread.sleep(3000);
    }

    @AfterSuite
    public void closeChromeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}

