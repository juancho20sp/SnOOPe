package presentation;

import domain.GUIConfiguration;
import domain.GameData;

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

    /**
     * Constructor for the GameBoard class
     */
    public GameBoard(JFrame frame, GUIConfiguration guiConfig, GameData data){
        super(frame, guiConfig, data);

        this.prepareLayout();
    }

    /**
     * Method for preparing the layout
     */
    private void prepareLayout(){
        // Main Panel
        setLayout(null);

        // Panel
        this.createUpperPanel();
       /* this.createGameBoardPanel();

        // Label
        this.createLabels();

        // Buttons
        this.createButtons();

        // Actions
        this.addActionsToButtons();

        // Add
        this.addElements();*/
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
        //this.createUpperPanelLabels();

        // Actions
        this.createUpperPanelActions();

        // Add
        //this.addElementsToUpperPanel();
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
   /* private void createUpperPanelLabels(){
        this.playerOnePointsLabel(new JLabel(, SwingConstants.CENTER));
        this.playerTwoPointsLabel( new JLabel("Puntos: " + + logicBoard.getPoints(),
                SwingConstants.CENTER));
    }*/

    /**+
     * Method for adding actions to the upper panel buttons
     */
    private void createUpperPanelActions(){
        // Main menu
        this.mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //toggleMainMenu();
            }
        });
    }

}
