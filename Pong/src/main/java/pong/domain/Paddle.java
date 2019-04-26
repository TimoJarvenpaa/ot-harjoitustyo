package pong.domain;

import javafx.scene.shape.Rectangle;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

public class Paddle {

    private Rectangle paddle;
    private int paddleWidth = 10;
    private int paddleHeight = 100;
    private int y = (HEIGHT / 2) - (paddleHeight / 2);
    private int x;
    private int score;
    private boolean left;

    public Paddle(boolean left) {
        this.left = left;
        if (left) {
            x = paddleWidth;
            this.paddle = new Rectangle(x, y, paddleWidth, paddleHeight);
        } else {
            x = WIDTH - (2 * paddleWidth);
            this.paddle = new Rectangle(x, y, paddleWidth, paddleHeight);
        }
        paddle.setFill(javafx.scene.paint.Color.WHITE);
        this.score = 0;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public int getScore() {
        return this.score;
    }

    public void incrementScore() {
        this.score++;
    }

    public boolean move(int amount) {
        if (this.paddle.getY() + amount >= 0 && this.paddle.getY() + amount <= HEIGHT - paddleHeight) {
            this.paddle.setY(this.paddle.getY() + amount);
            return true;
        }
        return false;
    }

    public boolean isLeft() {
        return this.left;
    }

    public int getPaddleWidth() {
        return this.paddleWidth;
    }
    
    public int getPaddleHeight() {
        return this.paddleHeight;
    }

}
