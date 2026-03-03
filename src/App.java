import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main application for analyzing state health data from the 2020 CDC Census.
 *
 * This program reads the StateData2020-CDC-Census CSV file and analyzes
 * health statistics including population, insurance rates, fatality rates,
 * and drug overdose data across US states.
 */
public class App {

    public static void main(String[] args) {
        
        // Path to the CSV file
        File file = new File("src/StateData2020-CDC-Census.csv");

        // Create an array to store state data (50 states + DC)
        Data[] stateData = new Data[51];
        int count = 0;

        // Read and parse the CSV file
        try {
            Scanner scanner = new Scanner(file);
            
            // Skip the header row
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Read each data row
            while (scanner.hasNextLine() && count < stateData.length) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                // Parse the data from the CSV columns
                String state = parts[0];
                int population = Integer.parseInt(parts[1]);
                double uninsuredPercentage = Double.parseDouble(parts[2]);
                double firearmDeathRate = Double.parseDouble(parts[3]);
                double drugOverdoseRate = Double.parseDouble(parts[5]);

                // Create a Data object and add to array
                stateData[count] = new Data(state, population, uninsuredPercentage,
                                           firearmDeathRate, drugOverdoseRate);
                count++;
            }
            scanner.close();

            // Display results
            System.out.println("=== State Health Data Analysis ===\n");
            System.out.println("Total states loaded: " + count + "\n");

            // Find and display state with highest firearm death rate
            Data highestFirearm = findMaxFirearmDeathRate(stateData, count);
            System.out.println("Highest Firearm Death Rate:");
            System.out.println(highestFirearm.getState() + ": " + 
                             highestFirearm.getFirearmDeathRate() + " per 100K");

            // Find and display state with highest drug overdose rate
            Data highestDrugOverdose = findMaxDrugOverdoseRate(stateData, count);
            System.out.println("\nHighest Drug Overdose Rate:");
            System.out.println(highestDrugOverdose.getState() + ": " + 
                             highestDrugOverdose.getDrugOverdoseRate() + " per 100K");

            // Find and display state with lowest uninsured percentage
            Data lowestUninsured = findMinUninsuredPercentage(stateData, count);
            System.out.println("\nLowest Uninsured Percentage:");
            System.out.println(lowestUninsured.getState() + ": " + 
                             lowestUninsured.getUninsuredPercentage() + "%");

            // Calculate and display average firearm death rate
            double avgFirearm = calculateAverageFirearmDeathRate(stateData, count);
            System.out.println("\nAverage Firearm Death Rate: " + 
                             Math.round(avgFirearm * 100.0) / 100.0 + " per 100K");

            // Calculate and display average drug overdose rate
            double avgDrugOverdose = calculateAverageDrugOverdoseRate(stateData, count);
            System.out.println("Average Drug Overdose Rate: " + 
                             Math.round(avgDrugOverdose * 100.0) / 100.0 + " per 100K");

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
            e.printStackTrace();
        }
    }

    /**
     * Finds the state with the highest firearm death rate.
     *
     * @param data the array of state data
     * @param count the number of valid entries in the array
     * @return the Data object with the highest firearm death rate
     */
    public static Data findMaxFirearmDeathRate(Data[] data, int count) {
        Data maxState = data[0];
        for (int i = 1; i < count; i++) {
            if (data[i].getFirearmDeathRate() > maxState.getFirearmDeathRate()) {
                maxState = data[i];
            }
        }
        return maxState;
    }

    /**
     * Finds the state with the highest drug overdose rate.
     *
     * @param data the array of state data
     * @param count the number of valid entries in the array
     * @return the Data object with the highest drug overdose rate
     */
    public static Data findMaxDrugOverdoseRate(Data[] data, int count) {
        Data maxState = data[0];
        for (int i = 1; i < count; i++) {
            if (data[i].getDrugOverdoseRate() > maxState.getDrugOverdoseRate()) {
                maxState = data[i];
            }
        }
        return maxState;
    }

    /**
     * Finds the state with the lowest uninsured percentage.
     *
     * @param data the array of state data
     * @param count the number of valid entries in the array
     * @return the Data object with the lowest uninsured percentage
     */
    public static Data findMinUninsuredPercentage(Data[] data, int count) {
        Data minState = data[0];
        for (int i = 1; i < count; i++) {
            if (data[i].getUninsuredPercentage() < minState.getUninsuredPercentage()) {
                minState = data[i];
            }
        }
        return minState;
    }

    /**
     * Calculates the average firearm death rate across all states.
     *
     * @param data the array of state data
     * @param count the number of valid entries in the array
     * @return the average firearm death rate
     */
    public static double calculateAverageFirearmDeathRate(Data[] data, int count) {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += data[i].getFirearmDeathRate();
        }
        return sum / count;
    }

    /**
     * Calculates the average drug overdose rate across all states.
     *
     * @param data the array of state data
     * @param count the number of valid entries in the array
     * @return the average drug overdose rate
     */
    public static double calculateAverageDrugOverdoseRate(Data[] data, int count) {
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += data[i].getDrugOverdoseRate();
        }
        return sum / count;
    }
}