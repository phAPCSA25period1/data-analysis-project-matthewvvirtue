/**
 * Represents one row from the StateData2020-CDC-Census CSV dataset.
 * Contains information about health statistics for US states in 2016.
 */
public class Data {

    private String state;
    private int population;
    private double uninsuredPercentage;
    private double firearmDeathRate;
    private double drugOverdoseRate;

    /**
     * Constructor that initializes all StateData attributes.
     *
     * @param state the name of the state
     * @param population the 2016 population
     * @param uninsuredPercentage the percentage of uninsured people
     * @param firearmDeathRate the firearm death rate per 100K population
     * @param drugOverdoseRate the drug overdose death rate per 100K population
     */
    public Data(String state, int population, double uninsuredPercentage,
                double firearmDeathRate, double drugOverdoseRate) {
        this.state = state;
        this.population = population;
        this.uninsuredPercentage = uninsuredPercentage;
        this.firearmDeathRate = firearmDeathRate;
        this.drugOverdoseRate = drugOverdoseRate;
    }

    /**
     * Gets the state name.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the population.
     *
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Gets the uninsured percentage.
     *
     * @return the uninsured percentage
     */
    public double getUninsuredPercentage() {
        return uninsuredPercentage;
    }

    /**
     * Gets the firearm death rate per 100K population.
     *
     * @return the firearm death rate
     */
    public double getFirearmDeathRate() {
        return firearmDeathRate;
    }

    /**
     * Gets the drug overdose death rate per 100K population.
     *
     * @return the drug overdose rate
     */
    public double getDrugOverdoseRate() {
        return drugOverdoseRate;
    }

    /**
     * Returns a string representation of the state data.
     *
     * @return formatted string with state information
     */
    @Override
    public String toString() {
        return state + " | Population: " + population + 
               " | Uninsured: " + uninsuredPercentage + "%" +
               " | Firearm Death Rate: " + firearmDeathRate +
               " | Drug Overdose Rate: " + drugOverdoseRate;
    }
}