package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvoiceServiceTest {
    InvoiceService invoiceService = null;

    @BeforeEach
    public void setUp() {
        invoiceService = new InvoiceService();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFair(distance, time);
        Assertions.assertEquals(25, fare);
    }

    @Test
    void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFair(distance, time);
        Assertions.assertEquals(5, fare);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(2.0, 5),
                        new Ride(0.1, 1),
        };
        InvoiceSummary summary = invoiceService.calculateFair(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assertions.assertEquals(expectedInvoiceSummary,  summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoice() {
        String userId = "maddy";
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1),
        };
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assertions.assertEquals(expectedInvoiceSummary,  summary);
    }
}
