package softwareGame;

/**
 * This class deals with the two ends of the domino tiles <b>on</b> the table. It
 * has a default constructor and 4 methods
 *
 * @author FATEEN, Mostafa A.M. <mostafafateen@gmail.com>
 * @author SOBEHY, Abdallah <a-sobehy@hotmail.com>
 * 
 */
public class Table {
	/** an integer to indicates the left value of the domino tile */
	private final int LEFT = 1;
	/** an integer to indicates the right value of the domino tile */
	private final int RIGHT = 2;
	/** an integer to indicates the occurance of an error */
	private final int NO_PLACE = 0;
	/** an integer to store the left value of the domino table */
	private int rightValue;
	/** an integer to store the right value of the domino table */
	private int leftValue;

	/**
	 * Default constructor for the class. Inside it the 2 integers
	 * {@code rightValue} and {@code leftValue} are initialized with {@code -1}
	 * to show that the table is empty
	 */
	Table() {
		rightValue = -1;
		leftValue = -1;

	}

	/**
	 * A getter that returns one of the 2 values {@code leftValue} or
	 * {@code rightValue} depending on the input. 1 indicates the need of the
	 * {@code leftValue} and, 2 the {@code rightValue} with a default return of
	 * the {@code rightValue}
	 * 
	 * @param side
	 *            integer that indicates which value is needed to be returned
	 * @return An integer containing the left or right value of the table
	 *         depending on the input
	 */
	int getValue(int side) {
		if (side == LEFT)
			return leftValue;
		return rightValue;
	}

	/**
	 * checks the whether the passed domino could be put on the table, and on
	 * which side
	 * 
	 * @param d
	 *            input domino to be checked at which side it is supposed to be
	 *            put
	 * 
	 * @return <li>0 => Not possible to put on table <li>1 => indicating left <li>2 => indicating right <li>3 =>
	 *         indicating the possible to be put on both sides of table
	 */
	int addDominoSide(Domino d) {
		int ret = NO_PLACE;
		if (rightValue == -1 && leftValue == -1)
			ret+= LEFT;
		if (d.getRightValue() == leftValue || d.getLeftValue() == leftValue)
			ret+= LEFT;
		if (d.getLeftValue() == rightValue || d.getRightValue() == rightValue)
			ret+= RIGHT;

		return ret;
	}

	/**
	 * Updates {@code leftValue} or {@code rightValue} or both depending on the
	 * added domino on the table. If it is the first domino added, both sides
	 * are updated. All following dominos update <b>only</b> one side
	 * 
	 * @param d
	 *            the added domino to the table
	 * @param updateSide
	 *            indicates which side to be updated: <li>1 => left <li>2 =>
	 *            right <li>3 => both
	 */
	void updateTableSide(Domino d, int updateSide) {
		if (updateSide == LEFT) {
			if (leftValue != d.getLeftValue())// updating the side with the
												// value of the domino which is
												// different from the existing
												// side
				leftValue = d.getLeftValue();
			else
				leftValue = d.getRightValue();
		} else if (updateSide == RIGHT) {
			if (rightValue != d.getRightValue())// updating the side with the
												// value of the domino which is
												// different from the existing
												// side
				rightValue = d.getRightValue();
			else
				rightValue = d.getLeftValue();
		} else {
			leftValue = d.getLeftValue();
			rightValue = d.getRightValue();
		}
	}

	/**
	 * This method is for developing and testing purposes, it displays the end
	 * values of the domino table on the terminal
	 */
	void dispEnds() {
		System.out.println("Left end of the table: " + leftValue
				+ " Right end of the table: " + rightValue);
	}
}
