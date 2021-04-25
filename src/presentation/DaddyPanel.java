package presentation;

import javax.swing.*;
import java.awt.*;

public class DaddyPanel extends JPanel{
    private JFrame frame;

    /**
     * Constructor of the DaddyPanel class
     * @param frame
     */
    public DaddyPanel(JFrame frame){
        this.frame = frame;

        // Configure panel
        this.setupPanel();
    }

    /**
     * Method for setting up the dimensions of the panel
     */
    private void setupPanel(){
        // Frame size
        Dimension frameSize = this.frame.getSize();

        // Panel
        new JPanel();
        setSize(frameSize.width, frameSize.height);
    }

    /**
     * Method for getting the configured panel
     */
    public JFrame getFrame(){
        return this.frame;
    }


}
