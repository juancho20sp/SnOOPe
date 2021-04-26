package presentation;

import domain.GUIConfiguration;
import domain.GameData;
import domain.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends DaddyPanel{
    private JPanel upperPanel;
    private JPanel boardPanel;
    private JButton mainMenuButton;
    private JLabel playerOnePointsLabel;
    private JLabel playerTwoPointsLabel;

    Player playerOne;
    Player playerTwo;

    // Game type
    private String gameType;

    // Main menu
    private MainMenu mainMenu;

    /**
     * Constructor for the GameBoard class
     */
    public GameBoard(JFrame frame, GUIConfiguration guiConfig, GameData data){
        super(frame, guiConfig, data);

        // Start game
        super.getGameData().setGameRunning(true);

        // Set Players
        this.setupPlayers();

        this.prepareLayout();
    }

    /**
     * Method for setting up the players
     */
    private void setupPlayers(){
        this.setPlayerOne(super.getGameData().getPlayerOne());

        if (super.getGameData().getGameType().equals(GameSetup.MULTIPLAYER)){
            this.setPlayerTwo(super.getGameData().getPlayerTwo());
        }

        if (super.getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)){
            this.setPlayerTwo(super.getGameData().getPlayerMachine());
        }
    }

    /**
     * Method for preparing the layout
     */
    private void prepareLayout(){
        // Main Panel
        setLayout(null);

        // Panel
        this.createUpperPanel();
        this.createGameBoardPanel();
    }

    /**
     * Method for creating the upper panel
     */
    private void createUpperPanel(){
        // Panel
        this.setUpperPanelLayout();

        // Buttons
        this.createUpperPanelButtons();

        // Labels
        this.createUpperPanelLabels();

        // Actions
        this.createUpperPanelActions();

        // Add
        this.addElementsToUpperPanel();
    }

    /**
     * Method for setting up the panels of the upper board
     */
    private void setUpperPanelLayout(){
        this.upperPanel = new JPanel();
        this.upperPanel.setBounds(0, 0, super.getWidth(), 30);
        this.upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.upperPanel.setLayout(new GridLayout(1, 3));
        //this.upperPanel.setBackground(this.config.getBackgroundColor());
    }

    /**
     * Method for creating the upper panel buttons
     */
    private void createUpperPanelButtons(){
        this.mainMenuButton = new JButton("Menú principal");
    }

    /**
     * Method for creating the upper panel labels
     */
   private void createUpperPanelLabels(){
        this.playerOnePointsLabel = new JLabel(this.getPlayerOne().getNumber() + " : 0", SwingConstants.CENTER);

        if (!this.getGameType().equals(GameSetup.SINGLE_PLAYER)){
            this.playerTwoPointsLabel = new JLabel(this.getPlayerTwo().getNumber() + " : 0", SwingConstants.CENTER);
        }

    }

    /**
     * Method for adding elements to the upper panel
     */
    private void addElementsToUpperPanel(){
        // Elements
        this.upperPanel.add(mainMenuButton);
        this.upperPanel.add(this.playerOnePointsLabel);

        if (!this.getGameType().equals(GameSetup.SINGLE_PLAYER)) {
            this.upperPanel.add(this.playerTwoPointsLabel);
        }

        // Add panel
        add(upperPanel);
    }

    /**+
     * Method for adding actions to the upper panel buttons
     */
    private void createUpperPanelActions(){
        // Main menu
        this.mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuClicked();
            }
        });
    }

    /**
     * Method for creating the game board panel
     * @return
     */
    private void createGameBoardPanel(){
        JLabel test = new JLabel("Aquí va el tablero de juego");

        this.boardPanel = new JPanel();
        this.boardPanel.setBackground(Color.YELLOW);
        this.boardPanel.setBounds(0, 30, super.getWidth(), super.getHeight() - 30);

        this.boardPanel.add(test);

        // Add panel
        add(boardPanel);
    }

    /**
     * Method for going to the menu
     */
    private void mainMenuClicked(){
        this.mainMenu = new MainMenu(super.getFrame(), super.getGUIConfig(), super.getGameData());

        changeCard(this.mainMenu, SnOOPe.GAME_PAUSE_MENU);
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getGameType() {
        return super.getGameData().getGameType();
    }
}
