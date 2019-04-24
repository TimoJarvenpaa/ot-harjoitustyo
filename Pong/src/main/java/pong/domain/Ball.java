package pong.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

public class Ball {

    private Circle ball;
    private double xSpeed;
    private double ySpeed;

    public Ball() {
        this.ball = new Circle(10, Color.WHITE);
        reset();
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

        if (this.ball.getLayoutY() < 0 + this.ball.getRadius()) {
            this.ySpeed = -1 * ySpeed;
            this.ball.setLayoutY(this.ball.getLayoutY() + this.ball.getRadius());
        }

        if (this.ball.getLayoutY() > HEIGHT - this.ball.getRadius()) {
            this.ySpeed = -1 * ySpeed;
            this.ball.setLayoutY(this.ball.getLayoutY() - this.ball.getRadius());
        }
    }

    public boolean collides(Paddle paddle) {
        Shape area = Shape.intersect(this.ball, paddle.getPaddle());
        return area.getBoundsInLocal().getWidth() != -1;
    }

    public final void reset() {
        this.ball.setLayoutX(0);
        this.ball.setLayoutY(0);
        this.ball.relocate((WIDTH / 2) - this.ball.getRadius(), (HEIGHT / 2) - this.ball.getRadius());
        randomizeDirectionAndSpeed();
    }

    public void ricochet(Paddle paddle) {
        this.xSpeed *= -1;

        if (paddle.isLeft()) {
            double diff = (this.ball.getLayoutY() - paddle.getPaddle().getY()) / (paddle.getPaddleHeight() / 2);
            this.ySpeed = 5 * diff;
            this.ball.setLayoutX(this.ball.getLayoutX() + paddle.getPaddleWidth() / 2);
        }

        if (!paddle.isLeft()) {
            double diff = (this.ball.getLayoutY() - paddle.getPaddle().getY()) / (paddle.getPaddleHeight() / 2);
            this.ySpeed = 5 * diff;
            this.ball.setLayoutX(this.ball.getLayoutX() - paddle.getPaddleWidth() / 2);
        }
    }

    public double getXSpeed() {
        return xSpeed;
    }

    public double getYSpeed() {
        return ySpeed;
    }

    public void randomizeDirectionAndSpeed() {
        double min = Math.PI / 4 * -1;
        double max = Math.PI / 4;
        double angle = Math.random() * (max - min) + min;
        this.xSpeed = 5 * Math.cos(angle);
        this.ySpeed = 5 * Math.sin(angle);

        if (Math.random() < 0.5) {
            this.xSpeed *= -1;
        }
    }
}
