import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel {
   
   Snake snek;
   Fruit fruit;

   public GamePanel () {
	snek = null;
 	fruit = null;
   }


   public void createGameEntities() {
       snek = new Snake (this, 50, 350); 
       fruit = new Fruit (this, 200, 10, snek); 
   }


   public void drawGameEntities() {

       if (snek != null) {
       	  snek.draw();
       }
   }


   public void updateGameEntities(int direction) {

	if (snek == null)
 	   return;

	snek.erase();
       	snek.move(direction);

   }


   public void spawnFruit() {
	   if (fruit != null) {
	   	fruit.start();
	   }
   }


   public boolean isOnSnake (int x, int y) {
	if (snek != null)
      	   return snek.isOnSnake(x, y);
  	else
	   return false;
   }

   public void handleGameOver() {
      JOptionPane.showMessageDialog(this, "Game Over!\nPress R to restart", "Game Over", JOptionPane.INFORMATION_MESSAGE);
      System.exit(0);
   }

}