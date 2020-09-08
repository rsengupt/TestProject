package com.demo.test.bu.booking;

import com.demo.test.bu.data.DataUtil;
import com.demo.test.bu.web.Web;
import org.openqa.selenium.support.ui.Select;

public class CreateBooking {
    private static CreateBooking inst;
    
    private CreateBooking() { }
    
    
    public static CreateBooking getInstance() {
        if (inst == null) {
            inst = new CreateBooking();
        }
        return inst;
    }
    
    
    public void openUrl(final String url) {
        Web.getInstance().openUrl(url);
    }
    
    
    public void closeBrowser() {
        Web.getInstance().closeBrowser();
    }
    
    
    public boolean selectSourceAndDestination(final String source, final String dest) {
        boolean bRet = false;
        
        boolean flagSourceSelect = Web.getInstance().selectFromDropDown("ddmSourceCity", source);
        boolean flagDestSelect = Web.getInstance().selectFromDropDown("ddmDestCity", dest);
        
        if (flagSourceSelect && flagDestSelect) {
            Web.getInstance().clickSubmitBtn();
            bRet = true;
        }
        if (bRet) {
            System.out.println("Source and Destination selection are successful.");
        }
        else {
            System.out.println("Source and Destination selection are failed.");
        }
        return bRet;
    }
    
    
    public boolean selectFlight(final String flightName, final String depart, final String arrive, final String price){
        boolean bRet = false;
        
        bRet = Web.getInstance().selectRowFromWebTable("flightTable", flightName, depart, arrive, price);
        
        if (bRet) {
            System.out.println("Flight selection successful.");
        }
        else {
            System.out.println("Flight selection failed.");
        }
        return bRet;
    }
    
    
    
    public boolean setPassengerDetails(final String name, final String addr, final String city, final String state,
                                       final String zipCode) {
        boolean bRet = true;
        
        if (!Web.getInstance().setText("txtPersonName", name)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonAddr", addr)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonCity", city)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonState", state)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonZipCode", zipCode)) {
            bRet = false;
        }
        
        if (bRet) {
            System.out.println("Passenger details saved.");
        }
        else {
            System.out.println("Failed to save Passenger details.");
        }
        
        return bRet;
    }
    
    
    public boolean setCardInfo(final String cardType, final String cardNum, final String cardMonth, final String cardYear,
                               final String cardName) {
        boolean bRet = true;
    
        if (!Web.getInstance().selectFromDropDown("ddmPersonCardType", cardType)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonCardNum", cardNum)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonCardMonth", cardMonth)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonCardYear", cardYear)) {
            bRet = false;
        }
        if (!Web.getInstance().setText("txtPersonCardName", cardName)) {
            bRet = false;
        }
    
        if (bRet) {
            Web.getInstance().clickSubmitBtn();
            System.out.println("Passenger Card details saved.");
        }
        else {
            System.out.println("Failed to save Passenger Card details.");
        }
    
        return bRet;
    }
    
    
    
}
