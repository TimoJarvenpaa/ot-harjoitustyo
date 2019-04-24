package pong.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pong.domain.Score;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

public class PlayerNames {

    public void display(Stage window, Scene returnScene) {

        VBox layout = new VBox(30);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setStyle("-fx-background-color: black;");
        layout.setAlignment(Pos.CENTER);

        Label header = new Label("Enter names");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font("Verdana", 30));

        Label info = new Label("(*No spaces allowed, 3 characters required)");
        info.setTextFill(Color.WHITE);
        info.setFont(Font.font("Verdana", 15));

        Label label1 = new Label("Enter a name for player 1 (left)");
        label1.setTextFill(Color.WHITE);
        label1.setFont(Font.font("Verdana", 20));

        TextField name1 = new TextField();
        name1.setFont(Font.font("Verdana", 15));
        name1.setMaxWidth(80);

        Label label2 = new Label("Enter a name for player 2 (right)");
        label2.setTextFill(Color.WHITE);
        label2.setFont(Font.font("Verdana", 20));

        TextField name2 = new TextField();
        name2.setFont(Font.font("Verdana", 15));
        name2.setMaxWidth(80);

        Button continueButton = new Button("Continue");
        continueButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 0 0 0 0;");
        continueButton.setOnAction(event -> {
            if (name1.getText().contains(" ") || name1.getText().length() < 3) {
                event.consume();
            } else if (name2.getText().contains(" ") || name2.getText().length() < 3) {
                event.consume();
            } else {
                Score score = new Score();
                score.setPlayer1Name(name1.getText().substring(0, 3).toUpperCase());
                score.setPlayer2Name(name2.getText().substring(0, 3).toUpperCase());
                GameMode modeMenu = new GameMode();
                modeMenu.display(window, returnScene, score);
            }
        });

        Button returnButton = new Button("Return");

        returnButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 0 0 0 0;");
        returnButton.setOnAction(event -> {
            window.setScene(returnScene);
        });

        layout.getChildren().addAll(header, info, label1, name1, label2, name2, continueButton, returnButton);
        Scene playerNamesScene = new Scene(layout);

        window.setScene(playerNamesScene);

        window.show();
    }
}
