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

        this.addFruit();
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
