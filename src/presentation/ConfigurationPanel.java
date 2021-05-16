package presentation;

import domain.Game;
import domain.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class ConfigurationPanel extends DaddyPanel implements Serializable {
        private JLabel title;
        private JButton infiniteBoardButton;
        private JButton validationsButton;
        private JButton cellSizeButton;
        private JButton goBack;

        /**
         * Constructor for the ColorSetup class
         */
        public ConfigurationPanel(JFrame frame, Game game){
            super(frame, game);

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
            this.title = new JLabel("Configuración del juego");
            this.title.setHorizontalAlignment(SwingConstants.CENTER);
        }

        /**
         * Method for creating the buttons of the view
         */
        private void createButtons(){
            boolean infiniteBoard = getGame().getGameData().isBoardInfinite();
            String status = infiniteBoard ? "ACTIVADO" : "DESACTIVADO";

            boolean validations = getGame().getGameData().isValidationsActivated();
            String statusValidations = validations ? "ACTIVADAS" : "DESACTIVADAS";

            int cellSize = getGame().getGuiConfiguration().getCellSize();

            this.infiniteBoardButton = new JButton("Activar / desactivar tablero infinito: " + status);
            this.validationsButton = new JButton("Activar / desactivar validaciones: " + statusValidations);

            this.cellSizeButton = new JButton("Tamaño de las celdas: " + cellSize + " px");

            this.goBack = new JButton("Volver");
        }

        /**
         * Method for adding the elements to the panel
         */
        private void addElements(){
            // Label
            add(this.title);

            // Buttons
            add(this.infiniteBoardButton);
            add(this.validationsButton);
            add(this.cellSizeButton);
            add(this.goBack);
        }

        /**
         * Method for setting up the button actions
         */
        private void addActionsToButtons(){
            this.infiniteBoardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleInfiniteBoard();
                }
            });

            this.validationsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleValidations();
                }
            });

            this.cellSizeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeCellSize();
                }
            });

            this.goBack.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goToCard(SnOOPe.MAIN_MENU);
                }
            });
        }

        /**
         * Method for toggling the infinite board
         */
        private void toggleInfiniteBoard(){
            boolean oldInfiniteBoard = getGame().getGameData().isBoardInfinite();

            getGame().getGameData().setBoardInfinite(!oldInfiniteBoard);

            this.refresh();
        }

        /**
         * Method for toggling the validations
         */
        private void toggleValidations(){
            boolean oldValidations = getGame().getGameData().isValidationsActivated();

            getGame().getGameData().setValidationsActivated(!oldValidations);

            this.refresh();
        }

    /**
     * Method for changing the cell size
     */
    private void changeCellSize(){
        int newValue = 0;

        try {
            newValue = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo tamaño de las celdas: ").trim());

            getGame().getGuiConfiguration().setCellSize(newValue);
        } catch(Exception ex) {}

        this.refresh();
    }

    /**
     * Method for refreshing the view
     */
    private void refresh() {
        removeAll();
        revalidate();
        repaint();

        this.prepareLayout();

        revalidate();
        repaint();
    }

}


