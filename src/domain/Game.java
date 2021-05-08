package domain;

import domain.edibles.Edible;
import domain.players.Player;
import domain.players.PlayerMachine;
import domain.snakes.Snake;
import domain.snakes.SnakeP1;
import domain.snakes.SnakeP2;
import presentation.GameBoard;
import presentation.GameSetup;

import java.util.Random;

public class Game extends Thread {
    long lastGame = 0;
    long lastSnake1 = 0;
    long lastSnake2 = 0;

    int gameFrequency = 10;

    // Game data
    private GameData gameData;

    // GUI Configuration
    private GUIConfiguration guiConfiguration;

    // Game Board
    GameBoard board;

    // Players
    Player playerOne;
    Player playerTwo;
    PlayerMachine playerMachine;

    // Snakes
    Snake snake1;
    Snake snake2;

    // Fruits
    Edible fruit1;

    // Random
    Random random = new Random();


    /**
     * Method for setting up the players
     */
    private void setupPlayers() {
        /*this.setPlayerOne(super.getGameData().getPlayerOne());
        this.snake1 = new SnakeP1(3, new int[]{2, 0}, this.playerOne.getHeadColor(),
                this.playerOne.getBodyColor()
                , super.getGameData());


        if (super.getGameData().getGameType().equals(GameSetup.MULTIPLAYER)) {
            System.out.println(this.rows);

            this.setPlayerTwo(super.getGameData().getPlayerTwo());
            this.snake2 = new SnakeP2(3, new int[]{2, rows - 1},
                    this.playerTwo.getHeadColor(),
                    this.playerTwo.getBodyColor()
                    , super.getGameData());
        }

        if (super.getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)) {
            this.setPlayerTwo(super.getGameData().getPlayerMachine());
            this.snake2 = new SnakeP2(3, new int[]{cols - 2, 5},
                    this.getPlayerOne().getHeadColor(),
                    this.playerOne.getBodyColor()
                    , super.getGameData());
        }*/
    }


    /**
     * Method for running the thread
     */
    public void run() {
        while (true) {
            if ((System.currentTimeMillis() - lastGame) > gameFrequency) {
                if (this.gameData.isGameRunning()) {

                    // Velocity for the snake 1
                    if ((System.currentTimeMillis() - lastSnake1) > snake1.getFrequency()) {
                        snake1.move(snake1.getDirection());
                        snake1.updatePositions(this.getBoard().getRows(), this.getBoard().getCols());

                        lastSnake1 = System.currentTimeMillis();
                    }

                    // Velocity for the snake 2
                    if (!isSinglePlayer()) {
                        if ((System.currentTimeMillis() - lastSnake2) > snake2.getFrequency()) {
                            snake2.move(snake2.getDirection());
                            snake2.updatePositions(this.getBoard().getRows(), this.getBoard().getCols());

                            lastSnake2 = System.currentTimeMillis();
                        }
                    }

                    this.getBoard().refresh();

                    lastGame = System.currentTimeMillis();
                }
            }
        }
    }

    /**
     * Method for getting the game type
     *
     * @return The game type
     */
    public String getGameType() {
        return gameData.getGameType();
    }

    /**
     * Method that verifies if is only one player on the game
     *
     * @return true if it is a single player game, false otherwise
     */
    public boolean isSinglePlayer() {
        return getGameType().equals(GameSetup.SINGLE_PLAYER);
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public GUIConfiguration getGuiConfiguration() {
        return guiConfiguration;
    }

    public void setGuiConfiguration(GUIConfiguration guiConfiguration) {
        this.guiConfiguration = guiConfiguration;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }
}
