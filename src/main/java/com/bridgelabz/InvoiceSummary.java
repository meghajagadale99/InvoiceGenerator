package com.bridgelabz;

public class InvoiceSummary {
    private final int numberOfRides;
    private final double totalFair;
    private final double avgFarePerRide;

    public InvoiceSummary(int numberOfRides, double totalFair) {
        this.numberOfRides = numberOfRides;
        this.totalFair = totalFair;
        this.avgFarePerRide = this.totalFair / this.numberOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary summary = (InvoiceSummary) o;
        return numberOfRides == summary.numberOfRides &&
                Double.compare(summary.totalFair, totalFair) == 0 &&
                Double.compare(summary.avgFarePerRide, avgFarePerRide) == 0;
    }

}
