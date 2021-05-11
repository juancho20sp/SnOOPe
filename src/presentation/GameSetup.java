package presentation;

import domain.Game;
import domain.exceptions.SnOOPeExceptions;
import domain.players.Player;
import domain.players.Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSetup extends DaddyPanel{
    private JLabel title;
    private JButton namePlayerOne;
    private JButton colorPlayerOne;
    private JButton namePlayerTwo;
    private JButton colorPlayerTwo;
    private JButton machineType;
    private JButton startGame;
    private JButton goBack;

    // Game Modes
    public static final String SINGLE_PLAYER = "single player";
    public static final String MULTIPLAYER = "multiplayer";
    public static final String PLAYER_MACHINE = "player machine";

    // Players
    Player playerOne;
    Player playerTwo;
    Machine machine;

    // Color setup
    ColorSetup colorSetup;

    // Game board
    GameBoard gameBoard;

    // Machine type
    SelectMachineType selectMachineType;


    /**
     * Constructor for the GameSetup class
     */
    public GameSetup(JFrame frame, Game game){
        super(frame, game);

        // Players
        this.createPlayers(super.getGameData().getGameType());

        this.prepareLayout();
    }

    /**
     * Method for creating the players for the game
     * @param gameMode A game mode (static variable of this class)
     */
    private void createPlayers(String gameMode){
        this.playerOne = new Player();
        super.getGameData().setPlayerOne(this.playerOne);

        if (gameMode.equals(MULTIPLAYER)){
            this.playerTwo = new Player();
            super.getGameData().setPlayerTwo(this.playerTwo);
        } else {
            this.machine = new Machine();
            super.getGameData().setPlayerMachine(this.machine);
        }
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
        setLayout(new GridLayout(9, 1));
        //this.selectModePanel.setBackground(this.guiConfig.getBackgroundColor());
    }

    /**
     * Method for creating the labels of the view
     */
    private void createLabels(){
        this.title = new JLabel("Configurar juego:");
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Method for creating the buttons of the view
     */
    private void createButtons(){
        this.namePlayerOne = new JButton("Ingresar nombre del jugador");
        this.colorPlayerOne = new JButton("Seleccionar colores del jugador");

        switch (super.getGameData().getGameType()){
            case MULTIPLAYER:
                this.namePlayerOne = new JButton("Ingresar nombre del jugador 1");
                this.colorPlayerOne = new JButton("Seleccionar colores del jugador 1");
                this.namePlayerTwo = new JButton("Ingresar nombre del jugador 2");
                this.colorPlayerTwo = new JButton("Seleccionar colores del jugador 2");
                break;
            case PLAYER_MACHINE:
                this.namePlayerTwo = new JButton("Ingresar nombre de la máquina");
                this.colorPlayerTwo = new JButton("Seleccionar colores de la máquina");
                this.machineType = new JButton("Seleccionar el tipo de máquina");
                break;
        }

        this.startGame = new JButton("Iniciar juego");
        this.goBack = new JButton("Volver");
    }

    /**
     * Method for adding the elements to the panel
     */
    private void addElements(){
        System.out.println(super.getGameData().getGameType());

        // Label
        add(this.title);

        // Buttons
        if (super.getGameData().getGameType().equals(SINGLE_PLAYER)){
            add(this.namePlayerOne);
            add(this.colorPlayerOne);
        } else {
            add(this.namePlayerOne);
            add(this.colorPlayerOne);
            add(this.namePlayerTwo);
            add(this.colorPlayerTwo);
        }

        if (super.getGameData().getGameType().equals(PLAYER_MACHINE)){
            add(this.machineType);
        }

        add(this.startGame);
        add(this.goBack);
    }

    /**
     * Method for setting up the button actions
     */
    private void addActionsToButtons(){
        this.namePlayerOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerOneName();
            }
        });

        this.colorPlayerOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorSetupClicked(playerOne);
            }
        });

        if (!super.getGameData().getGameType().equals(SINGLE_PLAYER)){
            this.namePlayerTwo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setPlayerTwoName();
                }
            });

            this.colorPlayerTwo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    colorSetupClicked(playerTwo);
                }
            });

            // Machine
            if (super.getGameData().getGameType().equals(PLAYER_MACHINE)){
                this.machineType.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        machineTypeClicked();
                    }
                });
            }
        }


        this.startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        this.goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToCard(SnOOPe.SELECT_GAME_MODE);
            }
        });
    }

    /**
     * Method for adding the name of the player one
     */
    private void setPlayerOneName(){
        String name = JOptionPane.showInputDialog("Ingresar nombre del jugador");

        GameSetup.super.getGameData().getPlayerOne().setName(name);
    }

    /**
     * Method for adding the name of the player two
     */
    private void setPlayerTwoName(){
        String name = JOptionPane.showInputDialog("Ingresar nombre del jugador");

        GameSetup.super.getGameData().getPlayerTwo().setName(name);
    }

    /**
     * Method for going to the ColorSetupView
     * @param player The player that will be modified
     */
    private void colorSetupClicked(Player player){
        this.colorSetup = new ColorSetup(super.getFrame(), super.getGame(), player);

        changeCard(this.colorSetup, SnOOPe.SELECT_GAME_COLOR_SETUP);

    }

    /**
     * Method for selecting the machine type
     */
    private void machineTypeClicked(){
        this.selectMachineType = new SelectMachineType(super.getFrame(), super.getGame());

        changeCard(this.selectMachineType, SnOOPe.SELECT_MACHINE_TYPE);
    }

    /**
     * Method for starting up the game
     */
    private void startGame(){
        if (this.validateFields() || true){
            this.gameBoard = new GameBoard(super.getFrame(), super.getGame());

            getGame().getGameData().setGamePaused(false);

            changeCard(this.gameBoard, SnOOPe.GAME_BOARD);
        }

    }

    /**
     * Validate fields
     * @return true if the fields are filled, false otherwise
     */
    private boolean validateFields(){
        boolean valid = true;

        Player player1 = super.getGameData().getPlayerOne();
        Player player2 = super.getGameData().getPlayerTwo();
        Machine machine = super.getGameData().getPlayerMachine();

        // First player
        if (player1.getName() == null || player1.getHeadColor() == null || player1.getBodyColor() == null){
            valid = false;
        }

        // Multiplayer
        if (super.getGameData().getGameType().equals(MULTIPLAYER)){
            if (player2.getName() == null || player2.getHeadColor() == null || player2.getBodyColor() == null){
                valid = false;
            }
        }

        // Machine
        if (super.getGameData().getGameType().equals(PLAYER_MACHINE)){
            if (machine.getHeadColor() == null || machine.getBodyColor() == null){
                valid = false;
            }

            if (machine.getMachineType() == null){
                valid = false;
            }
        }

        // All fields are mandatory
        if (!valid){
            JOptionPane.showMessageDialog(null, SnOOPeExceptions.ALL_FIELDS_ARE_MANDATORY);
        }

        // Colors
        if (getGameData().getGameType().equals(MULTIPLAYER)){
            if (getGameData().getPlayerTwo().getHeadColor() == getGameData().getPlayerOne().getHeadColor() ||
                    getGameData().getPlayerTwo().getHeadColor() == getGameData().getPlayerOne().getBodyColor() ||
                    getGameData().getPlayerTwo().getBodyColor() == getGameData().getPlayerOne().getHeadColor() ||
                    getGameData().getPlayerTwo().getBodyColor() == getGameData().getPlayerOne().getBodyColor()){
                JOptionPane.showMessageDialog(null, SnOOPeExceptions.SNAKES_MUST_BE_DIFFERENT);
                valid = false;
            }
        }

        if (getGameData().getGameType().equals(PLAYER_MACHINE)){
            if (getGameData().getPlayerMachine().getHeadColor() == getGameData().getPlayerOne().getHeadColor() ||
                    getGameData().getPlayerMachine().getHeadColor() == getGameData().getPlayerOne().getBodyColor() ||
                    getGameData().getPlayerMachine().getBodyColor() == getGameData().getPlayerOne().getHeadColor() ||
                    getGameData().getPlayerMachine().getBodyColor() == getGameData().getPlayerOne().getBodyColor()){
                JOptionPane.showMessageDialog(null, SnOOPeExceptions.SNAKES_MUST_BE_DIFFERENT);
                valid = false;
            }
        }


        return valid;
    }


}
