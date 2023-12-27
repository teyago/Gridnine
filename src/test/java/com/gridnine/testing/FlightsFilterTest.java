package com.gridnine.testing;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FlightsFilterTest {
    FlightsFilter filter = new FlightsFilterImpl();

    @Test
    public void hasDepartedUntilCurrentTime() {
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.of(2025, 12, 27, 0, 0),
                LocalDateTime.of(2025, 12, 28, 0, 0)));
        Flight flight = new Flight(segments);
        Assert.assertTrue(filter.hasDepartedUntilCurrentTime(flight));
    }

    @Test
    public void hasArrivalBeforeDeparture() {
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.of(2025, 12, 27, 20, 0),
                LocalDateTime.of(2025, 12, 15, 7, 0)));
        Flight flight = new Flight(segments);
        Assert.assertFalse(filter.hasArrivalBeforeDeparture(flight));
    }

    @Test
    public void hasLayoverMoreThanTwoHours() {
        List<Segment> segments = new ArrayList<>();
        segments.add(new Segment(LocalDateTime.of(2025, 12, 27, 8, 0),
                LocalDateTime.of(2025, 12, 27, 10, 0)));
        segments.add(new Segment(LocalDateTime.of(2025, 12, 27, 19, 0),
                LocalDateTime.of(2025, 12, 27, 23, 0)));
        Flight flight = new Flight(segments);
        Assert.assertFalse(filter.hasLayoverMoreThanTwoHours(flight));
    }
}
