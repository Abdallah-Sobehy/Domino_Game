package graphicInterface;

	
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

	 class GHand extends JPanel
	{
		private static final long serialVersionUID = 3322400819162756303L;
		Dimension taille = new Dimension(550,150);
		Vector <ImageDomino>images = new Vector<ImageDomino>();
		 GHand()
		{
			
			setSize(100,450);
			setLayout(new GridLayout(3,7));
			setBackground(Color.blue);
		}
		
		public Dimension getMinimumSize()
		{
			return taille;
		}
		
		public Dimension getMaximumSize()
		{
			return taille;
		}
		
		public Dimension getPreferedSize()
		{
			return taille;
		}
		 void addDomino(ImageDomino image)
		{
			images.add(image);
			add(image);
			validate();
		}
		
		/**
		 * Remove the image from the user's hand.
		 * @param image
		 */
		 void removeDomino(ImageDomino image)
		{
			
			for(int i=0; i<images.size();i++)
			{
				if (image.equals((ImageDomino)images.get(i)))
				{
					
					images.remove(i);
					remove(i);
					validate();
					repaint();
				}
			}
		}
		
		/**
		 * Enable or disable the possibility to click on the images of the user's hand.
		 * @param b
		 */
		void setHandEnabled(boolean b)
		{
			@SuppressWarnings("rawtypes")
			Iterator iterator = images.iterator();
			// keep each image like disable
			while (iterator.hasNext())
			{
				ImageDomino d=(ImageDomino)iterator.next();
				 d.setEnabled(b);
				
			}
				
			
		}
	}
