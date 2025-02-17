import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for Layout Managers
import java.awt.event.*;		// need this to respond to GUI events
	
public class GameWindow extends JFrame 
				implements ActionListener,
					   KeyListener,
					   MouseListener
{
	// declare instance variables for user interface objects

	// declare labels 

	private JLabel statusBarL;
	private JLabel keyL;
	private JLabel mouseL;

	// declare text fields

	private JTextField statusBarTF;
	private JTextField keyTF;
	private JTextField mouseTF;

	// declare buttons

	private JButton startB;
	private JButton pauseB;
	private JButton focusB;
	private JButton exitB;

	private Container c;

	private JPanel mainPanel;
	private GamePanel gamePanel;

	@SuppressWarnings({"unchecked"})
	public GameWindow() {
 
		setTitle ("Snakey");
		setSize (500, 550);

		// create user interface objects

		// create labels

		statusBarL = new JLabel ("Application Status: ");
		keyL = new JLabel("Key Pressed: ");
		mouseL = new JLabel("Current Score: ");

		// create text fields and set their colour, etc.

		statusBarTF = new JTextField (25);
		keyTF = new JTextField (25);
		mouseTF = new JTextField (25);

		statusBarTF.setEditable(false);
		keyTF.setEditable(false);
		mouseTF.setEditable(false);

		statusBarTF.setBackground(Color.CYAN);
		keyTF.setBackground(Color.YELLOW);
		mouseTF.setBackground(Color.GREEN);

		// create buttons

		startB = new JButton ("Start Game");
		exitB = new JButton ("Exit");

		// add listener to each button (same as the current object)

		startB.addActionListener(this);
		exitB.addActionListener(this);

		
		// create mainPanel

		mainPanel = new JPanel();
		FlowLayout flowLayout = new FlowLayout();
		mainPanel.setLayout(flowLayout);

		GridLayout gridLayout;
		
		// create the gamePanel for game entities

		gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(400, 400));
		gamePanel.createGameEntities();


		// create infoPanel

		JPanel infoPanel = new JPanel();
		gridLayout = new GridLayout(3, 2);
		infoPanel.setLayout(gridLayout);
		infoPanel.setBackground(Color.ORANGE);

		// add user interface objects to infoPanel
	
		infoPanel.add (statusBarL);
		infoPanel.add (statusBarTF);

		infoPanel.add (keyL);
		infoPanel.add (keyTF);		

		infoPanel.add (mouseL);
		infoPanel.add (mouseTF);

		
		// create buttonPanel

		JPanel buttonPanel = new JPanel();
		gridLayout = new GridLayout(1, 2);
		buttonPanel.setLayout(gridLayout);

		// add buttons to buttonPanel

		buttonPanel.add (startB);
		buttonPanel.add (exitB);

		// add sub-panels with GUI objects to mainPanel and set its colour

		mainPanel.add(infoPanel);
		mainPanel.add(gamePanel);
		mainPanel.add(buttonPanel);
		mainPanel.setBackground(Color.PINK);

		// set up mainPanel to respond to keyboard and mouse

		gamePanel.addMouseListener(this);
		mainPanel.addKeyListener(this);

		// add mainPanel to window surface

		c = getContentPane();
		c.add(mainPanel);

		// set properties of window

		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		// set status bar message

		statusBarTF.setText("Application started.");
	}


	// implement single method in ActionListener interface

	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		
		statusBarTF.setText(command + " button clicked.");

		if (command.equals(startB.getText())){
			gamePanel.startGame();
			updateScore();
		}

		if (command.equals(exitB.getText()))
			System.exit(0);

		mainPanel.requestFocus();
	}


	// implement methods in KeyListener interface

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String keyText = e.getKeyText(keyCode);
		keyTF.setText(keyText + " pressed.");

		updateScore();

		if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
			gamePanel.updateGameEntities(3);
			// gamePanel.drawGameEntities();
			gamePanel.setDirection(3);
		}
		if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
			gamePanel.updateGameEntities(1);
			// gamePanel.drawGameEntities();
			gamePanel.setDirection(1);
		}
		if(keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
			gamePanel.updateGameEntities(2);
			// gamePanel.drawGameEntities();
			gamePanel.setDirection(2);
		}
		if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
			gamePanel.updateGameEntities(4);
			// gamePanel.drawGameEntities();
			gamePanel.setDirection(4);
		}

	}

	public void updateScore() {
		mouseTF.setText("Current Score: " + gamePanel.getScore());
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	// implement methods in MouseListener interface

	public void mouseClicked(MouseEvent e) {

	}


	public void mouseEntered(MouseEvent e) {
	
	}

	public void mouseExited(MouseEvent e) {
	
	}

	public void mousePressed(MouseEvent e) {
	
	}

	public void mouseReleased(MouseEvent e) {
	
	}

}