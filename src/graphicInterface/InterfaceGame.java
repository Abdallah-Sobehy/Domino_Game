package graphicInterface;

/**
 * All class which want to use this graphical interface must inherit from this interface.
 * 
 */
public interface InterfaceGame 
{

	/**
	 * The type is one of "DATA_NAME","PLAY","JUMP","DRAW",
	 *  VALIDPCPLAY, NEWGAME, PLAYRIGHT defined in GGame class.
	 */
	public void receivedMessage(int type);
}
