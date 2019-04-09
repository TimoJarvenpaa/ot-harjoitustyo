package pong.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import static pong.ui.PongUI.HEIGHT;
import static pong.ui.PongUI.WIDTH;

public class Ball {

    private Circle ball;
    private int xSpeed;
    private int ySpeed;

    public Ball() {
        this.ball = new Circle(10, Color.WHITE);
        this.ball.relocate(WIDTH / 2, HEIGHT / 2);
        this.xSpeed = 2;
        this.ySpeed = 4;
    }

    public Circle getBall() {
        return ball;
    }

    public void update(Paddle leftPaddle, Paddle rightPaddle) {
        this.ball.setLayoutX(this.ball.getLayoutX() + xSpeed);
        this.ball.setLayoutY(this.ball.getLayoutY() + ySpeed);

        if (this.ball.getLayoutX() < 0) {
            rightPaddle.incrementScore();
            reset();
        }
        
        if (this.ball.getLayoutX() > WIDTH) {
            leftPaddle.incrementScore();
            reset();
        }

        if (this.ball.getLayoutY() < 0 || this.ball.getLayoutY() > HEIGHT) {
            this.ySpeed = -1 * ySpeed;
        }
    }

    public boolean collides(Paddle paddle) {
        Shape area = Shape.intersect(this.ball, paddle.getPaddle());
        return area.getBoundsInLocal().getWidth() != -1;
    }

    public void reset() {
        this.ball.setLayoutX(0);
        this.ball.setLayoutY(0);
        this.ball.relocate(WIDTH / 2, HEIGHT / 2);
    }

    public void ricochet() {
        this.xSpeed = this.xSpeed * -1;
    }
}
