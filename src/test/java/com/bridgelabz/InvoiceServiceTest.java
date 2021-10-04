package com.bridgelabz;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Mock
    RideRepository rideRepository ;

    @InjectMocks
    InvoiceService invoiceService;



    Ride[] rides ;
    InvoiceSummary expectedInvoiceSummary;


    @BeforeEach
    public void setUp() {
        invoiceService.setRideRepository(rideRepository);
        rides = new Ride[]{
                new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1)

        };
        expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
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
    public void givenUserIDAndRideList_ShouldReturnInvoiceSummary() {
        String userId = "maddy";
        Ride[] rides = {new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1)
        };
        invoiceService.addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualSummary);
    }

    @Test
    public void givenUserIDAndMultipleRideList_ShouldReturnInvoiceSummary() {
        String userId = "maddy";
        Ride[] rides = {new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1)
        };
        invoiceService.addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualSummary);
    }

    @Test
    void givenDistanceAndTime_WhenCalculatedForPremiumRide_ShouldReturnInvoiceSummary() {
        String userId = "maddy";
        Ride[] rides = {new Ride(CabRide.PREMIUM, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1),
                new Ride(CabRide.PREMIUM, 1.0, 5)
        };
        invoiceService.addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 85.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualSummary);
    }

    @Test
    void givenDistanceAndTime_WhenCalculatedForNormalRide_ShouldReturnInvoiceSummary() {
        String userId = "maddy";
        Ride[] rides = {new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.NORMAL, 0.1, 1),
                new Ride(CabRide.NORMAL, 1.0, 5)
        };
        invoiceService.addRides(userId, rides);
        when(rideRepository.getRides(userId)).thenReturn(rides);
        InvoiceSummary actualSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(3, 45.0);
        Assertions.assertEquals(expectedInvoiceSummary, actualSummary);
    }

}
