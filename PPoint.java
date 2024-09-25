/**
A class in order to represent a point in the screen
@author Antonio Rodríguez
@version 0.29, 8 Dec 2017
*/
public class PPoint {

	private double x;
	private double y;

	/**
	Construct a point
	@param x x-coordinate
	@param y y-coordinate
	*/
	public PPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	Construct a point
	@param other the reference for the creatinon
	*/
	public PPoint(PPoint other) {
		this.x = other.getX();
		this.y = other.getY();
	}

	/**
	Set x to a particular value
	@param x x-set value
	*/
	public void setX(double x) {
		this.x = x;
	}

	/**
	Set y to a particular value
	@param y y-set value
	*/
	public void setY(double y) {
		this.y = y;
	}

	/**
	Get the value of x
	@return the value of x
	*/
	public double getX() {
		return this.x;
	}

	/**
	Get the integer part of x
	@return x casted to integer
	*/
	public int intX() {
		return (int) this.x;
	}

	/**
	Get the value of y
	@return the value of y
	*/
	public double getY() {
		return this.y;
	}

	/**
	Get the integer part of y
	@return y casted to integer
	*/
	public int intY() {
		return (int) this.y;
	}

	/**
	Move the PPoint 1 unit to the right
	*/
	public void moveRight() {
		this.x++;
	}

	/**
	Move the PPoint 1 unit to the left
	*/
	public void moveLeft() {
		this.x--;
	}

	/**
	Move the PPoint 1 unit up
	*/
	public void moveUp() {
		this.y--;
	}

	/**
	Move the PPoint 1 unit down
	*/
	public void moveDown() {
		this.y++;
	}

	/**
	Rotate the PPoint 90 degrees clockwise
	*/
	public void flipRight() {
		double tmp = this.x;
		this.x = -this.y;
		this.y = tmp;
	}

	/**
	Rotate the PPoint 90 degrees anticlockwise
	*/
	public void flipLeft() {
		double tmp = this.x;
		this.x = this.y;
		this.y = -tmp;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		PPoint other = null;
		if (obj instanceof PPoint) {
			other = (PPoint) obj;
		}
		else {
			return false;
		}
		if (this.getX() == other.getX() && this.getY() == other.getY()) {
			return true;
		}
		else {
			return false;
		}
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	public String toStringInt() {
		return "(" + (int)this.x + ", " + (int)this.y + ")";
	}

}
