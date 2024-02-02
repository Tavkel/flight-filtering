package com.gridnine.testing.filters;

import com.gridnine.testing.models.Flight;

import java.time.Duration;
import java.util.List;

public class FlightDurationFilter implements Filter {
    private final Duration duration;

    public FlightDurationFilter(Duration duration) {
        this.duration = duration;
    }

    @Override
    final public List<Flight> doFilter(List<Flight> flights) {
        return flights.stream()
                .filter(f -> {
                    var firstSegment = FilterUtils.getFirstSegment(f.getSegments());
                    var lastSegment = FilterUtils.getLastSegment(f.getSegments());

                    return Duration.between(firstSegment.getDepartureDate(), lastSegment.getArrivalDate())
                            .compareTo(duration) <= 0;
                })
                .toList();
    }
}
