package com.dynatrace.pong.dto;

public class GameRequest {
    private String player1Name;
    private String player2Name;
    private int scorePlayer1;
    private int scorePlayer2;

    public GameRequest() {
    }

    public GameRequest(String player1Name, String player2Name, int scorePlayer1, int scorePlayer2) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }

    public void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }
}
