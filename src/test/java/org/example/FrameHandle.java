package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class FrameHandle {
    protected String url = "https://practice-automation.com/iframes/";

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
    public void countIframe() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        List<ElementHandle> iframes = page.querySelectorAll("iframe");
        System.out.println(iframes.size());
    }

    @Test(priority = 1)
    public void switchToFrame() throws InterruptedException {
        Frame iframe = page.frame("top-iframe");
        Thread.sleep(3000);

        ElementHandle text = iframe.querySelector("//b[@class='navbar__title text--truncate']");
        text.click();
        Thread.sleep(3000);
    }


    @AfterSuite
    public void closeChromeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
