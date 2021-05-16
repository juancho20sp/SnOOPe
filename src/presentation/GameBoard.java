package presentation;

import domain.Game;
import domain.edibles.SpeedArrow;
import domain.edibles.Edible;
import domain.edibles.PowerUp;
import domain.players.Machine;
import domain.players.Player;
import domain.players.SuperPlayer;
import domain.snakes.SuperSnake;
import domain.directions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard extends DaddyPanel implements Serializable {
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

    // PoweUps
    PowerUp powerUp;
    boolean isPowerUpHidden = false;

    // Game over
    private boolean gameOver = super.getGameData().isGameRunning();

    // Random
    Random random = new Random();

    // Timer
    transient Timer timer;

    /**
     * Constructor for the GameBoard class
     */
    public GameBoard(JFrame frame, Game game) {
        super(frame, game);

        // Start game
        super.getGameData().setGameRunning(true);

        // Set board
        game.setGameBoard(this);

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

        if (getGameType().equals(GameSetup.PLAYER_MACHINE)){
            System.out.println(getGameData().getPlayerMachine().getMachineType());
        }
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

        // Create board
        getGame().createBoard();

        // Add fruits
        super.getGame().addFruit();

        // Add power up
        this.addPowerUp();
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

        if (getGameData().getGameType().equals(GameSetup.MULTIPLAYER)) {
            this.playerTwoPointsLabel = new JLabel(this.getPlayerTwo().getName() + " : " + this.getPlayerTwo().getPoints(),
                    SwingConstants.CENTER);
        } else if (getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)) {
            this.playerTwoPointsLabel = new JLabel(this.getPlayerMachine().getName() + " : " + this.getPlayerMachine().getPoints(),
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

        // Save rows and cols
        getGame().getGameData().setGetGameBoardCols(this.cols);
        getGame().getGameData().setGameBoardRows(this.rows);

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
        if (getFruit1() != null){
            g.setColor(getFruit1().getColor());
            g.fillRect(fixXPosition(getFruit1().getX()), fixYPosition(getFruit1().getY()), CELL_SIZE, CELL_SIZE);
            g.drawImage(new ImageIcon(getFruit1().getImage()).getImage(), fixXPosition(getFruit1().getX()),
                    fixYPosition(getFruit1().getY()),this);
        }


        // Pasar un powerup
        //g.setColor(powerUp.getColor());
        //g.fillRect(fixXPosition(powerUp.getX()), fixYPosition(powerUp.getY()), CELL_SIZE, CELL_SIZE);
        if (!isPowerUpHidden){
            g.drawImage(new ImageIcon(powerUp.getImage()).getImage(), fixXPosition(powerUp.getX()), fixYPosition(powerUp.getY()), this);
        }


        // Fruta
        //g.drawImage(new ImageIcon(fruit1.getImage()).getImage(), fixXPosition(2), fixYPosition(2), this);

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

        // Check power ups
        this.checkPowerUps();

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
    public void addFruit() {
        //Color[] colors = new Color[]{snake1.getHeadColor(), snake1.getBodyColor()};
        /*Color[] colors = new Color[]{this.getPlayerOne().getHeadColor(), this.getPlayerOne().getBodyColor()};

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

        if (this.getSnake1().getPositions().contains(fruit1Coordinate) || this.getSnake1().getHeadPosition().equals(fruit1Coordinate)){
            this.addFruit();
        } else {
            // Create the fruit
            this.fruit1 = new Apple(x, y, colors[color]);

            // Set its coordinate
            getGame().updateCoordinates(x, y, 1);
        }*/

        //setFruit1(getGame().addFruit1());
        //setFruit1(getGame().getBoard().addFruit());
    }

    /**
     * Method for adding powerups to the game board
     */
    private void addPowerUp() {
        //Color[] colors = new Color[]{snake1.getHeadColor(), snake1.getBodyColor()};
          // Arrow -> 0
        // Trap -> 1

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

        //random.nextInt(1);
        int power = 1;

        int[] powerUpCoordinate = new int[]{x,y};

        if (this.getSnake1().getPositions().contains(powerUpCoordinate) || this.getSnake1().getHeadPosition().equals(powerUpCoordinate)){
            this.addPowerUp();
        } else {
            // Create the power up
            switch (power){
                case 1:
                    this.powerUp = new SpeedArrow(x, y, Color.red, 5);
            }

            // Set its coordinate
            getGame().updateCoordinates(x, y, 2);
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


        int appleX = getFruit1().getX();
        int appleY = getFruit1().getY();


        // Fruta 1 - Snake 1
        if ((snake1X == appleX) && (snake1Y == appleY)) {
            // Eat the apple
            getFruit1().eatEdible(getFruit1(), getPlayerOne(), getGame());

            super.getGame().addFruit1();
            super.getGame().setTimerFruit1(0);
        }

        // Fruta 1 - Snake 2
        if (!this.isSinglePlayer()){
            if ((snake2X == appleX) && (snake2Y == appleY)) {

                getFruit1().eatEdible(getFruit1(), getPlayerTwo(), getGame());


                super.getGame().addFruit1();
            }
        }

    }

    /**
     * Method for checking if the snake ate the apple
     */
    private void checkPowerUps() {
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


        int powerX = powerUp.getX();
        int powerY = powerUp.getY();


        // Fruta 1 - Snake 1
        if ((snake1X == powerX) && (snake1Y == powerY)) {
            // Eat the power up
            powerUp.eatPowerUp(powerUp, getPlayerOne(), getGame());

            setPowerUpHidden(true);

            //this.addPowerUp();
        }

        // Fruta 1 - Snake 2
        if (!this.isSinglePlayer()){
            if ((snake2X == powerX) && (snake2Y == powerY)) {

                powerUp.eatEdible(powerUp, getPlayerTwo(), getGame());

                setPowerUpHidden(true);

                //this.addPowerUp();
            }
        }

        if (isPowerUpHidden) {
            timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    setPowerUpHidden(false);
                    addPowerUp();
                }
            };

            timer.schedule(timerTask, 2000);
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

        super.getGame().pauseGame();

        changeCard(this.mainMenu, SnOOPe.GAME_PAUSE_MENU);
    }

    /**
     * Method for ending the game
     */
    private void endGame(){
        EndGame endGame = new EndGame(getFrame(), getGame());

        super.getGame().getGameData().setGamePaused(false);

        changeCard(endGame, SnOOPe.END_GAME);
    }

    public Player getPlayerOne() {
        return super.getGame().getGameData().getPlayerOne();
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public SuperPlayer getPlayerTwo() {
        if (getGameData().getGameType().equals(GameSetup.MULTIPLAYER)){
            return super.getGame().getGameData().getPlayerTwo();
        } else {
            return super.getGame().getGameData().getPlayerMachine();
        }
    }

    public Machine getPlayerMachine() {
        return super.getGame().getGameData().getPlayerMachine();
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
        if(getGameData().getGameType().equals(GameSetup.MULTIPLAYER)){
            return super.getGame().getGameData().getPlayerTwo().getSnake();
        } else if (getGameData().getGameType().equals(GameSetup.PLAYER_MACHINE)){
            return super.getGame().getGameData().getPlayerMachine().getSnake();
        }

        return null;
    }

    public boolean isPowerUpHidden() {
        return isPowerUpHidden;
    }

    public void setPowerUpHidden(boolean powerUpHidden) {
        isPowerUpHidden = powerUpHidden;
    }

    public Edible getFruit1() {
        return super.getGame().getFruit1();
    }

    public void setFruit1(Edible fruit1) {
        this.fruit1 = fruit1;
    }

    /**
     * Inner class for handling key events
     */
    public class myKeys extends KeyAdapter implements Serializable{
        @Override
        public void keyPressed(KeyEvent e) {

            if (!gameOver) {
                //System.out.println("Game over");
            }

            // Trigger power up on spacebar
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                getPlayerOne().usePowerUp();


            }

            // Add fuit when spacebar is clicked
            /*if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                addFruit();
            }*/

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



