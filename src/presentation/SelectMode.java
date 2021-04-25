package presentation;

import domain.GUIConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMode extends DaddyPanel {
    private JLabel title;
    private JButton singlePlayerButton;
    private JButton playerAndPlayer;
    private JButton playerAndComputer;
    private JButton goBack;

    // Config
    private GUIConfiguration guiConfig;

    // Game setup
    private GameSetup gameSetup;

    /**
     * Constructor for the SelectMode class
     */
    public SelectMode(JFrame frame, GUIConfiguration guiConfig){
        super(frame);

        this.guiConfig = guiConfig;

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
        setLayout(new GridLayout(7, 1));
        //this.selectModePanel.setBackground(this.guiConfig.getBackgroundColor());
    }

    /**
     * Method for creating the labels of the view
     */
    private void createLabels(){
        this.title = new JLabel("Modo de juego:");
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Method for creating the buttons of the view
     */
    private void createButtons(){
        this.singlePlayerButton = new JButton("Un jugador");
        this.playerAndPlayer = new JButton("Jugador Vs. Jugador");
        this.playerAndComputer = new JButton("Jugador Vs. Máquina");
        this.goBack = new JButton("Volver");
    }

    /**
     * Method for adding the elements to the panel
     */
    private void addElements(){
        // Label
       add(this.title);

        // Buttons
        add(this.singlePlayerButton);
        add(this.playerAndPlayer);
        add(this.playerAndComputer);
        add(this.goBack);
    }

    /**
     * Method for setting up the button actions
     */
    private void addActionsToButtons(){
        this.singlePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singlePlayerClicked();
            }
        });

        this.playerAndPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiplayerClicked();
            }
        });

        this.playerAndComputer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMachineClicked();
            }
        });

        this.goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
    }

    /**
     * Method for going to the single player game setup
     */
    private void singlePlayerClicked(){
        this.gameSetup = new GameSetup(super.getFrame(), this.guiConfig, GameSetup.SINGLE_PLAYER);
        changeCard(this.gameSetup);
    }

    /**
     * Method for going to the multiplayer game setup
     */
    private void multiplayerClicked(){
        this.gameSetup = new GameSetup(super.getFrame(), this.guiConfig, GameSetup.MULTIPLAYER);
        changeCard(this.gameSetup);
    }

    /**
     * Method for going to the player machine game setup
     */
    private void playerMachineClicked(){
        this.gameSetup = new GameSetup(super.getFrame(), this.guiConfig, GameSetup.PLAYER_MACHINE);
        changeCard(this.gameSetup);
    }

    /**
     * Method for switching the main frame cards
     */
    private void changeCard(GameSetup gameSetup){
        SnOOPe.cards.add(gameSetup, SnOOPe.SELECT_GAME_SETUP);
        SnOOPe.selectCard(SnOOPe.SELECT_GAME_SETUP);
    }

    /**
     * Method for going back
     */
    private void goBack(){
        SnOOPe.selectCard(SnOOPe.MAIN_MENU);
    }
}
