package graphicInterface;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Manage the playPC button.
 */
 class PlayPCManager implements ActionListener
{
	GameZone gameZone;
	public PlayPCManager(GameZone zone)
	{
		gameZone=zone;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		gameZone.sendMessage(GGame.VALIDPCPLAY);
	}
}

