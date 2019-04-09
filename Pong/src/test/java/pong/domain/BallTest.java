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

}
