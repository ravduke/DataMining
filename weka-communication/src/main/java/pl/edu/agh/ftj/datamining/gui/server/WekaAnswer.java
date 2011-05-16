package pl.edu.agh.ftj.datamining.gui.server;

import javax.xml.bind.annotation.*;
import weka.core.Capabilities;
import weka.core.DistanceFunction;
import weka.core.Instances;

/**
 * Klasa obiektu przechowującego dane wyprodukowane przez algorytmy Weki. Obiekt ten będzie zwracany do silnika.
 * @author  Bartłomiej Wojas
 * @version  0.8.0
 */
@XmlRootElement
public class WekaAnswer {
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
	 * Standardowe możliwości jakie posiada wybrany typ algorytmu.
	 * @uml.property  name="capabilities"
	 */
    private Capabilities capabilities;

    /**
	 * Zbiór instancji będących środkami wszystkich wyznaczonych klastrów.
	 * @uml.property  name="clusterCentroids"
	 */
    private Instances clusterCentroids;

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
	 * Odchylenia standardowe atrybutow numerycznych w klastrach.
	 * @uml.property  name="clusterStandardDevs"
	 */
    private Instances clusterStandardDevs;

    /**
	 * Obiekt z funkcja dystansu.
	 * @uml.property  name="distanceFunction"
	 */
    private DistanceFunction distanceFunction;

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
	 * Zwraca obiekt z możliwościami jakie posiada użyty typ algorytmu.
	 * @return  Obiekt możliwości.
	 * @uml.property  name="capabilities"
	 */
    @XmlAnyElement
    public Capabilities getCapabilities() {
        return capabilities;
    }

    /**
	 * Ustawia możliwości jakie posiadał użyty typ algorytmu.
	 * @param capabilities  Obiekt z możliwościami użytego algorytmu.
	 * @uml.property  name="capabilities"
	 */
    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    /**
	 * Oblicza i zwraca srodki wszystkich znalezionych klastrow w postaci zbioru instacji.
	 * @return  Zbior instancji bedacych srodkami wszystkich wyznaczonych klastrow.
	 * @uml.property  name="clusterCentroids"
	 */
     @XmlAnyElement
    public Instances getClusterCentroids() {
        return clusterCentroids;
    }

    /**
	 * Ustawia obiekt ze środkami klastrów.
	 * @param clusterCentroids  Instacje będące środkami klastrów.
	 * @uml.property  name="clusterCentroids"
	 */
    public void setClusterCentroids(Instances clusterCentroids) {
        this.clusterCentroids = clusterCentroids;
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
	 * Zwraca odchylenia standardowe atrybutow numerycznych w kazdym klastrze.
	 * @return  Odchylenia standardowe atrybutow numerycznych w klastrach
	 * @uml.property  name="clusterStandardDevs"
	 */
     @XmlAnyElement
    public Instances getClusterStandardDevs() {
        return clusterStandardDevs;
    }

    /**
	 * Ustawia odchylenia standardowe atrybutow numerycznych w kazdym klastrze.
	 * @param clusterStandardDevs  Odchylenia standardowe atrybutow numerycznych w klastrach.
	 * @uml.property  name="clusterStandardDevs"
	 */
    public void setClusterStandardDevs(Instances clusterStandardDevs) {
        this.clusterStandardDevs = clusterStandardDevs;
    }

    /**
	 * Pobiera funkcje odleglosci, ktora jest aktualnie w uzyciu.
	 * @return  Obiekt zawierajacy m.in. funkcje dystansu, wszystkie instancje, a takze pozwalajacy na obliczenie odleglosci miedzy poszczegolnymi instancjami.
	 * @uml.property  name="distanceFunction"
	 */
     @XmlAnyElement
    public DistanceFunction getDistanceFunction() {
        return distanceFunction;
    }

    /**
	 * Ustawia własną funkcję obliczającą odległość między klastrami.
	 * @param distanceFunction  Obiekt z funkcją dystansu.
	 * @uml.property  name="distanceFunction"
	 */
    public void setDistanceFunction(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
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
