package pong.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

/**
 * Pelin kontrollit esittelevä näkymä.
 */
public class Controls {

    public void display(Stage window, Scene returnScene) {
        
        VBox layout = new VBox(30);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setStyle("-fx-background-color: black;");
        layout.setAlignment(Pos.CENTER);

        Label header = new Label("Controls");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font("Verdana", 30));
        header.setStyle("-fx-padding: 0 0 40 0;");
        
        Label label1 = new Label("Move left paddle:    W/S");
        label1.setTextFill(Color.WHITE);
        label1.setFont(Font.font("Verdana", 20));
        
        Label label2 = new Label("Move right paddle:    ↑/↓");
        label2.setTextFill(Color.WHITE);
        label2.setFont(Font.font("Verdana", 20));
        
        Label label3 = new Label("Exit to main menu:    ESC");
        label3.setTextFill(Color.WHITE);
        label3.setFont(Font.font("Verdana", 20));
        
        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 40 0 0 0;");
        returnButton.setOnAction(event -> {
            window.setScene(returnScene);
        });

        layout.getChildren().addAll(header, label1, label2, label3, returnButton);
        Scene controlsScene = new Scene(layout);

        window.setScene(controlsScene);
        window.show();
    }

}
