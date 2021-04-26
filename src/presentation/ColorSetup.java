package presentation;

import domain.GUIConfiguration;
import domain.GameData;
import domain.exceptions.SnOOPeExceptions;
import domain.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorSetup extends DaddyPanel{
    private JLabel title;
    private JButton headButton;
    private JButton bodyButton;
    private JButton goBack;

    // Config
    private GUIConfiguration guiConfig;
    private GameData data;

    // Player
    private Player player;

    /**
     * Constructor for the ColorSetup class
     */
    public ColorSetup(JFrame frame, GUIConfiguration guiConfig, GameData data, Player player){
        super(frame, guiConfig, data);

        // Configuration
        this.guiConfig = guiConfig;
        this.data = data;

        // Player
        this.player = player;


            this.prepareLayout();



    }

    /**
     * Method for preparing the layout
     */
    private void prepareLayout() {
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
    private void createLabels() {
            this.title = new JLabel("Colores del jugador " + player.getNumber() + ":");
            this.title.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Method for creating the buttons of the view
     */
    private void createButtons(){
        this.headButton = new JButton("Seleccionar color de la cabeza");
        this.bodyButton = new JButton("Seleccionar color del cuerpo");
        this.goBack = new JButton("Volver");
    }

    /**
     * Method for adding the elements to the panel
     */
    private void addElements(){
        // Label
        add(this.title);

        // Buttons
        add(this.headButton);
        add(this.bodyButton);
        add(this.goBack);
    }

    /**
     * Method for setting up the button actions
     */
    private void addActionsToButtons(){
        this.headButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "head color clicked");
            }
        });

        this.bodyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "body color clicked");
            }
        });

        this.goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToCard(SnOOPe.SELECT_GAME_SETUP);
            }
        });
    }
}
