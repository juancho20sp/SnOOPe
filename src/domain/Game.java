package domain;

import domain.edibles.Edible;
import domain.edibles.PowerUp;
import domain.exceptions.SnOOPeExceptions;
import domain.snakes.SuperSnake;
import presentation.GameBoard;
import presentation.GameSetup;

import java.awt.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Random;

public class Game implements Runnable, Serializable {
    // Game data
    private GameData gameData;

    // GUI Configuration
    private GUIConfiguration guiConfiguration;

    // Game Board
    transient GameBoard gameBoard;

    // Board
    Board board;

    // Fruits
    Edible fruit1;
    Edible fruit2;

    // Power Ups
    PowerUp powerUp;

    // Dimensions
    private int rows = 100;
    private int cols = 100;

    // Thread
    transient Thread thread;

    // Fruit timers
    long timerFruit1 = 0;
    long timerFruit2 = 0;

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

        // Start thread
        thread.start();

        // Create board
        this.createBoard();

        // Add fruit 1
        //this.addFruit1();

        // Add power up
        //this.addPowerUp();

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

    /**
     * Method for stopping the game
     */
    public void stop(){
        this.exit = true;
    }

    /**
     * Method for creating the board
     */
    public void createBoard(){
        this.board = new Board(this);
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

        /*    this.gameData.getPlayerTwo().createSnake(new int[]{getCols() - 1, getRows() - 1}, new int[]{getCols()-1,
                            getRows()-1}
            , this);*/

            this.gameData.getPlayerTwo().createSnake(new int[]{board.getCols() - 3, board.getRows() - 1},
                    new int[]{board.getCols(),
                            board.getRows()-1}
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
     * Method for saving the game
     */
    public void save(File archivo) throws IOException {
        try {
            // Creamos el stream
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo.getAbsolutePath()));

            // Guardamos el objeto
            try {
                out.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Cerramos el stream
            out.close();

        } catch (IOException ex) {
            throw new IOException();
        }
    }

    public static Game open(File file) throws SnOOPeExceptions {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()));

            Game game = (Game) in.readObject();

            in.close();

            return game;
        }catch (IOException | ClassNotFoundException e){
            throw new SnOOPeExceptions(SnOOPeExceptions.ERROR_OPEN);
        }
    }

    /**
     * Method for running the thread
     */
    public void run() {
        long lastGame = 0;
        long lastSnake1 = 0;
        long lastSnake2 = 0;

        long powerUp = 0;

        int gameFrequency = 10;

        while (!exit) {
            if ((System.currentTimeMillis() - lastGame) > gameFrequency) {
                if (!this.gameData.isGamePaused()) {
                    if (this.gameData.isGameRunning()) {
                        if (this.getGameBoard() == null) {
                            System.out.println("running");
                            getSnake1().move(getSnake1().getDirection());
                            getSnake1().updatePositions(18, 37);
                        }


                        // Velocity for the snake 1
                        if ((System.currentTimeMillis() - lastSnake1) > getSnake1().getFrequency() && this.getGameBoard() != null) {
                            getSnake1().move(getSnake1().getDirection());
                            getSnake1().updatePositions(this.getGameBoard().getRows(), this.getGameBoard().getCols());

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


                                getSnake2().updatePositions(this.getGameBoard().getRows(), this.getGameBoard().getCols());

                                lastSnake2 = System.currentTimeMillis();
                            }
                        }

                        // Fruit 1 timer
                        if((System.currentTimeMillis() - getTimerFruit1()) > gameData.getFruitsTimer()){

                            addFruit1();
                            setTimerFruit1(System.currentTimeMillis());
                        }

                        // Fruit 2 timer
                        if (isSinglePlayer()){
                            if((System.currentTimeMillis() - getTimerFruit2()) > gameData.getFruitsTimer()){

                                addFruit2();
                                setTimerFruit2(System.currentTimeMillis());
                            }
                        }


                        if (this.getGameBoard() != null) {
                            this.getGameBoard().refresh();
                        }

                        // Power up
                        if (getPowerUp() == null){
                            powerUp = random.nextInt(10);
                            powerUp++;

                            if ((System.currentTimeMillis() % powerUp) == 0){
                                int a = random.nextInt(10);
                                int b = random.nextInt(10);

                                if (a == b) {
                                    //addPowerUp();
                                }
                            }
                            //addPowerUp();
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
     * Method for adding fruits
     */
    public void addFruit(){
        this.addFruit1();

        if (!isSinglePlayer()){
            this.addFruit2();
        }
    }

    /**
     * Method for adding the fruit1
     */
    public void addFruit1() {
        Edible newFruit = this.board.addFruit();

        this.setFruit1(newFruit);
    }

    /**
     * Method for adding the fruit2
     */
    public void addFruit2() {
        Edible newFruit = this.board.addFruit();

        this.setFruit2(newFruit);
    }

    /**
     * Method for adding power ups
     */
    public void addPowerUp() {
        PowerUp newPowerUp = this.board.addPowerUp();

        this.setPowerUp(newPowerUp);
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

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public SuperSnake getSnake1(){
        return this.gameData.getPlayerOne().getSnake();
    }

    public SuperSnake getSnake2(){
        if(this.gameData.getGameType().equals(GameSetup.MULTIPLAYER)){
            return this.gameData.getPlayerTwo().getSnake();
        } else if (this.gameData.getGameType().equals(GameSetup.PLAYER_MACHINE)){
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Edible getFruit1() {
        return fruit1;
    }

    public void setFruit1(Edible fruit1) {
        this.fruit1 = fruit1;
    }

    public Edible getFruit2() {
        return fruit2;
    }

    public void setFruit2(Edible fruit2) {
        this.fruit2 = fruit2;
    }

    public long getTimerFruit1() {
        return timerFruit1;
    }

    public void setTimerFruit1(long timerFruit1) {
        this.timerFruit1 = timerFruit1;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public long getTimerFruit2() {
        return timerFruit2;
    }

    public void setTimerFruit2(long timerFruit2) {
        this.timerFruit2 = timerFruit2;
    }

    /*public static void main(String[] args) {
        Game game = new Game();


        game.gameData = new GameData();
        game.gameData.setGameRunning(true);
        game.gameData.setGameType(GameSetup.SINGLE_PLAYER);
        game.gameData.setPlayerOne(new PlayerOne());

        game.setupPlayers();
        game.startGame();

    }*/
}
