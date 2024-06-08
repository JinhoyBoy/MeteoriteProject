package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Die RegionFilter-Klasse filtert Meteoriten basierend auf ihrer Position innerhalb eines bestimmten Radius um eine gegebene geografische Koordinate.
 */
public class RegionFilter implements Filter {
    private double latitude;
    private double longitude;
    private double radius;

    /**
     * Standardkonstruktor.
     */
    public RegionFilter(double latitude, double longitude, double radius) {
        // make constructor
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    @Override
    public String getName() {
        return "Region";
    }

    @Override
    public void configure(Scanner consoleScanner) {
        System.out.print("Enter parameters for Region (latitude,longitude,radius): ");
        String[] regionParams = consoleScanner.nextLine().split(",");
        if (regionParams.length == 3) {
            this.latitude = Double.parseDouble(regionParams[0].trim());
            this.longitude = Double.parseDouble(regionParams[1].trim());
            this.radius = Double.parseDouble(regionParams[2].trim());
        } else {
            System.out.println("Invalid input for Mass filter");
        }
    }

    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(this::isWithinRadius)
                .collect(Collectors.toList());
    }

    private boolean isWithinRadius(Meteorite meteorite) {
        if (meteorite.getReclat() == 0.0 || meteorite.getReclong() == 0.0) {
            return false;
        }
        double lat = meteorite.getReclat();
        double lon = meteorite.getReclong();
        double distance = haversine(latitude, longitude, lat, lon);
        return distance <= radius;
    }

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}
