package org.example.Filters;

import org.example.Meteorite;

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
    public RegionFilter() {
    }

    //weiterer Konstruktor für Testing
    public RegionFilter(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getName() {
        return "Region";
    }

    @Override
    public void configure(Scanner consoleScanner) {
        try {
            System.out.print("Enter parameters for Region (latitude,longitude,radius): ");
            String[] regionParams = consoleScanner.nextLine().split(",");
            if (regionParams.length == 3) {
                this.latitude = Double.parseDouble(regionParams[0].trim());
                this.longitude = Double.parseDouble(regionParams[1].trim());
                this.radius = Double.parseDouble(regionParams[2].trim());
            } else if (regionParams[0].equals("h")) {
                helpUser(consoleScanner);
            }
            else {
                System.out.println("Invalid input format for Region filter. Expected input is (latitude,longitude,radius).");
            }
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid type of input. Expected input is a decimal number.");
        }
        catch(Exception e) {
            System.out.println("Unexpected error please try again.");
        }
    }

    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(this::isWithinRadius)
                .collect(Collectors.toList());
    }

    private boolean isWithinRadius(Meteorite meteorite) {
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

    @Override
    public void helpUser(Scanner consoleScanner) {
        System.out.println("Here you can enter the latitude, as well as the longitude of the region, where the meteor hit.");
        System.out.println("Both should be a decimal number. The last parameter is the radius in which the meteor should lie (km)." +
                " This should also be a decimal number.");
        System.out.println();
        configure(consoleScanner);
    }
}
