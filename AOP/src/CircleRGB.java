import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
   An icon that has the shape of a car.
*/
public class CircleRGB implements Icon
{
	
    private int size;
    public Color color;

    /**
     * Creates a circle Object with color set to red as default
     * @param diam. Set size of circle
     */
    public CircleRGB(int diam)
    {
    	size = diam;
    	this.color = Color.red;
    }
    /**
     *     
     * @param diam. Set size of circle
     * @param c. Set color of circle 
     */
    public CircleRGB(int diam, Color c)
    {
    	size = diam;
    	this.color = c;
    }
    
    /**
     * 
     * @param diam. Set size of object
     */
    public void setIconWidth(int diam)
    {
    	size = diam;
    }
   
    
    public int getIconWidth()
    {
    	return size;
    }

    public int getIconHeight()
    {
    	return size;
    }

    
    public void paintIcon(Component c, Graphics g, int x, int y)
    { 
    	Graphics2D g2 = (Graphics2D)g;
    	Ellipse2D.Double circle
        = new Ellipse2D.Double(x + size / 6, y + size / 6, 
           size * 0.75 , size * 0.75);
        g2.setColor(color);
    	g2.fill(circle);
    }
}


