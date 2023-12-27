package com.gridnine.testing;

public interface FlightsFilter {
    boolean hasDepartedUntilCurrentTime(Flight flight);

    boolean hasArrivalBeforeDeparture(Flight flight);

    boolean hasLayoverMoreThanTwoHours(Flight flight);
}
