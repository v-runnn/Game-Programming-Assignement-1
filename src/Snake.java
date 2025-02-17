import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Snake extends Thread {

   private JPanel panel;
   private ArrayList<Point> snake;

   private int segmentSize;

   private int dx;		// increment to move along x-axis
   private int dy;		// increment to move along y-axis

   private Color backgroundColour;
   private Dimension dimension;
   private boolean isRunning;
   private boolean isPaused;
   private int direction = 1;

   private Fruit fruit;

   public Snake (JPanel panel, int xPos, int yPos, Fruit fruit) {
      this.panel = panel;
      backgroundColour = this.panel.getBackground ();
      segmentSize = 10;
      dy = 10;
      dx = segmentSize;
      snake = new ArrayList<>();
      snake.add(new Point(xPos, yPos));
      this.fruit = fruit;
      direction = ((GamePanel)panel).getDirection();
   }

   public void draw (Graphics2D g2) {
      g2.setColor(Color.decode("#27FF22"));
      for (Point p : snake) {
         g2.fill(new Rectangle2D.Double(p.x, p.y, segmentSize, segmentSize));
      }
   }

   public void move(int direction) {
      if (!panel.isVisible ()) return;

      Point head = snake.get(0);
      int x = head.x;
      int y = head.y;

      //prevent snake from moving back onto itself
      if ((this.direction == 1 && direction == 3) || (this.direction == 3 && direction == 1) ||
         (this.direction == 2 && direction == 4) || (this.direction == 4 && direction == 2)) {
         direction = this.direction;
      } else {
        this.direction = direction;
      }

      if(direction == 3) {// move right
         x += dx;
      }
      else if(direction == 1) {// move left
         x -= dx;
      }
      else if(direction == 2) {// move up
         y -= dy;
      }
      else if(direction == 4) {// move down
         y += dy;
      }

      if (x < 0) {
         x = panel.getWidth() - segmentSize;
      } else if (x >= panel.getWidth()) {
         x = 0;
      }

      if (y < 0) {
         y = panel.getHeight() - segmentSize;
      } else if (y >= panel.getHeight()) {
         y = 0;
      }

      snake.add(0, new Point(x, y));
      snake.remove(snake.size() - 1);

      if(snake.size()!=1){
         head = snake.get(0);
         for(int i = 1; i < snake.size(); i++) {
            if(head.equals(snake.get(i))) {
               isRunning = false;
               ((GamePanel)panel).gameOver();
            }
         }
      }

      if(eatsFruit()) {
         grow();
         fruit.setLocation();
         ((GamePanel)panel).increaseScore();
      }
   }

   public void grow(){
      Point tail = snake.get(snake.size() - 1);
      snake.add(new Point(tail.x, tail.y));
   }

   public void run () {
      isRunning = true;
      dimension = panel.getSize();
      try {
         while (isRunning) {
            move(((GamePanel)panel).getDirection());
            panel.repaint();
            sleep (100);	// increase value of sleep time to slow down snake
         }
      }
      catch(InterruptedException e) {}
   }

   public boolean isOnSnake (int x, int y) {
      for (Point p : snake) {
         if (p.x == x && p.y == y) {
            return true;
         }
      }
      return false;
   }

   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (snake.get(0).x, snake.get(0).y, segmentSize, segmentSize);
   }

   public boolean eatsFruit() {
      Rectangle2D.Double myRect = getBoundingRectangle();
      Rectangle2D.Double fruitRect = fruit.getBoundingRectangle();
      return myRect.intersects(fruitRect); 
   }
}