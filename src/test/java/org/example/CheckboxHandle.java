package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxHandle {
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
    public void individualCheck() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        ElementHandle checkbox = page.querySelector("#hobbies");
        checkbox.click();
        Thread.sleep(3000);
    }

    @Test
    public void checkAll() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        List<ElementHandle> checkboxes = page.querySelectorAll("//input[@type=\"checkbox\"]");
        for (ElementHandle cb1 : checkboxes) {
            if (!cb1.isChecked()) {
                cb1.click();
                Thread.sleep(2000);
            }
        }
        Thread.sleep(3000);
    }

    @AfterSuite
    public void closeChromeBrowser() {
        page.close();
        browser.close();
        playwright.close();
    }
}
