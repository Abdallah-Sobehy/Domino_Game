/**
 * softwareGame contains classes created to use the graphical interface package
 * The logic and components of the game are created here.
 */
package softwareGame;

import graphicInterface.BadMatchException;
import graphicInterface.GGame;
import graphicInterface.InterfaceGame;

/** Runs the game between a player and the computer.
 * <br>The graphical user interface is created using <code>gGame</code>.<br>
 * 2 players are created, one is the PC by the default, the other player is created with the name entered by the user.<br>
 * Stock of 28 dominos is created where each player is given 6 dominos in hand at the start of the game.<br>
 * Table of dominos where the dominos are put during the game is created, initially empty.<br>
 * @author FATEEN, Mostafa A.M. <mostafafateen@gmail.com>
 * @author SOBEHY, Abdallah <a-sobehy@hotmail.com>
 * @version march 2015
 *
 */
public class Game implements InterfaceGame {
	/** indicates that the domino is to be played on the left of the table. */
	private final int TABLE_LEFT = 1;
	/** indicates that the domino is to be played on the right of the table. */
	private final int TABLE_RIGHT = 2;
	/** indicates that the domino could be played on both sides of the table. */
	private final int TABLE_BOTH = 3;
	/** The graphical interface.*/
	GGame gGame;
	/** used during the computer's turn to store domino temporarily>*/
	Domino computerTemp = null;
	/** The stock*/
	private Stock stock;

	/** The board where dominos are put.*/
	private Table table;

	/**Player 1*/
	private Player player1;

	/**Computer*/
	private Player pc;

	/** Represents the current state of the game. The constants afterwards describe the different states.*/
	int indState;
    /** Game state where a player is asked to play a double 6.*/
	private final int DOUBLE_6 = 6;
	/** Game state where a player is asked to play a double 5.*/
	private final int DOUBLE_5 = 5;
	/** Game state where a player is asked to play a double 4.*/
	private final int DOUBLE_4 = 4;
	/** Game state where a player is asked to play a double 3.*/
	private final int DOUBLE_3 = 3;
	/** Game state where a player is asked to play a double 2.*/
	private final int DOUBLE_2 = 2;
	/** Game state where a player is asked to play a double 1.*/
	private final int DOUBLE_1 = 1;
	/** Game state where a player is asked to play a double 0.*/
	private final int DOUBLE_0 = 0;
	
	/** Game state when it the player's turn after the first double domino play.*/
	private final int PLAYER_TURN = 8;
	/** Game state when it the player's turn after the first double domino play and the stock is empty.*/
	private final int PLAYER_TURN_NO_STOCK = 9;
	/** Game state when it the computer's turn after the first double domino play.*/
	private final int COMPUTER_TURN = 10;
	/** Game state when it the computer's turn after the first double domino play and the stock is empty.*/
	private final int COMPUTER_TURN_NO_STOCK = 11;
	/** Game state when neither player has a double domino to start the game.*/
	private final int NO_DOUBLES = 12;
	/** Game state when the game ends due to: <li> The 2 players make 2 consecutive jumps</li>
	 * <li>Either the player or the computer win the game by finishing all dimonos in hand</li>.*/
	private final int GAME_BLOCKED = 13;

	/** indicates that the left end of the table is to be updated.
	 * sent to updateTableSide function
	 * @see Table#updateTableSide(Domino, int)
	 */
	private final int UPDATE_LEFT = 1;
	/** indicates that the right end of the table is to be updated.
	 * sent to updateTableSide function
	 * @see Table#updateTableSide(Domino, int)
	 */
	private final int UPDATE_RIGHT = 2;
	/** indicates that both ends of the table are to be updated.
	 * sent to updateTableSide function
	 * @see Table#updateTableSide(Domino, int)
	 */
	private final int UPDATE_BOTH = 3;
	/** True if the player validated the PC play, so that the PC can execute it.*/
	boolean pcPlayValidated = false;
	/** True when a player executes a jump, used as track if 2 consecutive jumps occurred.*/
	boolean firstJump = false; /** < set to true when either player jumps, so if 2 jumps occurs the game is blocked and ends*/
	/**set true when 2 jumps occur consecutively and the game is blocked, complements firstJump variable.*/
	boolean consecJumps = false;
	/** set true when the player plays and no dominos are left in hand*/
	boolean playerWins = false;
	/** set true when the computer plays and no dominos are left in hand*/
	boolean compWins = false;
	/** set true when the player wants to play a domino on the RHS of the table*/
	boolean playRight = false;
	/** Describes the state of the stock; true if empty false if contains dominos.*/
	boolean stockEmptyKnown = false;

	/** Constructor for the Game.
	 * <br> <li>Creates a graphical interface</li> <li>sends the graphical interface a
	 * message to enter the player's name.</li>
	 */
	public Game() 
	{
		indState = DOUBLE_6;// The game is initialized with the double 6 state
		gGame = new GGame(this);
		gGame.setVisible(true);
		gGame.setMessage("Please enter your name: ");
	}
	
	/**
	 * Simple getter for the instance of {@link GGame}
	 * 
	 * @return the GGame instance in the game.
	 */
	GGame getGGame() {
		return gGame;
	}

	/**
	 * Simple getter for the instance of {@link Stock}
	 * @return the current stock of the game
	 */
	Stock getStock() {
		return stock;
	}

	/**
	 * This method is called when an event is produced in the graphical
	 * interface.
	 * @param eventType Describes the event that triggered the graphical interface.
	 */
	public void receivedMessage(int eventType) {
		System.out.println("\ntype received message  " + eventType + " for state "
				+ indState);
		/** Used to store dominos temporarily after an event is triggered in the graphical interface.*/
		Domino temp;
		switch (eventType) {
		/**
		 * <b> The event that triggered the interface: </b> For each event the program has a
		 * different behaviour.
		 */
		/** When the user enters his name to start the game. {@link initialize(String)} function is called  */
		case GGame.DATA_NAME:
			initialize(gGame.getPlayerName());
			break;
		/** When the user clicks a right click on a domino to play it on the right end of the table.*/
		case GGame.PLAYRIGHT:
			//TODO
			playRight = true;
		/** The player has left clicked on a domino. */
		case GGame.PLAY:
			// TODO
			switch (indState) {
			/** The current state of the game is checked to execute the proper behaviousr. */
			/** In case of any of the DOUBLE states the selected Domino is checked to be valid 
			 * by calling {@link treatDoubleAnswer(Domino)}
			 */
			case DOUBLE_6:
			case DOUBLE_5:
			case DOUBLE_4:
			case DOUBLE_3:
			case DOUBLE_2:
			case DOUBLE_1:
			case DOUBLE_0:
				temp = (Domino) gGame.getDomino();
				try {
					treatDoubleAnswer(temp);
				} catch (BadMatchException e) {
					e.printStackTrace();
				}
				playRight=false;
				break;
				/** In case neither player has any double dominos. The player is allowed to choose any domino 
				 * to start the game with. Function {@link treatDoubleAnswer(Domino)} is called.*/
			case NO_DOUBLES:
				temp = (Domino) gGame.getDomino();
				try {
					treatDoubleAnswer(temp);
				} catch (BadMatchException e) {
					e.printStackTrace();
				}
				playRight=false;
				break;
			/** in case it is the player's turn after the first play.
			 * The chosen domino is checked for validity by calling {@link treatDoubleAnswer(Domino)}*/
			case PLAYER_TURN:
			case PLAYER_TURN_NO_STOCK:
				/** < Player's turn after the double domino part */
				temp = (Domino) gGame.getDomino();
				try {
					treatDoubleAnswer(temp);
				} catch (BadMatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				playRight=false;
				break;
			/** In case it is the computer turn, the player is asked to validate the computer's turn play before
			 * being able to play.
			 */
			case COMPUTER_TURN:
				/** < Computer's turn after the double domino part */
				gGame.setMessage("it is the computer's turn to play, press Play PC to validate the play.");
				break;
			/**
			 * In case the game is blocked nothing happens.
			 */
			case GAME_BLOCKED:
				break;
			}
			break;
			
		/**
		 * The player clicked the jump button. First the program checks if it is a second consecutive jump 
		 * to block the game, or tracks  first jump has occured. @see firstJump @see consecJumps<br>
		 * If it is not a second consecutive jump then the game is continued normally and {@link treatJumpAnswer()}
		 * is called.<br>
		 * Then the jump button is set to inactive
		 * and the computer plays by calling the {@link computerPlay()} function.
		 */
		case GGame.JUMP:
			if (!firstJump )// checking if the computer has already jumped in the previous turn 
			{
				firstJump = true;
			}
			else
			{
				consecJumps = true;
				endGame();
			}
			if ( !consecJumps )
			{
				treatJumpAnswer();
				gGame.setEnabledJump(false);
				computerPlay();
			}
			break;
		/** The player clicked Draw button.
		 * {@link playerDraw()} function is called.
		 */
		case GGame.DRAW:
			playerDraw();
			break;
		/** the player clicked on valid PC play button.
		 * <code>PcPlayValidated</code> is set to true and the {@link computerPlay()} function is called. 
		 */
		case GGame.VALIDPCPLAY:
			pcPlayValidated = true;
			computerPlay();
			break;
		/** The player chose new game from file menu.*/
		case GGame.NEWGAME:
			gGame.setVisible(false);
			Game g2 = new Game ();
			break;
		}
	
	}

	/**
	 * The computer plays in relationship with the state of the game. If state =
	 * n (with n =1,2,3,4,5,6) we look for a double n in the computer's hand. If
	 * yes, the computer plays else the player is asked to play the double
	 * domino (n-1). If n=0 we look for a double 0 in the computer's hand. If
	 * yes, the computer plays, otherwise the player is asked to play any other
	 * domino. If n=8 or 9 normal game managing the stock and the empty stock.
	 * If n=11 blocked game.
	 */
	public void computerPlay() 
	{
		System.out.println("state:" + indState + ". computer plays");
		gGame.setEnabledDraw(false);
		switch (indState) {
		case DOUBLE_6:
		case DOUBLE_5:
		case DOUBLE_4:
		case DOUBLE_3:
		case DOUBLE_2:
		case DOUBLE_1:
		case DOUBLE_0:

			if (!canPlayFirst(pc, indState)) {
				if (indState > 0 && indState <= 6)// if it is the first play for
													// the computer to play
													// double 6, 5 .. 1
				{
					gGame.setMessage("The computer does not have a double "
							+ indState + " in hand. Please play double "
							+ (indState - 1) + " or jump if you do not have.");
					indState--;
					canPlayFirst(player1, indState);
				} else if (indState == 0) // in case the first Domoni is with
											// neither player and the computer
											// is t be checked for having double
											// 0
				{
					gGame.setMessage("The computer does not have a double "
							+ indState
							+ " in hand. Please select any domino to start the game with");
					indState = NO_DOUBLES; // the case where neither player have
											// any double domino
				}
			} else // the First play can be played by the pc
			{
				if (pcPlayValidated) {
					computerTemp = pc.remove(indState, indState);
					gGame.putDominoOnTable(computerTemp);
					gGame.setEnabledPlayPC(false);
					pcPlayValidated = false;
					// updating the table with the domino added by the computer
					if ((indState >= DOUBLE_0 && indState <= DOUBLE_6)
							|| indState == NO_DOUBLES)
						table.updateTableSide(computerTemp, UPDATE_BOTH);
					else
						table.updateTableSide(computerTemp, UPDATE_LEFT);
					firstJump = false;
					//checking if the computer finished all dominos in hand and won the game
					if ( pc.emptyHand() )
					{
						compWins = true;
						endGame();
						return;
					}
					table.dispEnds();
					indState = PLAYER_TURN;// The game state is turned to
											// player's nth play
					gGame.setMessage("The computer executed the play. Please, select a vaild domino to play.");
					if (stockEmptyKnown == false)
						gGame.setEnabledDraw(true);
					else 
					{
						gGame.setEnabledJump(true);
						indState = PLAYER_TURN_NO_STOCK;
					}
				} 
				else 
				{
					gGame.setMessage("the Computer will play Domino: <<"
							+ indState + "," + indState + ">> \n Press Play PC");
					gGame.setEnabledPlayPC(true);
					gGame.setEnabledJump(false);
				}

			}
			// TODO
			break;
		case COMPUTER_TURN_NO_STOCK:
			if (pcPlayValidated) 
			{
				if (!firstJump )// checking if the player has already jumped in the previous turn 
				{
					firstJump = true;
				}
				else
				{
					consecJumps = true;
					endGame();
					return;
				}
				gGame.setMessage("Computer jumped. Please, select a vaild domino to play.");
				gGame.setEnabledPlayPC(false);
				if (stockEmptyKnown == false)// before setting draw active check if the stock is known to be empty
					gGame.setEnabledDraw(true);
				else // if stock is known to be empty then set jump active instead
				{
					gGame.setEnabledJump(true);
				}
				indState = PLAYER_TURN;
				pcPlayValidated = false;
			} 
			else 
			{
				gGame.setMessage("Computer does not have a valid domino and the stock is empty, computer will jump. Press Play PC to continue");
				gGame.setEnabledPlayPC(true);
				gGame.setEnabledJump(false);
			}
			// TODO
			break;
		case COMPUTER_TURN: // The game status is cimputer's nth play
			int playSide;// the side the computer will play on the table 1 ->
							// left, 2-> right , 0 -> can not play
			playSide = compCanPlayNth();
			if (pcPlayValidated) {
				if (playSide != 0) // the pc has a valid domino to be played
				{
					if (playSide == TABLE_LEFT) {
						pc.remove(computerTemp);
						try {
							gGame.putDominoOnLeftTable(computerTemp);
						} catch (BadMatchException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						table.updateTableSide(computerTemp, UPDATE_LEFT);
						table.dispEnds();
					} else if (playSide == TABLE_RIGHT) {
						pc.remove(computerTemp);
						try {
							gGame.putDominoOnRightTable(computerTemp);
						} catch (BadMatchException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						table.updateTableSide(computerTemp, UPDATE_RIGHT);
						table.dispEnds();
					}
					firstJump = false;
					//checking if the computer finished all dominos in hand and won the game
					if ( pc.emptyHand() )
					{
						compWins = true;
						endGame();
						return;
					}
					pc.dispPlayerHand();// TODO teb2a teshelha
					gGame.setMessage("The computer executed the play. Choose a valid domino to continue.");
					gGame.setEnabledPlayPC(false);
					if (stockEmptyKnown == false)
						gGame.setEnabledDraw(true);
					else 
					{
						gGame.setEnabledJump(true);
						indState = PLAYER_TURN_NO_STOCK;
					}
					pcPlayValidated = false;
					indState = PLAYER_TURN;// state of the game is returned to
											// player's turn
				} else // the PC does not have a valid domino
				{
					if ( stockEmptyKnown == false ) // only draw if the stock is not known to be empty
						playerDraw();
					else
						indState = COMPUTER_TURN_NO_STOCK;
					pcPlayValidated = false;
					pc.dispPlayerHand(); // TODO shelha
					computerPlay();
				}

			} else// if the play is not validated
			{
				if (playSide != 0) {
					gGame.setMessage("the Computer will play Domino: <<"
							+ computerTemp.getLeftValue() + ","
							+ computerTemp.getRightValue() + ">> Press Play PC");
					gGame.setEnabledPlayPC(true); // TODO el mafrood ne-assign
					gGame.setEnabledJump(false);
													// el computertemp bee null
													// hena aw ba3d a5er
													// este5dam leeh
				} else// if the Computer does not have a valid domino to play
				{
					if ( stockEmptyKnown == false ) // only draw if the stock is not known to be empty
						gGame.setMessage("The Computer will Draw press play PC");
					else// if the stock known to be empty the computer will jump
					{
						indState = COMPUTER_TURN_NO_STOCK;
						computerPlay();
					}
					gGame.setEnabledPlayPC(true);
					gGame.setEnabledJump(false);
				}

			}
			break;

		case NO_DOUBLES:
			break;

		default:
			System.out.println("state no valid");
			// TODO mmkn hena an2el gGame.setEnabledDraw(true); azon bas msh
			// akeed teb2a tegarab fel a5er
		}

	}

	/**
	 * Creates a stock, a board, two players (player and computer), initializes
	 * the graphical interface: hand, buttons and send it the first message.<br>
	 * asks the user to choose a double 6 domino or jump.
	 * @param name The name of the player
	 */
	public void initialize(String name) 
	{
		// TODO
		stock = new Stock();
		table = new Table();
		stock.dispStock();
		player1 = new Player(name, this, true);
		player1.dispPlayerHand();
		pc = new Player("PC", this, false);
		pc.dispPlayerHand();
		indState = 6;
		gGame.setMessage("Hi " + name
				+ ", Please play double 6 or Jump if you don't have");
		canPlayFirst(player1, indState);
	}

	/**
	 * Checks if the player or the computer has a valid double domino to play the first play so that 
	 * the player can not cheat and jump if he does have the correct domino.<br>
	 * Enables the jump button if the player can not play the first play.
	 * @param indState the expected double domino for the first play sets jump button
	 * enabled if player 1 does not have the required double domino
	 * @return returns true if the first play can be played
	 */
	boolean canPlayFirst(Player p, int indState) {
		if (!p.inHand(indState, indState)) {
			if (p.getUserType())
				gGame.setEnabledJump(true);
			return false;
		}
		return true;
	}

	/**
	 * Checks the pc's dominos, if any can be used to play on the table
	 * @return and integer to describe at which side the computer can play:
	 * <li> 1 if the pc can play on the left side of the table.</li>
	 * <li> 2 if the pc can play on the right side of the table.</li>
	 * <li> 0 if the pc can not play on either sides of the table.</li>
	 * Assigns the domino found to the <code>computertemp</code> variable
	 */
	int compCanPlayNth() {
		if (pc.inHand(table.getValue(TABLE_LEFT)) != null) {
			computerTemp = pc.inHand(table.getValue(TABLE_LEFT));
			return 1;
		}

		if (pc.inHand(table.getValue(TABLE_RIGHT)) != null) {
			computerTemp = pc.inHand(table.getValue(TABLE_RIGHT));
			return 2;
		} else
			return 0;
	}

	/**
	 * gets the the value on either sides of the table.
	 * @param side The side to be considered(1: left, 2: right)
	 * @return The extremity value of the domino on the considered side of the table.
	 */
	public int getEnd(int side) {
		return table.getValue(side);
	}

	/**
	 * Verify if the input domino can be played on the board.
	 * calls {@link treatAnswer(Domino)} method if the domino can be played
	 * otherwise sends a message to the user indicating that the domino is invalid.
	 * @param d The selected domino.
	 * @throws BadMatchException
	 */
	public void treatDoubleAnswer(Domino d) throws BadMatchException {
		/** if it is the first play and ( the domino is the correct double domino
		* or neither players had a double domino, the player chooses whatever
		* he wants to start )
		*/
		if (getEnd(TABLE_LEFT) == -1
				&& (d.equals(indState, indState) || indState == NO_DOUBLES)) {
			treatAnswer(d);
			return;
		}
		/**if the domino to be added has a value equal to the right end or left
		* end of the table
		*/
		if (d.getLeftValue() == getEnd(TABLE_LEFT)
				|| d.getLeftValue() == getEnd(TABLE_RIGHT)
				|| d.getRightValue() == getEnd(TABLE_LEFT)
				|| d.getRightValue() == getEnd(TABLE_RIGHT)) {
			treatAnswer(d);
			return;
		}

		gGame.setMessage("Choose another domino, this is an invalid domino");
	}

	/**
	 * The input domino is removed from the hand of the player and put on one of the sides of the table.
	 * Then the computer plays by calling {@link #computerPlay()} function
	 * @param d The domino selected by the player.
	 * @throws BadMatchException
	 */
	public void treatAnswer(Domino d) throws BadMatchException { // TODO
		/** checks where the domino should be put on the left or on the right. */
		int side = table.addDominoSide(d);
		System.out.println("Domino chosen by player to be added on " + side
				+ " side");
		if (side == TABLE_LEFT || (side == TABLE_BOTH && !playRight)) 
		{
			player1.remove(d);
			gGame.putDominoOnLeftTable(d);
			// updating the table with the domino added by the player
			if ((indState >= DOUBLE_0 && indState <= DOUBLE_6)
					|| indState == NO_DOUBLES)
				table.updateTableSide(d, UPDATE_BOTH);
			else
				table.updateTableSide(d, UPDATE_LEFT);
			table.dispEnds();
			firstJump = false;
			//checking if the player finished all dominos in hand and won the game
			if ( player1.emptyHand() )
			{
				playerWins = true;
				endGame();
				return;
			}
			indState = COMPUTER_TURN;// The game state is turned to computer's
										// nth play
			computerPlay();
		} 
		else if (side == TABLE_RIGHT || (side == TABLE_BOTH && playRight)) 
		{
			player1.remove(d);
			gGame.putDominoOnRightTable(d);
			// updating the table with the domino added by the player
			if ((indState >= DOUBLE_0 && indState <= DOUBLE_6)
					|| indState == NO_DOUBLES)
				table.updateTableSide(d, UPDATE_BOTH);
			else
				table.updateTableSide(d, UPDATE_RIGHT);
			firstJump = false;
			//checking if the player finished all dominos in hand and won the game
			if ( player1.emptyHand() )
			{
				playerWins = true;
				endGame();
				return;
			}
			table.dispEnds();
			indState = COMPUTER_TURN;// The game state is turned to computer's
										// nth play
			computerPlay();
		} else
			throw new BadMatchException();
	}

	/** Draw a domino from the stock and puts it in the hand of the player or pc
	 * depending in the current indState.
	 * If the stock is empty, jump button is activated in case it is the player turn,
	 * The <code>indState</code> is changed to <code>PLAYER_TURN_NO_STOCK</code> or 
	 * <code>COMPUTER_TURN_NO_STOCK</code> depending on the current game state.
	 */
	public void playerDraw() {
		// TODO
		if (stock.isEmpty() && stockEmptyKnown == false) 
		{
			if (indState == PLAYER_TURN) 
			{
				indState = PLAYER_TURN_NO_STOCK;
				gGame.setEnabledJump(true);
				gGame.setEnabledDraw(false);
				gGame.setMessage("The stock is empty, please play a valid domino or Jump");
			} 
			else if (indState == COMPUTER_TURN) 
			{
				indState = COMPUTER_TURN_NO_STOCK;
				gGame.setMessage("The computer does not have a valid domino and can not draw from the empty stock.");
			}
			stockEmptyKnown = true;
			System.out.println("Stock is known to be empty !!");
		}
		else if ( stock.isEmpty() && stockEmptyKnown == true )// in case the stock is empty and it is known
		{
			
		}
		else // stock is not empty
		{
			if (indState == PLAYER_TURN)
				player1.addDomino();
			else if (indState == COMPUTER_TURN)
				pc.addDomino();
		}
	}

	/**
	 * called when the player presses on the Jump button.
	 * To verify that the player does not jump when he can play the first domino.
	 * Changes the state of the game to <code>COMPUTER_TURN</code> when the stock is empty.
	 * Disables the jump button in the end.
	 */
	public void treatJumpAnswer() 
	{
		System.out
				.println("State:" + indState + ". Into jump player's process");
		switch (indState) {
		case 6:
		case 5:
		case 4:
		case 3:
		case 2:
		case 1:
			if (canPlayFirst(player1, indState)) {
				gGame.setMessage("You have a double "
						+ indState
						+ " domino, you have to play it int the beginning of the game.");
			}
			firstJump = false;
			break;

		case PLAYER_TURN_NO_STOCK: case PLAYER_TURN:
			indState = COMPUTER_TURN;
			break;
		// TODO

		}
		gGame.setEnabledJump(false);
	}
	/**
	 * ends the game when it is blocked or any of the players win
	 */
	void endGame()
	{
		if (consecJumps)// in case the game is blocked
		{
			gGame.setMessage("the game is blocked");
		}
		
		else if ( playerWins )
		{
			gGame.setMessage("Congratulations " + player1.getName() + " You have defeated the computer" );
		}
		else if ( compWins )
		{
			gGame.setMessage("You have lost to the computer, better luck next time." );
		}
		gGame.setEnabledDraw(false);
		gGame.setEnabledJump(false);
		gGame.setEnabledPlayPC(false);
		indState = GAME_BLOCKED;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Game game = new Game();
	}
}
