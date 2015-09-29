package graphicInterface;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * To manage the general frame.
 */
 class WindowManager extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    { System.exit(0);}
}

