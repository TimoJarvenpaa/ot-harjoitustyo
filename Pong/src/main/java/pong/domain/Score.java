package pong.domain;

/**
 * Yksittäisen pelin pisteistä ja pelaajien nimistä vastaava luokka.
 */
public class Score {

    private String player1Name;
    private int player1Score;
    private String player2Name;
    private int player2Score;

    public Score() {
        this.player1Score = 0;
        this.player2Score = 0;
        this.player1Name = "_P1";
        this.player2Name = "_P2";
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    /**
     * Pistetilanteen esitysmuoto, jota käytetään mm. pistelistan luomisessa.
     *
     * @return pelin loppupisteiden merkkijonoesitys
     */
    @Override
    public String toString() {
        return player1Name + "  " + player1Score + "    -    " + player2Score + "  " + player2Name;
    }
}
