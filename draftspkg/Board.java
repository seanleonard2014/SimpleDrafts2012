/***********/
/** Sean Leonard					 */
/** C05633915						 */
/**------------------------------------------------------*/
/** The Board class will the board with	DraftsPieces. A player can press Reset in the menubar which will place all of */
/** the pieces back to their original position and the opposite player will win that game. The Update score button in */
/** the DraftsGame class will show the updated scores. If a player presses Close in the menubar the window is closed */
/** and the scores will be updated. The panel above the board will display which player is next to move and the black */
/** panel to the left hand side display the scores of the current game. To move a piece the player can click and drag a
/** piece from the board and then click on an appropriate square to place that piece. The player panel will then update to */
/** indicate that it is the next players turn to move. The MouseListener on the ColouredPanels hold the constrainst for */
/** which the DraftPieces are to move.*/


package draftspkg;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.EventObject;
import java.util.ArrayList;

import java.lang.Object;
import java.lang.Runtime;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Board extends JFrame implements ActionListener
{
		public int playerswitch = 0;			//Will determine which players go it is.
		public int redwin = 0;				//will be 1 if Red player wins.
		public int bluewin = 0;				//will be 1 if Blue player wins.
		int n;						//Used to determine if row is odd or even.
		public int rs = 0;				//Records the score of the activate game.
		public int bs = 0;				//Records the score of the activate game.
		JLabel rvalues;					//Displays red score of the activate game.
		JLabel bvalues;					//Displays blue score of the activate game.
		JLabel player;					//Displays whos move is next.
		JPanel playerpanel;				//Displays whos move is next.
		public int pt = 0;				//Tempory variable to hold t value of previous panel clicked.
		public int psp = 0;				//Tempory variable to hold sp value of previous panel clicked.
		public int index =0;				//Tempory variable to hold index value of previous panel clicked.
		public JMenuItem Game, quit;
		public ArrayList dp = new ArrayList();		//Array list to hold Red Pieces
		public ArrayList dp2 = new ArrayList(); 	//Array list to hold Blue Pieces
		public ArrayList cp = new ArrayList(); 		//Array list to hold Black Panels
		public ArrayList cp2 = new ArrayList();		//Array list to hold White Panels
		DraftsPiece dpt;
        	DraftsPiece dp2t;
        	ColouredPanel cpt;
        	ColouredPanel cp2t;
        	ColouredPanel cp3t;
		ColouredPanel bcp;
		int pdp = 0;
		int pdp2 = 0;
		int cj = 1;					//Used to intialise columns 1 to 4.
		public int row = 0;				//Tempory variable to hold row value of previous panel clicked.
		public int col = 0;				//Tempory variable to hold column value of previous panel clicked.
		Boolean readytojump = false;
		int [][] possiblejumps = new int[2][2];
		Board theBoard;					//Used To start new game.

	public Board(String myTitle)
	{
		super(myTitle);
		setBackground(Color.RED);
		setSize(700, 600);
		setLocation(320, 100);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setContentPane(createpane());
		setJMenuBar(createMenuBar());
		setVisible(true);
		showPane();
	}

	private Container createpane()
	{
		Container pane = new JPanel(new BorderLayout());	//8 JPanels are created.
		JPanel mypane = new JPanel();
		mypane.setLayout(new GridLayout(8, 8));
		JPanel Row1 = new JPanel();
		Row1.setBackground(Color.BLUE);
		JPanel Row2 = new JPanel();
		Row2.setBackground(Color.BLUE);
		JPanel Row3 = new JPanel();
		Row3.setBackground(Color.BLUE);
		JPanel Row4 = new JPanel();
		Row4.setBackground(Color.BLUE);
		JPanel Row5 = new JPanel();
		Row5.setBackground(Color.BLUE);
		JPanel Row6 = new JPanel();
		Row6.setBackground(Color.BLUE);
		JPanel Row7 = new JPanel();
		Row7.setBackground(Color.BLUE);
		JPanel Row8 = new JPanel();
		Row8.setBackground(Color.BLUE);

		dp2.add(pdp2 ,new Circle(10, 10, 20, Color.blue));	//Adds DraftsPieces to the dp and dp2 arrays/
        	dp.add(pdp ,new Circle(10, 10, 20, Color.red));		//These pieces will later be drawn in the paint method.


	//Add 8 ColouredPanels each to 8 rows to make Board.

	//Row1
			for (int i = 0; i < 4; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 1, 1, 1, cj, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 1, 1, 1, cj+1, i, this));
				Row1.add((ColouredPanel)cp2.get(i));
				Row1.add((ColouredPanel)cp.get(i));
				cj = cj + 2;
			}
		mypane.add(Row1);
		cj = 1;


	//Row2
			for (int i = 4; i < 8; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 1, 1, 2, cj+1, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 1, 1, 2, cj, i, this));
				Row2.add((ColouredPanel)cp.get(i));
				Row2.add((ColouredPanel)cp2.get(i));
				cj = cj + 2;
			}
		mypane.add(Row2);
		cj = 1;


	//Row3
			for (int i = 8; i < 12; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 1, 1, 3, cj, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 1, 1, 3, cj+1, i, this));
				Row3.add((ColouredPanel)cp2.get(i));
				Row3.add((ColouredPanel)cp.get(i));
				cj = cj + 2;;
		}
		mypane.add(Row3);
		cj = 1;

	//Row4
			for (int i = 12; i < 16; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 0, 0, 4, cj+1, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 0, 0, 4, cj, i, this));
				Row4.add((ColouredPanel)cp.get(i));
				Row4.add((ColouredPanel)cp2.get(i));
				cj = cj + 2;;
			}
		mypane.add(Row4);
		cj = 1;

	//Row5
			for (int i = 16; i < 20; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 0, 0, 5, cj, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 0, 0, 5, cj+1, i, this));
				Row5.add((ColouredPanel)cp2.get(i));
				Row5.add((ColouredPanel)cp.get(i));
				cj = cj + 2;;
			}
		mypane.add(Row5);
		cj = 1;

	//Row6
			for (int i = 20; i < 24; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 1, 0, 6, cj+1, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 1, 0, 6, cj, i, this));
				Row6.add((ColouredPanel)cp.get(i));
				Row6.add((ColouredPanel)cp2.get(i));
				cj = cj + 2;;
			}
		mypane.add(Row6);
		cj = 1;

	//Row7
			for (int i = 24; i < 28; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 1, 0, 7, cj, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 1, 0, 7, cj+1, i, this));
				Row7.add((ColouredPanel)cp2.get(i));
				Row7.add((ColouredPanel)cp.get(i));
				cj = cj + 2;;
		}
		mypane.add(Row7);
		cj = 1;

	//Row8
			for (int i = 28; i < 32; i++)
            		{
				cp2.add(i ,new ColouredPanel(Color.white, 60, 60, 1, 0, 8, cj+1, i, this));
        			cp.add(i ,new ColouredPanel(Color.black, 60, 60, 1, 0, 8, cj, i, this));
				Row8.add((ColouredPanel)cp.get(i));
				Row8.add((ColouredPanel)cp2.get(i));
				cj = cj + 2;;
		}
		mypane.add(Row8);
		cj = 1;

	pane.add(mypane, BorderLayout.CENTER);

	playerpanel = new JPanel(new BorderLayout()); 	//Displays who turn it is.
	playerpanel.setBackground(Color.RED);
	player = new JLabel("RED Move");		//Red Players move first.
	playerpanel.add(player, BorderLayout.CENTER);
	pane.add(playerpanel, BorderLayout.NORTH);

	JPanel valuespanel = new JPanel(new BorderLayout());

	valuespanel.setBackground(Color.black);		//Displays the score of the current game.
	rvalues = new JLabel("Reds: " +  rs);
	rvalues.setForeground(Color.WHITE);
	bvalues = new JLabel("Blues: " +  bs);
	bvalues.setForeground(Color.WHITE);
	valuespanel.add(rvalues, BorderLayout.CENTER);
	valuespanel.add(bvalues, BorderLayout.WEST);
	pane.add(valuespanel, BorderLayout.WEST);

        return pane;
    	}

	public int getredscore()		//Method will be called by DraftsGame class to update scores.
	{
	return redwin;
	}

	public int getbluescore()		//Method will be called by DraftsGame class to update scores.
	{
	return bluewin;
	}

	public void checkscore()		//Called everytime a DraftPiece is moved. to see if the game is won or not.
	{
	if(rs == 12)				//If Red player has all blue pieces then Red Player wins.
	{
		redwin = 1;
		JOptionPane.showMessageDialog(this,"Red Player has won this game");
			theBoard = new Board("SimpleDraft");			//Creates new Board.
			dispose();						//Removes old Board.
	}
	else
	if(bs == 12)				//If Blue player has all red pieces then Blue Player wins.
	{
		bluewin = 1;
		JOptionPane.showMessageDialog(this,"Blue Player has won this game");
			theBoard = new Board("SimpleDraft");			//Creates new Board.
			dispose();						//Removes old Board.
	}
	}

    	public void showPane() {

		setVisible(true);
	}

	public void hidePane() {

		setVisible(false);
	}

	private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();

        JMenu menu = new JMenu("Menu");
        menubar.add(menu);
        Game = new JMenuItem("Reset");			//Return Board to original state.

        Game.addActionListener(this);
        menu.add(Game);
        menu.addSeparator();
        quit = new JMenuItem("Quit Game");
	quit.addActionListener(this);			//Closes Board window.
        menu.add(quit);

        return menubar;
    }

public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof JMenuItem)
	{						//Return Board to original state.
        	if (e.getSource() == Game) {
			if(playerswitch == 0)		//Indicates who forfeits the game.
			{
			bluewin = 1;
			}
			else
			if(playerswitch == 1)
			{
			redwin = 1;
			}
			theBoard = new Board("SimpleDraft");
			JOptionPane.showMessageDialog(this,"Return Board to original state.");
			dispose();

	}
   	else
	if (e.getSource() == quit)
	{						//Closes Board window.
		if(playerswitch == 0)			//Indicates who forfeits the game.
			{
			bluewin = 1;
			}
			else
			if(playerswitch == 1)
			{
			redwin = 1;
			}
		dispose();
        }

}

}//End actionPerformed


//----------------------------------------------------------------------------------------------------------------------
	public class ColouredPanel extends JPanel implements MouseListener, MouseMotionListener
	{
						//Each Sqaure on the Board is made of a single Coloured Panel.
	public int sp;				//sp refers to the Start point of each Piece.
	public int t;				//t is an extra variable to determine what colour the DraftsPiece is.
	public int r;				//r will return the row of the current panel.
	public int c;				//c will return the column of the current panel.
	public int ind;				//ind will return the index of the current panel.
	public JFrame mainframe;
	public ColouredPanel(Color myColor, int height, int width, int sp, int t, int r, int c, int ind, JFrame mainframe)
	{
		super();
		setBackground(myColor);
		setPreferredSize(new Dimension(height, width));
		setVisible(true);
		this.sp = sp;
		this.t = t;
		this.r = r;
		this.c = c;
		this.ind = ind;
		this.mainframe = mainframe;
		addMouseListener(this);
		addMouseMotionListener(this);
	}


	public void paint(Graphics g) {			//DraftsPiece are drawn on to ColouredPanel.
			super.paint(g);

            for (int i = 0; i < dp.size(); i++)
            {
                dpt = (DraftsPiece)dp.get(i);
                dp2t = (DraftsPiece)dp2.get(i);
                if (dp != null)
					if(getBackground() == Color.black)	//if sp is 1 and t is 1 then draw a red piece.
						if(sp == 1)
							if(t == 1)
            		{
                	dpt.draw(g);
                	}
			else if (dp2 != null)
			    if(getBackground() == Color.black)
				if(sp == 1)
					if(t == 0)				//if sp is 1 and t is 0 then draw a blue piece.
					{
                			dp2t.draw(g);
					}
	}
}
//---------------------------------------------------------------------------
//MousePressed is used to remove pieces from the Board and MouseClicked is used to place Pieces on the Board.
//There are 8 different method on placing a piece depending on wheather the row is an odd or even number:
//	Place Red on Odd Row
//	Place Red on even row
//	Place Blue on Odd row
//	Place Blue on Odd row
// 	Place Red 0ver Blue on Odd row
//	Place Red 0ver Blue on even row
//	Place Blue Over Red on odd row
//	Place Blue Over Red on even row

	public void mouseExited (MouseEvent m) {

	}

	public void mouseReleased (MouseEvent m) {

		}

	public void mouseEntered (MouseEvent m) {

		}

	public void mousePressed (MouseEvent m) {


		}

	public void mouseDragged (MouseEvent m) {

		}
	public void mouseMoved (MouseEvent m) {

	}

	public void mouseClicked (MouseEvent m) {

	if (m.getSource() instanceof ColouredPanel)
	{
		if(getBackground() == Color.black)
		{
		System.out.println("\n\n\n\n\n|-------------------------------------------------");
		System.out.println("Row no:" + r);
		System.out.println("Col no:" + c);
		System.out.println("last Row no:" + row);
		System.out.println("last Col no:" + col + "\n");
		System.out.println("playerswitch = " + playerswitch);
		System.out.println("this.sp = " + this.sp);
		System.out.println("this.t = " + this.t);
		System.out.println("pt = " + pt);
		System.out.println("psp = " + psp);
		System.out.println("-------------------------------------------------");









//Remove Red Piece from ColouredPanel

						if(playerswitch == 0	&&
					  	 this.sp == 1	&&
							this.t == 1	&&
							pt == 0		&&
							psp == 0)
							{
								System.out.println("checkred\n");
								for (int i = 0; i < cp.size(); i++)
								{
									cpt = (ColouredPanel)cp.get(i);

									if(cpt.r == this.r + 1)
									{
										if(cpt.c == this.c - 1 || cpt.c == this.c + 1)
										{
											System.out.println("redcheck == cpt.r:" + cpt.r + "check == cpt.c:" + cpt.c + "\n");
											System.out.println();
											if(cpt.sp == 0 && cpt.t == 0)
											{
												this.sp = 0;
												this.t = 0;
												row = this.r;
												col = this.c;
												index = this.ind;
												pt = 1;
												psp = 1;


												System.out.println("Remove Red Piece from ColouredPanel\n");
												System.out.println("psp:" + psp+ "\n");
												System.out.println("pt:" + pt+ "\n");
												System.out.println("cpt.sp:" + cpt.sp + "\n");
												System.out.println("cpt.t:" + cpt.t + "\n");
												System.out.println("cpt.r:" + cpt.r + "\n");
												System.out.println("cpt.c:" + cpt.c);
												System.out.println("---------------\n");
												System.out.println("1100\n");
												//System.out.println("this.ind: " + this.ind + " \n");
												break;
											}//if(cpt.sp == 0 && cpt.t == 0)




				//if there is a blue draught to the left or right

											else if(cpt.sp == 1 && cpt.t == 0)
											{
												int findc = 0;
												if(cpt.c - this.c == 1)
												{
													findc = cpt.c + 1;
												}

												else if(cpt.c - this.c == -1)
												{
													findc = cpt.c - 1;
												}

												for (int j = 0; j < cp.size(); j++)
												{
													cp2t = (ColouredPanel)cp.get(j);

													if(cp2t.r == cpt.r + 1)
													{
														if(cp2t.c == findc)
														{
															System.out.println("redcheck2 == cpt.r:" + cpt.r + "check2 == cpt.c:" + cpt.c + "\n");
															System.out.println();
																if(cp2t.sp == 0 && cp2t.t == 0)
																{
																	this.sp = 0;
																	this.t = 0;
																	row = this.r;
																	col = this.c;
																	index = this.ind;
																	pt = 1;
																	psp = 1;
																	readytojump = true;

																	break;
																}//if(cpt2.sp == 0 && cpt2.t == 0)
														}//if(cpt2.c == findc)
													}//if(cpt2.r == this.r + 1)
												}//end of for


											}


										}//if(cpt.c == this.c - 1 || cpt.c == this.c + 1)
									}//if(cpt.r == this.r + 1)
								}//end of for


			}//else if(playerswitch == 0)











//Remove Blue Piece from ColouredPanel

			else if(playerswitch == 1	&&
					this.sp == 1		&&
					this.t == 0			&&
					pt == 0				&&
					psp == 0)
						{
							System.out.println("pingblue\n");
							for (int i = 0; i < cp.size(); i++)
							{
								cpt = (ColouredPanel)cp.get(i);
								//System.out.println("checkblue\n");
									if(cpt.r == this.r - 1)
									{
										if(cpt.c == this.c - 1 || cpt.c == this.c + 1)
										{
											System.out.println("bluecheck == cpt.r:" + cpt.r + "\n");
											System.out.println("bluecheck == cpt.c:" + cpt.c + "\n");
											if(cpt.sp == 0 && cpt.t == 0)
											{
																this.sp = 0;
																this.t = 0;
																row = this.r;
																col = this.c;
																index = this.ind;
																pt = 0;
																psp = 1;

																System.out.println("Remove Blue Piece from ColouredPanel\n");
																System.out.println("psp:" + psp+ "\n");
																System.out.println("pt:" + pt+ "\n");
																System.out.println("cpt.sp:" + cpt.sp + "\n");
																System.out.println("cpt.t:" + cpt.t + "\n");
																System.out.println("cpt.r:" + cpt.r + "\n");
																System.out.println("cpt.c:" + cpt.c);
																System.out.println("last Row no:" + row);
																System.out.println("last Col no:" + col + "\n");
																System.out.println("---------------\n");
																//System.out.println("workk\n");
																break;
											}//if(cpt.sp == 0 && cpt.t == 0)

										//if there is a red draught to the left or right

											else if(cpt.sp == 1 && cpt.t == 1)
											{
												int findc = 0;

												if(cpt.c - this.c == 1)
												{
													findc = cpt.c + 1;
												}

												else if(cpt.c - this.c == -1)
												{
													findc = cpt.c - 1;
												}

												for (int j = 0; j < cp.size(); j++)
												{
													cp2t = (ColouredPanel)cp.get(j);

													if(cp2t.r == cpt.r - 1)
													{
														if(cp2t.c == findc)
														{
														System.out.println("bluecheck2 == cpt.r:" + cpt.r + "check2 == cpt.c:" + cpt.c + "\n");
														System.out.println();
															if(cp2t.sp == 0 && cp2t.t == 0)
															{
																	this.sp = 0;
																	this.t = 0;
																	row = this.r;
																	col = this.c;
																	index = this.ind;
																	pt = 0;
																	psp = 1;
																	readytojump = true;

																	break;
															}//if(cpt2.sp == 0 && cpt2.t == 0)
														}//if(cpt2.c == findc)
													}//if(cpt2.r == this.r + 1)
												}//end of for


											}



														}//if(cpt.c == this.c - 1 || cpt.c == this.c + 1)
													}//if(cpt.r == this.r - 1)
												}//for (int i = 0; i < cp.size(); i++)

							}//if(playerswitch == 1)










//Place Red on empty square

			else if(playerswitch == 0	&&
					this.r == row + 1	&&
					(this.c == col - 1 ||this.c == col + 1) 	&&
					this.sp == 0		&&
					this.t == 0			&&
					pt == 1				&&
					psp == 1)
						{
									this.t = 1;
									this.sp = 1;
									pt = 0;
									psp = 0;
									row = 9;
									col = 9;

									playerswitch = 1;
									player.setText("BLUE move");
									playerpanel.setBackground(Color.BLUE);

									System.out.println("Place Red on empty square\n");
									System.out.println("\n\n\n\n\n-------------------------------------------------");
									System.out.println("Row no:" + r);
									System.out.println("Col no:" + c);
									//System.out.println("last Row no:" + row);
									//System.out.println("last Col no:" + col + "\n");
									System.out.println("playerswitch = " + playerswitch);
									System.out.println("this.sp = " + this.sp);
									System.out.println("this.t = " + this.t);
									System.out.println("pt = " + pt);
									System.out.println("psp = " + psp);
									System.out.println("-------------------------------------------------|");


			}//if(this.r == row + 1)











//Place Blue on empty square

			else if(playerswitch == 1	&&
					this.r == row - 1	&&
					(this.c == col - 1 ||this.c == col+ 1)	&&
					this.sp == 0	&&
					this.t == 0		&&
					pt == 0			&&
					psp == 1)
					{
						this.t = 0;
						this.sp = 1;
						pt = 0;
						psp = 0;

						playerswitch = 0;
						player.setText("RED move");
						playerpanel.setBackground(Color.RED);
						System.out.println("Place Blue on empty square\n");
						System.out.println("\n\n\n\n\n-------------------------------------------------");
						System.out.println("Row no:" + r);
						System.out.println("Col no:" + c);
						//System.out.println("last Row no:" + row);
						//System.out.println("last Col no:" + col + "\n");
						System.out.println("playerswitch = " + playerswitch);
						System.out.println("this.sp = " + this.sp);
						System.out.println("this.t = " + this.t);
						System.out.println("pt = " + pt);
						System.out.println("psp = " + psp);
						System.out.println("-------------------------------------------------|");
					}






//Place Red back

			else if(playerswitch == 0	&&
					this.r == row	&&
					this.c == col	&&
					this.sp == 0	&&
					this.t == 0		&&
					pt == 1			&&
					psp == 1)
						{
						this.t = 1;
						this.sp = 1;
						pt = 0;
						psp = 0;
						row = 9;
						col = 9;

						System.out.println("Place Red back\n");
						}







//Place blue back

			else if(playerswitch == 1	&&
					this.r == row	&&
					this.c == col	&&
					this.sp == 0	&&
					this.t == 0		&&
					pt == 0			&&
					psp == 1)
					{
					this.t = 0;
					this.sp = 1;
					pt = 0;
					psp = 0;
					row = 9;
					col = 9;

					System.out.println("Place blue back\n");
					}

//Place Red 0ver Blue

			else if(playerswitch == 0	&&
					this.r == row + 2	&&
					(this.c == col - 2 || this.c == col + 2)	&&
					this.sp == 0	&&
					this.t == 0		&&
					pt == 1			&&
					psp == 1		&&
					readytojump == true)
					{

						int rowtoclear = this.r - 1;		//Find draught piece to clear
						int coltoclear = 0;

						if(this.c - col == 2)
						{
							coltoclear = this.c - 1;
						}

						else if(this.c - col == -2)
						{
							coltoclear = this.c + 1;
						}


						System.out.println("rowtoclear = " + rowtoclear + "\n");
						System.out.println("coltoclear = " + coltoclear + "\n");

										for (int i = 0; i < cp.size(); i++)
										{
											cpt = (ColouredPanel)cp.get(i);
											if(cpt.r == rowtoclear)
											{
												if(cpt.c == coltoclear)
												{
													System.out.println("rowinarray = " + cpt.r + "\n");
													System.out.println("colinarray= " + cpt.c + "\n");
													System.out.println("cpt.sp = " + cpt.sp + "\n");
													System.out.println("cpt.t = " + cpt.t + "\n");

													cpt.sp = 0;
													cpt.t = 0;
													System.out.println("|||cpt.sp = " + cpt.sp + "\n");
													System.out.println("|||cpt.t = " + cpt.t + "\n");

													row = 0;
													col = 0;
													index = cpt.ind;
													pt = 0;
													psp = 0;

													System.out.println("repaint()");
													break;
												}
											}
										}

										System.out.println("thisworked\n");
										this.t = 1;
										this.sp = 1;
										pt = 0;
										psp = 0;
										rs++;
										rvalues.setText("Reds: " +  rs);
										checkscore();
										playerswitch = 1;
										player.setText("BLUE move");
										playerpanel.setBackground(Color.BLUE);
										System.out.println("Place Red 0ver Blue\n");





										int rowtocopy = this.r;
										int coltocopy = this.c;

										int rowtojump = 0;
										int coltojump = 0;

										int rowtojumpto = 0;
										int coltojumpto = 0;

										boolean endofmcap = true;





						//Find if jump is multiple

						while(endofmcap == true)
						{

						for(int k = 0; k < 2; k++)
						{
							rowtojump = rowtocopy + 1;

							if(k == 0)
							{
								coltojump = coltocopy - 1;
							}

							else if(k == 1)
							{
								coltojump = coltocopy + 1;
							}


							System.out.println("rowtojump = " + rowtojump + "\n");
							System.out.println("coltojump = " + coltojump + "\n");








							for (int i = 0; i < cp.size(); i++)
							{
							  cpt = (ColouredPanel)cp.get(i);
								if(cpt.r == rowtojump)
							    {
									if(cpt.c == coltojump)
			                		{





										if(cpt.sp == 1 && cpt.t == 0)	//IF Blue is found
										{
														//Find panel to land.

												rowtojumpto = rowtojump + 1;

												if(k == 0)
												{
													coltojumpto = coltojump - 1;
												}

												else if(k == 1)
												{
													coltojumpto = coltojump + 1;
												}

												System.out.println("k =" + k + "\n");
												System.out.println("rowtojumpto = " + rowtojumpto + "\n");
												System.out.println("coltojumpto = " + coltojumpto + "\n");







												for (int j = 0; j < cp.size(); j++)
												{
													cp2t = (ColouredPanel)cp.get(j);

														if(cp2t.r == rowtojumpto)
														{
															 if(cp2t.c == coltojumpto)
															 {
																if(cp2t.sp == 0 && cp2t.t == 0)
																{

																	possiblejumps[k][0] = rowtojumpto;
																	possiblejumps[k][1] = coltojumpto;

																	cp2t.setBackground(Color.GREEN);

																	playerswitch = 0;
																	row = this.r;
																	col = this.c;

																	this.t = 0;
																	this.sp = 0;
																	pt = 1;
																	psp = 1;

																	/*
																	System.out.println("Blue draught can be jumped");
																	cpt.sp = 0 ;
																	cpt.t = 0;
																	System.out.println("This happened");
																	cp2t.sp = 1;
																	cp2t.t = 1;
																	rs++;
																	rvalues.setText("Reds: " +  rs);
																	checkscore();

																	index = cpt.ind;
																	pt = 0;
																	psp = 0;


																	for (int l = 0; l < cp.size(); l++)
																	{
																		cp3t = (ColouredPanel)cp.get(l);
																		if(cp3t.r == rowtocopy)
																		{

																			if(cp3t.c == coltocopy)
																			{
																				cp3t.sp = 0 ;
																				cp3t.t = 0;
																			}
																		}
																	}

																	rowtojump = 0;
																	coltojump = 0;
																	rowtocopy = rowtojumpto;
																	coltocopy = coltojumpto;
																	break;
																	*/
																}
																else
																{System.out.println("Blue draught cannot be jumped");}
															}
														}
												}//end for j





										}//IF Blue is found




									}
								}
							}//end for i




						if(rowtojump == 0 && coltojump == 0)
						{
							System.out.println("rowtojumpto == 0 && coltojumpto == 0");
							break;
						}



					}//end for k
					if(rowtojump == rowtocopy + 1)
					{
						System.out.println("endofmcap = false");
						endofmcap = false;
					}

				}//end of while
				//readytojump = false;
				}					//End Place Red 0ver Blue




//Place Blue 0ver Red

			else if(playerswitch == 1	&&
					this.r == row - 2	&&
					(this.c == col - 2 || this.c == col + 2)	&&
					this.sp == 0	&&
					this.t == 0		&&
					pt == 0			&&
					psp == 1		&&
					readytojump == true)
					{



					int rowtoclear = this.r + 1;
					int coltoclear = 0;

					if(this.c - col == 2)
					{
						coltoclear = this.c - 1;
					}
					else if(this.c - col == -2)
					{
						coltoclear = this.c + 1;
					}



					System.out.println("rowtoclear = " + rowtoclear + "\n");
					System.out.println("coltoclear = " + coltoclear + "\n");

					for (int i = 0; i < cp.size(); i++)
					{
			               cpt = (ColouredPanel)cp.get(i);
			                if(cpt.r == rowtoclear)
			                {
			                	if(cpt.c == coltoclear)
			                	{
			                		System.out.println("rowinarray = " + cpt.r + "\n");
									System.out.println("colinarray= " + cpt.c + "\n");
									System.out.println("cpt.sp = " + cpt.sp + "\n");
									System.out.println("cpt.t = " + cpt.t + "\n");

									cpt.sp = 0;
									cpt.t = 0;
									System.out.println("|||cpt.sp = " + cpt.sp + "\n");
									System.out.println("|||cpt.t = " + cpt.t + "\n");

									row = 0;
									col = 0;
									index = cpt.ind;
									pt = 0;
									psp = 0;

									System.out.println("repaint()");
									break;
								}
							}
					}

						System.out.println("thisworked\n");
						this.t = 0;
						this.sp = 1;
						pt = 0;
						psp = 0;
						bs++;
						bvalues.setText("Blues: " +  bs);
						checkscore();
						playerswitch = 0;
						player.setText("RED move");
						playerpanel.setBackground(Color.RED);
						System.out.println("Place Blue 0ver Red\n");










					}//End Place Blue 0ver Red




					mainframe.repaint();




			}//end of background if

				if(playerswitch == 0	&&							//For multiple red jumps
					this.getBackground() == Color.GREEN)
					{
						System.out.println("this.r = " + this.r + "\n");
						System.out.println("this.c = " + this.c + "\n");
						System.out.println("row = " + row + "\n");
						System.out.println("col = " + col + "\n");
						System.out.println("this.sp = " + this.sp + "\n");
						System.out.println("this.t = " + this.t + "\n");
						System.out.println("pt = " + pt + "\n");
						System.out.println("psp = " + psp + "\n");
						System.out.println("readytojump = " + readytojump + "\n");
					}


				if(playerswitch == 0	&&							//For multiple red jumps
					this.getBackground() == Color.GREEN &&

					this.r == row + 2	&&
					(this.c == col - 2 || this.c == col + 2)	&&
					this.sp == 0	&&
					this.t == 0		&&
					pt == 1			&&
					psp == 1		&&
					readytojump == true)
					{
						this.setBackground(Color.BLACK);
						this.sp = 1;
						this.t = 1;
						pt = 0;
						psp = 0;
						readytojump = false;
						playerswitch = 1;

						for (int l = 0; l < cp.size(); l++)
						{
							cp3t = (ColouredPanel)cp.get(l);
							if(cp3t.getBackground() == Color.GREEN)
							{
								cp3t.setBackground(Color.BLACK);
							}
						}
					}



		}//end getsource if


	}//End of MouseClicked



}//end of coloured Panel
//----------------------------------------------------------------------------------------------------------------------



}