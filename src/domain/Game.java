package domain;

import domain.edibles.Edible;
import domain.players.Player;
import domain.players.Machine;
import domain.snakes.SuperSnake;
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

    // Dimensions
    private int rows = 18;
    private int cols = 37;

    // Players
    Player playerOne;
    Player playerTwo;
    Machine machine;

    // Snakes
    SuperSnake snake1;
    SuperSnake snake2;

    // Fruits
    Edible fruit1;

    // Thread
    Thread thread;

    // Random
    Random random = new Random();

    /**
     * Method for starting the game
     */
    public void startGame(){
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Method for setting up the players
     */
   public void setupPlayers() {
       this.gameData.getPlayerOne().createSnake(new int[]{2,0}, new int[]{0,0}, this);

        /*this.snake1 = new SnakeP1(3, new int[]{2, 0}, this.playerOne.getHeadColor(),
                this.playerOne.getBodyColor()
                , super.getGameData());*/


        if (getGameData().getGameType().equals(GameSetup.MULTIPLAYER)) {
            System.out.println(this.rows);

            //this.setPlayerTwo(getGameData().getPlayerTwo());
            this.gameData.getPlayerTwo().createSnake(new int[]{getCols() - 1, getRows() - 1}, new int[]{getCols()-1,
                            getRows()-1}
            , this);

        }

        /*if (super.getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)) {
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
                    if ((System.currentTimeMillis() - lastSnake1) > getSnake1().getFrequency()) {
                        getSnake1().move(getSnake1().getDirection());
                        getSnake1().updatePositions(this.getBoard().getRows(), this.getBoard().getCols());

                        lastSnake1 = System.currentTimeMillis();
                    }

                    // Velocity for the snake 2
                    if (!isSinglePlayer()) {
                        if ((System.currentTimeMillis() - lastSnake2) > getSnake2().getFrequency()) {
                            getSnake2().move(getSnake2().getDirection());
                            getSnake2().updatePositions(this.getBoard().getRows(), this.getBoard().getCols());

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

    public SuperSnake getSnake1(){
        return this.gameData.getPlayerOne().getSnake();
    }

    public SuperSnake getSnake2(){
        return this.gameData.getPlayerTwo().getSnake();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
