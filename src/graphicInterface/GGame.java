package graphicInterface;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;


/**
 * Graphical interface of the game. If you want to use this interface, you have to call 
 * the constructor with an occurrence of your class which implements InterfaceGame.
 * This graphical interface sends a message to your class in the receivedMessage method. 
 * This message contains one element of type: DATA_NAME, PLAY, PLAYRIGHT, JUMP, DRAW, 
 * VALIDPCPLAY or NEWGAME.
 * 		- If the user clicks on the right mouse button
 * the graphical interface tries to put the domino on the right side of the table. 
 * 		- If the user clicks on left mouse button, the graphical interface tries to put 
 * the domino on the left side of the table if the "putDominoOnLeftTable" method 
 * is used. 
 * 		- If the "putDominoOnTable" method is used, the graphical interface chooses
 * on which side the domino is put on the table.
 *
 */
public class GGame extends JFrame
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3953940062126575231L;
	private GMessageBar stateBar;	
	private GameZone gameZone;
	private GHand gHand;
	private InterfaceGame game;
	private final static int HIGHT = 1000;
	private final static int WIDE = 600;

	private String playerName="";
	/**
	 * The last domino selected. When the player clicks on a domino, this attribute is updated.
	 */
	public InterfaceDomino dominoChoiced = null;
	
	/**
	 * Indicate the player has entered his name.
	 */
	public  static final int DATA_NAME = 0;
	/**
	 * The player has double clicked on a domino. Means that the domino must be put on the 
	 * right side of the table
	 */
	public static final int PLAYRIGHT = 6;
	/**
	 * The player has clicked on a domino.
	 */
	public static final int PLAY = 1;
	/**
	 * The player has clicked on the jump button.
	 */
	public static final int JUMP = 2;
	/**
	 * The player has clicked on the draw button.
	 */
	public static final int DRAW = 3;
	/**
	 * The player has clicked on the playPC button.
	 */
	public static final int VALIDPCPLAY = 4;
	/**
	 * The player has clicked on the new game menu.
	 */
	public static final int NEWGAME = 5;
	Container contentPane ;
	/**
	 * The constructor to be call by the user application.
	 * @param game The  class of the user application which are going to receive the 
	 * messages from this interface. This user's class must implement InterfaceGame interface.
	 */
	public GGame(InterfaceGame game)
	{
		this.game = game;
		setTitle("Dominos game");
	    setSize(HIGHT,WIDE);
	    
	    //record of events
	    addWindowListener( new WindowManager());
	
	    setJMenuBar(new GMenuBar(this));
	    contentPane = getContentPane();
	    contentPane.setLayout(new GridLayout(3,1));
	    initialize();
	    
	}
	
	/**
	 * Add a domino into the graphical hand of the player.
	 */
	
	public void addDominoInHand(InterfaceDomino d)
    {
		String imageName = "imagesDominos/"+d.getLeftValue()+"-"+
							d.getRightValue()+".jpg";
		ImageDomino image = new ImageDomino(imageName,this,d);
		gHand.addDomino(image);
    }
	
	 /**
	  * Return the last domino selected.
	  */
	public InterfaceDomino getDomino()
	{
		return dominoChoiced;
	}
	
	/**
	 * Return the name written by the player.
	 */
	public String getPlayerName()
	{
		return playerName;
	}
	
	/**
	 * Initialise the graphical interface.
	 */
	 void initialize()
	{
		// build components
	    stateBar = new GMessageBar(this);
	    gameZone = new GameZone(this);
	    
	    gHand = new GHand();
	    
	    // insert tool bar
	    contentPane.add(gameZone);
	    // insert graphical zone
	    contentPane.add(gHand);
	    // Insert state bar
	    contentPane.add(stateBar);
	}
	 
	 void newGame()
	{
		sendMessage(NEWGAME );
	}
	 
	/**
	 * Put a domino on the graphic table. If domino does not match with graphical table, 
	 * this domino is put on left side. Be careful with this use. If you want more security 
	 * please use methods putDominoOnLeftTable and putDominoOnRightTable. But in this
	 * case you have to manage exception.
	 * @param d Domino to put on the table. 
	 * 
	 */
	public void   putDominoOnTable (InterfaceDomino d)
	{
		int leftEnd=gameZone.getValueLeftTable();	
		int rightEnd=gameZone.getValueRightTable();	
			
		//TODO 7ell el moshkela
		
		if (gameZone.noDomino()) // no domino on the table
			{
				String nomImage = "imagesDominos/"+d.getLeftValue()+"-"+d.getRightValue()+".jpg";
				ImageDomino image = new ImageDomino(nomImage);
				
				gameZone.addFirstDomino(image,d.getLeftValue(),d.getRightValue());
				
			}
		else if(d.getLeftValue()==leftEnd)
	    	{
	    		String nomImage = "imagesDominos/"+d.getRightValue()+"-"+d.getLeftValue()+".jpg";
	    		ImageDomino image = new ImageDomino(nomImage);
	    		
	    		gameZone.addDominoLeft(image,d.getRightValue());
	    			
	    	}
	    else if(d.getLeftValue()==rightEnd)
	    	{
	    		String nomImage = "imagesDominos/"+d.getLeftValue()+"-"+d.getRightValue()+".jpg";
	    		ImageDomino image = new ImageDomino(nomImage);
	    		
	    		gameZone.addDominoRight(image,d.getRightValue());
	    	}
	    else if(d.getRightValue()==leftEnd)
	    	{
	    		String nomImage = "imagesDominos/"+d.getLeftValue()+"-"+d.getRightValue()+".jpg";
	    		ImageDomino image = new ImageDomino(nomImage);
	    		
	    		gameZone.addDominoLeft(image,d.getLeftValue());
	    		
	    	}
	    else 
	    	{
	    		String nomImage = "imagesDominos/"+d.getRightValue()+"-"+d.getLeftValue()+".jpg";
	    		ImageDomino image = new ImageDomino(nomImage);
	    		
	    		gameZone.addDominoRight(image,d.getLeftValue());
	    	}    		
	 }
		
	
	/**
	 * Put a domino on the left of the graphical table. 
	 * If you do not want to manage exception, use the 
	 * method putDominoOnTable().
	 * @param d Domino to put on the table. 
	 * @throws BadMatchException if Domino does not match with table left side.
	 */
	public void   putDominoOnLeftTable (InterfaceDomino d) throws BadMatchException
	{
		//TODO 7ell el moshkela
		
		int leftEnd=gameZone.getValueLeftTable();
		
		if (gameZone.noDomino()) // no domino on the table
		{
			String imageName = "imagesDominos/"+d.getLeftValue()+"-"+d.getRightValue()+".jpg";
			ImageDomino image = new ImageDomino(imageName);
			
			gameZone.addFirstDomino(image,d.getLeftValue(),d.getRightValue());
			
		}
		else if(d.getLeftValue()==leftEnd)
    	{
    		String imageName = "imagesDominos/"+d.getRightValue()+"-"+d.getLeftValue()+".jpg";
    		ImageDomino image = new ImageDomino(imageName);
    		
    		gameZone.addDominoLeft(image,d.getRightValue());
    	}
    	else if(d.getRightValue()==leftEnd)
        {
        	String imageName = "imagesDominos/"+d.getLeftValue()+"-"+d.getRightValue()+".jpg";
        	ImageDomino image = new ImageDomino(imageName);
        	
        	gameZone.addDominoLeft(image,d.getLeftValue());
        }
    	else
    		throw new BadMatchException();	
    }
	
	/**
	 * Put a domino on the right of the graphical table. 
	 * If you do not want to manage exceptions, use the 
	 * method putDominoOnTable().
	 * @param d Domino to put on the table. 
	 * @throws BadMatchException if Domino does not match with table right side.
	 */
	public void   putDominoOnRightTable (InterfaceDomino d) throws BadMatchException
	{
		int rightEnd=gameZone.getValueRightTable();	
	
		if (gameZone.noDomino()) // no domino on the table
		{
			String imageName = "imagesDominos/"+d.getLeftValue()+"-"+d.getRightValue()+".jpg";
			ImageDomino image = new ImageDomino(imageName);
			
			gameZone.addFirstDomino(image,d.getLeftValue(),d.getRightValue());
			
		}
		
    	else if(d.getLeftValue()==rightEnd)
    	{
    		String imageName = "imagesDominos/"+d.getLeftValue()+"-"+d.getRightValue()+".jpg";
    		ImageDomino image = new ImageDomino(imageName);
    		
    		gameZone.addDominoRight(image,d.getRightValue());
    	}
    	
    	else  if(d.getRightValue()==rightEnd)
    	{
    		String imageName = "imagesDominos/"+d.getRightValue()+"-"+d.getLeftValue()+".jpg";
    		ImageDomino image = new ImageDomino(imageName);
    		gameZone.addDominoRight(image,d.getLeftValue());
    	}
    	else
    		throw new BadMatchException();
	
	
    }	
	
	/**
	 * Remove a domino from the graphical hand of the player.
	 */
	public void removeDominoFromHand(InterfaceDomino d)
	{
		String imageName = "imagesDominos/"+d.getLeftValue()+"-"+
								d.getRightValue()+".jpg";
		ImageDomino image = new ImageDomino(imageName,this,d);
		gHand.removeDomino(image);
	}
	
	/**
	 * Send a message to the application. The type is one of "DATA_NAME","PLAY","JUMP","DRAW"
	 */
	void sendMessage(int type)
	{
		game.receivedMessage(type);
	}
	
	/**
	 * Set the jump button enabled if the boolean is true, and not enabled if false.
	 */
	public void setEnabledJump(boolean b)
	{
		gameZone.setEnabledJump(b);
	}
	
	/**
	 * Set the draw button enabled if the boolean is true, and not enabled if false.
	 */
	public void setEnabledDraw(boolean b)
	{
		gameZone.setEnabledDraw(b);
	}
	
	/**
	 * Write the string s in the editing graphical zone.
	 */
	public void setMessage(String s)
	{
		stateBar.setTexte(s);
	}
	/**
	 * Set the editing graphical zone  enabled if the boolean is true, and not enabled if false.
	 */
	public void setMessageEnabled(boolean b)
	{
		stateBar.setEnabled(b);
	}
	/**
	 * Store the choosen domino.
	 * @param d
	 */
	void setDomino(InterfaceDomino d)
	{
		dominoChoiced = d;
	}
	
	 void setPlayerName(String name)
	{
		playerName = name;
	}
	
	/**
	 * Set the click on the dominos enabled or not enabled.
	 */
	public void setHandEnable( boolean b)
	{		 
			gHand.setHandEnabled(b);		
	}
	
	/**
	 * Set the playPC button enabled or not enabled.
	 */
	public void setEnabledPlayPC(boolean b) 
	{
		gameZone.setEnabledPlayPC(b);		
	}
	
	

}
