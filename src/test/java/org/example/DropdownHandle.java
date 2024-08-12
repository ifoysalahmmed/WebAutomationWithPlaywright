package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DropdownHandle {
    protected String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";

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
    public void selectByIndex() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        ElementHandle dropdown = page.querySelector("#state");
        dropdown.selectOption(new SelectOption().setIndex(1));
        Thread.sleep(3000);
    }

//    @Test
    public void selectByValue() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        ElementHandle dropdown = page.querySelector("#state");
        dropdown.selectOption(new SelectOption().setValue("Uttar Pradesh"));
        Thread.sleep(3000);
    }

    @Test
    public void selectByLabel() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        ElementHandle dropdown = page.querySelector("#state");
        dropdown.selectOption(new SelectOption().setLabel("Haryana"));
        Thread.sleep(3000);
    }

    @AfterSuite
    public void closeChromeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
