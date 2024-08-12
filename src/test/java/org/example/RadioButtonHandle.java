package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class RadioButtonHandle {
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
    public void individualRadioButton() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        ElementHandle radioButton = page.querySelector("#gender");
        radioButton.click();
        Thread.sleep(3000);
    }

    @Test
    public void radioButtonAll() throws InterruptedException {
        page.navigate(url);
        Thread.sleep(3000);

        List<ElementHandle> radioButtons = page.querySelectorAll("//input[@type=\"radio\"]");
        for (ElementHandle rb1 : radioButtons) {
            if (!rb1.isChecked()) {
                rb1.click();
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
