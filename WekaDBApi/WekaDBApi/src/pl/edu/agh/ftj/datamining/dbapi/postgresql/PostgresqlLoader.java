package pl.edu.agh.ftj.datamining.dbapi.postgresql;

import java.sql.SQLException;
import pl.edu.agh.ftj.datamining.dbapi.exceptions.PgsqlSourceException;
import weka.core.Instances;
import weka.core.converters.DatabaseLoader;

/**
 * Klasa obslugujaca import danych z bazy danych PostgreSQL do obiektów Instance dla algorytmow WEKA.
 *
 * @author Szymon Stupkiewicz
 * @version 1.0.0
 */
public final class PostgresqlLoader {

    private String dbUrl, relation;
    private Instances instances;

    /**
     * Konstruktor pobierajacy dane z wybranej bazy danych i tabeli do obiektu typu Instances dla algorytmow WEKA
     *
     * @param url URL do bazy danych zawierajacy host, port, login i haslo do bazy danych
     * @param table Tabela w bazie danych, z ktorej maja byc pobrane dane
     * @throws PgsqlSourceException Wyjatek wyrzucany kiedy wystapi blad z polaczeniem badz wybraniem tabeli
     */
    public PostgresqlLoader(String url, String table) throws PgsqlSourceException {

        this.setDbUrl(url);
        this.setRelation(table);

        try {
            this.fetchInstances();
        } catch (ClassNotFoundException ex) {
            String message = "The required PostgreSQL JDBC driver was not found. Additional info:\n" + ex.getMessage();
            throw new PgsqlSourceException(message);
        } catch (SQLException ex) {
            String message = "There was an error in your SQL statement. Additional info:\n" + ex.getMessage();
            throw new PgsqlSourceException(message);
        } catch (Exception ex) {
            String message = "There was an exception. Additional info:\n" + ex.getMessage();
            throw new PgsqlSourceException(message);
        }
    }

    /**
     * Pobiera dane z bazy danych na podstawie URL i nazwy tabeli pobranych w konstruktorze.
     *
     * @throws ClassNotFoundException Wyrzucany, gdy nie zostal zaladowany sterownik JDBC dla PostgreSQL
     * @throws Exception Wyrzucany, gdy API weki sobie tego zazyczy.
     */
    private void fetchInstances() throws ClassNotFoundException,Exception {
        Class.forName("org.postgresql.Driver");
        DatabaseLoader loader = new DatabaseLoader();
        loader.setSource(this.getDbUrl());
        loader.setQuery("SELECT * FROM " + this.getRelation());
        this.setInstances(loader.getDataSet());
    }

    /**
     * Standardowy getter - pobiera URL do bazy danych
     * @return URL do bazy danych
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * Standardowy getter - pobiera dane wybrane z bazy danych
     * @return obiekt Instances reprezentujacy dane pobrane z bazy danych w formacie dla algorytmow WEKI
     */
    public Instances getInstances() {
        return instances;
    }

    /**
     * Standardowy getter - pobiera nazwe tabeli, z ktorej beda wybierane dane
     * @return nazwa tabeli
     */
    public String getRelation() {
        return relation;
    }

    /**
     * Standardowy setter - ustawia URL do bazy danych
     * @param db_url URL do bazy danych, zawierajacy nazwe hosta, port, nazwe bazy danych, uzytkownika oraz haslo
     */
    public void setDbUrl(String db_url) {
        this.dbUrl = db_url;
    }

    /**
     * Standardowy setter - ustawia nazwe tabeli w bazie danych, z ktorej maja byc pobierane dane
     * @param relation nazwa tabeli
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * Standardowy setter - ustawia obiekt Instances na pozadana wartosc
     * @param instances obiekt Instances
     */
    private void setInstances(Instances instances) {
        this.instances = instances;
    }
}
