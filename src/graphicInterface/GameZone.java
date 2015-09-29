package graphicInterface;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * Describe all the zones in this graphical interface.
 */

	 class GameZone  extends JPanel
	{
		 private static final long serialVersionUID = -5554714719712248972L;

		Dimension sizeGZ = new Dimension(1000,330);
		
		private GTable gTable;
		private JButton gStock;
		private JButton gDraw;
		private JButton gPlayPC;
		private GGame gGame;
		
		GameZone(GGame g)
		{
			gGame = g;
			
			setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			Dimension d = new Dimension(sizeGZ.width/8,sizeGZ.height);
			
			gTable = new GTable();
			
			
		    gStock = new JButton("I DRAW");
		    gStock.setPreferredSize(d);
		    gStock.setMinimumSize(d);
		    gStock.setMaximumSize(d);
		    gStock.setEnabled(false);
		    
		    gDraw = new JButton("I JUMP");		    
		    gDraw.setPreferredSize(d);
		    gDraw.setMinimumSize(d);
		    gDraw.setMaximumSize(d);		    
		    gDraw.setEnabled(false);
		    
		    gPlayPC = new JButton("Play PC");
		    gPlayPC.setPreferredSize(d);
		    gPlayPC.setMinimumSize(d);
		    gPlayPC.setMaximumSize(d);
		    gPlayPC.setEnabled(false);
		    
		    //Manage the layout 
		    setLayout(new GridBagLayout());
		    GridBagConstraints c = new GridBagConstraints(); 
		    c.fill =GridBagConstraints.HORIZONTAL;//to have automatically horizontal resize 
		    c.weighty= 1;  
		    
		    c.gridx = 0;        
		    add(gDraw, c); 		    
		    
		    c.gridx = 1;   
		    c.weightx=0.1;
		    add(gTable, c); 
		    
		    c.weightx = 0;     
		    c.gridx = 4;         
		    add(gStock, c);
		    
		    c.gridx = 6;         
		    add(gPlayPC, c);
		    
		    gDraw.addActionListener(new JumpManager(this));
		    gStock.addActionListener(new DrawManager(this));
		    gPlayPC.addActionListener(new PlayPCManager(this));
		    
			
		}
		/**
		 * Add the first domino on the table.
		 * @param image the domino'image to added
		 * @param left the left value of the domino
		 * @param right the right value of the domino
		 */
		 void addFirstDomino(ImageDomino image,int left, int right)
		{
			gTable.addFirstDomino(image,left,right);
			
		}
		/**
		 * Add a  domino on the left of the table.
		 * @param image the domino'image to added
		 * @param val the new value of the left table. eg the right value of the domino.
		 */
		 void addDominoLeft(ImageDomino image,int val)
		{
			gTable.addDominoLeft(image,val);
			
		}
		/**
		 * Add a  domino on the right of the table.
		 * @param image the domino'image to added
		 * @param val the new value of the right table. e.g the left value of the domino.
		 */
		 void addDominoRight(ImageDomino image,int val)
		{
			gTable.addDominoRight(image,val);
			
		}
	    public Dimension getMinimumSize()
		{
			return sizeGZ;
		}
		
		public Dimension getMaximumSize()
		{
			return sizeGZ;
		}
		
		public Dimension getPreferedSize()
		{
			return sizeGZ;
		}
		
		/**
		 * 
		 * @return the left value of the table.
		 */
		 int getValueLeftTable()
		{
			return gTable.getValueLeftTable();
		}
		/**
		 * 
		 * @return the right value of the table.
		 */
		 int getValueRightTable()
		{
			return gTable.getValueRightTable();
		}
		
		/**
		 * 
		 * @return true if there is no  domino on the table. Else return false.
		 */
		 boolean noDomino()
		{
			return gTable.noDomino();
		}
		
		/**
		 * Return true if there is only one domino on the table. Else return false.
		 */
		 boolean onlyOneDomino()
		{
			return gTable.onlyOneDomino();
		}
		
		/*
		 * Send a message from the buttons.
		 */
		 void sendMessage(int type)
		{
			gGame.sendMessage(type);
		}
		
		/**
		 * Enable or disable the use of jump button.
		 * @param b true or false
		 */
		 void setEnabledJump(boolean b)
		{
			gDraw.setEnabled(b);
		}
		
		/**
		 * Enable or disable the use of draw button.
		 * @param b true or false
		 */
		 void setEnabledDraw(boolean b)
		{
			gStock.setEnabled(b);
		}
		
		
	
		

		/**
		 * Enable or disable the use of PlayPC button.
		 * @param b true or false
		 */
		public void setEnabledPlayPC(boolean b) {
			gPlayPC.setEnabled(b);
			
		}
		
	}


