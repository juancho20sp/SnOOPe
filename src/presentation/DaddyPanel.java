package presentation;

import domain.GUIConfiguration;
import domain.GameData;

import javax.swing.*;
import java.awt.*;

public class DaddyPanel extends JPanel{
    private JFrame frame;
    private GameData data;
    private GUIConfiguration guiConfig;

    /**
     * Constructor of the DaddyPanel class
     * @param frame
     */
    public DaddyPanel(JFrame frame, GUIConfiguration guiConfig, GameData data){
        this.frame = frame;
        this.data = data;
        this.guiConfig = guiConfig;

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
     * Method for switching the main frame cards
     */
    public void changeCard(JPanel card, String cardName){
        SnOOPe.cards.add(card, cardName);
        SnOOPe.selectCard(cardName);
    }

    /**
     * Method for ending the actual game
     * @return true if clicked "Sí" false otherwise
     */
    public boolean askForConfirmation(String msg){
        String ObjButtons[] = {"Sí","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,msg,"SnOOPe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);

        if(PromptResult==JOptionPane.YES_OPTION)
        {
            return true;
        }

        return false;
    }

    /**
     * Method for getting the frame
     */
    public JFrame getFrame(){
        return this.frame;
    }

    /**
     * Method for getting the GUI configuration
     */
    public GUIConfiguration getGUIConfig(){
        return this.guiConfig;
    }

    /**
     * Method for getting the game data
     */
    public GameData getGameData(){
        return this.data;
    }

    /**
     * Method for going back
     * @param card The name of the card we want to go
     */
    public void goToCard(String card){
        SnOOPe.selectCard(card);
    }


}
