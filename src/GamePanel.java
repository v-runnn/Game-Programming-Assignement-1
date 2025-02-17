import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

/**
   A component that displays all the game entities
*/

public class GamePanel extends JPanel {
   
   Fruit fruit;
   Snake snake;
   int direction = 1;
   int score;

   public GamePanel () {
      fruit = null;
      snake = null;
      score = 0;
   }

   public void increaseScore() {
      this.score++;
   }

   public int getScore() {
      return this.score;
   }

   public void createGameEntities() {
      fruit = new Fruit (this, 50, 350); 
      snake = new Snake (this, 200, 10, fruit); 
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      if (fruit != null) {
         fruit.draw(g2);
      }
      if (snake != null) {
         snake.draw(g2);
      }
   }

   public void updateGameEntities(int direction) {
      if (snake != null) {
         snake.move(direction);
      }
      repaint();
   }

   public void startGame() {
      if (snake != null) {
         snake.start();
      }
   }

   public boolean isOnFruit (int x, int y) {
      if (fruit != null)
         return fruit.isOnFruit(x, y);
      else
         return false;
   }

   public void gameOver() {
      JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
      System.exit(0);
   }

   public void setDirection(int direction) {
      if (snake != null) {
         this.direction = direction;
      }
   }

   public int getDirection() {
      return this.direction;
   }

}
