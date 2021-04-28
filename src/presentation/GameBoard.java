package presentation;

import domain.GUIConfiguration;
import domain.GameData;
import domain.players.Player;
import domain.snakes.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends DaddyPanel{
    private JPanel upperPanel;
    private JPanel boardPanel;
    private JButton mainMenuButton;
    private JLabel playerOnePointsLabel;
    private JLabel playerTwoPointsLabel;

    Player playerOne;
    Player playerTwo;

    // Game type
    private String gameType;

    // Main menu
    private MainMenu mainMenu;

    // Panel dimensions
    private final int WIDTH = super.getWidth() - 20;
    private final int HEIGHT = super.getHeight() - 80;

    // Cell size
    private int CELL_SIZE = super.getGUIConfig().getCellSize();

    // Number of boxes
    private int cols;
    private int rows;

    // Snakes
    Snake snake1;

    // Snake directions
    int snake1Direction = KeyEvent.VK_RIGHT;

    // Fruits
    private int fruit[] = {0,0};

    // Game over
    private boolean gameOver = super.getGameData().isGameRunning();

    // Thread
    MainThread mainThread;
    Thread thread;

    /**
     * Constructor for the GameBoard class
     */
    public GameBoard(JFrame frame, GUIConfiguration guiConfig, GameData data){
        super(frame, guiConfig, data);

        // Start game
        super.getGameData().setGameRunning(true);

        // Set Players
        this.setupPlayers();

        // Layout
        this.prepareLayout();

        // Refresh
        this.refresh();

        // Keys
        this.setFocusable(true);
        this.addKeyListener(new myKeys());

        // Thread
        mainThread = new MainThread();
        thread = new Thread(mainThread);
        thread.start();
    }

    /**
     * Method for setting up the players
     */
    private void setupPlayers(){
        this.setPlayerOne(super.getGameData().getPlayerOne());
        this.snake1 = new Snake(3, new int[]{2, 0}, Color.red, Color.black);

        if (super.getGameData().getGameType().equals(GameSetup.MULTIPLAYER)){
            this.setPlayerTwo(super.getGameData().getPlayerTwo());
        }

        if (super.getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)){
            this.setPlayerTwo(super.getGameData().getPlayerMachine());
        }
    }

    /**
     * Method for preparing the layout
     */
    private void prepareLayout(){
        // Main Panel
        setLayout(null);

        // Panel
        this.createUpperPanel();
        this.createGameBoardPanel();
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
        this.createUpperPanelLabels();

        // Actions
        this.createUpperPanelActions();

        // Add
        this.addElementsToUpperPanel();
    }

    /**
     * Method for setting up the panels of the upper board
     */
    private void setUpperPanelLayout(){
        this.upperPanel = new JPanel();
        this.upperPanel.setBounds(0, 0, this.WIDTH, 30);
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
   private void createUpperPanelLabels(){
        this.playerOnePointsLabel = new JLabel(this.getPlayerOne().getNumber() + " : 0", SwingConstants.CENTER);

        if (!this.getGameType().equals(GameSetup.SINGLE_PLAYER)){
            this.playerTwoPointsLabel = new JLabel(this.getPlayerTwo().getNumber() + " : 0", SwingConstants.CENTER);
        }

    }

    /**
     * Method for adding elements to the upper panel
     */
    private void addElementsToUpperPanel(){
        // Elements
        this.upperPanel.add(mainMenuButton);
        this.upperPanel.add(this.playerOnePointsLabel);

        if (!this.getGameType().equals(GameSetup.SINGLE_PLAYER)) {
            this.upperPanel.add(this.playerTwoPointsLabel);
        }

        // Add panel
        add(upperPanel);
    }

    /**+
     * Method for adding actions to the upper panel buttons
     */
    private void createUpperPanelActions(){
        // Main menu
        this.mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuClicked();
            }
        });
    }

    /**
     * Method for creating the game board panel
     * @return
     */
    private void createGameBoardPanel(){
        this.boardPanel = new JPanel();
        //this.boardPanel.setBackground(Color.YELLOW);
        this.boardPanel.setBounds(0, 31, this.WIDTH, this.HEIGHT);

        // Rows and cols
        this.cols = this.boardPanel.getWidth() / CELL_SIZE;
        this.rows = this.boardPanel.getHeight() / CELL_SIZE;

        // Layout
        this.boardPanel.setLayout(new GridLayout(this.rows, this.cols));

        // Create grid
        /*for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create the label
                JLabel box = new JLabel();
                box.setBorder(BorderFactory.createLineBorder(Color.black));

                // Fruit 1
                if (i == fruit[0] && j == fruit[1]){
                    //box.setOpaque(true);
                    //box.setBackground(Color.RED);
                    //this.fruit = null;
                }

                // Snake 1
                if (i == snake1.getHeadPosition()[0] && j == snake1.getHeadPosition()[1]){
                    box.setOpaque(true);
                    box.setBackground(snake1.getHeadColor());
                }



                box.setBounds(i, j, CELL_SIZE, CELL_SIZE);

                // Add label
                this.boardPanel.add(box);
            }*/
        //}

        // Add panel
        //add(boardPanel);
    }

    /**
     * Method for moving the snakes
     */
    public void moveSnakes(){
        snake1.move(this.snake1Direction);
    }

    /**
     * Paint
     */
    @Override
    public void paint(Graphics g){
        // Horizontal skew
        int horizontalSkew = 5;

        // Reset rows and cols
        int newCols = cols + 1;
        int newRows = rows + 1;

        super.paint(g);

        // Color
        g.setColor(Color.black);

        // Vertical lines
        for (int i = 0; i < newCols; i++) {
            g.drawLine((i*CELL_SIZE) + horizontalSkew, upperPanel.getHeight() + 5, (i*CELL_SIZE) + horizontalSkew,
                    HEIGHT + upperPanel.getHeight() - 6);
        }

        // Horizontal lines
        for (int i = 0; i < newRows; i++) {
            g.drawLine(horizontalSkew, upperPanel.getHeight() + (i*CELL_SIZE) + 5, WIDTH - (horizontalSkew + 4),
                    upperPanel.getHeight() + (i*CELL_SIZE) + 5);
        }

        // Snake 1
        // Body
        g.setColor(snake1.getBodyColor());
        for(int[] pos : snake1.getPositions()){
            g.fillRect(fixXPosition(pos[0]), fixYPosition(pos[1]), CELL_SIZE, CELL_SIZE);
        }

        // Head
        g.setColor(snake1.getHeadColor());
        g.fillRect(fixXPosition(snake1.getHeadPosition()[0]), fixYPosition(snake1.getHeadPosition()[1]), CELL_SIZE, CELL_SIZE);


        /*g.setColor(Color.red);
        g.fillRect(fixXPosition(0), fixYPosition(0), CELL_SIZE, CELL_SIZE);

        g.setColor(Color.blue);
        g.fillRect(fixXPosition(2), fixYPosition(2), CELL_SIZE, CELL_SIZE);

        g.setColor(Color.black);
        g.fillRect(fixXPosition(36), fixYPosition(17), CELL_SIZE, CELL_SIZE);

        g.setColor(Color.blue);
        g.fillRect(fixXPosition(10), fixYPosition(10), CELL_SIZE, CELL_SIZE);*/

    }

    /**
     * Method for fixing the locations of the boxes
     * @param x The x position of the elements
     * @return The position fixed
     */
    private int fixXPosition(int x){
        return (x * CELL_SIZE) + 5;
    }

    /**
     * Method for fixing the locations of the boxes
     * @param y The y position of the elements
     * @return The position fixed
     */
    private int fixYPosition(int y){
        return (y * CELL_SIZE) + upperPanel.getHeight() + 5;
    }

    /**
     * Method for redrawing the board
     */
    private void refresh(){
        //removeAll();
        remove(boardPanel);
        revalidate();
        repaint();

        //this.prepareLayout();
        this.createGameBoardPanel();
        revalidate();
        repaint();
    }

    /**
     * Method for adding fruits to the game board
     */
    private void addFruit(){
        int fruit[] = {0, 0};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Create the label
                JLabel box = new JLabel();
                box.setBorder(BorderFactory.createLineBorder(Color.black));

                // Add the fruit
                if (i == fruit[0] && j == fruit[1]){
                    box.setBackground(Color.YELLOW);
                }


                box.setBounds(i, j, CELL_SIZE, CELL_SIZE);

                // Add label
                this.boardPanel.add(box);
            }
        }

        this.refresh();
    }

    /**
     * Paint component method
     * @param g An instance of the 'Graphics' class
     */
    /*public void paintComponent(Graphics g){
        super.paintComponent(g);

        //draw(g);
    }*/

    private void draw(Graphics g){
        int cellSize = super.getGUIConfig().getCellSize();
        int frameWidth = super.getFrame().getWidth();
        int frameHeight = super.getGUIConfig().getFrameHeight() - 30;
        int upperPanelHeight = this.upperPanel.getHeight();
        System.out.println("Upper panel: " + this.upperPanel.getHeight());


        System.out.println(this.WIDTH/cellSize);
        System.out.println(this.HEIGHT/cellSize);

        System.out.println(super.getFrame().getWidth());
        System.out.println(super.getFrame().getHeight());

        // Grid lines
        for(int i = 0; i < frameWidth/cellSize; i++){
            // Vertical lines
            g.drawLine(i*cellSize, upperPanelHeight, i*cellSize, frameHeight);
        }

        for(int i = 0; i < (this.HEIGHT + 80)/cellSize; i++){
            // Horizontal lines
            //g.drawLine(0, i*cellSize + upperPanelHeight, this.WIDTH, i*cellSize + upperPanelHeight);
        }
    }




    /**
     * Method for going to the menu
     */
    private void mainMenuClicked()  {
          this.mainMenu = new MainMenu(super.getFrame(), super.getGUIConfig(), super.getGameData());

        changeCard(this.mainMenu, SnOOPe.GAME_PAUSE_MENU);
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getGameType() {
        return super.getGameData().getGameType();
    }

    /**
     * Inner class for handling key events
     */
    public class myKeys extends KeyAdapter {
        public myKeys() {
            System.out.println("keys creado");
        }

        @Override
        public void keyPressed (KeyEvent e){
            System.out.println("Tecla presionad");

            if (!gameOver){
                System.out.println("Game over");
            }

            System.out.println(e.getKeyCode());

            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    //System.out.println("UP");
                    if (snake1.getDirection() != KeyEvent.VK_DOWN){
                        snake1.setDirection(KeyEvent.VK_UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    //System.out.println("DOWN");
                    if (snake1.getDirection() != KeyEvent.VK_UP){
                        snake1.setDirection(KeyEvent.VK_DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    //System.out.println("LEFT");
                    if (snake1.getDirection() != KeyEvent.VK_RIGHT){
                        snake1.setDirection(KeyEvent.VK_LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    //System.out.println("RIGHT");
                    if (snake1.getDirection() != KeyEvent.VK_LEFT){
                        snake1.setDirection(KeyEvent.VK_RIGHT);
                    }
                    break;
            }
        }
    }

    /**
     * Inner class for thread handling
     */
    public class MainThread extends Thread {
        long last = 0;

        public void run(){
            while(true){
                if((java.lang.System.currentTimeMillis() - last) > snake1.getFrequency()) {
                    /*int x = snake1.getHeadPosition()[0];
                    int y = snake1.getHeadPosition()[1];

                    switch (snake1.getDirection()){
                        case KeyEvent.VK_UP:
                            y = y-1;
                            snake1.setHeadPosition(new int[]{x, y});

                            if (y > rows){
                                snake1.setHeadPosition(new int[]{x, 0});
                            }

                            if (y < 0){
                                snake1.setHeadPosition(new int[]{x, rows - 1});
                            }
                            break;

                        case KeyEvent.VK_DOWN:
                            y = y + 1;
                            snake1.setHeadPosition(new int[]{x, y});

                            if (y > rows){
                                snake1.setHeadPosition(new int[]{x, 0});
                            }

                            if (y < 0){
                                snake1.setHeadPosition(new int[]{x, rows - 1});
                            }
                            break;

                        case KeyEvent.VK_LEFT:
                            x = x - 1;
                            snake1.setHeadPosition(new int[]{x, y});

                            if (x > cols){
                                snake1.setHeadPosition(new int[]{0, y});
                            }

                            if (x < 0){
                                snake1.setHeadPosition(new int[]{cols - 1, y});
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            x = x + 1;
                            snake1.setHeadPosition(new int[]{x, y});

                            if (x > cols){
                                snake1.setHeadPosition(new int[]{0, y});
                            }

                            if (x < 0){
                                snake1.setHeadPosition(new int[]{cols - 1, y});
                            }
                            break;

                    }*/
                    snake1.move(snake1.getDirection());
                    snake1.updatePositions(rows, cols);

                    refresh();

                    last = java.lang.System.currentTimeMillis();
                    }
                }
            }
        }
    }


