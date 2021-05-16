package domain;

import domain.players.Player;
import domain.players.Machine;

import java.io.Serializable;

public class GameData implements Serializable {
    private boolean isGameRunning = false;
    private boolean isGamePaused = false;
    private String gameType;
    private Player playerOne;
    private Player playerTwo;
    private Machine machine;

    private int frequencyValue = 20;

    private int fruitsTimer = 5000;

    //private boolean validationsActivated = true;
    private boolean validationsActivated = false;
    //private boolean isBoardInfinite = false;
    private boolean isBoardInfinite = true;

    // Game board
    private int gameBoardRows;
    private int getGameBoardCols;

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

    public Machine getPlayerMachine() {
        return machine;
    }

    public void setPlayerMachine(Machine machine) {
        this.machine = machine;
    }

    public int getFrequencyValue() {
        return frequencyValue;
    }

    public void setFrequencyValue(int frequencyValue) {
        this.frequencyValue = frequencyValue;
    }

    public boolean isGamePaused() {
        return isGamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        isGamePaused = gamePaused;
    }

    public boolean isValidationsActivated() {
        return validationsActivated;
    }

    public void setValidationsActivated(boolean validationsActivated) {
        this.validationsActivated = validationsActivated;
    }

    public boolean isBoardInfinite() {
        return isBoardInfinite;
    }

    public void setBoardInfinite(boolean boardInfinite) {
        isBoardInfinite = boardInfinite;
    }

    public int getGameBoardRows() {
        return gameBoardRows;
    }

    public void setGameBoardRows(int gameBoardRows) {
        this.gameBoardRows = gameBoardRows;
    }

    public int getGetGameBoardCols() {
        return getGameBoardCols;
    }

    public void setGetGameBoardCols(int getGameBoardCols) {
        this.getGameBoardCols = getGameBoardCols;
    }

    public int getFruitsTimer() {
        return fruitsTimer;
    }

    public void setFruitsTimer(int fruitsTimer) {
        this.fruitsTimer = fruitsTimer;
    }
}
