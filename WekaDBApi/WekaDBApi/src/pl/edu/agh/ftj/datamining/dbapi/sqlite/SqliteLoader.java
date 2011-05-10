package pl.edu.agh.ftj.datamining.dbapi.sqlite;

import pl.edu.agh.ftj.datamining.dbapi.exceptions.DataSourceException;
import weka.core.Instances;
import weka.core.converters.DatabaseLoader;

/**
 * Klasa obslugujaca import danych z bazy danych SQLite do obiektów Instance dla algorytmow WEKA.
 *
 * @author Sebastian Klek
 * @version 1.0.0
 */
public final class SqliteLoader {

    private String dbUrl, relation;
    private Instances instances;

    /**
     * Konstruktor pobierajacy dane z wybranej bazy danych i tabeli do obiektu typu Instances dla algorytmow WEKA
     *
     * @param url URL do bazy danych zawierajacy host, port, login i haslo do bazy danych
     * @param table Tabela w bazie danych, z ktorej maja byc pobrane dane
     * @throws DataSourceException Wyjatek wyrzucany kiedy wystapi blad z polaczeniem badz wybraniem tabeli
     */
    public SqliteLoader(String url, String table) throws DataSourceException {

        dbUrl = url;
        relation = prepareProperName(table, "tab");
        try {
            fetchInstances();
        } catch (ClassNotFoundException ex) {
            throw new DataSourceException(ex.getMessage());
        } catch (Exception ex) {
            throw new DataSourceException(ex.getMessage());
        }
    }

    /**
     * Pobiera dane z bazy danych na podstawie URL i nazwy tabeli pobranych w konstruktorze.
     *
     * @throws ClassNotFoundException Wyrzucany, gdy nie zostal zaladowany sterownik JDBC dla PostgreSQL
     * @throws Exception Wyrzucany, gdy API weki sobie tego zazyczy.
     */
    private void fetchInstances() throws ClassNotFoundException, Exception {
        Class.forName("org.sqlite.JDBC");
        DatabaseLoader loader = new DatabaseLoader();
        loader.setSource(dbUrl);
        loader.setQuery("SELECT * FROM " + relation);
        instances = loader.getDataSet();
    }

    /**
     * Laduje dane z pliku arff do bazy danych SQLite
     * @param arffFileName Sciezka do pliku arff
     * @param relNameFromFileName Jesli true nazwy tablic brane z nazwy pliku arff
     * @param dbFileName Sciezka do pliku bazy danych
     */
    private String prepareProperName(String in, String pre) {
        String out = in.toLowerCase();
        if (out.length() > 20) {
            out = out.substring(0, 19);
        }
        out = out.split("\t")[0].trim();
        out = out.replaceAll(" ", "_");
        out = out.replaceAll("-", "_");
        out = out.replaceAll("\\.", "_");
        char[] tab = out.toCharArray();
        out = "";
        for (int i = 0; i < tab.length; i++) {
            char c = tab[i];
            if (Character.isLetterOrDigit(c) || c == '_') {
                out += c;
            }
        }
        if (out.charAt(0) >= '0' && out.charAt(0) <= '9') {
            out = pre + "_" + out;
        }
        return out;
    }


    /**
     * Standardowy getter - pobiera dane wybrane z bazy danych
     * @return obiekt Instances reprezentujacy dane pobrane z bazy danych w formacie dla algorytmow WEKI
     */
    public Instances getInstances() {
        return instances;
    }
}
