import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
A class to draw the minoes on the screen
@author Julián Marrades
@version 0.14, 21 Oct 2017
*/
public class Draw implements PInfo {

	private PCanvas canvas;
	private PPoint boardPosition;

	/**
	Initialize the attributes
	@param canvas the canvas where we want to draw the minoes
	*/
	public Draw(PCanvas canvas) {
		this.canvas = canvas;
		this.boardPosition = this.canvas.getBoard().getPosition();
	}

	/**
	Transform from board coordinate to pixel coordinate
	@param position the position in relation to the board
	@return the actual position on the screen
	*/
	private PPoint calculatePosition(PPoint position) {
		int x = position.intX()*MINO_WIDTH + this.boardPosition.intX();
		int y = position.intY()*MINO_HEIGHT + this.boardPosition.intY();
		return new PPoint(x, y);
	}

	/**
	Draw a mino on the screen
	@param position position in relation to the board
	@param g2 the Graphics2D variable that will draw on the screen
	@param name the name of the Pentomino
	*/
	public void drawMino(PPoint position, Graphics2D g2, int name) {
		int x = calculatePosition(position).intX();
		int y = calculatePosition(position).intY();

		switch (name) {

			case PENTO_P1:
				g2.setColor(COLOR_PENTO_P1);
				break;
			case PENTO_P2:
				g2.setColor(COLOR_PENTO_P2);
				break;
			case PENTO_X:
				g2.setColor(COLOR_PENTO_X);
				break;
			case PENTO_F1:
				g2.setColor(COLOR_PENTO_F1);
				break;
			case PENTO_F2:
				g2.setColor(COLOR_PENTO_F2);
				break;
			case PENTO_V:
				g2.setColor(COLOR_PENTO_V);
				break;
			case PENTO_W:
				g2.setColor(COLOR_PENTO_W);
				break;
			case PENTO_Y1:
				g2.setColor(COLOR_PENTO_Y1);
				break;
			case PENTO_Y2:
				g2.setColor(COLOR_PENTO_Y2);
				break;
			case PENTO_I:
				g2.setColor(COLOR_PENTO_I);
				break;
			case PENTO_T:
				g2.setColor(COLOR_PENTO_T);
				break;
			case PENTO_Z1:
				g2.setColor(COLOR_PENTO_Z1);
				break;
			case PENTO_Z2:
				g2.setColor(COLOR_PENTO_Z2);
				break;
			case PENTO_U:
				g2.setColor(COLOR_PENTO_U);
				break;
			case PENTO_N1:
				g2.setColor(COLOR_PENTO_N1);
				break;
			case PENTO_N2:
				g2.setColor(COLOR_PENTO_N2);
				break;
			case PENTO_L1:
				g2.setColor(COLOR_PENTO_L1);
				break;
			case PENTO_L2:
				g2.setColor(COLOR_PENTO_L2);
				break;
			default:
				g2.setColor(Color.WHITE);
				break;

		}

		g2.fillRect(x, y, MINO_WIDTH, MINO_HEIGHT);
	}

}
