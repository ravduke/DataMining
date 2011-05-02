package pl.edu.agh.ftj.datamining.gui.communication;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class WekaAnswer {
	private String temp = "";
	
	public WekaAnswer() {
		temp = "test";
	}
	
	public String getTemp() {
		return temp;
	}
}
