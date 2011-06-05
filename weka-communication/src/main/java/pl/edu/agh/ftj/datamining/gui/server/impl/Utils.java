package pl.edu.agh.ftj.datamining.gui.server.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * W tej klasie znajduja sie pomocnicze metody.
 */
public class Utils {

	/*
	 * Metoda przetwarzajaca odpowiedz z Weka serwisu do postaci listy algorytmow.
	 * @param odpowiedz z serwisu Weki
	 * @return lista dostepnych algorytmow
	 */
	public static List<String> getAlgorithms(String param) {
		List<String> result = new ArrayList<String>();
		String[] tmp = param.split("<return>");
		for(int i = 1; i < tmp.length ; i++) 
			result.add((tmp[i].split("</return>")[0]));
		
		return result;
	}
	
	public static byte[] readFromStream(InputStream stream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1000];
        int wasRead = 0;
        do {
            wasRead = stream.read(buffer);
            if(wasRead > 0)
                baos.write(buffer, 0, wasRead);
        } while(wasRead > -1);
        return baos.toByteArray();
    }
}
