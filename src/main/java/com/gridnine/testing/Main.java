package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FlightsFilter filter = new FlightsFilterImpl();

        List<Flight> flightList = FlightBuilder.createFlights();

        System.out.println("\nFiltered departures: no departures until the current time \n");
        flightList.stream()
                .filter(filter::hasDepartedUntilCurrentTime)
                .forEach(System.out::println);

        System.out.println("\nFiltered departures: no segments with an arrival date earlier than the departure date \n");
        flightList.stream()
                .filter(filter::hasArrivalBeforeDeparture)
                .forEach(System.out::println);

        System.out.println("\nFiltered departures: without time spent on the ground more than two hours \n");
        flightList.stream()
                .filter(filter::hasLayoverMoreThanTwoHours)
                .forEach(System.out::println);
    }
}