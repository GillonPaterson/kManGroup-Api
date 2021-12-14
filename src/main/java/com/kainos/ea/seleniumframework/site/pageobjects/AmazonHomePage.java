package com.kainos.ea.seleniumframework.site.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import uk.kainos.seleniumframework.site.Site;

public class AmazonHomePage extends Site {

    private static final String PAGE_TITLE = "Home";

    @FindBy(id = "sp-cc-accept")
    private WebElement acceptCookiesButton;

    @FindBy(id = "header") private WebElement pageHeader;

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyPageHeader() {
        verifyPageTitle(pageHeader.getText(), PAGE_TITLE);
    }

    public AmazonHomePage acceptCookies() {
        acceptCookiesButton.click();
        return this;
    }
}
