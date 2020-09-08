package com.demo.test.bu.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

class ChromeBrowser {
    private static ChromeBrowser inst;
    
    ChromeBrowser() { }
    
    static ChromeBrowser getInstance() {
        if (inst == null) {
            inst = new ChromeBrowser();
        }
        return inst;
    }
    
    WebDriver createChromeBrowser() {
        final String methodName = "createChromeBrowser()";
        
        try {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\AppStore\\drivers\\windows\\32bit\\chromedriver.exe");
            
            final HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("safebrowsing.enabled", "true");
            
            final ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--test-type");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-translate");
            options.addArguments("--incognito");
            options.addArguments("start-maximized");
            
            WebDriver driver = new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            return driver;
        }
        catch (final UnsupportedOperationException | ClassCastException | SecurityException | IllegalArgumentException
                | NullPointerException e) {
            System.out.println(methodName + ":: Exception occurred. Unable to create new Chrome browser. Error log: " + e.getMessage());
            return null;
        }
    }
    
    
}
