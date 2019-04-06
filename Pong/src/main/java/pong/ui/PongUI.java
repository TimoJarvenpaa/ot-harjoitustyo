package pong.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
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
        Paddle paddleLeft = new Paddle(true);
        Paddle paddleRight = new Paddle(false);
        pane.getChildren().addAll(ball.getBall(), paddleLeft.getPaddle(), paddleRight.getPaddle());

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
                ball.update();

                if (keysPressed.getOrDefault(KeyCode.W, false)) {
                    paddleLeft.move(-10);
                }

                if (keysPressed.getOrDefault(KeyCode.S, false)) {
                    paddleLeft.move(10);
                }

                if (keysPressed.getOrDefault(KeyCode.UP, false)) {
                    paddleRight.move(-10);
                }

                if (keysPressed.getOrDefault(KeyCode.DOWN, false)) {
                    paddleRight.move(10);
                }

                if (ball.collides(paddleLeft) || ball.collides(paddleRight)) {
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
