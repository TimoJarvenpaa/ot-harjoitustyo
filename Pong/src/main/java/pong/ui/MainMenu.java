package pong.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class MainMenu extends Application {

    public static int WIDTH = 600;
    public static int HEIGHT = 400;

    @Override
    public void start(Stage window) throws Exception {
        
        VBox layout = new VBox(20);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setStyle("-fx-background-color: black;");
        layout.setAlignment(Pos.CENTER);

        Label label = new Label("PONK");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 50));

        Button playButton = new Button("Play");
        playButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;");
        
        Button controlsButton = new Button("Controls");
        controlsButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;");

        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;");
        quitButton.setOnAction(event -> {
            window.close();
        });

        layout.getChildren().addAll(label, playButton, controlsButton, quitButton);

        Scene menuScene = new Scene(layout);

        playButton.setOnAction(event -> {
            PlayerNames nameInputMenu = new PlayerNames();
            try {
                nameInputMenu.display(window, menuScene);
            } catch (Exception e) {

            }
        });
        
        controlsButton.setOnAction(event -> {
            Controls controls = new Controls();
            try {
                controls.display(window, menuScene);
            } catch (Exception e) {

            }
        });

        window.setTitle("Pong");
        window.setScene(menuScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
