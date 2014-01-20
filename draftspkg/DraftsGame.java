/***********/
/** Sean Leonard					 */
/** C05633915						 */
/**------------------------------------------------------*/
/** The DraftsGame class will take in players names and assign a red player and a blue player.	 */
/** If no name has been entered the game will start without them. the DraftsGame will display the */
/** number of games won by each player. There is also an update score button that will reset the */
/** these value as games are being won. */


package draftspkg;

import java.awt.Container;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.WindowConstants;

public class DraftsGame extends JFrame implements ActionListener {
		JButton startButton;			//Calls new Board
		JButton updatebutton;			//Updates Scores
		JPanel redpanel;
		JPanel bluepanel;
		JPanel scorepanel;
		JPanel namespanel;
		JPanel startgamepanel;
		JMenuItem close, newsession;
		public int redwin = 0;
		public int bluewin = 0;
		String redn = "Reds";
		String bluen = "Blues";
		JTextField rednameField;
		JTextField bluenameField;
		JLabel redscore;
		JLabel bluescore;
		Board theBoard;				//Starts new game.
		DraftsGame theDraftsGame;

	public DraftsGame (String myTitle) {
		super (myTitle);
		setSize(350,200);
		setLocation(2,100);
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);
		setContentPane(createPane());
		setJMenuBar(createMenu());
		setVisible(true);
	}
	private Container createPane() {
		Container pane = new JPanel();
 		pane.setLayout(new BorderLayout());
		pane.setBackground(Color.YELLOW);

		namespanel = new JPanel(new BorderLayout());
		
		redpanel = new JPanel();
		redpanel.setBackground(Color.RED);
		JLabel redname = new JLabel ("RED Player : Type your name:");
		rednameField = new JTextField (10);
		rednameField.setToolTipText("Type your name");
		redpanel.add(redname);
		redpanel.add(rednameField);
		namespanel.add(redpanel, BorderLayout.NORTH);

		bluepanel = new JPanel();
		bluepanel.setBackground(Color.BLUE);
		JLabel bluename = new JLabel ("BLUE Player : Type your name:");
		bluenameField = new JTextField (10);
		bluenameField.setToolTipText("Type your name");

		bluepanel.add(bluename);
		bluepanel.add(bluenameField);
		namespanel.add(bluepanel, BorderLayout.SOUTH);

		pane.add(namespanel, BorderLayout.NORTH);

		startgamepanel = new JPanel();
		startgamepanel.setBackground(Color.YELLOW);

		startButton = new JButton("Start Game");
		startButton.setSize(120, 80);
		startButton.addActionListener(this);

		updatebutton = new JButton("Update Scores");
		updatebutton.setSize(100, 100);
		updatebutton.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(startButton);
		buttonPanel.add(updatebutton);
		buttonPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		startgamepanel.add(buttonPanel, BorderLayout.CENTER);
		pane.add(startgamepanel);
		
		Font newFont = new Font ("Verdana",Font.BOLD,14);

		scorepanel = new JPanel(new BorderLayout());
		scorepanel.setBackground(Color.GREEN);

		redscore = new JLabel(redn + ": " + redwin);
		redscore.setFont(newFont);
		scorepanel.add(redscore, BorderLayout.WEST);

		bluescore = new JLabel(bluen + ":" + bluewin);
		bluescore.setFont(newFont);
		scorepanel.add(bluescore, BorderLayout.EAST);
		pane.add(scorepanel, BorderLayout.SOUTH);

		return pane;
	}

	private JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu options = new JMenu("Options");
		newsession = new JMenuItem ("new Session");
		newsession.addActionListener(this);
		close = new JMenuItem ("Close");
		close.addActionListener(this);
		options.add(newsession);
		options.add(close);
		options.addSeparator();
		options.add(newsession);
		options.setMnemonic('O');
		newsession.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.ALT_MASK));
		menuBar.add(options);
		return menuBar;
	}

	public void updatescore()
	{
	redwin = redwin + theBoard.getredscore();
	bluewin = bluewin + theBoard.getbluescore();
	redscore.setText(redn + ": " + redwin);
	bluescore.setText(bluen + ": " + bluewin);
	}
		
	public void closeGui() 
	{
		JOptionPane.showMessageDialog(this,"You have chosen to Exit. press OK to continue exiting");
		System.exit(0);
	}
	public void actionPerformed(ActionEvent e) 
	{
	        if (e.getSource() instanceof JButton)
		{
            		if (e.getSource() == startButton)
			{
				theBoard = new Board("SimpleDraft");
				redn = rednameField.getText();
				bluen= bluenameField.getText();
				redscore.setText(redn + ": " + redwin);
				bluescore.setText(bluen + ": " + bluewin);
				validate();
			}
			if (e.getSource() == updatebutton)
			{
				updatescore();
			}
		}
		else
		if (e.getSource() instanceof JMenuItem)	
		{
			if (e.getSource() == close)
			{
				closeGui();
			}
			if (e.getSource() == newsession)
			{
				theDraftsGame = new DraftsGame("SimpleDrafts");
				dispose();
			}	
		}

	}//End actionPerformed
}
