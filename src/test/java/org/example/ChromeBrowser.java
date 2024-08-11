package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ChromeBrowser {
    protected static String url = "https://playwright.dev/docs/intro";

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
    public void openURL() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(5000);
    }

    @AfterSuite
    public void closeChromeBrowser(){
        page.close();
        browser.close();
        playwright.close();
    }
}
