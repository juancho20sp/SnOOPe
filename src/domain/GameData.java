package domain;

import domain.players.Player;

import java.awt.*;

public class GameData {
    private boolean isGameRunning = false;
    private String gameType;
    private Player playerOne;
    private Player playerTwo;
    private Player playerMachine;


    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getPlayerMachine() {
        return playerMachine;
    }

    public void setPlayerMachine(Player playerMachine) {
        this.playerMachine = playerMachine;
    }
}
