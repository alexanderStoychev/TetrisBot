import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Stack;
import java.util.ArrayList;
import java.lang.Thread;

/**
A class to manage all the Pentominoes present in the Canvas (not deposited)
@author Silvia Fallone
@author Julián Marrades
@author Pierre Bongrand
@version 0.89, 1 Dec 2017
*/
public class Piece implements PInfo {

	private PCanvas canvas;
	private Board board;
	private Pentomino fallingPento;
	private Pentomino nextPento;
	private Stack<Pentomino> bag;
	private PPoint position;
	private PPoint[] fallingMinoes;
	private PPoint[] nextMinoes;
	private Draw drawer;
	private boolean hit;
	private Bot bot;
	private boolean botActive;

	/**
	Initialize the attributes
	@param canvas the canvas in which we will project the Pentominoes
	*/
	public Piece(PCanvas canvas) {
		this.canvas = canvas;
		this.hit = false;
		this.drawer = this.canvas.getDrawer();
		this.board = this.canvas.getBoard();
		this.bag = new Stack<Pentomino>();
		this.fillBag();
		this.bot = new Bot(this);
		this.botActive = false;
		this.nextPento();
	}

	/**
	Fill the bag of Pentominoes with another 18 pieces
	*/
	private void fillBag() {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < QUANTITY_PENTOES; i++) {
			indexes.add(i);
		}
		while (indexes.size() > 0) {
			int index = (int) (Math.random()*indexes.size());
			int pentoName = indexes.get(index);
			this.bag.push(Pentomino.getPento(pentoName));
			indexes.remove(index);
		}
	}

	/**
	Set the position to the intial spot
	*/
	private void resetPosition() {
		this.position = new PPoint(STARTING_X, 0);
	}

	/**
	Pick a random Pentomino
	Get the array of positions of that random Pentomino
	*/
	private void nextPento() {
		this.resetPosition();
		this.fallingPento = this.bag.pop();
		this.fallingMinoes = this.fallingPento.getMinoes();
		// Check if the bag is empty after popping
		if (this.bag.empty()) {
			this.fillBag();
		}
		this.nextPento = this.bag.peek();
		this.nextMinoes = this.nextPento.getMinoes();

		//Check if the spawn is overlapping another mino, then stop
		boolean stopped = false;
		int i = 0;
		while (!stopped && i < fallingMinoes.length) {
			int x = this.position.intX() + this.fallingMinoes[i].intX();
			int y = this.position.intY() + this.fallingMinoes[i].intY();
			if (this.board.getData(x, y) != NO_PENTO) {
				this.stop();
			}
			else {
				i++;
			}
		}

	}

	/**
	Draw the Pentomino that is actually falling and the next Pentomino
	@param g2 the Graphics2D that will draw the pieces
	*/
	public void draw(Graphics2D g2) {
		for (PPoint mino : this.fallingMinoes) {
			int x = this.position.intX() + mino.intX();
			int y = this.position.intY() + mino.intY();
			this.drawer.drawMino(new PPoint(x, y), g2, this.fallingPento.getName());
		}
		for (PPoint mino : this.nextMinoes) {
			// Need to draw the following piece outside the board
			int x = mino.intX() + COLUMNS + 4;
			int y = mino.intY() + 3;
			this.drawer.drawMino(new PPoint(x, y), g2, this.nextPento.getName());
		}
	}

	/**
	Stop the game
	*/
	private void stop() {
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {}
		this.canvas.getScoreBoard().writeLeaderBoard();
		if (this.botActive) {
			System.out.println("Maybe look for better weights!");
		}
		else {
			System.out.println("Meh, you tried... but you still a terrible player!");
		}
		System.exit(0);
	}

	/**
	Store the falling Pentomino in the board
	*/
	private void store() {
		int data = this.fallingPento.getName();
		try {
			for (PPoint mino : this.fallingMinoes) {
				int col = this.position.intX() + mino.intX();
				int row = this.position.intY() + mino.intY();
				if (this.board.getData(col, row) == NO_PENTO) {
					this.board.setData(data, col, row);
				}
				else {
					this.stop();
				}

			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			this.stop();
		}
	}

	/**
	Move the falling Pentomino 1 unit right
	*/
	public void moveRight() {
		this.position.moveRight();
		if (this.wrongMove()) {
			this.position.moveLeft();
		}
	}

	/**
	Move the falling Pentomino 1 unit leff
	*/
	public void moveLeft() {
		this.position.moveLeft();
		if (this.wrongMove()) {
			this.position.moveRight();
		}
	}

	/**
	Move the falling Pentomino 1 unit up
	*/
	public void moveUp() {
		this.position.moveUp();
	}

	/**
	Move the falling Pentomino 1 unit down
	*/
	public void moveDown() {
		this.position.moveDown();
		if (this.wrongMove()) {
			this.position.moveUp();
			this.store();
			this.board.removeLines();
			this.nextPento();
			this.hit = true;
			if (this.botActive) {
				this.bot.pickMove();
			}
		}
	}

	/**
	Flip the falling Pentomino 90 degrees clockwise
	*/
	public void flipRight() {
		this.fallingPento.flipRight();
		if (this.wrongMove()) {
			this.fallingPento.flipLeft();
		}
	}

	/**
	Flip the falling Pentomino 90 degrees anticlockwise
	*/
	public void flipLeft() {
		this.fallingPento.flipLeft();
		if (this.wrongMove()) {
			this.fallingPento.flipRight();
		}
	}

	/**
	Drop the falling Pentomino to the bottom of the board
	*/
	public void drop() {
		while (!this.hit) {
			this.moveDown();
		}
		this.hit = false;
	}

	/**
	Guess if the falling Pentomino is at an illegal position
	@return true if the position is illegal, false otherwise
	*/
	private boolean wrongMove() {
		boolean wrong = false;
		int i = 0;
		while (!wrong && i < this.fallingMinoes.length) {
			int x = this.fallingMinoes[i].intX() + this.position.intX();
			int y = this.fallingMinoes[i].intY() + this.position.intY();
			if (x < 0 || x >= COLUMNS) {
				wrong = true;
			}
			if (!wrong && y >= ROWS) {
				wrong = true;
			}
			if (!wrong && this.board.getData(x, y) != NO_PENTO) {
				wrong = true;
			}
			i++;
		}
		return wrong;
	}

	/**
	Get access to the board of the piece
	@return the board
	*/
	public Board getBoard() {
		return this.board;
	}

	/**
	Get access to the position of the fallling Pentomino
	@return the PPoint representing the position
	*/
	public PPoint getPosition() {
		return this.position;
	}

	/**
	Get access to the falling Pentomino
	@return the falling pentomino
	*/
	public Pentomino getFallingPentomino() {
		return this.fallingPento;
	}

	/**
	Toggle bot active / inactive
	*/
	public void toggleBot() {
		this.botActive = !this.botActive;

		if (this.botActive) {
			System.out.println("Bot: ACTIVE");
		}
		else {
			System.out.println("Bot: DISABLED");
		}

		this.canvas.switchDelay();
		if (this.botActive) {
			this.bot.pickMove();
		}
	}

	/**
	Guess if the bot is active or not
	@return true if the bot is active, false otherwise
	*/
	public boolean isBotActive() {
		return this.botActive;
	}

}
