import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
The timer listener to move the Pentomino down automatically
@author Sarah Waseem
@version 0.11, 27 Oct 2017
*/
public class TimerListener implements ActionListener {

  private Piece piece;

  /**
  Initialize the attributes
  @param piece the piece that will be controlled by the timer
  */
  public TimerListener(Piece piece) {
    this.piece = piece;
  }

  /**
  Move the piece down
  */
  public void actionPerformed(ActionEvent event) {
    this.piece.moveDown();
  }

}
