import java.util.ArrayList;

/**
A class to keep general static methods that help procedures
@author Julián Marrades
@version 0.22, 5 Dec 2017
*/
public class Help {

	/**
	Create a deep copy of a matrix
	@param matrix the 2-Dimensional matrix that will be cloned
	@return the new matrix
	*/
	public static int[][] cloneMatrix(int[][] matrix) {
		int[][] newMatrix = new int[matrix.length][matrix[0].length];
		copyMatrixValues(matrix, newMatrix);
		return newMatrix;
	}

	/**
	Create a deep copy of a PPoint array
	@param array the array that will be copied
	@return the copied array
	*/
	public static PPoint[] cloneArray(PPoint[] array) {
		PPoint[] newArray = new PPoint[array.length];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = new PPoint(array[i]);
		}
		return newArray;
	}

	/**
	Copy the values from a matrix to another, assuming that both of them have
	the same size
	@param from the matrix that will provide the values
	@param to the matrix that will accept the values
	*/
	public static void copyMatrixValues(int[][] from, int[][] to) {
		for (int i = 0; i < from.length; i++) {
			for (int j = 0; j < from[i].length; j++) {
				to[i][j] = from[i][j];
			}
		}
	}

  /**
	Get the index of the smallest value of an array
	@param array the array we want to search
	@return the index of the minimum value
	*/
	public static int getSmallestScoreIndex(int[] array) {
		int minValue = Integer.MAX_VALUE;
		int minValueIndex = -1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < minValue) {
				minValue = array[i];
				minValueIndex = i;
			}
		}
		return minValueIndex;
	}

  /**
  Guess if a string is contained on a given array
  @param value the string we are looking for
  @param array the array in which we want to search
  @return true if the string is contained, false otherwise
  */
  public static boolean isIn(String value, String[] array) {
    boolean found = false;
    int i = 0;
    while (!found && i < array.length) {
      if (value.equals(array[i])) {
        found = true;
      }
      else {
        i++;
      }
    }
    return found;
  }

  /**
	Find the index of the greatest value of an array list
	@param array ArrayList in which we want to explore
	@return the index of the greatest value
	*/
	public static int findMaxValueIndex(ArrayList<Integer> array) {
		int greatestIndex = -1;
		int greatestValue = Integer.MIN_VALUE;
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > greatestValue) {
				greatestIndex = i;
				greatestValue = array.get(i);
			}
		}
		return greatestIndex;
	}

	/**
	Guess if an array of PPoints matches another array of PPoints
	@param arr1 the first array of PPoints
	@param arr2 the second array of PPoints
	@return true if they match, false otherwise
	*/
	public static boolean match(PPoint[] arr1, PPoint[] arr2) {
		boolean coincide = true;
		int i = 0;
		while (coincide && i < arr1.length) {
			if (!arr1[i].equals(arr2[i])) {
				coincide = false;
			}
			else {
				i++;
			}
		}
		return coincide;
	}

}
