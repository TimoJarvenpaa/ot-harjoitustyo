package pong.domain;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import pong.dao.SQLScoreDAO;
import pong.domain.Score;

public class ScoreServiceTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File scoreFile;  
    SQLScoreDAO dao;
    ScoreService scoreService;
    
    @Before
    public void setUp() throws Exception {
        scoreFile = testFolder.newFile("test.db");
        dao = new SQLScoreDAO(scoreFile.getAbsolutePath());
        Score score = new Score();
        score.setPlayer1Name("PL1");
        score.setPlayer1Score(6);
        score.setPlayer2Name("PL2");
        score.setPlayer2Score(1);
        scoreService = new ScoreService(dao);
        scoreService.saveScore(score);
    }
    
    @Test
    public void scoreServiceGetScoresReturnsScoresFromDatabase() throws Exception {
        List<Score> scores = scoreService.getScores();
        Score score = scores.get(0);
        assertEquals("PL1", score.getPlayer1Name());
        assertEquals(6, score.getPlayer1Score());
        assertEquals("PL2", score.getPlayer2Name());
        assertEquals(1, score.getPlayer2Score());
    }
    
    @Test
    public void scoreServiceSaveScoreMethodSavesScoreToDatabase() throws Exception {
        Score score = new Score();
        score.setPlayer1Name("CCC");
        score.setPlayer1Score(1);
        score.setPlayer2Name("DDD");
        score.setPlayer2Score(0);
        scoreService.saveScore(score);
        
        List<Score> scores = scoreService.getScores();
        assertEquals(2, scores.size());
        Score savedScore = scores.get(0);
        assertEquals("CCC", savedScore.getPlayer1Name());
    }
    
    @After
    public void tearDown() {
        scoreFile.delete();
    }

}