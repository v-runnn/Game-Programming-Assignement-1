import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Bat {

   private JPanel panel;
   private int x;
   private int y;
   private int width;
   private int height;

   private int dx;
   private int dy;

   private Rectangle2D.Double bat;

   private Color backgroundColour;
   private Dimension dimension;

   private Image batImage;
   private Image batLeftImage;
   private Image batRightImage;

   public Bat (JPanel p, int xPos, int yPos) {
      panel = p;
      dimension = panel.getSize();
      backgroundColour = panel.getBackground ();
      x = xPos;
      y = yPos;

      dx = 10;
      dy = 0;

      width = 50;
      height = 50;

      // batLeftImage = ImageManager.loadImage ("BatLeft.gif");
      // batRightImage = ImageManager.loadImage ("BatRight.gif");

      batImage = batRightImage;
   }


   public void draw () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      g2.drawImage(batImage, x, y, width, height, null);

      g.dispose();
   }


   public void erase () {
      Graphics g = panel.getGraphics ();
      Graphics2D g2 = (Graphics2D) g;

      // erase bat by drawing a rectangle on top of it with the background colour

      g2.setColor (backgroundColour);
      g2.fill (new Rectangle2D.Double (x, y, width, height));

      g.dispose();
   }


	public void move (int direction) {

		if (!panel.isVisible ()) return;
      
		if (direction == 1) {			// going left
			x = x - dx;
	                batImage = batLeftImage;          
			if (x < -30)			// move to right of GamePanel
				x = 380;
		}
		else 
		if (direction == 2) {			// going right
			x = x + dx;
          	  	batImage = batRightImage;
			if (x > 380)			// move to left of GamePanel
				x = -30;
		}
	}


   public boolean isOnBat (int x, int y) {
      if (bat == null)
      	  return false;

      return bat.contains(x, y);
   }


   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, width, height);
   }

}