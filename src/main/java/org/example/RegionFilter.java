package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class RegionFilter implements Filter {
    private double latitude;
    private double longitude;
    private double radius;

    public RegionFilter(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(m -> isWithinRadius(m))
                .collect(Collectors.toList());
    }

    private boolean isWithinRadius(Meteorite meteorite) {
        double lat = Double.parseDouble(meteorite.getReclat());
        double lon = Double.parseDouble(meteorite.getReclong());
        double distance = haversine(latitude, longitude, lat, lon);
        return distance <= radius;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Radius der Erde in Kilometern
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c; // Distanz in Kilometern
    }
}

