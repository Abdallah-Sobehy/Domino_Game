package graphicInterface;
	
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

	 class ImageDomino  extends Canvas 
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 8936982246035458285L;
		private Image image;
		public String nom;
		 InterfaceDomino d;
		
		public ImageDomino(String nomImage,GGame gPartie,InterfaceDomino d)
		{
			
			this(nomImage);
			this.d = d;
			addMouseListener(new ImageDominoManager(d,gPartie));
		}
		public ImageDomino(String nomImage)
		{

			image = Toolkit.getDefaultToolkit().getImage(nomImage);
			nom = nomImage;
			MediaTracker tracker = new MediaTracker(this);
	    	tracker.addImage(image,0,40,70);
	 
	    	try 
	    	{
	    		tracker.waitForID(0);
	    	}
	    	catch(InterruptedException e){
	    		System.out.println("erreur:" +getWidth()+"  "+getHeight());
	    	}

	    	repaint();
		}
		
		public void paint(Graphics g)
		{
			Dimension d = new Dimension(image.getWidth(this),image.getHeight(this));
			setSize(d);
			
			g.drawImage(image,0,0,this);
		}
		
		public boolean equals(ImageDomino img)
		{
			return(img.nom.equals(nom));
		}
		
		
	}
