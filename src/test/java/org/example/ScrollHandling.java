package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ScrollHandling {
    protected String url = "https://www.tutorialspoint.com/selenium/practice/scroll-top.php";

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

    @Test(priority = 0)
    public void scrollDown() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        String script = "window.scrollTo(0, document.body.scrollHeight)";
        page.evaluate(script);
        Thread.sleep(3000);
    }

    @Test(priority = 1)
    public void scrollUp() throws InterruptedException {
        String script = "window.scrollTo(0, 0)";
        page.evaluate(script);
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void scrollToSpecificLocator() throws InterruptedException {
        ElementHandle locator = page.querySelector("//h3[normalize-space()='Where can I get some?']");
        locator.scrollIntoViewIfNeeded();
        Thread.sleep(3000);
    }

    @AfterSuite
    public void closeChromeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
