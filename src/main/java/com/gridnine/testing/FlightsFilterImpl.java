package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.Duration.between;
import static java.util.stream.IntStream.range;

public class FlightsFilterImpl implements FlightsFilter {

    @Override
    public boolean hasDepartedUntilCurrentTime(Flight flight) {
        return flight
                .getSegments()
                .stream()
                .anyMatch(segment -> segment.getDepartureDate()
                        .isAfter(LocalDateTime.now()));
    }

    @Override
    public boolean hasArrivalBeforeDeparture(Flight flight) {
        return flight
                .getSegments()
                .stream()
                .anyMatch(segment -> segment.getDepartureDate()
                        .isBefore(segment.getArrivalDate()));
    }

    @Override
    public boolean hasLayoverMoreThanTwoHours(Flight flight) {
        List<Segment> segments = flight.getSegments();

        if (segments.isEmpty()) {
            return false;
        }

        long hoursCount = range(0, segments.size() - 1)
                .mapToLong(i ->
                        between(segments.get(i).getArrivalDate(),
                                segments.get(i + 1).getDepartureDate())
                                .toHours())
                .sum();

        return hoursCount < 2;
    }
}
