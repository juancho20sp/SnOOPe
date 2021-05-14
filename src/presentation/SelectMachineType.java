package presentation;

import domain.Game;
import domain.players.Machine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class SelectMachineType extends DaddyPanel implements Serializable {
    private JLabel title;
    private JButton distractedButton;
    private JButton cautiousButton;
    private JButton gluttonButton;
    private JButton goBack;


    /**
     * Constructor of the DaddyPanel class
     * @param frame
     * @param game
     */
    public SelectMachineType(JFrame frame, Game game) {
        super(frame, game);

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
        setLayout(new GridLayout(6, 1));
        //this.selectModePanel.setBackground(this.guiConfig.getBackgroundColor());
    }


    /**
     * Method for creating the labels of the view
     */
    private void createLabels(){
        this.title = new JLabel("Seleccionar tipo de máquina:");
        this.title.setHorizontalAlignment(SwingConstants.CENTER);
    }


    /**
     * Method for creating the buttons of the view
     */
    private void createButtons(){
        this.distractedButton = new JButton("Distraída");
        this.cautiousButton = new JButton("Prudente");
        this.gluttonButton = new JButton("Glotona");

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
        add(this.distractedButton);
        add(this.cautiousButton);
        add(this.gluttonButton);
        add(this.goBack);
    }

    /**
     * Method for setting up the button actions
     */
    private void addActionsToButtons(){
        this.distractedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMachineAsDistracted();
            }
        });

        this.cautiousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMachineAsCautious();
            }
        });

        this.gluttonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMachineAsGlutton();
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
     * Method for printing some data
     * @param msg The message to be displayed
     */
    private void printMsg(String msg){
        JOptionPane.showMessageDialog(null, msg);
    }

    /**
     * Method for setting the machine on a 'distracted' state
     */
    private void setMachineAsDistracted(){
        super.getGameData().getPlayerMachine().setMachineType(Machine.DISTRACTED);

        printMsg("La máquina quedó en estado: DISTRAÍDA");
    }

    /**
     * Method for setting the machine on a 'distracted' state
     */
    private void setMachineAsCautious(){
        super.getGameData().getPlayerMachine().setMachineType(Machine.CAUTIOUS);

        printMsg("La máquina quedó en estado: PRUDENTE");
    }

    /**
     * Method for setting the machine on a 'distracted' state
     */
    private void setMachineAsGlutton(){
        super.getGameData().getPlayerMachine().setMachineType(Machine.GLUTTON);

        printMsg("La máquina quedó en estado: GLOTONA");
    }
}
