package pl.edu.agh.ftj.datamining.gui.communication;

/*
 * Interfejs opisujacy sposob tworzenie komunikacji z Web Servicami Weki
 * @author Tomasz Pyszka
 */
public interface WekaCommunicationFactory {

	// Typy komunikacji (AXIS, REST)
	public enum Type { AXIS, REST }
	
	/*
	 * Metoda odpowiedzialna za utworzenie odpowiedniej komunikacji.
	 * @param typ komunikacji
	 */
	public WekaCommunication create(Type type);
}
