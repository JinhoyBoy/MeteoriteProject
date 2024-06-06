package org.example;

public class Meteorite {
    private String name;
    private String id;
    private String nametype;
    private String recclass;
    private double mass;
    private String fall;
    private String year;
    private String reclat;
    private String reclong;
    private GeoLocation geolocation;

    // Getter und Setter Methoden

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNametype() {
        return nametype;
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    public String getRecclass() {
        return recclass;
    }

    public void setRecclass(String recclass) {
        this.recclass = recclass;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getFall() {
        return fall;
    }

    public void setFall(String fall) {
        this.fall = fall;
    }
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

    public void setYear(String year) {
        this.year = year;
    }

    public String getReclat() {
        return reclat;
    }

    public void setReclat(String reclat) {
        this.reclat = reclat;
    }

    public String getReclong() {
        return reclong;
    }

    public void setReclong(String reclong) {
        this.reclong = reclong;
    }

    public GeoLocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeoLocation geolocation) {
        this.geolocation = geolocation;
    }

    // Innere Klasse für GeoLocation
    public static class GeoLocation {
        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
