package pong.domain;

import java.util.ArrayList;
import java.util.List;
import pong.dao.ScoreDAO;

/**
 * Sovelluslogiikkaluokka, jonka kautta on mahdollista käyttää ScoreDAO:n
 * tarjoamia pysyväistiedon käsittelyyn liittyviä metodeja
 */
public class ScoreService {

    private ScoreDAO scoreDAO;

    public ScoreService(ScoreDAO scoreDAO) {
        this.scoreDAO = scoreDAO;
    }

    /**
     * Metodi, joka tallentaa parametrina annetun pistetilanteen konstruktorissa määritellyn ScoreDAO-rajapinnan
     * toteuttavan ilmentymän oman toteutuksen mukaisesti
     * 
     * @param score tallennettava pistetilanne
     */
    public void saveScore(Score score) throws Exception {
        try {
            this.scoreDAO.create(score);
        } catch (Exception e) {

        }
    }

    /**
     * Metodi, joka palauttaa listan tiedostoon tallennetuista pisteistä käyttäen konstruktorissa määritellyn ScoreDAO-rajapinnan
     * toteuttavan ilmentymän omaa toteutusta
     * 
     * @return scores lista tallennetuista pisteistä
     */
    public List<Score> getScores() throws Exception {
        List<Score> scores = new ArrayList<>();
        try {
            scores = this.scoreDAO.getAll();
        } catch (Exception e) {

        }
        return scores;
    }

}
