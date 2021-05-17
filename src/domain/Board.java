package domain;

import domain.edibles.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Board {
    private int rows;
    private int cols;

    // Game
    private Game game;

    // List of fruits
    HashMap<Integer, Edible> fruits;

    // List of power ups
    HashMap<Integer, PowerUp> powerUps;

    // Edible board
    private Edible[][] board;

    // Random
    Random random = new Random();

    /**
     * Constructor of the Board class
     * @param game An instance of the game class
     */
    public Board(Game game){
        this.game = game;

        // Create the board
        this.createBoard();

        // Create the list of fruits
        this.createFruitList();

        // Create the list of power ups
        this.createPowerUpList();


        this.addFruit();
        //this.addPowerUp();
    }

    /**
     * Method for calculating rows and cols
     */
    private void createBoard(){
        this.rows = game.getGameData().getGameBoardRows();
        this.cols = game.getGameData().getGetGameBoardCols();

        for (int i = 0; i < this.cols; i++) {
            for (int j = 0; j < this.rows; j++) {
                this.board = null;
            }
        }
    }

    /**
     * Method for creating the fruit hashmap
     */
    private void createFruitList(){
        fruits = new HashMap<Integer, Edible>();

        // All fruits
        Apple apple = new Apple(0, 0, null);
        Rainbow rainbow = new Rainbow(0, 0, null);
        Candy candy = new Candy(0,0, null);
        Venom venom = new Venom(0,0,null);

        // Add fruits
        fruits.put(0, apple);
        fruits.put(1, rainbow);
        fruits.put(2, candy);
        fruits.put(3, venom);

        //fruits.put(0, venom);
    }

    /**
     * Method for creating the power up hashmap
     */
    private void createPowerUpList(){
        powerUps = new HashMap<Integer, PowerUp>();

        // All power ups
        SpeedArrow speedArrow = new SpeedArrow(0, 0, null, 5);
        BadArrow badArrow = new BadArrow(0, 0, null, 5);
        Division division = new Division(0, 0, null, 5);
        Brick brick = new Brick(0,0, null, 5);
        FireStar fireStar = new FireStar(0,0,null, 5);
        Lupa lupa = new Lupa(0, 0, null, 5);

        // Add fruits
        powerUps.put(0, speedArrow);
        powerUps.put(1, badArrow);
        powerUps.put(2, division);
        powerUps.put(3, brick);
        powerUps.put(4, fireStar);
        powerUps.put(5, lupa);

        //fruits.put(0, venom);
    }

    /**
     * Method for creating fruits
     * @return The created fruit
     */
    public Edible addFruit(){
        ArrayList<Color> colors = this.getSnakeColors();

        int x = random.nextInt(cols - 1);
        int y = random.nextInt(rows - 1);

        if (x == 0) {
            x++;
        }

        if (x == cols) {
            x--;
        }

        if (y == 0) {
            y++;
        }

        if (y == rows) {
            y--;
        }

        int option = random.nextInt(fruits.size());
        int color = random.nextInt(colors.size());

        if (this.isOccupied(x, y)){
            this.addFruit();
        }

        // Pick the fruit
        Edible selectedFruit = fruits.get(option);
        //Edible selectedFruit = fruits.get(0);

        // Edit the fruit
        selectedFruit.setX(x);
        selectedFruit.setY(y);
        selectedFruit.setColor(colors.get(color));

        return selectedFruit;
    }

    /**
     * Method for getting the snake colors
     * @return An array with all the colors of the snakes
     */
    private ArrayList<Color> getSnakeColors(){
        ArrayList<Color> colors = new ArrayList<>();

        colors.add(game.getGameData().getPlayerOne().getHeadColor());
        colors.add(game.getGameData().getPlayerOne().getBodyColor());


        if (!game.isSinglePlayer()){
            colors.add(game.getSnake2().getHeadColor());
            colors.add(game.getSnake2().getBodyColor());
        }

        return colors;
    }

    /**
     * Method for adding power ups
     */
    public PowerUp addPowerUp(){
        ArrayList<Color> colors = new ArrayList<>();

        colors.add(Color.yellow);

        int x = random.nextInt(cols - 1);
        int y = random.nextInt(rows - 1);

        if (x == 0) {
            x++;
        }

        if (x == cols) {
            x--;
        }

        if (y == 0) {
            y++;
        }

        if (y == rows) {
            y--;
        }

        int option = random.nextInt(powerUps.size());
        int color = random.nextInt(colors.size());

        if (this.isOccupied(x, y)){
            this.addPowerUp();
        }

        // Pick the fruit
        PowerUp selectedPowerUp = powerUps.get(option);
        //Edible selectedFruit = fruits.get(0);

        // Edit the fruit
        selectedPowerUp.setX(x);
        selectedPowerUp.setY(y);
        selectedPowerUp.setColor(colors.get(color));

        return selectedPowerUp;
    }

    /**
     * Method for verifying if a snake is in a given position
     * @return true if the snake is in the given position, false otherwise
     */
    private boolean isOccupied(int x, int y){
        int[] coordinate = new int[]{x, y};

        if (game.getSnake1().getPositions().contains(coordinate) || game.getSnake1().getHeadPosition().equals(coordinate)){
            return true;
        }

        if (!game.isSinglePlayer()){
            if (game.getSnake2().getPositions().contains(coordinate) || game.getSnake2().getHeadPosition().equals(coordinate)){
                return true;
            }
        }

        return false;
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
