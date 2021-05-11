package domain;

import domain.edibles.Edible;
import domain.players.Player;
import domain.players.Machine;
import domain.players.PlayerOne;
import domain.snakes.SuperSnake;
import presentation.GameBoard;
import presentation.GameSetup;

import java.util.Random;

public class Game implements Runnable{
    long lastGame = 0;
    long lastSnake1 = 0;
    long lastSnake2 = 0;

    int gameFrequency = 50;

    // Game data
    private GameData gameData;

    // GUI Configuration
    private GUIConfiguration guiConfiguration;

    // Game Board
    GameBoard board;

    // Dimensions
    private int rows = 100;
    private int cols = 100;

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

    // Coordinates
    int[][] coordinates = new int[getRows()][getCols()];

    private boolean exit = false;


    /**
     * Method for starting the game
     */
    public void startGame(){
        thread = new Thread(this);

        this.exit = false;

        thread.start();

        this.createCoordinates();
    }

    /**
     * Method for pausing the game
     */
    public void pauseGame(){
        this.gameData.setGamePaused(true);
    }

    /**
     * Method for resuming the game
     */
    public void resumeGame(){
        this.gameData.setGamePaused(false);
    }

    public void stop(){
        this.exit = true;
    }

    /**
     * Method for creating the coordinates
     */
    private void createCoordinates(){
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                coordinates[i][j] = 0;
            }
        }
    }

    /**
     * Method for updating the coordinates
     * @param x The 'x' position
     * @param y The 'y' position
     * @param value The value
     */
    public void updateCoordinates(int x, int y, int value){
        this.coordinates[x][y] = value;
    }

    /**
     * Method for setting up the players
     */
   public void setupPlayers() {
       this.gameData.getPlayerOne().createSnake(new int[]{2,0}, new int[]{0,0}, this);


        if (getGameData().getGameType().equals(GameSetup.MULTIPLAYER)) {

            this.gameData.getPlayerTwo().createSnake(new int[]{getCols() - 1, getRows() - 1}, new int[]{getCols()-1,
                            getRows()-1}
            , this);

            this.gameData.getPlayerTwo().getSnake().setDirection(directions.LEFT);

        }

        if (getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)) {
            this.gameData.getPlayerMachine().createSnake(new int[]{getCols() - 1, getRows() - 1}, new int[]{getCols()-1,
                            getRows()-1}
                    , this);

            /*this.setPlayerTwo(super.getGameData().getPlayerMachine());
            this.snake2 = new SnakeP2(3, new int[]{cols - 2, 5},
                    this.getPlayerOne().getHeadColor(),
                    this.playerOne.getBodyColor()
                    , super.getGameData());*/
        }

   }


    /**
     * Method for running the thread
     */
    public void run() {
        long lastGame = 0;
        long lastSnake1 = 0;
        long lastSnake2 = 0;

        int gameFrequency = 10;

        while (!exit) {
            if ((System.currentTimeMillis() - lastGame) > gameFrequency) {
               // while (this.gameData.isGamePaused()) {
               //     try {
               //         Thread.sleep(1);
               //     } catch (InterruptedException e) {
               //         e.printStackTrace();
               //     }
                //}
                if (!this.gameData.isGamePaused()) {
                    if (this.gameData.isGameRunning()) {
                        if (this.getBoard() == null) {
                            System.out.println("running");
                            getSnake1().move(getSnake1().getDirection());
                            getSnake1().updatePositions(18, 37);
                        }


                        // Velocity for the snake 1
                        if ((System.currentTimeMillis() - lastSnake1) > getSnake1().getFrequency() && this.getBoard() != null) {
                            getSnake1().move(getSnake1().getDirection());
                            getSnake1().updatePositions(this.getBoard().getRows(), this.getBoard().getCols());

                            lastSnake1 = System.currentTimeMillis();
                        }

                        // Velocity for the snake 2
                        if (!isSinglePlayer()) {
                            if ((System.currentTimeMillis() - lastSnake2) > getSnake2().getFrequency()) {

                                if (gameData.getGameType().equals(GameSetup.MULTIPLAYER)) {
                                    getSnake2().move(getSnake2().getDirection());
                                } else {
                                    getSnake2().move();
                                }


                                getSnake2().updatePositions(this.getBoard().getRows(), this.getBoard().getCols());

                                lastSnake2 = System.currentTimeMillis();
                            }
                        }

                        if (this.getBoard() != null) {
                            this.getBoard().refresh();
                        }

                        lastGame = System.currentTimeMillis();
                    } else {
                        System.out.println("game over");
                        break;
                    }
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
        if(getGameData().getGameType().equals(GameSetup.MULTIPLAYER)){
            return this.gameData.getPlayerTwo().getSnake();
        } else if (getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)){
            return this.gameData.getPlayerMachine().getSnake();
        }

        return null;
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

    public int[][] getCoordinates() {
        return coordinates;
    }

    public static void main(String[] args) {
        Game game = new Game();


        game.gameData = new GameData();
        game.gameData.setGameRunning(true);
        game.gameData.setGameType(GameSetup.SINGLE_PLAYER);
        game.gameData.setPlayerOne(new PlayerOne());

        game.setupPlayers();
        game.startGame();

    }
}
