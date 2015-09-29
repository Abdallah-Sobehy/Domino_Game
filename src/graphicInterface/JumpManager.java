package graphicInterface;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* Manage the jump button.
*/
  class JumpManager  implements ActionListener
{
	 GameZone gameZone;
	public JumpManager(GameZone zone)
	{
		gameZone=zone;
	}
	
	public void actionPerformed(ActionEvent e)
	{		
		gameZone.sendMessage(GGame.JUMP);		
	}
}