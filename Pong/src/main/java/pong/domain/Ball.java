package pong.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import static pong.ui.PongUI.HEIGHT;
import static pong.ui.PongUI.WIDTH;

public class Ball {

    private Circle ball;
    private int dx;
    private int dy;

    public Ball() {
        this.ball = new Circle(10, Color.WHITE);
        this.ball.relocate(WIDTH / 2, HEIGHT / 2);
        this.dx = 2;
        this.dy = 4;
    }

    public Circle getBall() {
        return ball;
    }

    public void update() {
        this.ball.setLayoutX(this.ball.getLayoutX() + dx);
        this.ball.setLayoutY(this.ball.getLayoutY() + dy);

        if (this.ball.getLayoutX() < 0 || this.ball.getLayoutX() > WIDTH) {
            reset();
        }
        
        if (this.ball.getLayoutY() < 0 || this.ball.getLayoutY() > HEIGHT) {
            this.dy = -1*dy;
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

    public void ricochet(){
        this.dx = this.dx * -1;
    }
}
