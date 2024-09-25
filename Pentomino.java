/**
A class to hold the position of the minoss of the Pentominoes, and their name
@author Silvia Fallone
@author Julián Marrades
@version 0.62, 3 Dec 2017
*/

public class Pentomino implements PInfo {

	private PPoint[] minoes;
	private int name;

	/**
	Initialize the attributes of the Pentomino
	@param name an integer representing the name of the Pentomino
	@param zero center position
	@param one a position
	@param two a position
	@param three a position
	@param four a position
	*/
	public Pentomino(int name, PPoint zero, PPoint one, PPoint two, PPoint three, PPoint four) {
		this.name = name;
		this.minoes = new PPoint[] {zero, one, two, three, four};
	}

	/**
	Initialize the attributes
	@param other the Pentomino reference
	*/
	public Pentomino(Pentomino other) {
		this.name = other.getName();
		this.minoes = Help.cloneArray(other.getMinoes());
	}

	/**
	Get access to the array of positions of the Pentomino
	@return the array containing the positions of the minoes
	*/
	public PPoint[] getMinoes() {
		return this.minoes;
	}

	/**
	Get access to the name of the Pentomino
	@return name the name of the Pentomino
	*/
	public int getName() {
		return this.name;
	}

	/**
	Flip all the positions of the Pentomino 90 degrees clockwise
	*/
	public void flipRight() {
		for (int i = 0; i < this.minoes.length; i++) {
			this.minoes[i].flipRight();
		}
	}

	/**
	Flip all the positions of the Pentomino 90 degrees anticlockwise
	*/
	public void flipLeft() {
		for (int i = 0; i < this.minoes.length; i++) {
			this.minoes[i].flipLeft();
		}
	}

	/**
	Get a random Pentomino
	@return a random Pentomino
	*/
	public static Pentomino random() {
		int index = (int) (Math.random()*QUANTITY_PENTOES);
		return getPento(index);
	}

	/**
	Get a Pentomino given the name
	@param name the name of the Pentomino
	@return the Pentomino with the name provided
	*/
	public static Pentomino getPento(int name) {

		Pentomino temporal = null;

		switch (name) {

			case PENTO_P1:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(0, 1), new PPoint(1, 0), new PPoint(1, -1));
				break;
			case PENTO_P2:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(0, 1), new PPoint(-1, 0), new PPoint(-1, -1));
				break;
			case PENTO_X:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(0, 1), new PPoint(1, 0), new PPoint(-1, 0));
				break;
			case PENTO_F1:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(-1, 0), new PPoint(0, 1), new PPoint(0, -1), new PPoint(1, -1));
				break;
			case PENTO_F2:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(0, 1), new PPoint(-1, -1), new PPoint(1, 0));
				break;
			case PENTO_V:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(0, -2), new PPoint(1, 0), new PPoint(2, 0));
				break;
			case PENTO_W:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(-1, 0), new PPoint(-1, -1), new PPoint(0, 1), new PPoint(1, 1));
				break;
			case PENTO_Y1:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(-1, 0), new PPoint(0, -1), new PPoint(0, 1), new PPoint(0, 2));
				break;
			case PENTO_Y2:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(1, 0), new PPoint(0, -1), new PPoint(0, 1), new PPoint(0, 2));
				break;
			case PENTO_I:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(0, -2), new PPoint(0, 1), new PPoint(0, 2));
				break;
			case PENTO_T:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(-1, 0), new PPoint(1, 0), new PPoint(0, 1), new PPoint(0, 2));
				break;
			case PENTO_Z1:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(-1, -1), new PPoint(0, 1), new PPoint(1, 1));
				break;
			case PENTO_Z2:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(0, 1), new PPoint(1, -1), new PPoint(-1, 1));
				break;
			case PENTO_U:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(-1, 0), new PPoint(-1, -1), new PPoint(1, 0), new PPoint(1, -1));
				break;
			case PENTO_N1:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(-1, 0), new PPoint(-2, 0), new PPoint(0, -1), new PPoint(1, -1));
				break;
			case PENTO_N2:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(1, 0), new PPoint(2, 0), new PPoint(-1, 0), new PPoint(-1, -1));
				break;
			case PENTO_L1:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, -1), new PPoint(-1, -1), new PPoint(0, 1), new PPoint(0, 2));
				break;
			case PENTO_L2:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, 1), new PPoint(0, 2), new PPoint(0, -1), new PPoint(1, -1));
				break;
			default:
				temporal = new Pentomino(name, new PPoint(0, 0), new PPoint(0, 0), new PPoint(0, 0), new PPoint(0, 0), new PPoint(0, 0));
				break;

		}

		return temporal;

	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		Pentomino other = null;
		if (obj instanceof Pentomino) {
			other = (Pentomino) obj;
		}
		else {
			return false;
		}
		if (this.name == other.name && Help.match(this.minoes, other.minoes)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	Get all the shapes a given Pentomino may have
	@param name the name of the pentomino you want to obtain shapes
	@return the array with all the shapes the given pentomino has
	*/
	public static Pentomino[] getAllShapes(int name) {
		Pentomino main = Pentomino.getPento(name);
		Pentomino[] array;
		if (name == PENTO_X) {
			array = new Pentomino[1];
		}
		else if (name == PENTO_I) {
			array = new Pentomino[2];
		}
		else {
			array = new Pentomino[4];
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = new Pentomino(main);
			main.flipRight();
		}
		return array;
	}

	public String toString() {
		String sum = "[ Name: " + this.name;
		for (PPoint mino : this.minoes) {
			sum += "; " + mino.toStringInt();
		}
		sum += " ]";
		return sum;
	}

}
