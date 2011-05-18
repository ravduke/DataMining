package pl.edu.agh.ftj.datamining.gui.shared;

import java.io.Serializable;


public class WekaAnswerDTO implements Serializable {
    /**
	 * Typ algorytmu jaki ma zostac uzyty. Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
	 * @uml.property  name="algorithmType"
	 */

    private int algorithmType;

    /**
	 * Nazwa użytego algorytmu.
	 * @uml.property  name="algorithmName"
	 */
    private String algorithmName;

    /**
	 * Tablica indeksów pozwalających powiązać środki klastrów z poszczególnymi instancjami.
	 * @uml.property  name="assignments"
	 */
    private int[] assignments;

    /**
	 * Liczba częstotliwości występowania wartości dla poszczególnych atrybutów.
	 * @uml.property  name="clusterNominalCounts"
	 */
    private int[][][] clusterNominalCounts;

    /**
	 * Tablica z liczbami instancji w klastrach.
	 * @uml.property  name="clusterSizes"
	 */
    private int[] clusterSizes;

    /**
	 * Maksymalna liczba iteracji.
	 * @uml.property  name="maxIterations"
	 */
    private int maxIterations;

    /**
	 * Liczba klastrow do wygenerowania.
	 * @uml.property  name="numClusters"
	 */
    private int numClusters;

    /**
	 * Opcje wg. których działał algorytm.
	 * @uml.property  name="options"
	 */
    private String[] options;

    /**
	 * Łańcuch z rewizją.
	 * @uml.property  name="revision"
	 */
    private String revision;

    /**
	 * Blad kwadratowy. NaN jesli jest uzywana szybka kalkulacja dystansow.
	 * @uml.property  name="squaredError"
	 */
    private double squaredError;

    /**
	 * Liczba klastrów.
	 * @uml.property  name="numberOfClusters"
	 */
    private int numberOfClusters;

    /**
	 * Poprzedniki[priors](?) klastrów
	 * @uml.property  name="clusterPriors"
	 */
    private double[] clusterPriors;

    /**
	 * Rozkłady normalne dla modeli klastra.
	 * @uml.property  name="clusterModelsNumericAtts"
	 */
    private double[][][] clusterModelsNumericAtts;

    /**
	 * Minimalne dopuszczalne odchylenie standardowe.
	 * @uml.property  name="minStdDev"
	 */
    private double minStdDev;

    /**
	 * Zwraca tablice indeksow pozwalajacych powiazac srodki klastrow z poszczegolnymi instancjami.
	 * @return  Tablica indeksów.
	 * @uml.property  name="assignments"
	 */
    public int[] getAssignments() {
        return assignments;
    }

    /**
	 * Ustawia tablice indeksow pozwalajacych powiazac srodki klastrow z poszczegolnymi instancjami.
	 * @param assignments  Tablica indeksów.
	 * @uml.property  name="assignments"
	 */
    public void setAssignments(int[] assignments) {
        this.assignments = assignments;
    }

    /**
	 * Dla kazdego klastra zwraca liczbe czestotliwosci wystepowania wartosci dla poszczegolnych atrybutow.
	 * @return  Liczby czestotliwosci.
	 * @uml.property  name="clusterNominalCounts"
	 */
    public int[][][] getClusterNominalCounts() {
        return clusterNominalCounts;
    }

    /**
	 * Ustawia liczbe czestotliwosci wystepowania wartosci dla poszczegolnych atrybutow.
	 * @param clusterNominalCounts  Liczby czestotliwosci.
	 * @uml.property  name="clusterNominalCounts"
	 */
    public void setClusterNominalCounts(int[][][] clusterNominalCounts) {
        this.clusterNominalCounts = clusterNominalCounts;
    }

    /**
	 * Zwraca tablice, ktorej elementy to liczby instancji w kazdym z klastrow.
	 * @return  Tablica z liczbami instancji w klastrach.
	 * @uml.property  name="clusterSizes"
	 */
    public int[] getClusterSizes() {
        return clusterSizes;
    }

    /**
	 * Ustawia tablice, ktorej elementy to liczby instancji w kazdym z klastrow.
	 * @param clusterSizes  tablica z liczbami instancji.
	 * @uml.property  name="clusterSizes"
	 */
    public void setClusterSizes(int[] clusterSizes) {
        this.clusterSizes = clusterSizes;
    }

    /**
	 * Zwraca maksymalna liczbe interacji jakie moga byc wykonane.
	 * @return  Maksymalna liczba iteracji.
	 * @uml.property  name="maxIterations"
	 */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
	 * Ustawia maksymalna liczbe interacji jakie moga byc wykonane.
	 * @param maxIterations  Maksymalna liczba iteracji.
	 * @uml.property  name="maxIterations"
	 */
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    /**
	 * Zwraca liczbę klastrów do wygenerowania.
	 * @return  Liczba klastrow do wygenerowania.
	 * @uml.property  name="numClusters"
	 */
    public int getNumClusters() {
        return numClusters;
    }

    /**
	 * Ustawia liczbę klastrów do wygenerowania.
	 * @param numClusters  Liczba klastrów do wygenerowania.
	 * @uml.property  name="numClusters"
	 */
    public void setNumClusters(int numClusters) {
        this.numClusters = numClusters;
    }

    /**
	 * Zwraca tablicę opcji, wg. których funkcjonował algorytm.
	 * @return  Tablica opcji algorytmu.
	 * @uml.property  name="options"
	 */
    public String[] getOptions() {
        return options;
    }

    /**
	 * Ustawia tablicę opcji, wg. których funkcjonował algorytm.
	 * @param options  Tablica opcji algorytmu.
	 * @uml.property  name="options"
	 */
    public void setOptions(String[] options) {
        this.options = options;
    }

    /**
	 * Zwraca lancuch z rewizja.
	 * @return  Rewizja.
	 * @uml.property  name="revision"
	 */
    public String getRevision() {
        return revision;
    }

    /**
	 * Ustawia łańcuch z rewizją.
	 * @param  revision
	 * @uml.property  name="revision"
	 */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
	 * Zwraca typ algorytmu jaki zostal uzyty. Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
	 * @return  Typ algorytmu.
	 * @uml.property  name="algorithmType"
	 */
    public int getAlgorithmType() {
        return algorithmType;
    }

    /**
	 * Ustawia typ algorytmu jaki został użyty.
	 * @param algorithmType  Typ algorytmu.
	 * @uml.property  name="algorithmType"
	 */
    public void setAlgorithmType(int algorithmType) {
        this.algorithmType = algorithmType;
    }

    /**
	 * Zwraca liczbe wyznaczonych klastrow.
	 * @return  Liczba klastrow.
	 * @uml.property  name="numberOfClusters"
	 */
    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    /**
	 * Ustawia liczbę wyznaczonych klastrów.
	 * @param numberOfClusters  Liczba klastrów.
	 * @uml.property  name="numberOfClusters"
	 */
    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    /**
	 * Zwraca blad kwadratowy dla wszystkich klastrow.
	 * @return  Blad kwadratowy. NaN jesli jest uzywana szybka kalkulacja dystansow.
	 * @uml.property  name="squaredError"
	 */
    public double getSquaredError() {
        return squaredError;
    }

    /**
	 * Ustawia blad kwadratowy dla wszystkich klastrow.
	 * @param squaredError  Błąd kwadratowy dla wszystkich klastrów.
	 * @uml.property  name="squaredError"
	 */
    public void setSquaredError(double squaredError) {
        this.squaredError = squaredError;
    }

    /**
	 * Zwraca rozkłady normalne dla modeli klastra.
	 * @return  Rozkłady normalne dla modeli klastra.
	 * @uml.property  name="clusterModelsNumericAtts"
	 */
    public double[][][] getClusterModelsNumericAtts() {
        return clusterModelsNumericAtts;
    }

    /**
	 * Ustawia rozkłady normalne dla modeli klastra.
	 * @param clusterModelsNumericAtts  Rozkłady normalne dla modeli klastra.
	 * @uml.property  name="clusterModelsNumericAtts"
	 */
    public void setClusterModelsNumericAtts(double[][][] clusterModelsNumericAtts) {
        this.clusterModelsNumericAtts = clusterModelsNumericAtts;
    }

    /**
	 * Zwraca poprzedniki[priors](?) klastra.
	 * @return  Poprzedniki klastra.
	 * @uml.property  name="clusterPriors"
	 */
    public double[] getClusterPriors() {
        return clusterPriors;
    }

    /**
	 * poprzedniki[priors](?) klastra.
	 * @param clusterPriors  Poprzedniki klastra.
	 * @uml.property  name="clusterPriors"
	 */
    public void setClusterPriors(double[] clusterPriors) {
        this.clusterPriors = clusterPriors;
    }

    /**
	 * Zwraca minimalne dopuszczalne odchylenie standardowe.
	 * @return   Minimalne dopuszczalne odchylenie standardowe.
	 * @uml.property  name="minStdDev"
	 */
    public double getMinStdDev() {
        return minStdDev;
    }

    /**
	 * Ustawia minimalne dopuszczalne odchylenie standardowe.
	 * @param minStdDev  Minimalne dopuszczalne odchylenie standardowe.
	 * @uml.property  name="minStdDev"
	 */
    public void setMinStdDev(double minStdDev) {
        this.minStdDev = minStdDev;
    }

    /**
     * Zwraca łańcuch z informacją o typie algorytmu i jego nazwie.
     * @return Łańcuch z informacją o algorytmie.
     */
    @Override
    public String toString() {
        return "Algorithm: " + algorithmType + "; Name: " + getAlgorithmName();
    }

    /**
	 * Ustawia nazwę algorytmu.
	 * @param algorithmName  Nazwa algorytmu.
	 * @uml.property  name="algorithmName"
	 */
    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    /**
	 * @return  the algorithmName
	 * @uml.property  name="algorithmName"
	 */
    public String getAlgorithmName() {
        return algorithmName;
    }
}