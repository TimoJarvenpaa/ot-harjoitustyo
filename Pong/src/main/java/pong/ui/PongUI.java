package pong.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pong.domain.Ball;
import pong.domain.Paddle;

public class PongUI extends Application {

    public static int WIDTH = 600;
    public static int HEIGHT = 400;

    @Override
    public void start(Stage stage) throws Exception {
        
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        pane.setStyle("-fx-background-color: black;");

        Ball ball = new Ball();
        pane.getChildren().add(ball.getBall());

        Paddle paddleLeft = new Paddle(true);
        pane.getChildren().add(paddleLeft.getPaddle());

        Paddle paddleRight = new Paddle(false);
        pane.getChildren().add(paddleRight.getPaddle());

        Scene scene = new Scene(pane);

        Map<KeyCode, Boolean> keysPressed = new HashMap<>();

        scene.setOnKeyPressed(event -> {
            keysPressed.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased(event -> {
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
            }
        }.start();

        stage.setTitle("Pong");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
