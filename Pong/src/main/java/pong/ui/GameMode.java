package pong.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pong.domain.Score;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

public class GameMode {
    
    public void display(Stage window, Scene returnScene, Score score) {
        
        VBox layout = new VBox(30);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setStyle("-fx-background-color: black;");
        layout.setAlignment(Pos.CENTER);

        Label header = new Label("Choose a game mode");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font("Verdana", 30));
        header.setStyle("-fx-padding: 0 0 40 0;");
        
        Button endlessModeButton = new Button("Endless Mode");
        endlessModeButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 0 0 0 0;");
        endlessModeButton.setOnAction(event -> {
            PongUI endless = new PongUI(false, score);
            endless.display(window, returnScene);
        });
        
        Button timeLimitModeButton = new Button("Time Limit (60 seconds)");
        timeLimitModeButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 0 0 0 0;");
        timeLimitModeButton.setOnAction(event -> {
            PongUI timeLimited = new PongUI(true, score);
            timeLimited.display(window, returnScene);
        });
        
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 40 0 0 0;");
        returnButton.setOnAction(event -> {
            window.setScene(returnScene);
        });

        layout.getChildren().addAll(header, endlessModeButton, timeLimitModeButton, returnButton);
        Scene gameModeScene = new Scene(layout);

        window.setScene(gameModeScene);
        window.show();
    }
}
