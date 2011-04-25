package pl.edu.agh.ftj.datamaining;

import weka.core.Capabilities;
import weka.core.DistanceFunction;
import weka.core.Instances;

/**
 * Klasa obiektu przechowującego dane wyprodukowane przez algorytmy Weki.
 * Obiekt ten będzie zwracany do silnika.
 * @author Bartek
 */
public class WekaAnswer {
    /**
     * Typ algorytmu jaki ma zostac uzyty. Dostepne opcje: 1 - SimpleKMeans, 2 - EM, 3 - HierarchicalClusterer, 4 - Cobweb.
     */
    private int algorithmType;
    
    /**
     * Nazwa użytego algorytmu.
     */
    private String algorithmName;

    private int[] assignments;
    private Capabilities capabilities;
    private Instances clusterCentroids;
    private int[][][] clusterNominalCounts;
    private int[] clusterSizes;
    private Instances clusterStandardDevs;
    private DistanceFunction distanceFunction;
    private int maxIterations;
    private int numClusters;
    private String[] options;
    private String revision;
    private double squaredError;
    private int numberOfClusters;

    public int[] getAssignments() {
        return assignments;
    }

    public void setAssignments(int[] assignments) {
        this.assignments = assignments;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public Instances getClusterCentroids() {
        return clusterCentroids;
    }

    public void setClusterCentroids(Instances clusterCentroids) {
        this.clusterCentroids = clusterCentroids;
    }

    public int[][][] getClusterNominalCounts() {
        return clusterNominalCounts;
    }

    public void setClusterNominalCounts(int[][][] clusterNominalCounts) {
        this.clusterNominalCounts = clusterNominalCounts;
    }

    public int[] getClusterSizes() {
        return clusterSizes;
    }

    public void setClusterSizes(int[] clusterSizes) {
        this.clusterSizes = clusterSizes;
    }

    public Instances getClusterStandardDevs() {
        return clusterStandardDevs;
    }

    public void setClusterStandardDevs(Instances clusterStandardDevs) {
        this.clusterStandardDevs = clusterStandardDevs;
    }

    public DistanceFunction getDistanceFunction() {
        return distanceFunction;
    }

    public void setDistanceFunction(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public int getNumClusters() {
        return numClusters;
    }

    public void setNumClusters(int numClusters) {
        this.numClusters = numClusters;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public int getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(int algorithmType) {
        this.algorithmType = algorithmType;
    }

    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    public double getSquaredError() {
        return squaredError;
    }

    public void setSquaredError(double squaredError) {
        this.squaredError = squaredError;
    }

    @Override
    public String toString() {
        return "Algorithm: " + algorithmType + "; Name: " + algorithmName;
    }

    /**
     * Ustawia nazwę algorytmu.
     * @param algorithmName Nazwa algorytmu.
     */
    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
