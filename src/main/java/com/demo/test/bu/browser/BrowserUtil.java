package com.demo.test.bu.browser;

import org.openqa.selenium.WebDriver;

public class BrowserUtil {
    
    private static BrowserUtil inst;
    
    private BrowserUtil() { }
    
    
    public static BrowserUtil getInstance() {
        if (inst == null) {
            inst = new BrowserUtil();
        }
        return inst;
    }
    
    
    public WebDriver getWebDriver() {
        return ChromeBrowser.getInstance().createChromeBrowser();
    }
    
    
}
