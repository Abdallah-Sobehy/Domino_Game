package softwareGame;

import graphicInterface.InterfaceDomino;


/**
 * In this class we have the data structure of our domino. It contains a
 * constructor and four methods.
 * <p>
 * This class implements the interface {@code InterfaceDomino}
 * 
 * @author FATEEN, Mostafa A.M. <mostafafateen@gmail.com>
 * @author SOBEHY, Abdallah <a-sobehy@hotmail.com>
 */

public class Domino implements InterfaceDomino {

	/** an integer to store the left value of the domino tile */
	private int leftValue;
	/** an integer to store the right value of the domino tile */
	private int rightValue;

	/**
	 * A constructor which takes 2 integers to initialize the values of
	 * <code>leftValue</code> and <code>rightValue</code>
	 *
	 * @param lfValue
	 *            The left value of the domino tile
	 * @param rtValue
	 *            The right value of the domino tile
	 */

	Domino(int lfValue, int rtValue) {
		leftValue = lfValue;
		rightValue = rtValue;
	}

	/**
	 * A getter for the left value of the domino tile
	 *
	 * @return An integer containing the left value of the domino tile
	 */

	public int getLeftValue() {
		return leftValue;
	}

	/**
	 * A getter for the right value of the domino tile
	 *
	 * @return An integer containing the right value of the domino tile
	 */

	public int getRightValue() {
		return rightValue;
	}

	/**
	 * This method checks the equality of this domino with another one in
	 * question. The method has 2 overloaded implementations. In the first one
	 * it takes a domino and checks if it has the same 2 values
	 * {@code leftValue} and {@code rightValue}, while in the second
	 * implementation it directly takes the 2 values of the domino in question
	 * 
	 * @see Domino#equals(int, int)
	 * @param d
	 *            The domino in question
	 * @return A whether the 2 domino tiles have the same values or not in the
	 *         form of a boolean
	 */

	boolean equals(Domino d) {
		if ((rightValue == d.rightValue && leftValue == d.leftValue)
				|| (rightValue == d.leftValue && leftValue == d.rightValue))
			return true;
		return false;
	}

	/**
	 * This method has 2 overloaded implementations. In the first one it takes a
	 * domino and checks whether it is equal to itself or not (have the same 2
	 * values {@code leftValue} and {@code rightValue}), while in the second
	 * implementation it directly takes the 2 values of the domino in question
	 * 
	 * @see Domino#equals(Domino)
	 * @param lft
	 *            The left value of the domino in question
	 * @param rht
	 *            The right value of the domino in question
	 * @return A whether the 2 domino tiles have the same values or not in the
	 *         form of a boolean
	 */

	boolean equals(int lft, int rht) {
		if ((rightValue == lft && leftValue == rht)
				|| (rightValue == rht && leftValue == lft))
			return true;
		return false;
	}

	/**
	 * This method is for developing and testing purposes, it displays the 
	 * values of the domino tile on the terminal	
	 */
	
	void dispDomino() {
		System.out.print("Domino <<" + leftValue + "," + rightValue + ">> \n");
	}

}
