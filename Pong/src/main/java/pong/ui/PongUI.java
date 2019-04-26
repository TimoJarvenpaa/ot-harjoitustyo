package pong.ui;

import java.util.HashMap;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import pong.domain.Ball;
import pong.domain.Paddle;
import pong.domain.Score;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

public class PongUI {

    private boolean timeLimit;
    private Score gameScore;

    public PongUI(boolean timeLimit, Score score) {
        this.timeLimit = timeLimit;
        this.gameScore = score;
    }

    public void display(Stage window, Scene returnScene) {

        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        pane.setStyle("-fx-background-color: black;");

        Ball ball = new Ball();

        Paddle leftPaddle = new Paddle(true);
        Paddle rightPaddle = new Paddle(false);

        Label leftScore = new Label("" + leftPaddle.getScore());
        leftScore.setStyle("-fx-text-fill: white;-fx-font-size: 20pt;");

        Label leftPlayer = new Label("" + gameScore.getPlayer1Name());
        leftPlayer.setStyle("-fx-text-fill: white;-fx-font-size: 20pt;");

        Label rightScore = new Label("" + rightPaddle.getScore());
        rightScore.setStyle("-fx-text-fill: white;-fx-font-size: 20pt;");

        Label rightPlayer = new Label("" + gameScore.getPlayer2Name());
        rightPlayer.setStyle("-fx-text-fill: white;-fx-font-size: 20pt;");

        Line middle = new Line(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
        middle.setStroke(Color.WHITE);
        middle.getStrokeDashArray().addAll(15.0);

        pane.getChildren().addAll(ball.getBall(), leftPaddle.getPaddle(), rightPaddle.getPaddle(), leftScore, rightScore, middle, leftPlayer, rightPlayer);

        leftScore.setLayoutX(WIDTH / 4);
        leftScore.setLayoutY(10);
        rightScore.setLayoutX(3 * (WIDTH / 4) - 20);
        rightScore.setLayoutY(10);

        leftPlayer.setLayoutX(leftScore.getLayoutX() - WIDTH / 8);
        leftPlayer.setLayoutY(10);
        rightPlayer.setLayoutX(rightScore.getLayoutX() + (WIDTH / 8) - 20);
        rightPlayer.setLayoutY(10);

        GameOver resultScene = new GameOver();

        Scene gameplayScene = new Scene(pane, WIDTH, HEIGHT);

        Map<KeyCode, Boolean> keysPressed = new HashMap<>();

        gameplayScene.setOnKeyPressed(event -> {
            keysPressed.put(event.getCode(), Boolean.TRUE);
        });

        gameplayScene.setOnKeyReleased(event -> {
            keysPressed.put(event.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {

            boolean firstCycle = true;
            PauseTransition timer = new PauseTransition(Duration.seconds(60));

            @Override
            public void handle(long now) {

                if (timeLimit && firstCycle) {
                    timer.setOnFinished(event -> {
                        gameScore.setPlayer1Score(leftPaddle.getScore());
                        gameScore.setPlayer2Score(rightPaddle.getScore());
                        resultScene.display(window, returnScene, gameScore);
                    });
                    timer.play();
                    firstCycle = false;
                }

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

                if (ball.collides(leftPaddle)) {
                    ball.ricochet(leftPaddle);
                }

                if (ball.collides(rightPaddle)) {
                    ball.ricochet(rightPaddle);
                }

                if (keysPressed.getOrDefault(KeyCode.ESCAPE, false)) {
                    if (timeLimit) {
                        timer.stop();
                    }
                    stop();
                    window.setScene(returnScene);
                }
            }
        }.start();

        window.setScene(gameplayScene);
        window.show();
    }
}
