package pong.domain;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.Ball;
import pong.domain.Paddle;

public class BallTest {

    int WIDTH;
    int HEIGHT;
    Ball ball;
    Paddle leftPaddle;
    Paddle rightPaddle;

    @Before
    public void setUp() {
        WIDTH = 600;
        HEIGHT = 400;
        ball = new Ball();
        leftPaddle = new Paddle(true);
        rightPaddle = new Paddle(false);
    }

    @Test
    public void ballBeginsInTheMiddle() {
        assertEquals(WIDTH / 2, ball.getBall().getLayoutX(), 0.1);
        assertEquals(HEIGHT / 2, ball.getBall().getLayoutY(), 0.1);
    }

    @Test
    public void locationOfTheBallChangesAccordingToXAndYSpeeds() {
        ball.update(leftPaddle, rightPaddle);
        assertEquals((WIDTH / 2) + ball.getXSpeed(), ball.getBall().getLayoutX(), 0.1);
        assertEquals((HEIGHT / 2) + ball.getYSpeed(), ball.getBall().getLayoutY(), 0.1);
    }

    @Test
    public void ballReturnsToTheMiddleAfterReset() {
        ball.update(leftPaddle, rightPaddle);
        ball.update(leftPaddle, rightPaddle);
        ball.reset();
        assertEquals(WIDTH / 2, ball.getBall().getLayoutX(), 0.1);
        assertEquals(HEIGHT / 2, ball.getBall().getLayoutY(), 0.1);
    }
    
    @Test
    public void collisionWithPaddleIsDetected() {
        assertFalse(ball.collides(leftPaddle));
        ball.getBall().setLayoutX(leftPaddle.getPaddle().getX());
        ball.getBall().setLayoutY(leftPaddle.getPaddle().getY());
        assertTrue(ball.collides(leftPaddle));
    }
    
    @Test
    public void ricochetChangesBallDirection(){
        double initialXSpeed = ball.getXSpeed();
        double initialYSpeed = ball.getYSpeed();
        ball.ricochet(leftPaddle);
        assertFalse(initialXSpeed == ball.getXSpeed());
        assertFalse(initialYSpeed == ball.getYSpeed());
    }
    
    @Test
    public void ifBallReachesTheLeftEdgeBallPositionIsResetAndRightPaddleScoreIncreases(){
        ball.getBall().setLayoutX(0 - ball.getBall().getRadius());
        int rightScore = rightPaddle.getScore();
        ball.update(leftPaddle, rightPaddle);
        
        assertEquals(WIDTH / 2, ball.getBall().getLayoutX(), 0.1);
        assertEquals(HEIGHT / 2, ball.getBall().getLayoutY(), 0.1);
        
        assertEquals(rightScore + 1, rightPaddle.getScore());
    }
    
    @Test
    public void ifBallReachesTheRightEdgeBallPositionIsResetAndLeftPaddleScoreIncreases(){
        ball.getBall().setLayoutX(WIDTH + ball.getBall().getRadius());
        int leftScore = leftPaddle.getScore();
        ball.update(leftPaddle, rightPaddle);
        
        assertEquals(WIDTH / 2, ball.getBall().getLayoutX(), 0.1);
        assertEquals(HEIGHT / 2, ball.getBall().getLayoutY(), 0.1);
        
        assertEquals(leftScore + 1, leftPaddle.getScore());
    }

}
