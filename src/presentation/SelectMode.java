package presentation;

import domain.GUIConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMode extends JPanel {
    private JFrame frame;
    private JPanel selectModePanel;
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
        this.frame = frame;

        this.guiConfig = guiConfig;

        this.prepareLayout();

        add(this.selectModePanel);
    }

    /**
     * Method for preparing the layour
     */
    private void prepareLayout(){
        // Frame size
        Dimension frameSize = this.frame.getSize();

        // Main Panel
        setLayout(null);

        // Panel
        this.createPanel(frameSize);

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
    private void createPanel(Dimension frameSize){
        // Panel
        this.selectModePanel = new JPanel();
        this.selectModePanel.setSize(frameSize.width, frameSize.height);
        this.selectModePanel.setLayout(new GridLayout(7, 1));
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
        this.selectModePanel.add(this.title);

        // Buttons
        this.selectModePanel.add(this.singlePlayerButton);
        this.selectModePanel.add(this.playerAndPlayer);
        this.selectModePanel.add(this.playerAndComputer);
        this.selectModePanel.add(this.goBack);
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
        this.gameSetup = new GameSetup(this.frame, this.guiConfig, GameSetup.SINGLE_PLAYER);
        changeCard(this.gameSetup);
    }

    /**
     * Method for going to the multiplayer game setup
     */
    private void multiplayerClicked(){
        this.gameSetup = new GameSetup(this.frame, this.guiConfig, GameSetup.MULTIPLAYER);
        changeCard(this.gameSetup);
    }

    /**
     * Method for going to the player machine game setup
     */
    private void playerMachineClicked(){
        this.gameSetup = new GameSetup(this.frame, this.guiConfig, GameSetup.PLAYER_MACHINE);
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
