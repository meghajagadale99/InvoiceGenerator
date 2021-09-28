package com.bridgelabz;

public class InvoiceService {
    private static final double COST_PER_KM = 10.0;
    private static final int COST_PER_MINT = 1;
    private static final double MINIMUM_FAIR = 5.0;
    private RideRepository rideRepository;

    public double calculateFair(double distance, int time) {
        double totalFair = COST_PER_KM * distance + COST_PER_MINT * time;
        return Math.max(totalFair, MINIMUM_FAIR);
    }

    public InvoiceSummary calculateFair(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += ride.cabRide.costOfRide(ride);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFair(rideRepository.getRides(userId));
    }

    public void setRideRepository(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }
}
