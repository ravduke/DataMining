package pl.edu.agh.ftj.datamining.gui.server;

import javax.xml.bind.annotation.*;
import weka.core.Capabilities;
import weka.core.DistanceFunction;
import weka.core.Instances;

/**
 * Klasa obiektu przechowującego dane wyprodukowane przez algorytmy Weki.
 * Obiekt ten będzie zwracany do silnika.
 * @author Bartłomiej Wojas
 * @version 0.8.0
 */
@XmlRootElement
public class WekaAnswer {
    /**
     * Typ algorytmu jaki ma zostac uzyty. Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
     */

    private int algorithmType;

    /**
     * Nazwa użytego algorytmu.
     */
    private String algorithmName;

    /**
     * Tablica indeksów pozwalających powiązać środki klastrów z poszczególnymi instancjami.
     */
    private int[] assignments;

    /**
     * Standardowe możliwości jakie posiada wybrany typ algorytmu.
     */
    private Capabilities capabilities;

    /**
     * Zbiór instancji będących środkami wszystkich wyznaczonych klastrów.
     */
    private Instances clusterCentroids;

    /**
     * Liczba częstotliwości występowania wartości dla poszczególnych atrybutów.
     */
    private int[][][] clusterNominalCounts;

    /**
     * Tablica z liczbami instancji w klastrach.
     */
    private int[] clusterSizes;

    /**
     * Odchylenia standardowe atrybutow numerycznych w klastrach.
     */
    private Instances clusterStandardDevs;

    /**
     * Obiekt z funkcja dystansu.
     */
    private DistanceFunction distanceFunction;

    /**
     * Maksymalna liczba iteracji.
     */
    private int maxIterations;

    /**
     * Liczba klastrow do wygenerowania.
     */
    private int numClusters;

    /**
     * Opcje wg. których działał algorytm.
     */
    private String[] options;

    /**
     * �?ańcuch z rewizją.
     */
    private String revision;

    /**
     * Blad kwadratowy. NaN jesli jest uzywana szybka kalkulacja dystansow.
     */
    private double squaredError;

    /**
     * Liczba klastrów.
     */
    private int numberOfClusters;

    /**
     * Poprzedniki[priors](?) klastrów
     */
    private double[] clusterPriors;

    /**
     * Rozkłady normalne dla modeli klastra.
     */
    private double[][][] clusterModelsNumericAtts;

    /**
     * Minimalne dopuszczalne odchylenie standardowe.
     */
    private double minStdDev;

    /**
    * Zwraca tablice indeksow pozwalajacych powiazac srodki klastrow z poszczegolnymi instancjami.
    * @return Tablica indeksów.
    */
    public int[] getAssignments() {
        return assignments;
    }

    /**
     * Ustawia tablice indeksow pozwalajacych powiazac srodki klastrow z poszczegolnymi instancjami.
     * @param assignments Tablica indeksów.
     */
    public void setAssignments(int[] assignments) {
        this.assignments = assignments;
    }

    /**
     * Zwraca obiekt z możliwościami jakie posiada użyty typ algorytmu.
     * @return Obiekt możliwości.
     */
    @XmlAnyElement
    public Capabilities getCapabilities() {
        return capabilities;
    }

    /**
     * Ustawia możliwości jakie posiadał użyty typ algorytmu.
     * @param capabilities Obiekt z możliwościami użytego algorytmu.
     */
    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    /**
    * Oblicza i zwraca srodki wszystkich znalezionych klastrow w postaci zbioru instacji.
    * @return Zbior instancji bedacych srodkami wszystkich wyznaczonych klastrow.
    */
     @XmlAnyElement
    public Instances getClusterCentroids() {
        return clusterCentroids;
    }

    /**
     * Ustawia obiekt ze środkami klastrów.
     * @param clusterCentroids Instacje będące środkami klastrów.
     */
    public void setClusterCentroids(Instances clusterCentroids) {
        this.clusterCentroids = clusterCentroids;
    }

    /**
    * Dla kazdego klastra zwraca liczbe czestotliwosci wystepowania wartosci dla poszczegolnych atrybutow.
    * @return Liczby czestotliwosci.
    */
    public int[][][] getClusterNominalCounts() {
        return clusterNominalCounts;
    }

    /**
     * Ustawia liczbe czestotliwosci wystepowania wartosci dla poszczegolnych atrybutow.
     * @param clusterNominalCounts Liczby czestotliwosci.
     */
    public void setClusterNominalCounts(int[][][] clusterNominalCounts) {
        this.clusterNominalCounts = clusterNominalCounts;
    }

    /**
    * Zwraca tablice, ktorej elementy to liczby instancji w kazdym z klastrow.
    * @return Tablica z liczbami instancji w klastrach.
    */
    public int[] getClusterSizes() {
        return clusterSizes;
    }

    /**
     * Ustawia tablice, ktorej elementy to liczby instancji w kazdym z klastrow.
     * @param clusterSizes tablica z liczbami instancji.
     */
    public void setClusterSizes(int[] clusterSizes) {
        this.clusterSizes = clusterSizes;
    }

    /**
    * Zwraca odchylenia standardowe atrybutow numerycznych w kazdym klastrze.
    * @return Odchylenia standardowe atrybutow numerycznych w klastrach
    */
     @XmlAnyElement
    public Instances getClusterStandardDevs() {
        return clusterStandardDevs;
    }

    /**
     * Ustawia odchylenia standardowe atrybutow numerycznych w kazdym klastrze.
     * @param clusterStandardDevs Odchylenia standardowe atrybutow numerycznych w klastrach.
     */
    public void setClusterStandardDevs(Instances clusterStandardDevs) {
        this.clusterStandardDevs = clusterStandardDevs;
    }

    /**
    * Pobiera funkcje odleglosci, ktora jest aktualnie w uzyciu.
    * @return Obiekt zawierajacy m.in. funkcje dystansu, wszystkie instancje, a takze pozwalajacy na obliczenie odleglosci miedzy poszczegolnymi instancjami.
    */
     @XmlAnyElement
    public DistanceFunction getDistanceFunction() {
        return distanceFunction;
    }

    /**
     * Ustawia własną funkcję obliczającą odległość między klastrami.
     * @param distanceFunction Obiekt z funkcją dystansu.
     */
    public void setDistanceFunction(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
    }

    /**
    * Zwraca maksymalna liczbe interacji jakie moga byc wykonane.
    * @return Maksymalna liczba iteracji.
    */
    public int getMaxIterations() {
        return maxIterations;
    }

    /**
     * Ustawia maksymalna liczbe interacji jakie moga byc wykonane.
     * @param maxIterations Maksymalna liczba iteracji.
     */
    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    /**
    * Zwraca liczbę klastrów do wygenerowania.
    * @return Liczba klastrow do wygenerowania.
    */
    public int getNumClusters() {
        return numClusters;
    }

    /**
     * Ustawia liczbę klastrów do wygenerowania.
     * @param numClusters Liczba klastrów do wygenerowania.
     */
    public void setNumClusters(int numClusters) {
        this.numClusters = numClusters;
    }

    /**
     * Zwraca tablicę opcji, wg. których funkcjonował algorytm.
     * @return Tablica opcji algorytmu.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Ustawia tablicę opcji, wg. których funkcjonował algorytm.
     * @param options Tablica opcji algorytmu.
     */
    public void setOptions(String[] options) {
        this.options = options;
    }

    /**
    * Zwraca lancuch z rewizja.
    * @return Rewizja.
    */
    public String getRevision() {
        return revision;
    }

    /**
     * Ustawia łańcuch z rewizją.
     * @param revision
     */
    public void setRevision(String revision) {
        this.revision = revision;
    }

    /**
     * Zwraca typ algorytmu jaki zostal uzyty.
     * Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
     * @return Typ algorytmu.
     */
    public int getAlgorithmType() {
        return algorithmType;
    }

    /**
     * Ustawia typ algorytmu jaki został użyty.
     * @param algorithmType Typ algorytmu.
     */
    public void setAlgorithmType(int algorithmType) {
        this.algorithmType = algorithmType;
    }

    /**
    * Zwraca liczbe wyznaczonych klastrow.
    * @return Liczba klastrow.
    */
    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    /**
     * Ustawia liczbę wyznaczonych klastrów.
     * @param numberOfClusters Liczba klastrów.
     */
    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    /**
    * Zwraca blad kwadratowy dla wszystkich klastrow.
    * @return Blad kwadratowy. NaN jesli jest uzywana szybka kalkulacja dystansow.
    */
    public double getSquaredError() {
        return squaredError;
    }

    /**
     * Ustawia blad kwadratowy dla wszystkich klastrow.
     * @param squaredError Błąd kwadratowy dla wszystkich klastrów.
     */
    public void setSquaredError(double squaredError) {
        this.squaredError = squaredError;
    }

    /**
     * Zwraca rozkłady normalne dla modeli klastra.
     * @return Rozkłady normalne dla modeli klastra.
     */
    public double[][][] getClusterModelsNumericAtts() {
        return clusterModelsNumericAtts;
    }

    /**
     * Ustawia rozkłady normalne dla modeli klastra.
     * @param clusterModelsNumericAtts Rozkłady normalne dla modeli klastra.
     */
    public void setClusterModelsNumericAtts(double[][][] clusterModelsNumericAtts) {
        this.clusterModelsNumericAtts = clusterModelsNumericAtts;
    }

    /**
     * Zwraca poprzedniki[priors](?) klastra.
     * @return Poprzedniki klastra.
     */
    public double[] getClusterPriors() {
        return clusterPriors;
    }

    /**
     * poprzedniki[priors](?) klastra.
     * @param clusterPriors Poprzedniki klastra.
     */
    public void setClusterPriors(double[] clusterPriors) {
        this.clusterPriors = clusterPriors;
    }

    /**
     * Zwraca minimalne dopuszczalne odchylenie standardowe.
     * @return  Minimalne dopuszczalne odchylenie standardowe.
     */
    public double getMinStdDev() {
        return minStdDev;
    }

    /**
     * Ustawia minimalne dopuszczalne odchylenie standardowe.
     * @param minStdDev Minimalne dopuszczalne odchylenie standardowe.
     */
    public void setMinStdDev(double minStdDev) {
        this.minStdDev = minStdDev;
    }

    /**
     * Zwraca łańcuch z informacją o typie algorytmu i jego nazwie.
     * @return �?ańcuch z informacją o algorytmie.
     */
    @Override
    public String toString() {
        return "Algorithm: " + algorithmType + "; Name: " + getAlgorithmName();
    }

    /**
     * Ustawia nazwę algorytmu.
     * @param algorithmName Nazwa algorytmu.
     */
    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    /**
     * @return the algorithmName
     */
    public String getAlgorithmName() {
        return algorithmName;
    }
}
