import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import javax.swing.JPanel;
import java.util.Random;

public class Fruit extends Thread {

   private JPanel panel;

   private int x;
   private int y;

   private int width;
   private int height;

   Ellipse2D.Double fruitShape;	// ellipse drawn for face

   private Color backgroundColour;
   private Dimension dimension;

   boolean isRunning;

   Random random;

   private Snake snek;

   public Fruit (JPanel p, int xPos, int yPos, Snake snek) {
      panel = p;
      dimension = panel.getSize();
      backgroundColour = panel.getBackground ();

      x = xPos;
      y = yPos;

      width = 20;
      height = 20;

      this.snek = snek;

      random = new Random();

   }


   public void setLocation() {
      int panelWidth = panel.getWidth();
      int panelHeight = panel.getHeight();
      x = random.nextInt (panelWidth - width);
      y = random.nextInt (panelHeight - height);
   }


   public void draw () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      // Draw the head

      fruitShape = new Ellipse2D.Double(x, y, width, height);

      g2.setColor(Color.red); 
      g2.fill(fruitShape);		// colour the fruit

      g2.setColor(Color.BLACK);	 
      g2.draw(fruitShape);		// draw outline around fruit

      g.dispose();
   }


   public void erase () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      // erase face by drawing a rectangle on top of it

      g2.setColor (backgroundColour);
      g2.fill (new Rectangle2D.Double (x, y, width, height));

      g.dispose();
   }


   public void move() {
      if (!panel.isVisible ()) return;

      boolean collision = collidesWithSnake();

      if(collision){
         snek.grow();
         setLocation();
      }
   }


   public void run () {

      isRunning = true;

      try {
        while (isRunning) {
            erase();
	         move ();
            draw();
            sleep (100);	// increase value of sleep time to slow down ball
         }
      }
      catch(InterruptedException e) {}
   }


   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, width, height);
   }

   
   public boolean collidesWithSnake() {
      Rectangle2D.Double myRect = getBoundingRectangle();
      Rectangle2D.Double snekRect = snek.getBoundingRectangle();
      
      return myRect.intersects(snekRect); 
   }

}