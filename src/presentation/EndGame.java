package presentation;

import domain.GUIConfiguration;
import domain.GameData;
import domain.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGame extends DaddyPanel{
    private JLabel title;
    private JLabel firstPlace;
    private JLabel secondPlace;
    private JButton endGameButton;

    /**
     * Constructor for the GameSetup class
     */
    public EndGame(JFrame frame, GUIConfiguration guiConfig, GameData data){
        super(frame, guiConfig, data);

        super.setFocusable(false);

        setFocusable(true);

        // Layout
        this.prepareLayout();
    }

    /**
     * Method for preparing the layout
     */
    private void prepareLayout(){
        // Main Panel
        setLayout(null);

        // Panel
        this.createPanel();

        // Label
        this.createLabels();

        // Buttons
        this.createButtons();

        // Actions
        this.addActionsToButtons();

        // Add
        this.addElements();
    }

    /**
     * Method for creating the main panel of the view
     */
    private void createPanel(){
        // Panel
        setLayout(new GridLayout(4, 1));
        //this.selectModePanel.setBackground(this.guiConfig.getBackgroundColor());
    }

    /**
     * Method for creating the labels of the view
     */
    private void createLabels(){
        this.title = new JLabel("¡Game over!");
        this.title.setHorizontalAlignment(SwingConstants.CENTER);

        this.firstPlace =
                new JLabel("1. " + this.getPositions()[0].getName() + " : " + this.getPositions()[0].getPoints() + " " +
                        "puntos");
        this.firstPlace.setHorizontalAlignment(SwingConstants.CENTER);

        if (!super.getGameData().getGameType().equals(GameSetup.SINGLE_PLAYER)){
            this.secondPlace =
                    new JLabel("2. " + this.getPositions()[1].getName() + " : " + this.getPositions()[1].getPoints() + " " +
                            "puntos");
            this.secondPlace.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    /**
     * Method for creating the buttons of the view
     */
    private void createButtons(){
        this.endGameButton = new JButton("Terminar juego");
    }

    /**
     * Method for adding the elements to the panel
     */
    private void addElements() {
        // Label
        add(this.title);

        // First place
        add(this.firstPlace);

        // Second place
        if (!super.getGameData().getGameType().equals(GameSetup.SINGLE_PLAYER)) {
            add(this.secondPlace);
        }

        add(this.endGameButton);
    }

    /**
     * Method for setting up the button actions
     */
    private void addActionsToButtons(){
        this.endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
                endGame();
            }
        });
    }

    /**
     * Method for ending the game
     */
    private void endGame(){
        System.out.println("clicked");
        SnOOPe.selectCard(SnOOPe.MAIN_MENU);
    }


    /**
     * Method for getting the players sorted by points
     * @return The list of players
     */
    private Player[] getPositions(){
        String gameType = super.getGameData().getGameType();

        Player[] players = {this.getPlayerOne(), this.getPlayerTwo(), this.getMachine()};

        // Single player
        if (gameType.equals(GameSetup.SINGLE_PLAYER)){
            return new Player[]{players[0], null};
        }

        // Multiplayer
        if (gameType.equals(GameSetup.MULTIPLAYER)){
            if (players[0].getPoints() > players[1].getPoints()){
                return new Player[]{players[0], players[1]};
            }

            return new Player[]{players[1], players[0]};
        }

        // Machine
        if (gameType.equals(GameSetup.PLAYER_MACHINE)){
            if (players[0].getPoints() > players[2].getPoints()){
                return new Player[]{players[0], players[2]};
            }

            return new Player[]{players[2], players[0]};
        }

        return new Player[]{null, null};
    }

    private Player getPlayerOne(){
        return super.getGameData().getPlayerOne();
    }

    private Player getPlayerTwo(){
        return super.getGameData().getPlayerTwo();
    }

    private Player getMachine(){
        return super.getGameData().getPlayerMachine();
    }
}
