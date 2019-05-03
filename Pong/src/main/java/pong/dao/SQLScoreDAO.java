package pong.dao;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import pong.domain.Score;

/**
 * Pelin lopullisten pisteiden tietokantaan tallennuksesta ja pisteiden
 * tietokannasta noutamisesta vastaava DAO.
 */
public class SQLScoreDAO implements ScoreDAO {

    private String file;

    public SQLScoreDAO() throws Exception {
        this.file = getScoreFile();
        createTable();
    }
    
    public SQLScoreDAO(String file) throws Exception {
        this.file = file;
        createTable();
    }

    /**
     * Metodi luo tietokantaan taulun Score, jos sitä ei ole jo luotu.
     */
    private void createTable() throws Exception {
        String createScoreTable = "CREATE TABLE IF NOT EXISTS score (id integer PRIMARY KEY, player1 varchar(10), player2 varchar(10), score1 integer, score2 integer);";
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute(createScoreTable);
            stmt.close();
            conn.close();
        } catch (SQLException e) {

        }
    }

    /**
     * Metodi, joka muodostaa ja palauttaa tietokantayhteyden konstruktorissa
     * määriteltyyn tiedostoon.
     *
     * @return Connection-olio, jota voidaan käyttää tietokantakyselyjen
     * tekemiseen.
     */
    public Connection getConnection() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:" + this.file);
    }

    /**
     * Metodi lisää tietokantaan parametrina annettua pistetilannetta vastaavan
     * uuden rivin.
     *
     * @return päättyneen pelin pistetilanteen ja pelaajien nimet sisältävä
     * Score-olio;
     */
    @Override
    public Score create(Score score) throws Exception {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Score (player1, player2, score1, score2) VALUES (?, ?, ?, ?)");
        stmt.setString(1, score.getPlayer1Name());
        stmt.setString(2, score.getPlayer2Name());
        stmt.setInt(3, score.getPlayer1Score());
        stmt.setInt(4, score.getPlayer2Score());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        return score;
    }

    /**
     * Metodi noutaa tietokannasta kaikki tallennetut pisteet ja palauttaa
     * listan Score-olioita.
     *
     * @return Lista tietokannasta noudettuja pisteitä uusimmasta vanhimpaan;
     */
    @Override
    public List<Score> getAll() throws Exception {
        String query = "SELECT * FROM Score ORDER BY id DESC;";
        List<Score> scores = new ArrayList<>();

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Score score = new Score();
            score.setPlayer1Name(rs.getString("player1"));
            score.setPlayer1Score(rs.getInt("score1"));
            score.setPlayer2Name(rs.getString("player2"));
            score.setPlayer2Score(rs.getInt("score2"));
            scores.add(score);
        }
        stmt.close();
        rs.close();
        conn.close();
        return scores;
    }

    /**
     * Apumetodi, joka palauttaa sovelluksen juurihakemistossa olevan
     * config.properties -tiedoston sisältämän scoreFile-parametrin, joka
     * puolestaan määrää luotavan tietokannan tiedostonimen.
     */
    private String getScoreFile() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        return scoreFile;
    }

}
