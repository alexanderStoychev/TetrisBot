import java.util.ArrayList;
import java.lang.Thread;

/**
A class to represent the Bot that plays PentriX
@author Julián Marrades
@version 0.58, 13 Dec 2017
*/
public class Bot implements PInfo {

	private double[] weights;
	private Piece piece;

	public Bot(Piece piece) {
		this.weights = BOT_WEIGHTS;
		this.piece = piece;
	}

	/**
	Pick the best move and perform it
	*/
	public void pickMove() {
		Pentomino[] shapes = Pentomino.getAllShapes(this.piece.getFallingPentomino().getName());
		int y = this.piece.getPosition().intY();

		// Get all possible boards
		ArrayList<VBoard> boards = this.getAllBoards(shapes, y);

		// Get the Board with the highest score
		VBoard greater = getGreatestVBoard(boards);

		System.out.println("Max Score: " + greater.getScore());

		// Get the desired shape
		Pentomino desiredPento = greater.getShape();
		System.out.println("Actual shape: " + this.piece.getFallingPentomino().toString());
		System.out.println("Desired shape: " + desiredPento.toString());

		// Rotate the Pentomino to get the shaoe
		this.rotateToDesired(desiredPento);

		//Get the desired position
		PPoint desiredPos = greater.getPosition();
		System.out.println("Actual position: " + this.piece.getPosition().toStringInt());
		System.out.println("Desired position: " + desiredPos.toStringInt());

		// Move the Pentomino to the desired position
		this.moveToPos(desiredPos);

	}

	/**
	Get all possible VBoards
	@param shapes the array of Pentominoes to check
	@param y the current row of the falling pentomino
	@return the array containing all the possible boards
	*/
	private ArrayList<VBoard> getAllBoards(Pentomino[] shapes, int y) {
		ArrayList<VBoard> boards = new ArrayList<VBoard>();
		for (Pentomino shape : shapes) {
			PPoint[] minoes = shape.getMinoes();
			for (int x = 0; x < COLUMNS; x++) {
				PPoint pos = new PPoint(x, y);
				if (!outside(minoes, pos)) {
					System.out.print("Adding board. Score: ");
					VBoard vboard = new VBoard(this.piece.getBoard().getMatrix(), pos, shape, this.weights);
					boards.add(vboard);
				}
			}
		}
		return boards;
	}

	/**
	Get the VBoard with the highest score
	@param boards the array of board to examine
	@return the board the the highest score
	*/
	public static VBoard getGreatestVBoard(ArrayList<VBoard> boards) {
		VBoard greater = null;
		double maxScore = 0;
		for (int i = 0; i < boards.size(); i++) {
			if (i == 0) {
				greater = boards.get(i);
				maxScore = greater.getScore();
			}
			else if (boards.get(i).getScore() > maxScore) {
				greater = boards.get(i);
				maxScore = greater.getScore();
			}
		}
		return greater;
	}

	/**
	Rotate the piece until it matches the desired shape
	@param desired the desired shape
	*/
	private void rotateToDesired(Pentomino desired) {
		int limitRotation = 3; // Bug prevention
		int rotCounter = 0;
		while (!this.piece.getFallingPentomino().equals(desired) && rotCounter < limitRotation) {
			this.piece.flipRight();
			System.out.println("Rotating");
			rotCounter++;
		}
	}

	/**
	Move the piece to a certain position
	@param pos the desired final position
	*/
	private void moveToPos(PPoint pos) {
		int distance = pos.intX() - this.piece.getPosition().intX();
		System.out.println("Distance: " + distance);
		if (distance > 0) {
			for (int i = 0; i < distance; i++) {
				this.piece.moveRight();
				System.out.println("Moving right");
			}
		}
		else if (distance < 0) {
			for (int i = 0; i < Math.abs(distance); i++) {
				this.piece.moveLeft();
				System.out.println("Moving left");
			}
		}
	}

	/**
	Guess if a certain shape is outside the board
	@param points the array of PPoint to check
	@param pos the position of the Pentomino
	@return true if it is outside, false otherwise
	*/
	public static boolean outside(PPoint[] points, PPoint pos) {
		boolean outside = false;
		int i = 0;
		while (!outside && i < points.length) {
			if (points[i].intX() + pos.intX() < 0 || points[i].intX() + pos.intX() >= COLUMNS) {
				outside = true;
			}
			else {
				i++;
			}
		}
		return outside;
	}

}
