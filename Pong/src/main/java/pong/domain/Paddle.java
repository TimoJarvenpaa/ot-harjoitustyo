package pong.domain;

import javafx.scene.shape.Rectangle;
import static pong.ui.PongUI.HEIGHT;
import static pong.ui.PongUI.WIDTH;

public class Paddle {

    private Rectangle paddle;
    private int paddleWidth = 10;
    private int paddleHeight = 100;
    private int y = (HEIGHT / 2) - (paddleHeight / 2);
    private int x;

    public Paddle(boolean left) {
        if (left) {
            x = paddleWidth;
            this.paddle = new Rectangle(x, y, paddleWidth, paddleHeight);
        } else {
            x = WIDTH - (2 * paddleWidth);
            this.paddle = new Rectangle(x, y, paddleWidth, paddleHeight);
        }
        paddle.setFill(javafx.scene.paint.Color.WHITE);
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public boolean move(int amount) {
        if (this.paddle.getY() + amount >= 0 && this.paddle.getY() + amount <= HEIGHT - paddleHeight) {
            this.paddle.setY(this.paddle.getY() + amount);
            return true;
        }
        //System.out.println("PaddleY" + this.paddle.yProperty());
        return false;
    }
}
