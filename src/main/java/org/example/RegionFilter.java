package org.example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Die RegionFilter-Klasse filtert Meteoriten basierend auf ihrer Position innerhalb eines bestimmten Radius um eine gegebene geographische Koordinate.
 */
public class RegionFilter implements Filter {
    private double latitude;
    private double longitude;
    private double radius;

    /**
     * Konstruktor, der die zentrale Koordinate und den Radius für den Filter festlegt.
     *
     * @param latitude Die geografische Breite des Zentrums.
     * @param longitude Die geografische Länge des Zentrums.
     * @param radius Der Radius um die zentrale Koordinate.
     */
    public RegionFilter(double latitude, double longitude, double radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    /**
     * Führt den Filter aus und gibt eine Liste der gefilterten Meteoriten zurück.
     *
     * @param meteorites Die Liste der Meteoriten, die gefiltert werden soll.
     * @return Eine Liste der Meteoriten, die sich innerhalb des angegebenen Radius befinden.
     */
    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        // Filtert die Meteoriten basierend auf ihrer Position innerhalb des gegebenen Radius
        return meteorites.stream()
                .filter(m -> isWithinRadius(m))
                .collect(Collectors.toList());
    }

    /**
     * Prüft, ob ein Meteorit sich innerhalb des festgelegten Radius befindet.
     *
     * @param meteorite Der zu überprüfende Meteorit.
     * @return true, wenn der Meteorit sich innerhalb des Radius befindet, andernfalls false.
     */
    private boolean isWithinRadius(Meteorite meteorite) {
        if (meteorite.getReclat() == 0.0 || meteorite.getReclong() == 0.0) {
            return false; // Falls keine gültigen Koordinaten vorhanden sind, wird false zurückgegeben.
        }
        double lat = meteorite.getReclat();
        double lon = meteorite.getReclong();
        double distance = haversine(latitude, longitude, lat, lon);
        return distance <= radius;
    }

    /**
     * Berechnet die Entfernung zwischen zwei geografischen Punkten mithilfe der Haversine-Formel.
     *
     * @param lat1 Die geografische Breite des ersten Punkts.
     * @param lon1 Die geografische Länge des ersten Punkts.
     * @param lat2 Die geografische Breite des zweiten Punkts.
     * @param lon2 Die geografische Länge des zweiten Punkts.
     * @return Die Entfernung zwischen den beiden Punkten in Kilometern.
     */
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
