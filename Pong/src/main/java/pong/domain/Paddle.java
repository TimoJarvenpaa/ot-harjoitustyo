package pong.domain;

import javafx.scene.shape.Rectangle;
import static pong.ui.MainMenu.HEIGHT;
import static pong.ui.MainMenu.WIDTH;

/**
 * Pelissä käytettäviä mailoja kuvaava luokka.
 */
public class Paddle {

    private Rectangle paddle;
    private int paddleWidth = 10;
    private int paddleHeight = 100;
    private int y = (HEIGHT / 2) - (paddleHeight / 2);
    private int x;
    private int score;
    private boolean left;

    /**
     * Mailan konstruktori, jossa mailan sijainti pelialueella määräytyy
     * parametrin left perusteella.
     *
     * @param left muuttuja, jonka tarkoituksena on määrätä, onko luotava maila
     * pelialueen vasemmalla vai oikealla puolella
     */
    public Paddle(boolean left) {
        this.left = left;
        if (left) {
            x = paddleWidth;
            this.paddle = new Rectangle(x, y, paddleWidth, paddleHeight);
        } else {
            x = WIDTH - (2 * paddleWidth);
            this.paddle = new Rectangle(x, y, paddleWidth, paddleHeight);
        }
        paddle.setFill(javafx.scene.paint.Color.WHITE);
        this.score = 0;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public int getScore() {
        return this.score;
    }

    /**
     * Metodi, jota käytetään päivittämään pelaajaan liittyvää pistelaskuria
     * pelin aikana.
     */
    public void incrementScore() {
        this.score++;
    }

    /**
     * Metodi, joka liikuttaa mailaa pelialueella pystyakselin suuntaisesti parametrin amount verran.
     * Jos 
     *
     * @param amount pystyakselilla liikkuttava askelmäärä pikseleinä. Huom. Y-akselin arvot kasvavat alaspäin 
     * ja pienenevät ylöspäin mennessä.
     *
     * @return true jos maila voi liikkua pyydettyyn suuntaan, muuten false
     */
    public boolean move(int amount) {
        if (this.paddle.getY() + amount >= 0 && this.paddle.getY() + amount <= HEIGHT - paddleHeight) {
            this.paddle.setY(this.paddle.getY() + amount);
            return true;
        }
        return false;
    }

    public boolean isLeft() {
        return this.left;
    }

    public int getPaddleWidth() {
        return this.paddleWidth;
    }

    public int getPaddleHeight() {
        return this.paddleHeight;
    }

}
