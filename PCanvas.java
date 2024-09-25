import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;

/**
A class to represent a canvas
@author Lucas Uberti-Bona
@author Sarah Waseem
@version 0.38, 20 Oct 2017
*/
public class PCanvas extends Canvas implements PInfo {

  private BufferStrategy buffer;
  private Graphics g;
  private KeyListener kListener;
  private ActionListener tListener;
  private Board board;
  private Piece piece;
  private Draw drawer;
  private Timer timer;
  private int delay;
  private Scoreboard scoreBoard;

  /**
  Initialize the attributes
  @param w the width of the canvas
  @param h the height of the canvas
  */
  public PCanvas (int w, int h){
    setSize(w, h);
    // To create the board, we need the drawer of the canvas
    // And to create the drawer of the canvas, we need the board
    // So the drawer of the Board will be initialized from the Canvas
    this.board = new Board(this);
    this.drawer = new Draw(this);
    this.board.setDrawer(this.drawer);
    this.scoreBoard = new Scoreboard(this);
    this.piece = new Piece(this);
    this.setFocusable(true);
    this.kListener = new Controls(this.piece);
    this.addKeyListener(kListener);
    this.tListener = new TimerListener(this.piece);
    this.delay = INITIAL_DELAY;
    this.timer = new Timer(this.delay, this.tListener);
    this.timer.start();
  }

  /**
  Reduce the delay of the timer
  */
  public void increaseTempo() {
    if (this.delay >= 2) {
      this.timer.stop();
      this.delay -= 2;
      this.timer = new Timer(this.delay, this.tListener);
      this.timer.start();
    }
  }

  /**
  Switch the delay for the bot, or for the player
  */
  public void switchDelay() {
    if (this.piece.isBotActive()) {
      this.delay = this.delay / MULT_DELAY;
    }
    else {
      this.delay = this.delay * MULT_DELAY;
    }
    this.timer.stop();
    this.timer = new Timer(this.delay, this.tListener);
    this.timer.start();
  }

  /**
  Draw the canvas on the Window
  */
  public void draw() {
    buffer = getBufferStrategy();
    if (buffer == null) {
      createBufferStrategy(2);
      return;
    }
    g = buffer.getDrawGraphics();

      // Draw here
      g.setColor(BACKGROUND);
      g.fillRect(0, 0, this.getWidth(), this.getHeight());

      this.board.draw((Graphics2D) g);
      this.piece.draw((Graphics2D) g);
      this.scoreBoard.draw((Graphics2D) g);

    g.dispose();
    buffer.show();
  }

  /**
  Get access to the board in the canvas
  @return the board
  */
  public Board getBoard() {
    return this.board;
  }

  /**
  Get access to the drawer of the canvas
  @return the drawer object
  */
  public Draw getDrawer() {
    return this.drawer;
  }

  /**
  Get access to the scoreboard in the canvas
  @return the scoreboard
  */
  public Scoreboard getScoreBoard() {
    return this.scoreBoard;
  }

}
