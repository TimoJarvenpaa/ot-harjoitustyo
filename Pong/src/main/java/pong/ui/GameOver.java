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

public class GameOver {
    
    public void display(Stage window, Scene returnScene, Score gameScore) {
        
        VBox layout = new VBox(30);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setStyle("-fx-background-color: black;");
        layout.setAlignment(Pos.CENTER);

        Label header = new Label("Game Over");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font("Verdana", 30));
        header.setStyle("-fx-padding: 0 0 40 0;");
        
        Label label1 = new Label(gameScore.getPlayer1Name() + " vs. " + gameScore.getPlayer2Name());
        label1.setTextFill(Color.WHITE);
        label1.setFont(Font.font("Verdana", 20));
        
        Label label2 = new Label(gameScore.getPlayer1Score() + " - " + gameScore.getPlayer2Score());
        label2.setTextFill(Color.WHITE);
        label2.setFont(Font.font("Verdana", 30));
        
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 40 0 0 0;");
        returnButton.setOnAction(event -> {
            window.setScene(returnScene);
        });

        layout.getChildren().addAll(header, label1, label2, returnButton);
        Scene gameOverScene = new Scene(layout);

        window.setScene(gameOverScene);
        window.show();
    }

}
