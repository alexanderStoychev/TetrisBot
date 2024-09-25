import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
A class to control the Pentomino falling
@author Lucas Uberti-Bona
@version 0.22, 4 Dec 2017
*/
public class Controls implements KeyListener {

  private Piece piece;

  /**
  Initialize the attributes
  @param piece the piece instance that will be controlled
  */
  public Controls(Piece piece) {
    this.piece = piece;
  }

  public void keyPressed(KeyEvent e) {

    // Right arrow to move right
    if (e.getKeyCode() == KeyEvent.VK_RIGHT && !this.piece.isBotActive()) {
      this.piece.moveRight();
      return;
    }

    // Left arrow to move left
    if (e.getKeyCode() == KeyEvent.VK_LEFT && !this.piece.isBotActive()) {
      this.piece.moveLeft();
      return;
    }

    // Up arrow to move rotate
    if (e.getKeyCode() == KeyEvent.VK_UP && !this.piece.isBotActive()) {
      this.piece.flipRight();
      return;
    }

    // Down arrow to move down
    if (e.getKeyCode() == KeyEvent.VK_DOWN && !this.piece.isBotActive()) {
      this.piece.moveDown();
      return;
    }

    // SPACE to drop
    if (e.getKeyCode() == KeyEvent.VK_SPACE && !this.piece.isBotActive()) {
      this.piece.drop();
      return;
    }

    // Q to quit
    if (e.getKeyCode() == KeyEvent.VK_Q) {
      System.exit(0);
    }

    // B to turn on / off the Bot
    if (e.getKeyCode() == KeyEvent.VK_B) {
      //Toggle bot
      this.piece.toggleBot();
      return;
    }

    // C to Hold
    if (e.getKeyCode() == KeyEvent.VK_C) {
      // Hold Code
      return;
    }

    // ESC to Pause / Resume
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      // Pause / Resume Code
      return;
    }

  }

  // Do nothing methods
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {}

}
