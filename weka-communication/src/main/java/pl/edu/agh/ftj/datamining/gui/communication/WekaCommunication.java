package pl.edu.agh.ftj.datamining.gui.communication;

/**
 * Interfejs do komunikacji z web serwisami Weki.
 * @author Tomasz Pyszka
 */
public interface WekaCommunication {
	
	/*
	 * @return zwraca tablice (XML) z nazwami dostepnych algorytmow
	 */
	public String getAlgorithms();
	
	/**
     * Metoda konfigurujaca algorytm Weki
     *
     * @param algorithmType wybiera typ algorytmu (od 0 do 3) (indeks tablicy z metody getAlgorithms)
     * @param location      adres URL do webservisu dbapi
     * @param id            id do danych (do webservisu dbapi)
     * @param table         table do danych (do webservisu dbapi)
     * @return              zwraca czy operacja wykonala sie poprawnie
     */
	public boolean setAlgorithm(Integer algorithmType, String location, String id, String table);
	
	/**
     * Metoda odpowiedzialna za uruchomienie algorytmu Weki.
     * @return Zwraca dane z algorytmu {@link WekaAnswer}.
     */
	public WekaAnswer runAlgorithm();
}
