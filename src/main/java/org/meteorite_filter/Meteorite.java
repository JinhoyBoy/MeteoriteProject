package org.meteorite_filter;

/**
 * Die Klasse repräsentiert einen Meteoriten mit verschiedenen Attributen wie Name, Masse, Jahr usw.
 */
public class Meteorite {
    private String name;
    private String id;
    private String nametype;
    private String recclass;
    private Double mass;
    private String fall;
    private String year;
    private Double reclat;
    private Double reclong;
    private Geolocation geolocation;

    /**
     * Konstruktor für die Klasse Meteorite. (Für den RegionFilterTest)
     *
     * @param name Der Name des Meteoriten.
     * @param reclat Die geografische Breite des Meteoritenfundorts.
     * @param reclong Die geografische Länge des Meteoritenfundorts.
     */
    public Meteorite(String name, double reclat, double reclong) {
        this.name = name;
        this.reclat = reclat;
        this.reclong = reclong;
    }

    // Getter Methoden

    public String getName() {
        return name != null ? name : "Unknown";
    }

    public String getId() {
        return id != null ? id : "Unknown";
    }

    public String getYear() {
        return year;
    }

    // Getter Methode, um Jahr als Integer zu bekommen

    public int getYearAsInt() {
        if (year != null && year.length() >= 4) {
            try {
                return Integer.parseInt(year.substring(0, 4));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return -1; // Ungültiges Jahr
            }
        }
        return -1; // Ungültiges Jahr
    }

    // Weitere Getter Methoden für die anderen Felder

    public double getMass() {return mass != null ? mass : Double.NaN;}

    public double getReclat() {
        return reclat != null ? reclat : Double.NaN;
    }

    public double getReclong() {
        return reclong != null ? reclong : Double.NaN;
    }

    public String getRecclass() {
        return recclass != null ? recclass : "Unknown";
    }

    public String getNametype() {
        return nametype != null ? nametype : "Unknown";
    }

    public String getFall() {
        return fall != null ? fall : "Unknown";
    }

    public Geolocation getGeolocation() {
        return geolocation != null ? geolocation : new Geolocation();
    }


    // Innere Klasse für GeoLocation
    public class Geolocation {
        private String latitude;
        private String longitude;

        // Getter Methoden

        public String getLatitude() {
            return latitude != null ? latitude : "NaN";
        }

        public String getLongitude() {
            return longitude != null ? longitude : "NaN";
        }
    }
}
