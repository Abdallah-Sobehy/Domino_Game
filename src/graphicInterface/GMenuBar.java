package graphicInterface;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


 class GMenuBar extends JMenuBar implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3062559281102480744L;
	private JMenuItem quit, newGame;
	private GGame game;
	
	 GMenuBar(GGame game) 
	{
		
		this.game = game;
		// built of the File menu.
		JMenu menuFichier = new JMenu("File");
		newGame = new JMenuItem("New game");
		quit = new JMenuItem("Quit");
		menuFichier.add(newGame);
		menuFichier.addSeparator();
		menuFichier.add(quit);

		add(menuFichier);
		newGame.addActionListener(this);
		quit.addActionListener(this);
	}

		public void actionPerformed(ActionEvent e)
		{
			Object origine = e.getSource();
			if (origine == newGame)
				game.newGame();		
			else if (origine == quit)
				System.exit(0);
		}
	}

