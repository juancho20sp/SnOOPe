package presentation;

import domain.GUIConfiguration;
import domain.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SnOOPe extends JFrame{
    JPanel menuPanel;
    JLabel menuLabel;
    JButton newGameButton;
    JButton openGameButton;
    JButton configButton;
    JButton creditsButton;
    JButton exitButton;

    // Cards
    public static JPanel cards;
    public final static String MAIN_MENU = "main menu";
    public final static String SELECT_GAME_MODE = "select game mode";
    public final static String SELECT_GAME_SETUP = "select game setup";
    public final static String SELECT_GAME_COLOR_SETUP = "select game color setup";
    public final static String GAME_BOARD = "game board";
    public final static String GAME_PAUSE_MENU = "game board pause menu";
    public final static String END_GAME = "end game";

    // Configuration
    private GameData data;
    private GUIConfiguration guiConfig;



    /**
     * Main method of the SnOOPe class
     */
    public static void main(String[] args) {
        JFrame frame = new SnOOPe();

        frame.setVisible(true);
        frame.setResizable(true);
    }

    /**
     * Constructor of the SnOOPe class
     */
    public SnOOPe(){
        // Initialize panel
        cards = new JPanel(new CardLayout());

        // Configuration
        this.data = new GameData();
        this.guiConfig = new GUIConfiguration();

        // Create elements
        this.prepareElements();

        // Key listener
        this.setFocusable(true);
    }

    /**
     * Method for preparing all the elements of the SnOOPe main view
     */
    private void prepareElements(){
        // Frame
        this.prepareElementsFrame();

        // Actions
        this.prepareElementsActions();

        // Add the cards to the frame
        add(cards);

        // Menu
        this.createMenu();

        // Menu actions
        this.prepareMenuActions();
    }

    /**
     * Method for setting up the main frame
     */
    private void prepareElementsFrame(){
        setTitle("SnOOPe");

        // Size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        //setSize(guiConfig.getFrameWidth(), guiConfig.getFrameHeight());

        // Position
        setLocationRelativeTo(null);
    }

    /**
     * Method for setting up the frame actions
     */
    private void prepareElementsActions(){
        // Close operation
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event){
                askBeforeClosing();
            }
        });
    }

    /**
     * Method for creating the menu and its layout
     */
    private void createMenu(){
        // Panel
        this.createMenuPanel();

        // Label
        this.createMenuLabels();

        // Buttons
        this.createMenuButtons();

        // Add elements
        this.createMenuAddElements();
    }

    /**
     * Method for setting up the main menu panel
     */
    private void createMenuPanel(){
        // Frame size
        Dimension frameSize = getSize();

        // Panel
        this.menuPanel = new JPanel();
        this.menuPanel.setSize(frameSize.width - 50, frameSize.height - 50);
        this.menuPanel.setLayout(new GridLayout(6, 1));
        //this.menuPanel.setBackground(this.config.getBackgroundColor());
    }

    /**
     * Method for setting up the labels of the main menu
     */
    private void createMenuLabels(){
        // Label
        this.menuLabel = new JLabel("SnOOPe");
        this.menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Method for setting up the menu buttons
     */
    private void createMenuButtons(){
        // Buttons
        this.newGameButton = new JButton("Iniciar juego");
        this.openGameButton = new JButton("Abrir juego");
        this.configButton = new JButton("Configuraci??n");
        this.creditsButton = new JButton("Cr??ditos");
        this.exitButton = new JButton("Salir");
    }

    /**
     * Method for adding the elements to the panel
     */
    private void createMenuAddElements(){
        // Add labels to the panel
        this.menuPanel.add(this.menuLabel);

        // Add buttons to the panel
        this.menuPanel.add(newGameButton);
        this.menuPanel.add(openGameButton);
        this.menuPanel.add(configButton);
        this.menuPanel.add(creditsButton);
        this.menuPanel.add(exitButton);

        // Add panel to the frame
        cards.add(menuPanel, MAIN_MENU);
    }

    /**
     * Method for preparing all the actions that can be triggered by the user
     * in the menu
     */
    private void prepareMenuActions(){
        // Preparamos los eventos de click de los botones
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });

        openGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "open game clicked");
                //openGame();
            }
        });

        configButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configureGame();

            }
        });

        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "credits clicked");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                askBeforeClosing();
            }
        });
    }

    /**
     * Method for creating a new game
     */
    private void newGame() {
        SelectMode selectMode = new SelectMode(this, this.guiConfig, this.data);

        cards.add(selectMode, SELECT_GAME_MODE);
        selectCard(SELECT_GAME_MODE);
    }

    /**
     * Method for open a previously saved game
     */
    private void openGame(){
        JOptionPane.showMessageDialog(null, "Abrir juego");

        // JFileChooser
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione un juego");
        int selected = chooser.showOpenDialog(menuPanel);

        switch(selected){
            case JFileChooser.APPROVE_OPTION:
                File file = chooser.getSelectedFile();

                System.out.println("\nEsta l??gica est?? en construcci??n");
                System.out.println("Opening " + file.getName() + " in " +file.getAbsolutePath());

                // L??gica con el documento
                JOptionPane.showMessageDialog(null, "Message selected");
                break;
            case JFileChooser.ERROR_OPTION:
                JOptionPane.showMessageDialog(null, "Something bad happened");
                break;
            case JFileChooser.CANCEL_OPTION:
                JOptionPane.showMessageDialog(null, "Cancel everything!");
                break;

        }
    }

    /**
     * Method for modifying the board
     */
    private void configureGame(){
        /*ModifyBoard modify = new ModifyBoard(this, this.config);

        this.prepareMenuActions();

        cards.add(modify, MODIFY_BOARD);

        //this.switchPanel(board);
        selectCard(MODIFY_BOARD);

        this.refresh();*/
    }

    /**
     * Method for verifying actions
     */
    private void askBeforeClosing() {
        String ObjButtons[] = {"S??","No"};
        int PromptResult = JOptionPane.showOptionDialog(null,"??Desea salir del programa?","SnOOPe",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);

        if(PromptResult==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    /**
     * Method for switching between cards
     * @return
     */
    public static void selectCard(String card){
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, card);
    }

    /**
     * Method for refreshing the menu
     */
    private void refresh(){
        this.remove(menuPanel);
        this.revalidate();
        this.repaint();

        this.prepareElements();
    }




}
