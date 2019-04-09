package pong.domain;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pong.domain.Paddle;

public class PaddleTest {

    int WIDTH;
    int HEIGHT;
    Paddle paddleLeft;

    @Before
    public void setUp() {
        WIDTH = 600;
        HEIGHT = 400;
        paddleLeft = new Paddle(true);
    }

    @Test
    public void paddleStartsInTheMiddleOfYAxis() {
        // i.e. paddle's top left corner is located at HEIGHT/2 - paddleHeight/2
        assertEquals(150, paddleLeft.getPaddle().getY(), 0.1);
    }
    
    @Test
    public void moveReturnsFalseIfPaddleIsTooCloseToBounds() {
        paddleLeft.getPaddle().setY(0);
        assertFalse(paddleLeft.move(-10));
    }
    
    @Test
    public void moveReturnsTrueIfPaddleIsNotTooCloseToBounds() {
        assertTrue(paddleLeft.move(10));
    }
}
