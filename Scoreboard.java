import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.text.DecimalFormat;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
A class to represent a scoreboard
@author Lucas Uberti-Bona
@author Julián Marrades
@version 0.03, 2 Dec 2017
*/
public class Scoreboard implements PInfo {

  private PCanvas canvas;
  private Board board;
  private String[] leaderNames;
	private int[] leaderScores;
  private String playerName;

  /**
  Initialize the attributes
  @param canvas the canvas in which the scoreboard will be displayed
  */
  public Scoreboard(PCanvas canvas) {
    this.canvas = canvas;
    this.board = this.canvas.getBoard();
    this.playerName = this.board.getPlayerName();
    this.initLeaderBoard();
    this.getLeaderBoard();
    this.sortArrays();
  }

  /**
	Initialize the leaders names and scores arrays
	*/
	private void initLeaderBoard() {
		this.leaderNames = new String[LEADERS];
		this.leaderScores = new int[LEADERS];
	}

  /**
	Get the high score from leaderboard.txt
	*/
	private void getLeaderBoard() {
		FileReader reader = null;
		try {
			reader = new FileReader(FILENAME);
		}
		catch (FileNotFoundException e) {
			try {
				PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
				writer.close();
				this.leaderNames[0] = this.playerName;
				this.leaderScores[0] = this.board.getScore();
			}
			catch (FileNotFoundException e2) {/*This will never happen*/}
			catch (UnsupportedEncodingException e3) {/*This will never happen*/}
		}
		if (reader != null) {
			Scanner in = new Scanner(reader);
			int counter = 0;
			while (in.hasNextLine() && counter < LEADERS) {
				String string = in.nextLine();
				StringTokenizer tokens = new StringTokenizer(string);
				this.leaderNames[counter] = tokens.nextToken();
				this.leaderScores[counter] = Integer.parseInt(tokens.nextToken());
				counter++;
			}
			if (this.emptySlot() && !Help.isIn(this.playerName, this.leaderNames)) {
				int index = this.findFirstEmptySlot();
				this.leaderNames[index] = this.board.getPlayerName();
				this.leaderScores[index] = this.board.getScore();
			}
			try {
				reader.close();
			}
			catch (IOException e) {
				System.out.println("Could not close the reader");
			}
		}
	}

  /**
  Update the scoreboard
  */
  public void update() {
    if (Help.isIn(this.board.getPlayerName(), this.leaderNames) && this.ownBeaten()) {
			int index = this.getLeaderIndex(this.playerName);
			this.leaderScores[index] = this.board.getScore();
		}
		else if (!Help.isIn(this.board.getPlayerName(), this.leaderNames) && this.beatSomeone()) {
			int index = Help.getSmallestScoreIndex(this.leaderScores);
			this.leaderScores[index] = this.board.getScore();
			this.leaderNames[index] = this.board.getPlayerName();
		}
    this.sortArrays();
  }

  /**
	Find the index of the first empty slot
	@return the index of the slot
	*/
	private int findFirstEmptySlot() {
		boolean found = false;
		int i = 0;
		while (!found && i < this.leaderNames.length) {
			if (this.leaderNames[i] == null) {
				found = true;
			}
			else {
				i++;
			}
		}
		return i;
	}

	/**
	Guess if there exists at least one empty slot in the leaderboard
	@return true if there exists, false otherwise
	*/
	private boolean emptySlot() {
		boolean found = false;
		int i = 0;
		while (!found && i < this.leaderNames.length) {
			if (this.leaderNames[i] == null) {
				found = true;
			}
			else {
				i++;
			}
		}
		return found;
	}

  /**
	Write the leaderboard in the end of a game
	*/
	public void writeLeaderBoard() {
		try {
			PrintWriter writer = new PrintWriter(FILENAME, "UTF-8");
			for (int i = 0; i < LEADERS; i++) {
				if (this.leaderNames[i] != null) {
					writer.println(this.leaderNames[i] + " " + this.leaderScores[i]);
				}
			}
			writer.close();
		}
		catch (FileNotFoundException e2) {/*This will never happen*/}
		catch (UnsupportedEncodingException e3) {/*This will never happen*/}
	}

  /**
	Sort the leaders arrays to display it in order
	*/
	private void sortArrays() {
		ArrayList<String> namesCopy = new ArrayList<String>();
		ArrayList<Integer> scoresCopy = new ArrayList<Integer>();
		for (int i = 0; i < this.leaderNames.length; i++) {
			namesCopy.add(this.leaderNames[i]);
			scoresCopy.add(this.leaderScores[i]);
		}
		int counter = 0;
		while (namesCopy.size() > 0 && scoresCopy.size() > 0) {
			int index = Help.findMaxValueIndex(scoresCopy);
			this.leaderNames[counter] = namesCopy.get(index);
			this.leaderScores[counter] = scoresCopy.get(index);
			namesCopy.remove(index);
			scoresCopy.remove(index);
			counter++;
		}
	}

  /**
	Guess if the actual player (not being in the list), has beaten someone
	@return true if the player has beaten someone, false otherwise
	*/
	private boolean beatSomeone() {
		int index = Help.getSmallestScoreIndex(this.leaderScores);
		boolean beat = false;
		if (this.board.getScore() >= this.leaderScores[index]) {
			beat = true;
		}
		return beat;
	}

  /**
	Guess if the actual player has beaten his own highscore
	@return true if the player has beaten himself, false otherwise
	*/
	private boolean ownBeaten() {
		boolean beaten = false;
		int index = this.getLeaderIndex(this.playerName);
		if (this.board.getScore() > this.leaderScores[index]) {
			beaten = true;
		}
		return beaten;
	}

  /**
	Get the index of a given leader
	@param leader the name of the leader
	@return the index of the leader in the array leaderNames
	*/
	private int getLeaderIndex(String leader) {
		boolean found = false;
		int i = 0;
		while (!found && i < this.leaderNames.length) {
			if (leader.equals(this.leaderNames[i])) {
				found = true;
			}
			else {
				i++;
			}
		}
		return i;
	}

  /**
  Draw the scoreboard
  @param g2 the Graphics2D in charge of drawing
  */
  public void draw(Graphics2D g2) {
    this.drawScore(g2);
    this.drawLevel(g2);
  }

  /**
  Draw the score and leaderboard
  @param g2 the Graphics2D in charge of drawing
  */
  private void drawScore(Graphics2D g2) {
    DecimalFormat df = new DecimalFormat("#,###");

		// Draw actual score
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("verdana", Font.PLAIN, 20));
		g2.drawString("Score: " + df.format(this.board.getScore()), 20, 30);

		// Draw the Leaderboard
		g2.drawString("Leaderboard:", 20, 60);
		int counter = 0;
		while (counter < LEADERS) {
			if (this.leaderNames[counter] != null && this.leaderScores[counter] >= 0) {
				if (!this.playerName.equals(this.leaderNames[counter])) {
					g2.setFont(new Font("verdana", Font.PLAIN, 16));
				}
				else {
					g2.setFont(new Font("verdana", Font.BOLD, 16));
				}
				g2.drawString(this.leaderNames[counter] + ": " + df.format(this.leaderScores[counter]), 20, 90 + 23*counter);
			}
			counter++;
		}
  }

  /**
  Draw the level and multiplier
  @param g2 the Graphics2D in charge of drawing
  */
  private void drawLevel(Graphics2D g2) {
    g2.setColor(Color.WHITE);
		g2.setFont(new Font("verdana", Font.PLAIN, 22));
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);
		g2.drawString("Level: " + this.board.getLevel(), 20, this.canvas.getHeight() - 55);
		g2.drawString("x" + df.format(this.board.getMultiplier()), 20, this.canvas.getHeight() - 20);
  }

}
