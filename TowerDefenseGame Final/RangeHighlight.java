import greenfoot.*;

/**
 * Class that draws a faint circle indicating the range of a SniperTower
 * @author Abdullah Qureshi
 * @version June 15 , 2023
 */
public class RangeHighlight  extends Actor
{
    // instance variables - replace the example below with your own
    private int radius;
    private GreenfootImage image;

    /**
     * Constructor for objects of class RangeHighlight
     * @param int - the range of sniper tower
     */
    public RangeHighlight(int radius)
    {
        this.radius = radius;
        
        image = new GreenfootImage(radius*2, radius*2);
        image.setColor(new Color(181, 177, 179, 100));
        image.drawOval(0,0, radius*2, radius*2);
        setImage(image);
    }

    
}
