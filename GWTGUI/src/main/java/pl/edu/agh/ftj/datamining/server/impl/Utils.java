package pl.edu.agh.ftj.datamining.server.impl;

import java.util.ArrayList;
import java.util.List;

/*
 * W tej klasie znajduja sie pomocnicze metody.
 */
/**
 *
 * @author ravduke
 */
public class Utils {

	/*
	 * Metoda przetwarzajaca odpowiedz z Weka serwisu do postaci listy algorytmow.
	 * @param odpowiedz z serwisu Weki
	 * @return lista dostepnych algorytmow
	 */
    /**
     *
     * @param param
     * @return
     */
    public static List<String> getAlgorithms(String param) {
		List<String> result = new ArrayList<String>();
		String[] tmp = param.split("<return>");
		for(int i = 1; i < tmp.length ; i++) 
			result.add((tmp[i].split("</return>")[0]));
		
		return result;
	}
}
