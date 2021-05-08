package presentation;

import domain.Game;
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

    // Player
    private Player player;

    /**
     * Constructor for the ColorSetup class
     */
    public ColorSetup(JFrame frame, Game game, Player player){
        super(frame, game);

        // Player
        this.player = player;

        // Layout
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
                setHeadColor(player);
            }
        });

        this.bodyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBodyColor(player);
            }
        });

        this.goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToCard(SnOOPe.SELECT_GAME_SETUP);
            }
        });
    }

    /**
     * Method for selecting the head color
     * @param player The player choosing the colors
     */
    private void setHeadColor(Player player){
        player.setHeadColor(this.chooseColor("Seleccione el color de la cabeza de la serpiente: "));
    }

    /**
     * Method for selecting the body color
     * @param player The player choosing the colors
     */
    private void setBodyColor(Player player){
        player.setBodyColor(this.chooseColor("Seleccione el color del cuerpo de la serpiente: "));
    }


    /**
     * Method for selecting colors
     * @oaram msg The message to be displayed to the user
     * @return The color chosen
     */
    private Color chooseColor(String msg){
        JColorChooser chooser = new JColorChooser();

        return chooser.showDialog(null, msg, Color.black);
    }
}
