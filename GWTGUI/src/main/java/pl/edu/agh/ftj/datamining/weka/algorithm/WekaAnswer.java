package pl.edu.agh.ftj.datamining.weka.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.core.Attribute;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

/**
 * Klasa obiektu przechowuj??cego dane wyprodukowane przez algorytmy Weki.
 * Obiekt ten b??dzie zwracany do silnika.
 * @author Bart??omiej Wojas, Adrian Kremblewski, Szymon Skupie??
 * @version 0.9.7

 */
public class WekaAnswer implements Serializable {
    /**
     * Typ algorytmu jaki ma zostac uzyty. Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
     */
    private int algorithmType = -1;

    /**
     * Informacja o b????dach lub o poprawno??ci wykonanego algorytmu. Je??eli jest ok w info znajdzie si?? string 'ok'
     * je??eli b??d?? b????dy, tutaj znajdzie si?? wiadomo???? o napotkanym b????dzie. Reszta p??l b??dzie wtedy pusta.
     */
    private String info;

    /**
     * Przechowuje informacj?? o tym, czy obiekt WekaAnswer zosta?? poprawnie utworzony (warto???? true).
     * Je??li wyst??pi?? b????d (warto???? false) wtedy wszystkie pola klasy b??d?? puste.
     */
    private boolean correct = true;

    /**
     * Nazwa u??ytego algorytmu.
     */
    private String algorithmName = "";

    /**
     * Tablica indeks??w pozwalaj??cych powi??za?? ??rodki klastr??w z poszczeg??lnymi instancjami.
     */
    private int[] assignments = null;

//    /**
//     * Standardowe mo??liwo??ci jakie posiada wybrany typ algorytmu.
//     */
//    private Capabilities capabilities = null;

    /**
     * Zbi??r instancji b??d??cych ??rodkami wszystkich wyznaczonych klastr??w.
     */
    private String clusterCentroids = "";

    /**
     * Liczba cz??stotliwo??ci wyst??powania warto??ci dla poszczeg??lnych atrybut??w.
     */
    private int[][][] clusterNominalCounts = null;

    /**
     * Tablica z liczbami instancji w klastrach.
     */
    private int[] clusterSizes = null;

    /**
     * Odchylenia standardowe atrybutow numerycznych w klastrach.
     */
    private String clusterStandardDevs = null;

    /**
     * Przechowuje dane instancji dla obiektu funkcji dystansu.
     */
    private String instancesForDistanceFunction = null;

    /**
     * Przechowuje atrybuty dla obiektu funkcji dystansu.
     */
    private String attributeIndicesForDistanceFunction = null;

    /**
     * Przechowuje informacj?? dla obiektu funkcji dystansu dotycz??c?? indeks??w atrybut??w.
     */
    private boolean invertSelectionForDistanceFunction = false;

    /**
     * Przechowuje opcje dla obiektu funkcji dystnasu.
     */
    private String[] optionsForDistanseFunction = null;

    /**
     * Maksymalna liczba iteracji.
     */
    private int maxIterations = -1;

    /**
     * Liczba klastrow do wygenerowania.
     */
    private int numClusters = -1;

    /**
     * Opcje wg. kt???rych dzia???a algorytm.
     */
    private String[] options = null;

    /**
     * ???ancuch z rewizja
     */
    private String revision = null;

    /**
     * Blad kwadratowy. NaN jesli jest uzywana szybka kalkulacja dystansow.
     */
    private double squaredError = -1.;

    /**
     * Liczba klastr??w.
     */
    private int numberOfClusters = -1;

    /**
     * Poprzedniki[priors](?) klastr??w
     */
    private double[] clusterPriors = null;

    /**
     * Rozk??ady normalne dla modeli klastra.
     */
    private double[][][] clusterModelsNumericAtts = null;

    /**
     * Minimalne dopuszczalne odchylenie standardowe.
     */
    private double minStdDev = -1;

    /**
     *
     */
    private double acuity = -1.;

    /**
     *
     */
    private double cutoff = -1.;

    /**
     *
     */
    private String graph = null;

    /**
     *
     */
    private int graphType = -1;

//    /**
//     *
//     */
//    private SelectedTag linkType = null;

    /**
     *
     */
    private boolean printNewick = false;

    /**
     * Obiekt klasteryzatora.
     */
    private Clusterer clusterer = null;

    /**
     * Obiekt ewaluacji modelu.
     */
    private ClusterEvaluation eval = null;

    /**
     * Obiekt z instancjami do analizy.
     */
    private Instances data = null;

    ////////////////////////////////////////////////////////////////////////////

    /**
    * Zwraca tablice indeksow pozwalajacych powiazac srodki klastrow z poszczegolnymi instancjami.
    * @return Tablica indeks??w.
    */
    public int[] getAssignments() {
        return assignments;
    }

    /**
     * Ustawia tablice indeksow pozwalajacych powiazac srodki klastrow z poszczegolnymi instancjami.
     * @param assignments Tablica indeks??w.
     */
    public void setAssignments(int[] assignments) {
        this.assignments = assignments;
    }

//    /**
//     * Zwraca obiekt z mo??liwo??ciami jakie posiada u??yty typ algorytmu.
//     * @return Obiekt mo??liwo??ci.
//     */
//    public Capabilities getCapabilities() {
//        return capabilities;
//    }

//    /**
//     * Ustawia mo??liwo??ci jakie posiada?? u??yty typ algorytmu.
//     * @param capabilities Obiekt z mo??liwo??ciami u??ytego algorytmu.
//     */
//    public void setCapabilities(Capabilities capabilities) {
//        this.capabilities = capabilities;
//    }

    /**
    * Oblicza i zwraca srodki wszystkich znalezionych klastrow w postaci zbioru instacji.
    * @return Zbior instancji bedacych srodkami wszystkich wyznaczonych klastrow.
    */
    public Instances getClusterCentroids() {
        if(clusterCentroids != null) {
            BufferedReader reader = new BufferedReader(new StringReader(clusterCentroids));
            ArffReader arff = null;
            try {
                arff = new ArffReader(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arff.getData();
        } else {
            return null;
        }
    }

    /**
     * Ustawia obiekt ze ??rodkami klastr??w.
     * @param clusterCentroids Instacje b??d??ce ??rodkami klastr??w.
     */
    public void setClusterCentroids(Instances clusterCentroids) {
        if(clusterCentroids != null)
            this.clusterCentroids = clusterCentroids.toString();
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
    public Instances getClusterStandardDevs() {
        if(clusterStandardDevs != null) {
            BufferedReader reader = new BufferedReader(new StringReader(clusterStandardDevs));
            ArffReader arff = null;
            try {
                arff = new ArffReader(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return arff.getData();
        } else {
            return null;
        }
    }

    /**
     * Ustawia odchylenia standardowe atrybutow numerycznych w kazdym klastrze.
     * @param clusterStandardDevs Odchylenia standardowe atrybutow numerycznych w klastrach.
     */
    public void setClusterStandardDevs(Instances clusterStandardDevs) {
        if(clusterStandardDevs != null)
            this.clusterStandardDevs = clusterStandardDevs.toString();
    }

    /**
    * Pobiera funkcje odleglosci, ktora jest aktualnie w uzyciu.
    * @return Obiekt zawierajacy m.in. funkcje dystansu, wszystkie instancje, a takze pozwalajacy na obliczenie odleglosci miedzy poszczegolnymi instancjami.
    */
    public DistanceFunction getDistanceFunction() {
        DistanceFunction d = new EuclideanDistance();
        d.setAttributeIndices(attributeIndicesForDistanceFunction);
        BufferedReader reader = new BufferedReader(new StringReader(instancesForDistanceFunction));
        ArffReader arff = null;
        try {
            arff = new ArffReader(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        d.setInstances(arff.getData());
        d.setInvertSelection(invertSelectionForDistanceFunction);
        return d;
    }

    /**
     * Ustawia w??asn?? funkcj?? obliczaj??c?? odleg??o???? mi??dzy klastrami.
     * @param distanceFunction Obiekt z funkcj?? dystansu.
     */
    public void setDistanceFunction(DistanceFunction distanceFunction) {
        this.instancesForDistanceFunction = distanceFunction.getInstances().toString();
        this.attributeIndicesForDistanceFunction = distanceFunction.getAttributeIndices();
        this.invertSelectionForDistanceFunction = distanceFunction.getInvertSelection();
        this.optionsForDistanseFunction = distanceFunction.getOptions().clone();

       // this.distanceFunction = distanceFunction;
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
    * Zwraca liczb?? klastr??w do wygenerowania.
    * @return Liczba klastrow do wygenerowania.
    */
    public int getNumClusters() {
        return numClusters;
    }

    /**
     * Ustawia liczb?? klastr??w do wygenerowania.
     * @param numClusters Liczba klastr??w do wygenerowania.
     */
    public void setNumClusters(int numClusters) {
        this.numClusters = numClusters;
    }

    /**
     * Zwraca tablic?? opcji, wg. kt??rych funkcjonowa?? algorytm.
     * @return Tablica opcji algorytmu.
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Ustawia tablic?? opcji, wg. kt??rych funkcjonowa?? algorytm.
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
     * Ustawia ??a??cuch z rewizj??.
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
     * Ustawia typ algorytmu jaki zosta?? u??yty.
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
     * Ustawia liczb?? wyznaczonych klastr??w.
     * @param numberOfClusters Liczba klastr??w.
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
     * @param squaredError B????d kwadratowy dla wszystkich klastr??w.
     */
    public void setSquaredError(double squaredError) {
        this.squaredError = squaredError;
    }

    /**
     * Zwraca rozk??ady normalne dla modeli klastra.
     * @return Rozk??ady normalne dla modeli klastra.
     */
    public double[][][] getClusterModelsNumericAtts() {
        return clusterModelsNumericAtts;
    }

    /**
     * Ustawia rozk??ady normalne dla modeli klastra.
     * @param clusterModelsNumericAtts Rozk??ady normalne dla modeli klastra.
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
     * Zwraca ???a???cuch z informacj??? o typie algorytmu i jego nazwie.
     * @return lancuch z informacja o algorytmie.
     */
    @Override
    public String toString() {
        return "Algorithm: " + algorithmType + "; Name: " + getAlgorithmName();
    }

    /**
     * Ustawia nazw?? algorytmu.
     * @param algorithmName Nazwa algorytmu.
     */
    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

/* --- Cobweb, HierarchicalClusterer --- */

    /**
     * @return the acuity
     */
    public double getAcuity() {
        return acuity;
    }

    /**
     * @param acuity the acuity to set
     */
    public void setAcuity(double acuity) {
        this.acuity = acuity;
    }

    /**
     * @return the cutoff
     */
    public double getCutoff() {
        return cutoff;
    }

    /**
     * @param cutoff the cutoff to set
     */
    public void setCutoff(double cutoff) {
        this.cutoff = cutoff;
    }

    /**
     * @return the graph
     */
    public String getGraph() {
        return graph;
    }

    /**
     * @param graph the graph to set
     */
    public void setGraph(String graph) {
        this.graph = graph;
    }

    /**
     * @return the graphType
     */
    public int getGraphType() {
        return graphType;
    }

    /**
     * @param graphType the graphType to set
     */
    public void setGraphType(int graphType) {
        this.graphType = graphType;
    }

//    /**
//     * @return the linkType
//     */
//    public SelectedTag getLinkType() {
//        return linkType;
//    }
//
//    /**
//     * @param linkType the linkType to set
//     */
//    public void setLinkType(SelectedTag linkType) {
//        this.linkType = linkType;
//    }

    /**
     * @return the printNewick
     */
    public boolean isPrintNewick() {
        return printNewick;
    }

    /**
     * @param printNewick the printNewick to set
     */
    public void setPrintNewick(boolean printNewick) {
        this.printNewick = printNewick;
    }

    /**
     * @return the algorithmName
     */
    public String getAlgorithmName() {
        return algorithmName;
    }

    /**
     * Informacja o b????dach lub o poprawno??ci wykonanego algorytmu. Je??eli jest ok w info znajdzie si?? string 'ok'
     * je??eli b??d?? b????dy, tutaj znajdzie si?? wiadomo???? o napotkanym b????dzie. Reszta p??l b??dzie wtedy pusta.
     * @return
     */
    public String getInfo() {
        if(info == null) {
            info = "\n==== WekaAnswer informations ====\n";
        }
        return info;
    }

    /**
     * Informacja o b????dach lub o poprawno??ci wykonanego algorytmu. Je??eli jest ok w info znajdzie si?? string 'ok'
     * je??eli b??d?? b????dy, tutaj znajdzie si?? wiadomo???? o napotkanym b????dzie. Reszta p??l b??dzie wtedy pusta.
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Zwraca informacj?? o poprawno??ci obiektu WekaAnswer.
     * @return TRUE - je??li obiekt zosta?? utworzony poprawnie. FALSE - w przeciwnym przypadku.
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * Ustawia parametr informuj??cy o poprawno??ci obiektu.
     * @param value TRUE - je??li obiekt poprawny, FALSE - w przeciwnym przypadku.
     */
    public void setCorrect(boolean value) {
        correct = value;
    }

    /**
     * Zwraca obiekt klasteryzatora.
     * @return Obiekt klasteryzatora.
     */
    public Clusterer getClusterer() {
        return clusterer;
    }

    /**
     * Ustawia obiekt klasteryzatora.
     * @param clusterer Obiekt klasteryzatora
     */
    public void setClusterer(Clusterer clusterer) {
        this.clusterer = clusterer;
    }

    /**
     * Zwraca obiekt ewaluacji modelu.
     * @return Obiekt ewaluacji modelu.
     */
    public ClusterEvaluation getEval() {
        return eval;
    }

    /**
     * Ustawia obiekt ewaluacji modelu.
     * @param eval Obiekt ewaluacji modelu.
     */
    public void setEval(ClusterEvaluation eval) {
        this.eval = eval;
    }

    /**
     * Zwraca tylko te instancje, kt??re nale???? do klastra o podanym numerze.
     * @param numClust Numer klastra
     * @return Instancje nale????ce do podanego klastra.
     */
    public Instances getClusterInstances(int numClust) {
        Instances inst = new Instances(data);
        inst.clear();

        try {
            for (int i = 0; i < data.numInstances(); ++i) {
                if (clusterer.clusterInstance(data.instance(i)) == numClust) {
                    inst.add(data.instance(i));
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return inst;
    }

    /**
     * Zwraca obiekt z instancjami, kt??re mia??y zosta?? poddane analizie.
     * @return Obiekt z instancjami
     */
    public Instances getData() {
        return data;
    }

    /**
     * Ustawia obiekt z instancjami, kt??re mia?? zosta?? poddane analizie.
     * @param data Obiekt z instancjami.
     */
    public void setData(Instances data) {
        this.data = data;
    }

    /**
     * Zwraca list?? dost??pnych atrybut??w.
     * @return Lista dost??pnych atrybut??w.
     */
    public List<String> getAttributeNames() {
        Enumeration e = data.enumerateAttributes();
        List<String> attributeNames = new ArrayList<String>();

        while (e.hasMoreElements()) {
            attributeNames.add(((Attribute)e.nextElement()).name());
        }

        return attributeNames;
    }

    /**
     * Metoda zwracaj??ca list?? warto??ci dla danej instancji.
     * @param inst Analizowana instancja
     * @param attrX Nazwa atrybutu dla osi X.
     * @param attrY Nazwa atrybutu dla osi Y
     * @return Lista dwuelementowa z warto??ciami kolejno dla osi X i Y.
     */
    public List<Number> getValueForInstance(Instance inst, String attrX, String attrY) {
        List<Number> value = new ArrayList<Number>();
        Attribute atX = inst.attribute(getAttributeNames().indexOf(attrX));
        Attribute atY = inst.attribute(getAttributeNames().indexOf(attrY));

        value.add(inst.value(atX));
        value.add(inst.value(atY));
        return value;
    }
}