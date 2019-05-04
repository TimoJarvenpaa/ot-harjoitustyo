package pong.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreTest {
    
    Score score;
    
    @Before
    public void setUp() {
        score = new Score();
        score.setPlayer1Name("PL1");
        score.setPlayer2Name("PL2");
        score.setPlayer1Score(2);
        score.setPlayer2Score(4);
    }
    
    @Test
    public void scoreToStringIsFormattedCorrectly() {
        String expectedResult = score.getPlayer1Name() + "  " + score.getPlayer1Score() + "    -    " + score.getPlayer2Score() + "  " + score.getPlayer2Name();
        assertEquals(expectedResult, score.toString());
    }

}
