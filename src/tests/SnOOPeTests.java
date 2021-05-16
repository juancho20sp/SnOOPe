package tests;


import static org.junit.Assert.*;

import domain.Game;
import domain.GameData;
import domain.edibles.Apple;
import domain.edibles.SpeedArrow;
import domain.edibles.Edible;
import domain.players.PlayerOne;
import org.junit.Before;
import org.junit.Test;
import presentation.GameSetup;

public class SnOOPeTests {
    private Game game;

    @Before
    public void setUp()
    {
        game = new Game();
        game.setGameData(new GameData());

        game.getGameData().setGameType(GameSetup.SINGLE_PLAYER);

        game.getGameData().setPlayerOne(new PlayerOne());

        game.setupPlayers();

        game.getCoordinates();
    }

    /**
     * Verifies if a new board is created
     */
    @Test
    public void shouldCreateBoard(){
        int sum = 0;
        for (int i = 0; i < game.getCoordinates().length; i++) {
            for (int q = 0; q < game.getCoordinates()[0].length; q++) {
                sum += game.getCoordinates()[i][q];
            }
        }

        assertEquals(0, sum);
    }

    /**
     * Verifies if a new apple is added is created
     */
    @Test
    public void shouldAddApple(){
        game.addFruit();

        int sum = 0;
        for (int i = 0; i < game.getCoordinates().length; i++) {
            for (int q = 0; q < game.getCoordinates()[0].length; q++) {
                sum += game.getCoordinates()[i][q];
            }
        }

        assertEquals(1, sum);
    }

    /**
     * Verifies if a new power up is created
     */
    @Test
    public void shouldAddPowerUp(){
        game.addPowerUp();

        int sum = 0;
        for (int i = 0; i < game.getCoordinates().length; i++) {
            for (int q = 0; q < game.getCoordinates()[0].length; q++) {
                sum += game.getCoordinates()[i][q];
            }
        }

        assertEquals(2, sum);
    }

    /**
     * Verifies if the speed is modified
     */
    @Test
    public void shouldSpeedUpPlayer(){
        int initialFreq = game.getSnake1().getFrequency();

        game.getGameData().getPlayerOne().setPowerUp(new SpeedArrow(2,2, null, 5));

        game.getGameData().getPlayerOne().usePowerUp();

        int finalFreq = game.getSnake1().getFrequency();


        assertTrue(initialFreq > finalFreq);
    }

    /**
     * Verifies if the player gets points after eating a fruit
     */
    @Test
    public void shouldIncreasePointsAfterFruit(){
        int initialPoints = game.getGameData().getPlayerOne().getPoints();

        Edible fruit = new Apple(2, 2, null);

        fruit.eatEdible(fruit, game.getGameData().getPlayerOne(), game);

        int finalPoints = game.getGameData().getPlayerOne().getPoints();

        assertTrue(initialPoints < finalPoints);
    }

    /**
     * Verifies if the player gets points after eating a fruit
     */
    @Test
    public void shouldIncreasePointsAfterPowerUp(){
        int initialPoints = game.getGameData().getPlayerOne().getPoints();

        System.out.println(initialPoints);

        game.getGameData().getPlayerOne().setPowerUp(new SpeedArrow(2,2, null, 5));

        game.getGameData().getPlayerOne().usePowerUp();

        int finalPoints = game.getGameData().getPlayerOne().getPoints();

        assertTrue(initialPoints == finalPoints);


    }

}
