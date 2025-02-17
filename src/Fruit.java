import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;

public class Fruit {

   private JPanel panel;
   private int x;
   private int y;
   private int width;
   private int height;
   private Random random = new Random();

   private Ellipse2D.Double fruit;

   private Color backgroundColour;

   public Fruit (JPanel p, int xPos, int yPos) {
      panel = p;
      backgroundColour = panel.getBackground ();
      x = xPos;
      y = yPos;
      width = 10;
      height = 10;
   }

   public void draw (Graphics2D g2) {
      fruit = new Ellipse2D.Double(x, y, width, height);
      g2.setColor(Color.decode("#FF8469"));
      g2.fill(fruit);
   }

   public void setLocation() {
      int panelWidth = panel.getWidth();
      int panelHeight = panel.getHeight();
      x = random.nextInt (panelWidth - width);
      y = random.nextInt(panelHeight- height);
   }

   public boolean isOnFruit (int x, int y) {
      if (fruit == null)
         return false;
      return fruit.contains(x, y);
   }

   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double (x, y, width, height);
   }
}