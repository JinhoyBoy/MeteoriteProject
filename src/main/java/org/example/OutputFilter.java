package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

/**
 * Die Klasse OutputFilter enthält eine Methode zum Schreiben einer Liste von Meteoriten in eine JSON-Datei.
 */
public class OutputFilter {

    private static final DecimalFormat DECIMAL_FORMAT;

    static {
        // Konfiguration des DecimalFormats mit US-DecimalFormatSymbols
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DECIMAL_FORMAT = new DecimalFormat("0.000000", symbols);
    }

    /**
     * Schreibt eine Liste von Meteoriten in eine formatierte JSON-Datei.
     *
     * @param meteorites Die Liste von Meteoriten, die geschrieben werden soll.
     * @param filePath   Der Pfad zur Ausgabedatei.
     */
    public static void write(List<Meteorite> meteorites, String filePath) {
        // Erstellen einer Gson-Instanz mit benutzerdefiniertem Serializer und Pretty-Printing-Einstellung
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Meteorite.class, new MeteoriteSerializer())
                .create();
        try (FileWriter writer = new FileWriter(filePath)) {
            // Konvertieren der Meteoritenliste in JSON und Schreiben in die Datei
            gson.toJson(meteorites, writer);
        } catch (IOException e) {
            e.printStackTrace(); // Fehlerbehandlung: Ausdrucken des Stacktraces
        }
    }

    /**
     * Der benutzerdefinierte Serializer für die Meteorite-Klasse, um reclat und reclong mit 6 Nachkommastellen zu formatieren.
     */
    private static class MeteoriteSerializer implements JsonSerializer<Meteorite> {

        @Override
        public JsonElement serialize(Meteorite meteorite, Type type, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", meteorite.getName());
            jsonObject.addProperty("id", meteorite.getId());
            jsonObject.addProperty("nametype", meteorite.getNametype());
            jsonObject.addProperty("recclass", meteorite.getRecclass());
            jsonObject.addProperty("mass", formatMass(meteorite.getMass()));
            jsonObject.addProperty("fall", meteorite.getFall());
            jsonObject.addProperty("year", meteorite.getYear());
            jsonObject.addProperty("reclat", DECIMAL_FORMAT.format(meteorite.getReclat()));
            jsonObject.addProperty("reclong", DECIMAL_FORMAT.format(meteorite.getReclong()));
            // Format geolocation as a nested object
            JsonObject geolocationObject = new JsonObject();
            geolocationObject.addProperty("latitude", meteorite.getGeolocation().getLatitude());
            geolocationObject.addProperty("longitude", meteorite.getGeolocation().getLongitude());
            jsonObject.add("geolocation", geolocationObject);
            return jsonObject;
        }
        private String formatMass(double mass) {
            if (mass == (long) mass) {
                return String.format("%d", (long) mass);
            } else {
                return String.format("%s", mass);
            }
        }
    }
}
