package presentation;

import domain.GUIConfiguration;

import javax.swing.*;
import java.awt.*;

public class ColorSetup extends JPanel{
    private JFrame frame;
    private JPanel selectColorPanel;
    private JLabel title;
    private JButton headButton;
    private JButton bodyButton;
    private JButton goBack;

    // Config
    private GUIConfiguration guiConfig;

    /**
     * Constructor for the ColorSetup class
     */
    public ColorSetup(JFrame frame, GUIConfiguration guiConfig){
        this.frame = frame;

        this.guiConfig = guiConfig;

        this.prepareLayout();

        add(this.selectColorPanel);
    }

    /**
     * Method for preparing the layout
     */
    private void prepareLayout(){
        // Frame size
        Dimension frameSize = this.frame.getSize();

        // Main Panel
        setLayout(null);

        // Panel
        /*this.createPanel(frameSize);

        // Label
        this.createLabels();

        // Buttons
        this.createButtons();

        // Actions
        this.addActionsToButtons();

        // Add
        this.addElements();*/
    }
}
