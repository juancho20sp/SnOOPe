package domain;

import domain.edibles.Edible;
import domain.players.Player;
import domain.snakes.Snake;
import domain.snakes.SnakeP1;
import domain.snakes.SnakeP2;
import presentation.GameBoard;
import presentation.GameSetup;

import java.util.Random;

public class GUIConfiguration {
    private int cellSize = 25;
    private int frameWidth = 600;
    private int frameHeight = 630;

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

}
