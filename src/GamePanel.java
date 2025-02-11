import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel
		       implements Runnable {
   
	private static int NUM_ALIENS = 3;

	// private SoundManager soundManager;
	private Bat bat;
	private Alien[] aliens;
	private boolean alienDropped;
	private boolean isRunning;
	private boolean isPaused;

	private Thread gameThread;

	public GamePanel () {
		bat = null;
		aliens = null;
		alienDropped = false;
		gameThread = null;
		isRunning = false;
		// soundManager = SoundManager.getInstance();
		// backgroundImage = ImageManager.loadImage ("Background.jpg");

	}


	public void createGameEntities() {
		bat = new Bat (this, 50, 350);
		aliens = new Alien [3];
		aliens[0] = new Alien (this, 275, 10, bat);
		aliens[1] = new Alien (this, 150, 10, bat);
		aliens[2] = new Alien (this, 330, 10, bat); 
	}


	public void run () {
		try {
			isRunning = true;
			while (isRunning) {
				gameUpdate();
				gameRender();
				Thread.sleep (50);	
			}
		}
		catch(InterruptedException e) {}
	}


	public void gameUpdate() {

		for (int i=0; i<NUM_ALIENS; i++) {
			aliens[i].move();
		}

	}


	public void updateBat (int direction) {

		if (bat != null && !isPaused) {
			bat.move(direction);
		}

	}


	public void gameRender() {

		Graphics g = getGraphics ();
      		Graphics2D g2 = (Graphics2D) g;

      		// g2.drawImage(backgroundImage, 0, 0, null);

		if (bat != null) {
			bat.draw();
		}

		if (aliens != null) {
			for (int i=0; i<NUM_ALIENS; i++)
				aliens[i].draw();
       		}

	}


	public void startGame() {				// initialise and start the game thread 

		if (isRunning)
			return;

		isPaused = false;

		// soundManager.playClip ("background", true);
		createGameEntities();
		gameThread = new Thread (this);			
		gameThread.start();

	}


	public void pauseGame() {				// pause the game (don't update game entities)
		if (isRunning) {
			if (isPaused)
				isPaused = false;
			else
				isPaused = true;
		}
	}


	public void endGame() {					// end the game thread


	}



	public boolean isOnBat (int x, int y) {
		return bat.isOnBat(x, y);
	}


	public boolean isOnAlien (int x, int y) {
		
		for (int i=0; i<NUM_ALIENS; i++) {
			if (aliens[i].isOnAlien(x, y))
				return true;
		}

		return false;
	}

}