import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Snake {

   private JPanel panel;
   private ArrayList<Point> body;//Point was used since it allows for the storage of 2D spaces within Java
   private int segmentSize;
   private int dx;
   private int dy;
   private Color backgroundColour;
   private Dimension dimension;
   public boolean isGameOver = false;

   public Snake (JPanel p, int xPos, int yPos) {
      panel = p;
      dimension = panel.getSize();
      backgroundColour = panel.getBackground ();
      segmentSize = 10;
      dx = segmentSize;	// horizontal movement; increase to move faster and decrease to move slower
      dy = 10;	// vertical movement; decrease to move faster and increase to move slower
      body = new ArrayList<>();
      body.add(new Point(xPos, yPos));
   }


   public void draw () {//Uses the Point array to draw the snake instead of the typical rectangle with co-ords
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.RED);
      for (Point p : body) {
         g2.fill(new Rectangle2D.Double(p.getX(), p.getY(), segmentSize, segmentSize));
      }
      g.dispose();
   }


   public void erase () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      // erase bat by drawing a rectangle on top of it
      g2.setColor (backgroundColour);
      for (Point p : body) {
         g2.fill(new Rectangle2D.Double(p.getX(), p.getY(), segmentSize, segmentSize));
      }
      g.dispose();
      }

      public void move (int direction) {

      if (!panel.isVisible ()) return;
      
      dimension = panel.getSize();
      Point h = body.get(0);
      int nX = h.x; // New x value for the head of the snake
      int nY = h.y; // New y value for the head of the snake

      switch (direction) {
         case 1: // move left
         nX -= dx;
         break;
         case 2: // move up
         nY -= dy;
         break;
         case 3: // move right
         nX += dx;
         break;
         case 4: // move down
         nY += dy;
         break;
      }

      // Wrap around the screen
      if (nX < 0) {
         nX = dimension.width - segmentSize;
      } else if (nX >= dimension.width) {
         nX = 0;
      }

      if (nY < 0) {
         nY = dimension.height - segmentSize;
      } else if (nY >= dimension.height) {
         nY = 0;
      }

      // Move the snake
      body.add(0, new Point(nX, nY));
      body.remove(body.size() - 1);

      if(checkSelfCollision()){
         isGameOver = true;
         ((GamePanel)panel).handleGameOver();
      }
   }
   
   public void grow() {
      Point t = body.get(body.size()-1);
      body.add(new Point(t.x, t.y));
   }

   public boolean isOnSnake (int x, int y) {
      for (Point p : body) {
         if (p.getX() == x && p.getY() == y) {
            return true;
         }
      }
      return false;
   }


   public ArrayList<Point> getBody() {
      return body;
   }

   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double(body.get(0).getX(), body.get(0).getY(), segmentSize, segmentSize);
   }

   private boolean checkSelfCollision() {
      Point head = body.get(0);
      for (int i = 1; i < body.size(); i++) {
         if (head.equals(body.get(i))) {
            return true;
         }
      }
      return false;
   }

}