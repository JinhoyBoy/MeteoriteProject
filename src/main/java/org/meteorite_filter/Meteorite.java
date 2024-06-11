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

    // Getter und Setter Methoden
    public String getId() {
        return id != null ? id : "Unknown";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name != null ? name : "Unknown";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    // Weitere Getter und Setter Methoden für die anderen Felder

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

    public double getMass() {return mass != null ? mass : Double.NaN;}

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public double getReclat() {
        return reclat != null ? reclat : Double.NaN;
    }

    public void setReclat(Double reclat) {
        this.reclat = reclat;
    }

    public double getReclong() {
        return reclong != null ? reclong : Double.NaN;
    }

    public void setReclong(Double reclong) {
        this.reclong = reclong;
    }

    public String getRecclass() {
        return recclass != null ? recclass : "Unknown";
    }

    public void setRecclass(String recclass) {
        this.recclass = recclass;
    }

    public String getNametype() {
        return nametype != null ? nametype : "Unknown";
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    public String getFall() {
        return fall != null ? fall : "Unknown";
    }

    public void setFall(String fall) {
        this.fall = fall;
    }

    public Geolocation getGeolocation() {
        return geolocation != null ? geolocation : new Geolocation();
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }


    // Innere Klasse für GeoLocation
    public class Geolocation {
        private String latitude;
        private String longitude;

        // Getter und Setter Methoden

        public String getLatitude() {
            return latitude != null ? latitude : "NaN";
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude != null ? longitude : "NaN";
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
