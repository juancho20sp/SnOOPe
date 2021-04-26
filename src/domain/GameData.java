package domain;

import java.awt.*;

public class GameData {
    private String namePlayerOne;
    private String namePlayerTwo;
    private Color colorPlayerOne;
    private Color colorPlayerTwo;
    private int pointsPlayerOne;
    private int pointsPlayerTwo;

    public String getNamePlayerOne() {
        return namePlayerOne;
    }

    public void setNamePlayerOne(String namePlayerOne) {
        this.namePlayerOne = namePlayerOne;
    }

    public String getNamePlayerTwo() {
        return namePlayerTwo;
    }

    public void setNamePlayerTwo(String namePlayerTwo) {
        this.namePlayerTwo = namePlayerTwo;
    }

    public Color getColorPlayerOne() {
        return colorPlayerOne;
    }

    public void setColorPlayerOne(Color colorPlayerOne) {
        this.colorPlayerOne = colorPlayerOne;
    }

    public Color getColorPlayerTwo() {
        return colorPlayerTwo;
    }

    public void setColorPlayerTwo(Color colorPlayerTwo) {
        this.colorPlayerTwo = colorPlayerTwo;
    }

    public int getPointsPlayerOne() {
        return pointsPlayerOne;
    }

    public void setPointsPlayerOne(int pointsPlayerOne) {
        this.pointsPlayerOne = pointsPlayerOne;
    }

    public int getPointsPlayerTwo() {
        return pointsPlayerTwo;
    }

    public void setPointsPlayerTwo(int pointsPlayerTwo) {
        this.pointsPlayerTwo = pointsPlayerTwo;
    }
}
