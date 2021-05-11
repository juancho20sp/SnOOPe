package presentation;

import domain.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends DaddyPanel{
    private JLabel title;
    private JButton backToGameButton;
    private JButton saveGameButton;
    private JButton endGameButton;

    /**
     * Constructor for the MainMenu class
     */
    public MainMenu(JFrame frame, Game game){
        super(frame, game);

        // Configuration
        //this.guiConfig = guiConfig;
        //this.data = data;

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
        this.title = new JLabel("Menú principal");
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Method for creating the buttons of the view
     */
    private void createButtons(){
        this.backToGameButton = new JButton("Volver al juego");
        this.saveGameButton = new JButton("Guardar juego");
        this.endGameButton = new JButton("Terminar juego");
    }

    /**
     * Method for adding the elements to the panel
     */
    private void addElements(){
        // Label
        add(this.title);

        // Buttons
        add(this.backToGameButton);
        add(this.saveGameButton);
        add(this.endGameButton);
    }

    /**
     * Method for setting up the button actions
     */
    private void addActionsToButtons(){
        this.backToGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToGameClicked();
            }
        });

        this.saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameClicked();
            }
        });

        this.endGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGameClicked();
            }
        });
    }

    /**
     * Method for going back to the game
     */
    private void backToGameClicked(){
        super.getGame().resumeGame();
        SnOOPe.selectCard(SnOOPe.GAME_BOARD);
    }

    /**
     * Method for saving the game
     */
    private void saveGameClicked(){
        JOptionPane.showMessageDialog(null, "save game clicked");
    }

    /**
     * Method for ending the game
     */
    private void endGameClicked(){
        boolean res = askForConfirmation("¿Desea terminar el juego actual?");

        getGame().stop();

        if (res){
            SnOOPe.selectCard(SnOOPe.MAIN_MENU);
        }

    }
}
