package graphicInterface;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

 class GMessageBar  extends JTextField implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4051317654380140690L;
	Dimension dimension = new Dimension(550,50);
	String text="";

	GGame game;
	public GMessageBar (GGame game) 
	{
		this.game = game;
		setFont(new Font(null,Font.BOLD,14));
	
		setSize(dimension);
		setBackground(Color.white); // background color			
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object origine = e.getSource();
		if (origine == this)
		{
			String tex = getText();
			
			String name = tex.substring(text.length(), tex.length());
			setText("");
			setEditable(false);
			
			game.setPlayerName(name);
			game.sendMessage(GGame.DATA_NAME);
		
			
		}	
	}
	
	 void setTexte(String s)
	{
		text = s;
		setText(s);
		setCaretPosition(s.length());
		repaint();
	}
	public Dimension getMinimumSize()
	{
		return dimension;
	}
	
	public Dimension getMaximumSize()
	{
		return dimension;
	}
	
	public Dimension getPreferedSize()
	{
		return dimension;
	}
}

