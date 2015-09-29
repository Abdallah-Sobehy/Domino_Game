package graphicInterface;





import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * To manage the clicks on the dominos. If the user click on right mouse button
 * the graphical interface try to put the domino on the right side of the table. 
 * If the user click on left mouse button, the graphical interface try to put 
 * the domino on the left side of the table if the "putDominoOnLeftTable" method 
 * is used. If the "putDominoOnTable" method is used, the graphic interface choose
 * on which side the domino is put on the table.
 */
 class ImageDominoManager  extends MouseAdapter
{
	
	InterfaceDomino d;
	GGame gGame;
	 public  ImageDominoManager (InterfaceDomino d,GGame gGame)
	{
		this.gGame =gGame;
		
		this.d = d;
	}
	
	/** If the right button is clicked, the message PLAYRIGHT is sent to the GGame. 
	 * Else the message PLAY is sent. Because by default the domino is put on the 
	 * left side if it's possible
	 */
	@SuppressWarnings("static-access")
	public void mouseClicked(MouseEvent e)
	{
		gGame.setDomino(d);
		if (e.getButton()==e.BUTTON3)
		{
			System.out.println("Right click ");
			gGame.sendMessage(GGame.PLAYRIGHT);
			
		}
		else
		{
			System.out.println("Left click :");
			gGame.sendMessage(GGame.PLAY);
		}
		
	}  
}
