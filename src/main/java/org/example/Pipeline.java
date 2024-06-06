package org.example;


import java.util.List;

public class Pipeline {
    private List<Filter> filters;

    public Pipeline(List<Filter> filters) {
        this.filters = filters;
    }

    public void process(String inputFilePath, String outputFilePath) {
        List<Meteorite> meteorites = InputFilter.read(inputFilePath);

        for (Filter filter : filters) {
            meteorites = filter.execute(meteorites);
        }

        OutputFilter.write(meteorites, outputFilePath);
    }
}


/*
public class Pipeline {
    private List<Filter> filters;

    public Pipeline() {
        filters = new ArrayList<>();
        loadConfigFromConsole();
    }

    private void loadConfigFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to apply region filter? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter region (latitude,longitude,radius):");
            String[] params = scanner.nextLine().split(",");
            double lat = Double.parseDouble(params[0]);
            double lon = Double.parseDouble(params[1]);
            double radius = Double.parseDouble(params[2]);
            filters.add(new RegionFilter(lat, lon, radius));
        }

        System.out.println("Would you like to apply mass filter? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter mass range (min,max):");
            String[] params = scanner.nextLine().split(",");
            double minMass = Double.parseDouble(params[0]);
            double maxMass = Double.parseDouble(params[1]);
            filters.add(new MassFilter(minMass, maxMass));
        }

        System.out.println("Would you like to apply classification filter? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter classifications (comma separated):");
            String[] classifications = scanner.nextLine().split(",");
            filters.add(new ClassificationFilter(classifications));
        }

        System.out.println("Would you like to apply year filter? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Enter year range (min,max):");
            String[] params = scanner.nextLine().split(",");
            int startYear = Integer.parseInt(params[0]);
            int endYear = Integer.parseInt(params[1]);
            filters.add(new YearFilter(startYear, endYear));
        }

        System.out.println("You can see the result in output.json");
    }

    public void process(String inputFilePath, String outputFilePath) {
        List<Meteorite> meteorites = InputFilter.read(inputFilePath);

        for (Filter filter : filters) {
            meteorites = filter.execute(meteorites);
        }

        OutputFilter.write(meteorites, outputFilePath);
    }
}
*/