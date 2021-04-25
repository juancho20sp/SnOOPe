package presentation;

import domain.GUIConfiguration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSetup extends JPanel{
    private JFrame frame;
    private JPanel gameSetupPanel;
    private JLabel title;
    private JButton namePlayerOne;
    private JButton colorPlayerOne;
    private JButton namePlayerTwo;
    private JButton colorPlayerTwo;
    private JButton startGame;
    private JButton goBack;

    // Config
    private GUIConfiguration guiConfig;

    // Game Modes
    public static final String SINGLE_PLAYER = "single player";
    public static final String MULTIPLAYER = "multiplayer";
    public static final String PLAYER_MACHINE = "player machine";

    // Strings
    private String gameMode;


    /**
     * Constructor for the GameSetup class
     */
    public GameSetup(JFrame frame, GUIConfiguration guiConfig, String gameMode){
        this.frame = frame;

        this.guiConfig = guiConfig;

        this.gameMode = gameMode;

        this.prepareLayout();

        add(this.gameSetupPanel);
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
        this.gameSetupPanel = new JPanel();
        this.gameSetupPanel.setSize(frameSize.width, frameSize.height);
        this.gameSetupPanel.setLayout(new GridLayout(8, 1));
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

        switch (this.gameMode){
            case MULTIPLAYER:
                this.namePlayerOne = new JButton("Ingresar nombre del jugador 1");
                this.colorPlayerOne = new JButton("Seleccionar colores del jugador 1");
                this.namePlayerTwo = new JButton("Ingresar nombre del jugador 2");
                this.colorPlayerTwo = new JButton("Seleccionar colores del jugador 2");
                break;
            case PLAYER_MACHINE:
                this.namePlayerTwo = new JButton("Ingresar nombre de la máquina");
                this.colorPlayerTwo = new JButton("Seleccionar colores de la máquina");
                break;
        }

        this.startGame = new JButton("Iniciar juego");
        this.goBack = new JButton("Volver");
    }

    /**
     * Method for adding the elements to the panel
     */
    private void addElements(){
        System.out.println(this.gameMode);

        // Label
        this.gameSetupPanel.add(this.title);

        // Buttons
        if (this.gameMode.equals(SINGLE_PLAYER)){
            this.gameSetupPanel.add(this.namePlayerOne);
            this.gameSetupPanel.add(this.colorPlayerOne);;
        } else {
            this.gameSetupPanel.add(this.namePlayerOne);
            this.gameSetupPanel.add(this.colorPlayerOne);
            this.gameSetupPanel.add(this.namePlayerTwo);
            this.gameSetupPanel.add(this.colorPlayerTwo);
        }

        this.gameSetupPanel.add(this.startGame);
        this.gameSetupPanel.add(this.goBack);
    }

    /**
     * Method for setting up the button actions
     */
    private void addActionsToButtons(){
        this.namePlayerOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "name player one clicked");
            }
        });

        this.colorPlayerOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "color player one clicked");
            }
        });

        if (!this.gameMode.equals(SINGLE_PLAYER)){
            this.namePlayerTwo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "name player two clicked");
                }
            });

            this.colorPlayerTwo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "color player two clicked");
                }
            });
        }


        this.startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "start game clicked");
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
     * Method for going back
     */
    private void goBack(){
        SnOOPe.selectCard(SnOOPe.SELECT_GAME_MODE);
    }


}
