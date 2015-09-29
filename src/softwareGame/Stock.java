package softwareGame;

/**
 * This class represents the stock of the domino tiles which is initially full
 * with all the dominos and then both the player and pc are dealt from it.
 * During the game both players can draw from it. The class consists of a
 * constructor and 3 methods
 * 
 * 
 * @author FATEEN, Mostafa A.M. <mostafafateen@gmail.com>
 * @author SOBEHY, Abdallah <a-sobehy@hotmail.com>
 */

public class Stock {

	/** the stock is in form of an array of dominos */
	Domino[] dominos;
	/** Constant containing the number of all domino tiles */
	final int MAX_DOMINOS_NUM = 28;
	/**
	 * Constant containing the maximum value which could be on one of the
	 * domino's sides
	 */
	final int MAX_VALUE_ON_TILE = 6;
	/** integer which contains the actual length of {@code dominos} */
	int stockSize;

	/**
	 * Default constructor where the array (the stock of the dominos) is created
	 * and initialized by the 28 domino tiles
	 */
	Stock() {
		dominos = new Domino[MAX_DOMINOS_NUM];
		Domino tmp;
		int x = 0;
		stockSize = MAX_DOMINOS_NUM;
		for (int i = 0; i <= MAX_VALUE_ON_TILE; i++) {
			for (int j = i; j <= MAX_VALUE_ON_TILE; j++) {
				tmp = new Domino(i, j);
				dominos[x++] = tmp;
			}
		}

	}

	/**
	 * This method randomly chooses a domino from the stack, removes it, and
	 * returns it.
	 * <p>
	 * {@link Math#random()} is used to get a random {@code double} between 0
	 * and 1 which is multiplied by {@code stockSize-1} to get a random
	 * {@code double} between 0 and {@code stockSize-1} which in turn is rounded
	 * by {@link Math#round(double)} to get an integer between 0 and
	 * {@code stockSize-1}. By this we have a randomly chosen domino from the
	 * stock
	 * 
	 * 
	 * @return A random domino from the stock
	 * 
	 * @see Math#random()
	 * @see Math#round(double)
	 * 
	 */
	Domino draw() {
		Domino dom;
		int x = (int) Math.round(Math.random() * (stockSize - 1));
		dom = dominos[x];

		for (int i = x; i < stockSize - 1; i++)
			dominos[i] = dominos[i + 1];
		stockSize--;
		return dom;
	}

	/**
	 * This method is for developing and testing purposes, it displays the
	 * values of the dominos in the stock using {@link Domino#dispDomino()}
	 * 
	 * @see Domino#dispDomino()
	 */

	void dispStock() {
		for (int i = 0; i < stockSize; i++) {
			System.out.print("item" + i + " in stock= ");
			dominos[i].dispDomino();
		}
	}

	/**
	 * Method to check whether the stock is empty or not
	 * 
	 * @return a boolean true if the stock is empty and false if not
	 */
	boolean isEmpty() {
		if (stockSize == 0)
			return true;
		return false;
	}

}
