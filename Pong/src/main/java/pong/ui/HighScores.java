package pong.ui;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pong.dao.SQLScoreDAO;
import pong.domain.Score;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

/**
 * Aikarajoitettujen otteluiden lopputulokset esittävä näkymä.
 */
public class HighScores {
    
    public void display(Stage window, Scene returnScene) {

        VBox layout = new VBox(30);
        layout.setPrefSize(WIDTH, HEIGHT);
        layout.setStyle("-fx-background-color: black;");
        layout.setAlignment(Pos.CENTER);

        Label header = new Label("High-Scores");
        header.setTextFill(Color.WHITE);
        header.setFont(Font.font("Verdana", 30));
        header.setStyle("-fx-padding: 0 0 40 0;");


        Button returnButton = new Button("Return");
        returnButton.setStyle("-fx-background-color: black;-fx-text-fill: white;-fx-font-size: 20pt;-fx-padding: 40 0 0 0;");
        returnButton.setOnAction(event -> {
            window.setScene(returnScene);
        });
        
        VBox scoreNodes = new VBox(10);
        scoreNodes.setAlignment(Pos.CENTER);
        
        List<Score> scores;
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background: black; -fx-border-color: black;");
        
        try {
            String scoreFile = getScoreFile();
            SQLScoreDAO scoreDAO = new SQLScoreDAO(scoreFile);
            scores = scoreDAO.getAll();
            scores.forEach((score) -> scoreNodes.getChildren().add(createScoreNode(score)));
        } catch (Exception e) {

        }
        
        scrollPane.setContent(scoreNodes);

        layout.getChildren().addAll(header, scrollPane, returnButton);
        Scene scoreScene = new Scene(layout);

        window.setScene(scoreScene);
        window.show();
    }

    public String getScoreFile() throws Exception {

        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        return scoreFile;
    }
    
    public Node createScoreNode(Score score) {
        HBox box = new HBox(10);
        box.setStyle("-fx-background-color: black;");
        box.setAlignment(Pos.CENTER);
        Label label = new Label(score.toString());
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Verdana", 20));
        box.getChildren().addAll(label);
        return box;
    }

}
