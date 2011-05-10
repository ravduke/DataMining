package pl.edu.agh.ftj.datamining.gui.communication.impl;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	public static List<String> getAlgorithms(String param) {
		List<String> result = new ArrayList<String>();
		String[] tmp = param.split("<return>");
		for(int i = 1; i < tmp.length ; i++) 
			result.add((tmp[i].split("</return>")[0]));
		
		return result;
	}
}
