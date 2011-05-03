package pl.edu.agh.ftj.datamining.dbapi.exceptions;

/**
 * Wyjatki zwracane przez modul PostgreSQL
 * @author Szymon Stupkiewicz
 * @version 1.0.0
 */
public class PgsqlSourceException extends DataSourceException {

    /**
     * Domyślny konstruktor.
     */
    public PgsqlSourceException() {
        super();
    }

    /**
     * Konstruktor wyjatku.
     * @param message tresc komunikatu
     */
    public PgsqlSourceException(String message) {
        super(message);
    }
}
