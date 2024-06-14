# Meteorite Data Processor

## Description
This SWE project is a program that reads meteorite data from a JSON file and uses various evaluation modules that can be combined in any order. 
The available evaluation modules and their configuration are to be defined via a configuration file.

## Usage Instructions

To run the program, you need a Java environment or IDE (e.g., IntelliJ, Eclipse). Ensure that the main class is executed.

1. After starting the program, you will be prompted to select the filters to apply to the meteorite data. The available filters will be displayed.
2. Enter the desired filters separated by semicolons (`;`). If there are any incorrect inputs, the errors will be detected, and an appropriate error message will be generated.
3. After selecting the filters, you will be prompted to enter the required parameters for each filter. You can enter "h" each time for help with the input.
4. After configuration and filter execution, the filtered meteorite data will be written to the output file (`src/main/resources/output.json`).

## Adding a Filter Module

To add a new filter module to your meteorite data processing program, follow these steps:

1. Open the `config.json` file located in the `src/main/resources/` directory.
2. Add a new entry for the filter module (e.g., `{ "name": "Placeholder" }`).
3. Create a new filter class that implements the `Filter` interface. The name must follow the format `PlaceholderFilter` (e.g., `MassFilter`).
4. Implement all methods such as `configure`, `execute`, and `helpUser`.
