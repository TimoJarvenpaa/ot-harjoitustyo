package pong.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import pong.domain.Ball;
import pong.domain.Paddle;

public class PongUI {

    public static int WIDTH = 600;
    public static int HEIGHT = 400;

    public void display(Stage window, Scene returnScene) {

        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        pane.setStyle("-fx-background-color: black;");

        Ball ball = new Ball();
        
        Paddle leftPaddle = new Paddle(true);
        Paddle rightPaddle = new Paddle(false);
        
        Label leftScore = new Label("" + leftPaddle.getScore());
        leftScore.setStyle("-fx-text-fill: white;-fx-font-size: 20pt;");
        
        Label rightScore = new Label("" + rightPaddle.getScore());
        rightScore.setStyle("-fx-text-fill: white;-fx-font-size: 20pt;");
        
        Line middle = new Line(WIDTH/2, 0, WIDTH/2, HEIGHT);
        middle.setStroke(Color.WHITE);
        middle.getStrokeDashArray().addAll(15.0);
        
        pane.getChildren().addAll(ball.getBall(), leftPaddle.getPaddle(), rightPaddle.getPaddle(), leftScore, rightScore, middle);
        
        leftScore.setLayoutX(WIDTH / 4);
        leftScore.setLayoutY(10);
        rightScore.setLayoutX(3 * (WIDTH / 4));
        rightScore.setLayoutY(10);

        Scene gameplayScene = new Scene(pane, WIDTH, HEIGHT);

        Map<KeyCode, Boolean> keysPressed = new HashMap<>();

        gameplayScene.setOnKeyPressed(event -> {
            keysPressed.put(event.getCode(), Boolean.TRUE);
        });

        gameplayScene.setOnKeyReleased(event -> {
            keysPressed.put(event.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                ball.update(leftPaddle, rightPaddle);
                leftScore.setText("" + leftPaddle.getScore());
                rightScore.setText("" + rightPaddle.getScore());

                if (keysPressed.getOrDefault(KeyCode.W, false)) {
                    leftPaddle.move(-10);
                }

                if (keysPressed.getOrDefault(KeyCode.S, false)) {
                    leftPaddle.move(10);
                }

                if (keysPressed.getOrDefault(KeyCode.UP, false)) {
                    rightPaddle.move(-10);
                }

                if (keysPressed.getOrDefault(KeyCode.DOWN, false)) {
                    rightPaddle.move(10);
                }

                if (ball.collides(leftPaddle) || ball.collides(rightPaddle)) {
                    ball.ricochet();
                }

                if (keysPressed.getOrDefault(KeyCode.ESCAPE, false)) {
                    stop();
                    window.setScene(returnScene);
                }
            }
        }.start();

        window.setScene(gameplayScene);
        window.show();
    }
}
