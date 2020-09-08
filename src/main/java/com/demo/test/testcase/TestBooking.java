package com.demo.test.testcase;

import com.demo.test.bu.booking.CreateBooking;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBooking {
    
    @BeforeTest
    public void openBrowser() {
        CreateBooking.getInstance().openUrl("https://blazedemo.com/");
    }
    
    @Test(priority = 1)
    public void testBooking() {
        final String sourceCity = "Boston";
        final String destCity = "Rome";
        final String flightName = "United Airlines";
        final String depart = "7:43 AM";
        final String arrive = "10:45 PM";
        final String price = "$432.98";
        final String passengerName = "RS";
        final String passengerAddr = "Kol";
        final String passengerCity = "Kol";
        final String passengerState = "WB";
        final String passengerZipCode = "IN";
        final String passengerCardType = "Visa";
        final String passengerCardNum = "1111";
        final String passengerCardMonth = "1";
        final String passengerCardYear = "2020";
        final String passengerCardName = "test";
        
        if (CreateBooking.getInstance().selectSourceAndDestination(sourceCity, destCity)) {
            if(CreateBooking.getInstance().selectFlight(flightName, depart, arrive, price)) {
                if (CreateBooking.getInstance().setPassengerDetails(passengerName, passengerAddr, passengerCity, passengerState, passengerZipCode)) {
                    if (CreateBooking.getInstance().setCardInfo(passengerCardType, passengerCardNum, passengerCardMonth, passengerCardYear, passengerCardName)) {
                        System.out.println("Flight booking successful.");
                    }
                    else {
                        System.out.println("Flight booking failed");
                    }
                }
                else {
                    System.out.println("Flight booking failed");
                }
            }
            else {
                System.out.println("Flight booking failed");
            }
        }
        else {
            System.out.println("Flight booking failed");
        }
    }
    
    
    @AfterTest
    public void closeBrowser() {
        CreateBooking.getInstance().closeBrowser();
    }
}
