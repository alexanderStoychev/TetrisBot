import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import java.util.ArrayList;

/**
A class to represent a 2D board
@author Antonio Rodríguez
@author Julián Marrades
@version 0.24, 25 Oct 2017
*/
public class Board implements PInfo {

	private PCanvas canvas;
	private PPoint position;
	private int[][] matrix;
	private Draw drawer;
	private int score;
	private int scoref;
	private double multiplier;
	private int level;
	private String[] leaderNames;
	private int[] leaderScores;
	private String playerName;
	private int totalLines;

	/**
	Initialize the attributes
	@param canvas the canvas that will contain the board
	*/
	public Board(PCanvas canvas) {
		this.canvas = canvas;
		this.matrix = new int[COLUMNS][ROWS];
		this.resetBoard();
		this.calcPosition();
	}

	/**
	Calculate the position of the board (from top-left corner) on the canvas
	*/
	private void calcPosition() {
		int width = this.canvas.getWidth();
		int height = this.canvas.getHeight();
		this.position = new PPoint((width - BOARD_WIDTH)/2, (height - BOARD_HEIGHT)/2);
	}

	/**
	Set all the cells of the board to -1 (NO_PENTO)
	*/
	private void resetBoard() {
		this.score = 0;
		this.scoref = 730;
		this.level = 1;
		this.multiplier = 1;
		this.totalLines = 0;
		for (int y = 0; y < ROWS; y++) {
			this.resetLine(y);
		}
		this.askName();
	}

	/**
	Increase the tempo and score of the game
	*/
	private void levelUp() {
		this.level++;
		this.multiplier *= 1.5;
		this.canvas.increaseTempo();
	}

	/**
	Prompt the player for his or her name
	*/
	private void askName() {
		Scanner in = new Scanner(System.in);
		System.out.println("What is your name?");
		this.playerName = in.nextLine();
	}

	/**
	Set all the cells of a line to -1 (NO_PENTO)
	@param y the row that needs to be reset
	*/
	private void resetLine(int y) {
		for (int x = 0; x < COLUMNS; x++) {
			this.matrix[x][y] = NO_PENTO;
		}
	}

	/**
	Remove all the full lines of the board
	*/
	public void removeLines() {
		int y = ROWS - 1;
		int lines = 0;
		while (y >= 0) {
			int x = 0;
			while (x < COLUMNS && this.matrix[x][y] != NO_PENTO) {
				x++;
			}
			if (x == COLUMNS) { //Arrived to the end
				lines++;
				if (y != 0) {
					this.moveLinesDown(y);
				}
				else {
					this.resetLine(y);
				}
			}
			else {
				y--;
			}
		}
		double bonus = Math.pow(1.1, lines - 1);
		this.score += (int) (bonus*lines*this.scoref*this.multiplier);

		//Update the scoreboard
		this.canvas.getScoreBoard().update();
		//If in total there is more than 10 lines removed, level UP!
		this.totalLines += lines;
		if (this.totalLines >= LIMIT_LINES) {
			this.levelUp();
			this.totalLines -= LIMIT_LINES;
		}
	}

	/**
	Move everything 1 line down
	@param y the row which is the startpoint to move every line above, 1 unit down
	*/
	private void moveLinesDown(int y) {
		this.resetLine(y);
		while (y > 0) {
			for (int x = 0; x < COLUMNS; x++) {
				this.matrix[x][y] = this.matrix[x][y-1];
			}
			y--;
		}
		this.resetLine(0);
	}

	/**
	Draw the board in the canvas
	@param g2 the Graphics2D variable which is responsible for drawing
	*/
	public void draw(Graphics2D g2) {
		// Draw the grid
		for (int y = 0; y < ROWS; y++) {
			for (int x = 0; x < COLUMNS; x++) {

				PPoint tmp = new PPoint(x*MINO_WIDTH+this.position.intX(), y*MINO_HEIGHT+this.position.intY());

				if (this.matrix[x][y] == NO_PENTO) {
					g2.setColor(Color.WHITE);
					g2.drawRect(tmp.intX(), tmp.intY(), MINO_WIDTH, MINO_HEIGHT);
				}
				else { // There is a mino stored
					this.drawer.drawMino(new PPoint(x, y), g2, this.matrix[x][y]);
				}
			}
		}

	}

	/**
	Get access to the position of the board
	@return the PPoint of the position
	*/
	public PPoint getPosition() {
		return this.position;
	}

	/**
	Set the drawer of the board
	@param drawer the drawer object
	*/
	public void setDrawer(Draw drawer) {
		this.drawer = drawer;
	}

	/**
	Get access to the cells of the board
	@param x the column
	@param y the row
	@return the content of the cell [x][y]
	*/
	public int getData(int x, int y) {
		if (y < 0) {
			return NO_PENTO;
		}
		else {
			return this.matrix[x][y];
		}
	}

	/**
	Set a cell of the matrix to a given value
	@param data the data that wants to be stored
	@param x the column
	@param y the row
	*/
	public void setData(int data, int x, int y) {
		this.matrix[x][y] = data;
	}

	/**
	Get access to the score of the current game
	@return the score
	*/
	public int getScore() {
		return this.score;
	}

	/**
	Get access to the name of the player
	@return the name of the player
	*/
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	Get access to the current level
	@return the current level
	*/
	public int getLevel() {
		return this.level;
	}

	/**
	Get access to the multiplier
	@return the current multiplier
	*/
	public double getMultiplier() {
		return this.multiplier;
	}

	/**
	Get access to the matrix of the Board
	@return the matrix
	*/
	public int[][] getMatrix() {
		return this.matrix;
	}

}
