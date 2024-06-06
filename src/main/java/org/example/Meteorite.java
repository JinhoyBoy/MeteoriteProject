package org.example;

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

    public Meteorite(String name, double reclat, double reclong) {
        this.name = name;
        this.reclat = reclat;
        this.reclong = reclong;
    }

    // Getter und Setter Methoden

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

    public double getMass() {
        return mass != null ? mass : 0.0;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public double getReclat() {
        return reclat != null ? reclat : 0.0;
    }

    public void setReclat(Double reclat) {
        this.reclat = reclat;
    }

    public double getReclong() {
        return reclong != null ? reclong : 0.0;
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
            return latitude != null ? latitude : "0.0";
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude != null ? longitude : "0.0";
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
