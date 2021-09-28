package com.bridgelabz;

import java.util.Objects;

public class InvoiceSummary {
    private final int numberOfRides;
    private final double totalFare;
    private final double avgFarePerRide;

    public InvoiceSummary(int numberOfRides, double totalFare) {
        this.numberOfRides = numberOfRides;
        this.totalFare = totalFare;
        this.avgFarePerRide = this.totalFare / this.numberOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary summary = (InvoiceSummary) o;
        return numberOfRides == summary.numberOfRides &&
                Double.compare(summary.totalFare, totalFare) == 0 &&
                Double.compare(summary.avgFarePerRide, avgFarePerRide) == 0;
    }

}
