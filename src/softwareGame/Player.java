package softwareGame;

import graphicInterface.GGame;

/**
 * 
 * This class deals with everything that is related to a player; his name and
 * the dominos in his hand. It contains a constructor and 7 methods
 * 
 * @author FATEEN, Mostafa A.M. <mostafafateen@gmail.com>
 * @author SOBEHY, Abdallah <a-sobehy@hotmail.com>
 */
public class Player {

	/**
	 * String containing the name of the player. The computer is always named
	 * "pc", the user chooses his name
	 */
	private String name;
	/** Array containing all dominos in the player's hand */
	private Domino[] dominosInHand;
	/**
	 * Integer containing the actual size of the {@code dominosInHand[]} array;
	 * the number of domino tiles in hand
	 */
	private int domInHand = 0;
	/**
	 * the game so that both classes {@link Stock} and {@link GGame} can be
	 * accessed without passing a lot of arguments
	 */
	private Game game;
	/**
	 * Integer containing the maximum size of the {@code dominosInHand[]} which
	 * is equal to 22 = 28 - 6 where 28 is the number of all domino tiles and 6
	 * is the number of tiles in the hand of the other player if he drew nothing
	 */
	private final int MAX_DOMINOS_IN_HAND = 22;

	/** type of the user; true if user , false if pc */
	private boolean userType;

	/**
	 * Constructor of Player which initializes {@code game}, {@code name}, and
	 * {@code dominosInHand}
	 * 
	 * @param pN
	 *            String containing the player name
	 * @param g
	 *            the game
	 */
	Player(String pN, Game g, boolean type) {
		game = g;
		name = pN;
		userType = type;
		System.out.println("Player Name: " + name);
		dominosInHand = new Domino[MAX_DOMINOS_IN_HAND];
		for (int i = 0; i < 6; i++)
			addDomino();

	}

	/**
	 * simple getter for the user type
	 * @return <li> true => human user <li> false => PC
	 */
	boolean getUserType()
	{
		return userType;
	}
	
	/**
	 * Adds a random domino from the stock to the hand after getting the stock
	 * from {@link Game}
	 * 
	 * @see Game#getStock()
	 * @see Stock#draw()
	 */

	void addDomino() {
		Domino d = game.getStock().draw();
		dominosInHand[domInHand] = d;
		domInHand++;
		if (userType)
			game.getGGame().addDominoInHand(d);
		return;
	}

	/**
	 * This method has 2 overloaded implementations. It removes the passed
	 * domino from the hand. If the player was not of type computer, the
	 * {@code userType=false}, the tile will also be removed from the GUI
	 * <p>
	 * In the first implementation the domino is passed as an instance of class
	 * {@link Domino} and it returns nothing, while in the other only the values
	 * of the needed domino are passed and it returns the {@link Domino}
	 * instance which has the same values
	 * 
	 * @param d
	 *            Domino to be removed from hand
	 */

	void remove(Domino d) {
		for (int i = 0; i <= domInHand; i++)
			if (dominosInHand[i].equals(d)) {
				for (int j = i; j < domInHand - 1; j++)
					dominosInHand[j] = dominosInHand[j + 1];
				domInHand--;
				game.getGGame().removeDominoFromHand(d);
				return;
			}
		// TODO write error case
	}

	/**
	 * This method has 2 overloaded implementations. It removes the passed
	 * domino from the hand. If the player was <b>not</b> the {@code pc} the
	 * tile will also be removed from the GUI
	 * <p>
	 * In the first implementation the domino is passed as an instance of class
	 * {@link Domino} and it returns nothing, while in the other only the values
	 * of the needed domino are passed and it returns the {@link Domino}
	 * instance which has the same values
	 * 
	 * 
	 * @param lft
	 *            The left value of the domino
	 * @param rht
	 *            The right value of the domino
	 * @return the domino instance which has been removed
	 */
	Domino remove(int lft, int rht) {
		for (int i = 0; i < domInHand; i++)
			if (dominosInHand[i].equals(lft, rht)) {
				Domino temp = dominosInHand[i];
				for (int j = i; j < domInHand - 1; j++)
					dominosInHand[j] = dominosInHand[j + 1];
				domInHand--;
				game.getGGame().removeDominoFromHand(dominosInHand[i]);
				return temp;
			}
		return null;
		// TODO write error case
	}

	/**
	 * This method has 3 overloaded methods. It takes a domino and search
	 * whether it is in the player's hand or not.
	 * <p>
	 * In the first implementation the domino is passed as an instance of class
	 * {@link Domino} and it returns a boolean indicating whether it was found
	 * or not, the second also returns the same boolean but only the values of
	 * the domino being searched are passed, on the other hand the third takes
	 * only 1 value, checks if any domino contains that value and then returns
	 * the {@link Domino} instance which has that value or {@code null} if it
	 * the domino was not found
	 * 
	 * @param d
	 *            Domino to be searched in hand
	 * @return boolean indicating whether the domino was found or not
	 */

	boolean inHand(Domino d) {
		for (int i = 0; i < domInHand; i++)
			if (dominosInHand[i].equals(d))
				return true;
		return false;
	}

	/**
	 * This method has 3 overloaded methods. It takes a domino and search
	 * whether it is in the player's hand or not.
	 * <p>
	 * In the first implementation the domino is passed as an instance of class
	 * {@link Domino} and it returns a boolean indicating whether it was found
	 * or not, the second also returns the same boolean but only the values of
	 * the domino being searched are passed, on the other hand the third takes
	 * only 1 value, checks if any domino contains that value and then returns
	 * the {@link Domino} instance which has that value or {@code null} if it
	 * the domino was not found
	 * 
	 * @param lft
	 *            The left value of the domino
	 * @param rght
	 *            The right value of the domino
	 * @return boolean indicating whether the domino was found or not
	 */

	boolean inHand(int lft, int rght) {
		for (int i = 0; i < domInHand; i++)
			if ((dominosInHand[i].getLeftValue() == lft && dominosInHand[i]
					.getRightValue() == rght)
					|| dominosInHand[i].getLeftValue() == rght
					&& dominosInHand[i].getRightValue() == lft)
				return true;
		return false;
	}

	/**
	 * This method has 3 overloaded methods. It takes a domino and search
	 * whether it is in the player's hand or not.
	 * <p>
	 * In the first implementation the domino is passed as an instance of class
	 * {@link Domino} and it returns a boolean indicating whether it was found
	 * or not, the second also returns the same boolean but only the values of
	 * the domino being searched are passed, on the other hand the third takes
	 * only 1 value, checks if any domino contains that value and then returns
	 * the {@link Domino} instance which has that value or {@code null} if it
	 * the domino was not found
	 * 
	 * @param val
	 *            value to be searched in any domino; either left or right
	 * 
	 * @return the domino with the needed value or null if the value is not
	 *         found
	 */
	Domino inHand(int val) {
		for (int i = 0; i < domInHand; i++)

			if (dominosInHand[i].getLeftValue() == val
					|| dominosInHand[i].getRightValue() == val)
				return dominosInHand[i];
		return null;
	}

	/**
	 * A simple getter for the player name
	 * 
	 * @return a string containing the player name
	 */
	String getName() {
		return name;
	}

	/**
	 * This method is for developing and testing purposes, it displays the
	 * values of the dominos in the hand using {@link Domino#dispDomino()}
	 * 
	 * @see Domino#dispDomino()
	 */

	void dispPlayerHand() {
		for (int i = 0; i < domInHand; i++) {
			System.out.print("item" + i + " in hand= ");
			dominosInHand[i].dispDomino();
		}

	}

	/**
	 * Method to check whether the stock is empty or not
	 * 
	 * @return a boolean true if the stock is empty and false if not
	 */
	boolean emptyHand() {
		if (domInHand == 0)
			return true;
		return false;
	}

}
