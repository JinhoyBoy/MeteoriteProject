package org.meteorite_filter.Filters;

import org.meteorite_filter.Meteorite;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Die RegionFilter-Klasse filtert Meteoriten basierend auf ihrer Position innerhalb eines bestimmten Radius um eine gegebene geografische Koordinate.
 */
public class RegionFilter implements Filter {
    private double latitude = Double.NaN;
    private double longitude = Double.NaN;
    private double radius;

    /**
     * Standardkonstruktor.
     */
    public RegionFilter() {
    }

    /**
     * Konstruktor für Tests.
     *
     * @param latitude  Die geografische Breite.
     * @param longitude Die geografische Länge.
     * @param radius    Der Radius in Kilometern.
     */
    public RegionFilter(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    /**
     * Gibt die geografische Breite zurück.
     *
     * @return Die geografische Breite.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gibt die geografische Länge zurück.
     *
     * @return Die geografische Länge.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gibt den Radius zurück.
     *
     * @return Der Radius in Kilometern.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Gibt den Namen des Filters zurück.
     *
     * @return Der Name des Filters.
     */
    @Override
    public String getName() {
        return "Region";
    }

    /**
     * Konfiguriert den Filter basierend auf der Benutzereingabe.
     *
     * @param consoleScanner Ein Scanner-Objekt zum Lesen der Benutzereingabe.
     */
    @Override
    public void configure(Scanner consoleScanner) {
        try {
            System.out.print("Enter parameters for Region " + "\u001B[33m" + "latitude; longitude; radius" + "\u001B[0m" + ": ");
            String[] regionParams = consoleScanner.nextLine().split(";");
            if (regionParams.length == 3) {
                this.latitude = Double.parseDouble(regionParams[0].trim());
                this.longitude = Double.parseDouble(regionParams[1].trim());
                this.radius = Double.parseDouble(regionParams[2].trim());
            } else if (regionParams[0].equals("h")) {
                helpUser(consoleScanner);
            } else {
                System.out.println("Invalid input format for Region filter. Expected input is (latitude; longitude; radius).");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid type of input. Expected input is a decimal number.");
        } catch (Exception e) {
            System.out.println("Unexpected error please try again.");
        }
    }

    /**
     * Führt den Filter auf die gegebene Liste von Meteoriten aus und gibt die gefilterte Liste zurück.
     * Nur Meteoriten, die sich innerhalb des angegebenen Radius um die geografische Koordinate befinden, werden beibehalten.
     *
     * @param meteorites Die Liste der Meteoriten, die gefiltert werden sollen.
     * @return Eine neue Liste von Meteoriten, die innerhalb des angegebenen Radius liegen.
     */
    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(this::isWithinRadius)
                .collect(Collectors.toList());
    }

    /**
     * Überprüft, ob ein Meteorit innerhalb des angegebenen Radius liegt.
     *
     * @param meteorite Der zu überprüfende Meteorit.
     * @return True, wenn der Meteorit innerhalb des Radius liegt, sonst false.
     */
    private boolean isWithinRadius(Meteorite meteorite) {
        double lat = meteorite.getReclat();
        double lon = meteorite.getReclong();
        double distance = haversine(latitude, longitude, lat, lon);
        return distance <= radius;
    }

    /**
     * Berechnet die Entfernung zwischen zwei geografischen Punkten mithilfe der Haversine-Formel.
     *
     * @param lat1 Die Breite des ersten Punkts.
     * @param lon1 Die Länge des ersten Punkts.
     * @param lat2 Die Breite des zweiten Punkts.
     * @param lon2 Die Länge des zweiten Punkts.
     * @return Die Entfernung in Kilometern.
     */
    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Radius der Erde in Kilometern
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    /**
     * Gibt dem Benutzer Hilfestellung zur Eingabe der Region-Parameter.
     *
     * @param consoleScanner Ein Scanner-Objekt zum Lesen der Benutzereingabe.
     */
    @Override
    public void helpUser(Scanner consoleScanner) {
        System.out.println("Here you can enter the latitude, as well as the longitude of the region, where the meteor hit.");
        System.out.println("Both should be a decimal number. The last parameter is the radius in which the meteor should lie (km)." +
                " This should also be a decimal number.");
        System.out.println();
        configure(consoleScanner);
    }
}
