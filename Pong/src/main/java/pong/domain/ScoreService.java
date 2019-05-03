package pong.domain;

import java.util.ArrayList;
import java.util.List;
import pong.dao.ScoreDAO;

public class ScoreService {

    private ScoreDAO scoreDAO;

    public ScoreService(ScoreDAO scoreDAO) {
        this.scoreDAO = scoreDAO;
    }

    public void saveScore(Score score) throws Exception {
        try {
            this.scoreDAO.create(score);
        } catch (Exception e) {

        }
    }

    public List<Score> getScores() throws Exception {
        List<Score> scores = new ArrayList<>();
        try {
            scores = this.scoreDAO.getAll();
        } catch (Exception e) {

        }
        return scores;
    }

}
