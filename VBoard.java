/**
A class to represent a virtual board in order to guess which possible move is the best
@author Silvia Fallone
@author Sarah Waseem
@author Lucas Uberti-Bona
@author Antonio Rodríguez
@author Julián Marrades
@version 0.46, 5 Dec 2017
*/
public class VBoard implements PInfo {

  private int[][] matrix;
  private double score;
  private PPoint initPosition;
  private PPoint actualPosition;
  private Pentomino shape;
  private PPoint[] minoes;
  private double[] weights;
  private int height;
  private int lines;
  private int holes;
  private int bumpiness;
  private int[] colHeights;
  private boolean storedOutside;

  /**
  Initialize the attributes
  @param matrix the board
  @param position the position of the Pentomino
  @param shape the Pentomino that is falling in that moment
  @param weights the weights to apply to the heuristics
  */
  public VBoard(int[][] matrix, PPoint position, Pentomino shape, double[] weights) {
    this.storedOutside = false;
    this.colHeights = new int[COLUMNS];
    this.matrix = Help.cloneMatrix(matrix);
    this.weights = weights;
    this.initPosition = position;
    this.actualPosition = new PPoint(this.initPosition);
    this.shape = new Pentomino(shape);
    this.minoes = this.shape.getMinoes();
    this.depositShape();

    if (!this.storedOutside) {
      this.calcScore();
    }
    else {
      this.score = Double.MIN_VALUE;
    }

    System.out.println(this.score);
  }

  /**
  Deposit the shape in the bottom part (as much as it is possible) of the virtual board
  */
  private void depositShape() {
    while (!this.outside() && !this.overlapping()) {
      this.actualPosition.moveDown();
    }
    this.actualPosition.moveUp();
    this.store();
  }

  /**
  Compute the score of the result board
  */
  private void calcScore() {
    this.calcHeight();
    this.calcLines();
    this.calcHoles();
    this.calcBumpiness();
    this.score = this.weights[0]*this.height + this.weights[1]*this.lines + weights[2]*this.holes + this.weights[3]*this.bumpiness;
  }

  /**
  Get access to the score of the board
  @return the score
  */
  public double getScore() {
    return this.score;
  }

  /**
  Get access to the Pentomino used
  @return the Pentomino
  */
  public Pentomino getShape() {
    return this.shape;
  }

  /**
  Get access to the initial position of the Pentomino
  @return the position
  */
  public PPoint getPosition() {
    return this.initPosition;
  }

  /**
  Store the Pentomino in the board
  */
  private void store() {
    int i = 0;
    while (!this.storedOutside && i < this.minoes.length) {
      int x = this.actualPosition.intX() + minoes[i].intX();
      int y = this.actualPosition.intY() + minoes[i].intY();
      if (y < 0) {
        this.storedOutside = true;
      }
      else {
        this.matrix[x][y] = this.shape.getName();
        i++;
      }
    }
  }

  /**
  See if the pentomino is overlapping another one stored in the board
  @return true if the Pentomino is overlapping, false otherwise
  */
  private boolean overlapping() {
    boolean overlapping = false;
    int i = 0;
    while (!overlapping && i < this.minoes.length) {
      int x = this.actualPosition.intX() + this.minoes[i].intX();
      int y = this.actualPosition.intY() + this.minoes[i].intY();
      if (y >= 0 && this.matrix[x][y] != NO_PENTO) {
        overlapping = true;
      }
      else {
        i++;
      }
    }
    return overlapping;
  }

  /**
  See if the Pentomino is outside the board
  @return true if it is outside, false otherwise
  */
  private boolean outside() {
    boolean outside = false;
    int i = 0;
    while (!outside && i < this.minoes.length) {
      int x = this.actualPosition.intX() + this.minoes[i].intX();
      int y = this.actualPosition.intY() + this.minoes[i].intY();
      if (y >= ROWS || x < 0 || x >= COLUMNS) {
        outside = true;
      }
      else {
        i++;
      }
    }
    return outside;
  }

  /**
  Compute the aggregate height of the board
  */
  private void calcHeight() {
    int sum = 0;
    for (int x = 0; x < COLUMNS; x++) {
      boolean found = false;
      int y = 0;
      while (!found && y < ROWS) {
        if (this.matrix[x][y] != NO_PENTO) {
          found = true;
          int dist = ROWS-1-y;
          sum += dist;
          this.colHeights[x] = dist;
        }
        else {
          y++;
        }
      }
    }
    this.height = sum;
  }

  /**
  Compute the bumpiness of the board
  */
  private void calcBumpiness() {
    int bump = 0;
    for (int i = 0; i < this.colHeights.length-1; i++) {
      bump += Math.abs(this.colHeights[i] - this.colHeights[i+1]);
    }
    this.bumpiness = bump;
  }

  /**
  Compute the number of filled lines in the board
  */
  private void calcLines() {
    int counter = 0;
    for(int y=0; y < ROWS; y++) {
      boolean full = true;
      int x = 0;
      while (full && x < COLUMNS) {
        if (matrix[x][y] == NO_PENTO) {
          full = false;
        }
        else{
          x++;
        }
      }
      if(x == COLUMNS-1) {
        counter++;
      }
    }
    this.lines = counter;
  }

  /**
  Compute the number of holes in the current board
  */
  private void calcHoles() {
    int count = 0;
    for (int x = 0; x < COLUMNS; x++) {
      for (int y = ROWS-1; y >= 1; y--) {
        if (this.hole(x, y)){
          count++;
        }
      }
    }
    this.holes = count;
  }

  /**
  Verify if a given position is a hole
  @param matrix the board
  @param x given row
  @param y given column
  @return true if the position represents a hole, false otherwise
  */
  private boolean hole(int x, int y) {
    boolean holeFound = false;
    if (this.matrix[x][y] == NO_PENTO && this.matrix[x][y-1] != NO_PENTO){
      holeFound = true;
    }
    return holeFound;
  }

}
