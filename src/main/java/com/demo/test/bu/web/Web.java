package com.demo.test.bu.web;

import com.demo.test.bu.browser.BrowserUtil;
import com.demo.test.bu.data.DataUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Web {
    private static Web inst;
    
    private Web() { }
    
    
    public static Web getInstance() {
        if (inst == null) {
            inst = new Web();
        }
        return inst;
    }
    
    
    private final WebDriver drv = BrowserUtil.getInstance().getWebDriver();
    
    
    public void openUrl(final String url) {
        drv.get(url);
        try {
            Thread.sleep(5000);
        }
        catch (final InterruptedException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
    
    
    public void closeBrowser() {
        drv.close();
        drv.quit();
    }
    
    
    public boolean selectFromDropDown(final String logicalName, final String value) {
        boolean bRet = false;
        Select sel;
    
        String locatorType = DataUtil.getInstance().getLocatorTypeValue(logicalName).split("::")[0];
        String locatorValue = DataUtil.getInstance().getLocatorTypeValue(logicalName).split("::")[1];
    
        if (locatorType.equalsIgnoreCase("name")) {
            sel = new Select(drv.findElement(By.name(locatorValue)));
            sel.selectByVisibleText(value);
            if (sel.getFirstSelectedOption().getAttribute("value").equalsIgnoreCase(value)) {
                bRet = true;
            }
        }
        return bRet;
    }
    
    
    public boolean selectRowFromWebTable(final String logicalName, final String flightName, final String departTime,
                                         final String arriveTime, final String price) {
        boolean bRet = false;
        
        String locatorType = DataUtil.getInstance().getLocatorTypeValue(logicalName).split("::")[0];
        String locatorValue = DataUtil.getInstance().getLocatorTypeValue(logicalName).split("::")[1];
        
        if (locatorType.equalsIgnoreCase("xpath")) {
            int getRowCount = drv.findElements(By.xpath(locatorValue + "/tbody/tr")).size();
            
            for (int i = 1; i <= getRowCount; i++) {
                String flight = drv.findElement(By.xpath(locatorValue + "/tbody/tr[" + i + "]/td[3]")).getText().strip();
                if (flight.equalsIgnoreCase(flightName)) {
                    String depart = drv.findElement(By.xpath(locatorValue + "/tbody/tr[" + i + "]/td[4]")).getText().strip();
                    if (depart.equalsIgnoreCase(departTime)) {
                        String arrive = drv.findElement(By.xpath(locatorValue + "/tbody/tr[" + i + "]/td[5]")).getText().strip();
                        if (arrive.equalsIgnoreCase(arriveTime)) {
                            String p = drv.findElement(By.xpath(locatorValue + "/tbody/tr[" + i + "]/td[6]")).getText().strip();
                            if (p.equalsIgnoreCase(price)) {
                                drv.findElement(By.xpath(locatorValue + "/tbody/tr[" + i + "]/td[1]/input")).click();
                                bRet = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return bRet;
    }
    
    
    public boolean setText(final String logicalName, final String value) {
        boolean bRet = false;
        
        String locatorType = DataUtil.getInstance().getLocatorTypeValue(logicalName).split("::")[0];
        String locatorValue = DataUtil.getInstance().getLocatorTypeValue(logicalName).split("::")[1];
        
        if (locatorType.equalsIgnoreCase("id")) {
            drv.findElement(By.id(locatorValue)).clear();
            drv.findElement(By.id(locatorValue)).sendKeys(value);
            if (drv.findElement(By.id(locatorValue)).getAttribute("value").equals(value)) {
                bRet = true;
            }
        }
        
        return bRet;
    }
    
    
    
    public void clickSubmitBtn() {
        String locatorValue = DataUtil.getInstance().getLocatorTypeValue("btnSubmit").split("::")[1];
        
        drv.findElement(By.xpath(locatorValue)).click();
    }
    
    
}
