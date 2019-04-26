package pong.dao;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import pong.domain.Score;

public class SQLScoreDAOTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File scoreFile;  
    SQLScoreDAO dao;
    
    @Before
    public void setUp() throws Exception {
        scoreFile = testFolder.newFile("test.db");
        dao = new SQLScoreDAO(scoreFile.getAbsolutePath());
        Score score = new Score();
        score.setPlayer1Name("PL1");
        score.setPlayer1Score(6);
        score.setPlayer2Name("PL2");
        score.setPlayer2Score(1);
        dao.create(score);
    }
    
    @Test
    public void scoresAreReadCorrectlyFromDatabase() throws Exception {
        List<Score> scores = dao.getAll();
        Score score = scores.get(0);
        assertEquals("PL1", score.getPlayer1Name());
        assertEquals(6, score.getPlayer1Score());
        assertEquals("PL2", score.getPlayer2Name());
        assertEquals(1, score.getPlayer2Score());
    }
    
    @Test
    public void scoresAreSavedCorrectlyToDatabase() throws Exception {
        Score score = new Score();
        score.setPlayer1Name("AAA");
        score.setPlayer1Score(2);
        score.setPlayer2Name("BBB");
        score.setPlayer2Score(2);
        dao.create(score);
        
        List<Score> scores = dao.getAll();
        assertEquals(2, scores.size());
        Score savedScore = scores.get(0);
        assertEquals("AAA", savedScore.getPlayer1Name());
    }
    
    @After
    public void tearDown() {
        scoreFile.delete();
    }

}
