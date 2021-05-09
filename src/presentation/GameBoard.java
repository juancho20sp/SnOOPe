package presentation;

import domain.Game;
import domain.edibles.Apple;
import domain.edibles.Edible;
import domain.players.Player;
import domain.snakes.Snake;
import domain.snakes.SuperSnake;
import domain.directions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameBoard extends DaddyPanel {
    private JPanel upperPanel;
    private JPanel boardPanel;
    private JButton mainMenuButton;
    private JLabel playerOnePointsLabel;
    private JLabel playerTwoPointsLabel;

    Player playerOne;
    Player playerTwo;

    // Game type
    private String gameType;

    // Is single player
    private boolean singlePlayer;

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
    SuperSnake snake1;
    SuperSnake snake2;

    // Fruits
    Edible fruit1;

    // Game over
    private boolean gameOver = super.getGameData().isGameRunning();

    // Random
    Random random = new Random();

    /**
     * Constructor for the GameBoard class
     */
    public GameBoard(JFrame frame, Game game) {
        super(frame, game);

        // Start game
        super.getGameData().setGameRunning(true);

        // Set board
        game.setBoard(this);

        // Set Players
        game.setupPlayers();

        // Layout
        this.prepareLayout();

        // Refresh
        this.refresh();

        // Keys
        this.setFocusable(true);
        super.getFrame().addKeyListener(new myKeys());

        // Thread
        game.startGame();
    }

    /**
     * Method for setting up the players
     */
    private void setupPlayers() {
        /*this.setPlayerOne(super.getGameData().getPlayerOne());
        this.superSnake1 = new Snake(3, new int[]{2, 0}, this.playerOne.getHeadColor(),
                this.playerOne.getBodyColor()
                , super.getGame());


        if (super.getGameData().getGameType().equals(GameSetup.MULTIPLAYER)) {
            System.out.println(this.rows);

            this.setPlayerTwo(super.getGameData().getPlayerTwo());
            this.superSnake2 = new Snake(3, new int[]{2, rows - 1},
                    this.playerTwo.getHeadColor(),
                    this.playerTwo.getBodyColor()
                    , super.getGame());
        }*/

        /*if (super.getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)) {
            this.setPlayerTwo(super.getGameData().getPlayerMachine());
            this.snake2 = new SnakeP2(3, new int[]{cols - 2, 5},
                    this.getPlayerOne().getHeadColor(),
                    this.playerOne.getBodyColor()
                    , super.getGameData());
        }*/
    }

    /**
     * Method for preparing the layout
     */
    private void prepareLayout() {
        // Main Panel
        setLayout(null);

        // Panel
        this.createUpperPanel();
        this.createGameBoardPanel();

        // Add fruits
        this.addFruit();
    }

    /**
     * Method for creating the upper panel
     */
    private void createUpperPanel() {
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
    private void setUpperPanelLayout() {
        this.upperPanel = new JPanel();
        this.upperPanel.setBounds(0, 0, this.WIDTH, 30);
        this.upperPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.upperPanel.setLayout(new GridLayout(1, 3));
        //this.upperPanel.setBackground(this.config.getBackgroundColor());
    }

    /**
     * Method for creating the upper panel buttons
     */
    private void createUpperPanelButtons() {
        this.mainMenuButton = new JButton("Menú principal");
    }

    /**
     * Method for creating the upper panel labels
     */
    private void createUpperPanelLabels() {
        this.playerOnePointsLabel = new JLabel(this.getPlayerOne().getName() + " : " + this.getPlayerOne().getPoints(),
                SwingConstants.CENTER);

        if (!this.isSinglePlayer()) {
            this.playerTwoPointsLabel = new JLabel(this.getPlayerTwo().getName() + " : " + this.getPlayerTwo().getPoints(),
                    SwingConstants.CENTER);
        }

    }

    /**
     * Method for adding elements to the upper panel
     */
    private void addElementsToUpperPanel() {
        // Elements
        this.upperPanel.add(mainMenuButton);
        this.upperPanel.add(this.playerOnePointsLabel);

        if (!this.isSinglePlayer()) {
            this.upperPanel.add(this.playerTwoPointsLabel);
        }

        // Add panel
        add(upperPanel);
    }

    /**
     * +
     * Method for adding actions to the upper panel buttons
     */
    private void createUpperPanelActions() {
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
     *
     * @return
     */
    private void createGameBoardPanel() {
        this.boardPanel = new JPanel();
        //this.boardPanel.setBackground(Color.YELLOW);
        this.boardPanel.setBounds(0, 31, this.WIDTH, this.HEIGHT);

        // Rows and cols
        this.cols = this.boardPanel.getWidth() / CELL_SIZE;
        this.rows = this.boardPanel.getHeight() / CELL_SIZE;

        super.getGame().setCols(this.cols);
        super.getGame().setRows(this.rows);
    }

    /**
     * Paint
     */
    @Override
    public void paint(Graphics g) {
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
            g.drawLine((i * CELL_SIZE) + horizontalSkew, upperPanel.getHeight() + 5, (i * CELL_SIZE) + horizontalSkew,
                    HEIGHT + upperPanel.getHeight() - 6);
        }

        // Horizontal lines
        for (int i = 0; i < newRows; i++) {
            g.drawLine(horizontalSkew, upperPanel.getHeight() + (i * CELL_SIZE) + 5, WIDTH - (horizontalSkew + 4),
                    upperPanel.getHeight() + (i * CELL_SIZE) + 5);
        }

        // Snake 1
        // Body
        g.setColor(this.getSnake1().getBodyColor());
        for (int[] pos : this.getSnake1().getPositions()) {
            g.fillRect(fixXPosition(pos[0]), fixYPosition(pos[1]), CELL_SIZE, CELL_SIZE);
        }

        // Head
        g.setColor(this.getSnake1().getHeadColor());
        g.fillRect(fixXPosition(this.getSnake1().getHeadPosition()[0]), fixYPosition(this.getSnake1().getHeadPosition()[1]), CELL_SIZE, CELL_SIZE);

        // Snake 2
        if (!this.isSinglePlayer()){
            // Body
            g.setColor(this.getSnake2().getBodyColor());
            for (int[] pos : this.getSnake2().getPositions()) {
                g.fillRect(fixXPosition(pos[0]), fixYPosition(pos[1]), CELL_SIZE, CELL_SIZE);
            }

            // Head
            g.setColor(this.getSnake2().getHeadColor());
            g.fillRect(fixXPosition(this.getSnake2().getHeadPosition()[0]), fixYPosition(this.getSnake2().getHeadPosition()[1]), CELL_SIZE,
                    CELL_SIZE);
        }



        // Fruits
        g.setColor(fruit1.getColor());
        g.fillRect(fixXPosition(fruit1.getX()), fixYPosition(fruit1.getY()), CELL_SIZE, CELL_SIZE);
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
     *
     * @param x The x position of the elements
     * @return The position fixed
     */
    private int fixXPosition(int x) {
        return (x * CELL_SIZE) + 5;
    }

    /**
     * Method for fixing the locations of the boxes
     *
     * @param y The y position of the elements
     * @return The position fixed
     */
    private int fixYPosition(int y) {
        return (y * CELL_SIZE) + upperPanel.getHeight() + 5;
    }

    /**
     * Method for redrawing the board
     */
    public void refresh() {
        // Remove elements
        removeAll();
        revalidate();
        repaint();

        // Check apple
        this.checkApples();

        // Check collisions
        this.checkCollision();

        // Create panels
        this.createUpperPanel();
        this.createGameBoardPanel();

        revalidate();
        repaint();
    }

    /**
     * Method for adding fruits to the game board
     */
    private void addFruit() {
        //Color[] colors = new Color[]{snake1.getHeadColor(), snake1.getBodyColor()};
        Color[] colors = new Color[]{this.getPlayerOne().getHeadColor(), this.getPlayerOne().getBodyColor()};

        int x = random.nextInt(cols - 1);
        int y = random.nextInt(rows - 1);

        if (x == 0) {
            x++;
        }

        if (x == cols) {
            x--;
        }

        if (y == 0) {
            y++;
        }

        if (y == rows) {
            y--;
        }

        int color = random.nextInt(2);

        int[] fruit1Coordinate = new int[]{x,y};

        //if (this.snake1.getPositions().contains(fruit1Coordinate) || this.snake1.getHeadPosition().equals
        // (fruit1Coordinate)){
        if (this.getSnake1().getPositions().contains(fruit1Coordinate) || this.getSnake1().getHeadPosition().equals(fruit1Coordinate)){
            this.addFruit();
        } else {
            this.fruit1 = new Apple(x, y, colors[color]);
        }


    }

    /**
     * Method for checking if the snake ate the apple
     */
    private void checkApples() {
        // Snake 1
        int snake1X = this.getSnake1().getHeadPosition()[0];
        int snake1Y = this.getSnake1().getHeadPosition()[1];

        // Snake 2
        int snake2X = 0;
        int snake2Y = 0;

        if (!this.isSinglePlayer()) {
            snake2X = this.getSnake2().getHeadPosition()[0];
            snake2Y = this.getSnake2().getHeadPosition()[1];
        }


        int appleX = fruit1.getX();
        int appleY = fruit1.getY();


        // Fruta 1 - Snake 1
        if ((snake1X == appleX) && (snake1Y == appleY)) {
            if (fruit1 instanceof Apple) {
                // Add points
                int newPoints = getPlayerOne().getPoints() + 1;
                getPlayerOne().setPoints(newPoints);

                // Increase size
                this.getSnake1().increaseSize(fruit1.getPoints());
            }

            this.addFruit();
        }

        // Fruta 1 - Snake 2
        if (!this.isSinglePlayer()){
            if ((snake2X == appleX) && (snake2Y == appleY)) {
                if (fruit1 instanceof Apple) {
                    // Add points
                    int newPoints = this.getPlayerTwo().getPoints() + 1;
                    this.getPlayerTwo().setPoints(newPoints);

                    // Increase size
                    this.getSnake2().increaseSize(fruit1.getPoints());
                }

                this.addFruit();
            }
        }

    }

    /**
     * Method for verifying collision
     */
    private void checkCollision() {
        int[] head1 = this.getSnake1().getHeadPosition();
        int[] head2 = new int[2];

        if (!this.isSinglePlayer()){
            head2 = this.getSnake2().getHeadPosition();
        }


        // Snake one
        for (int[] position : this.getSnake1().getPositions()) {
            if (head1[0] == position[0] && head1[1] == position[1]) {
                super.getGameData().setGameRunning(false);
                System.out.println("GAME OVER");
            }

            if (!this.isSinglePlayer()){
                if (head2[0] == position[0] && head2[1] == position[1]) {
                    super.getGameData().setGameRunning(false);
                    System.out.println("GAME OVER - CHOQUE 2 CON 1");
                }
            }
        }

        // Snake two
        if (!this.isSinglePlayer()){
            for (int[] position : this.getSnake2().getPositions()) {
                if (head2[0] == position[0] && head2[1] == position[1]) {
                    super.getGameData().setGameRunning(false);
                    System.out.println("GAME OVER");
                }

                if (head1[0] == position[0] && head1[1] == position[1]) {
                    super.getGameData().setGameRunning(false);
                    System.out.println("GAME OVER - CHOQUE 1 CON 2");
                }
            }
        }


        // Death on collision with other snake
        // Que la cabeza del uno esté incluido en las posiciones del otro

        // End game
        if (!super.getGameData().isGameRunning()){
            this.endGame();
        }
    }

    /**
     * Method for going to the menu
     */
    private void mainMenuClicked() {
        this.mainMenu = new MainMenu(super.getFrame(), getGame());

        changeCard(this.mainMenu, SnOOPe.GAME_PAUSE_MENU);
    }

    /**
     * Method for ending the game
     */
    private void endGame(){
        EndGame endGame = new EndGame(getFrame(), getGame());

        changeCard(endGame, SnOOPe.END_GAME);
    }

    public Player getPlayerOne() {
        return super.getGame().getGameData().getPlayerOne();
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return super.getGame().getGameData().getPlayerTwo();
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getGameType() {
        return super.getGameData().getGameType();
    }

    public boolean isSinglePlayer() {
        return getGameType().equals(GameSetup.SINGLE_PLAYER);
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public SuperSnake getSnake1() {
        return super.getGame().getGameData().getPlayerOne().getSnake();
    }

    public SuperSnake getSnake2() {
        return super.getGame().getGameData().getPlayerTwo().getSnake();
    }

    /**
     * Inner class for thread handling
     */
    /*public class MainThread extends Thread {
        long lastGame = 0;
        long lastSnake1 = 0;
        long lastSnake2 = 0;

        int gameFrequency = 10;


        public void run() {
            while (true) {
                if ((java.lang.System.currentTimeMillis() - lastGame) > gameFrequency) {
                    if (getGameData().isGameRunning()) {

                        // Velocity for the snake 1
                        if ((java.lang.System.currentTimeMillis() - lastSnake1) > getSnake1().getFrequency()){
                            getSnake1().move(getSnake1().getDirection());
                            getSnake1().updatePositions(rows, cols);

                            lastSnake1 = java.lang.System.currentTimeMillis();
                        }

                        // Velocity for the snake 2
                        if (!isSinglePlayer()){
                            if ((java.lang.System.currentTimeMillis() - lastSnake2) > getSnake2().getFrequency()){
                                getSnake2().move(getSnake2().getDirection());
                                getSnake2().updatePositions(rows, cols);

                                lastSnake2 = java.lang.System.currentTimeMillis();
                            }
                        }

                        refresh();

                        lastGame = java.lang.System.currentTimeMillis();
                    }
                }
            }
        }
    }
    */
    /**
     * Inner class for handling key events
     */
    public class myKeys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            if (!gameOver) {
                //System.out.println("Game over");
            }

            // Add fuit when spacebar is clicked
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                addFruit();
            }

            // End game
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
                endGame();
            }

            // Increase snake 1 size
            if (e.getKeyCode() == KeyEvent.VK_1){
                getSnake1().increaseSize(1);
            }

            // Decrease snake 1 size
            if (e.getKeyCode() == KeyEvent.VK_2){
                getSnake1().decreaseSize(1);
            }

            // Increase snake 1 velocity
            if (e.getKeyCode() == KeyEvent.VK_Z){
                int initialFreq = getSnake1().getFrequency();
                getSnake1().setFrequency(initialFreq - 20);
            }

            // Decrease snake 1 velocity
            if (e.getKeyCode() == KeyEvent.VK_X){
                int initialFreq = getSnake1().getFrequency();
                getSnake1().setFrequency(initialFreq + 20);
            }

            if (!isSinglePlayer()){
                // Increase snake 1 velocity
                if (e.getKeyCode() == KeyEvent.VK_C){
                    int initialFreq = getSnake2().getFrequency();
                    getSnake2().setFrequency(initialFreq - 20);
                }

                // Decrease snake 1 velocity
                if (e.getKeyCode() == KeyEvent.VK_V){
                    int initialFreq = getSnake2().getFrequency();
                    getSnake2().setFrequency(initialFreq + 20);
                }
            }

            switch (e.getKeyCode()) {
                // Snake 1
                case KeyEvent.VK_UP:
                    if (getSnake1().getDirection() != directions.DOWN) {
                        getSnake1().setDirection(directions.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (getSnake1().getDirection() != directions.UP) {
                        getSnake1().setDirection(directions.DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (getSnake1().getDirection() != directions.RIGHT) {
                        getSnake1().setDirection(directions.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (getSnake1().getDirection() != directions.LEFT) {
                        getSnake1().setDirection(directions.RIGHT);
                    }
                    break;


                // Snake 2
                case KeyEvent.VK_W:
                    if (getSnake2().getDirection() != directions.DOWN) {
                        getSnake2().setDirection(directions.UP);
                    }
                    break;
                case KeyEvent.VK_S:
                    if (getSnake2().getDirection() != directions.UP) {
                        getSnake2().setDirection(directions.DOWN);
                    }
                    break;
                case KeyEvent.VK_A:
                    if (getSnake2().getDirection() != directions.RIGHT) {
                        getSnake2().setDirection(directions.LEFT);
                    }
                    break;
                case KeyEvent.VK_D:
                    if (getSnake2().getDirection() != directions.LEFT) {
                        getSnake2().setDirection(directions.RIGHT);
                    }
                    break;
                default:
                    System.out.println("Tecla no asignada");
            }
        }
    }
}



