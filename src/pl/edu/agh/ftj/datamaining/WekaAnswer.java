package pl.edu.agh.ftj.datamaining;

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
    private String algorithmName;
    
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
