package graphicInterface;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Manage the draw button.
 */
 class DrawManager implements ActionListener
{
	GameZone gameZone;
	public DrawManager(GameZone zone)
	{
		gameZone=zone;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gameZone.sendMessage(GGame.DRAW);
	}
}
