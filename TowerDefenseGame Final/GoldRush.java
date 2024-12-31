import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Doubles the cash that balloons drop when this event is happening
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class GoldRush extends Effects
{
    private int duration;

    /**
     * Constructor for objects of class GoldRush.
     * @ param duration - the duration of the event
     */
    public GoldRush (int duration){
        this.duration = duration;
    }

    /**
     * Add the GoldRush event into the world
     * @ param w - the world that the object is added to
     */
    public void addedToWorld (World w){
        image = drawGold (w.getWidth(), w.getHeight(), 200);
        setImage(image);
        ArrayList<Balloons> balloons =  (ArrayList<Balloons>) w.getObjects(Balloons.class);
        Balloons.rushMe();
    }

    public void act()
    {
        if (duration == 0){
            // speed vehicles back up 
            getWorld().removeObject(this);
        } else if (duration <= 90){
            fade (duration, 90);
        } 
        duration--;
    }

    /**
     * Draws the GoldRush event
     * @ param width - the width of the event
     * @ param height - the height of the event
     * @ param density - the density of the event
     */
    public static GreenfootImage drawGold (int width, int height, int density){

        Color[] swatch = new Color [64];
        int red = 128;
        int blue = 192;

        // Build a color pallete out of shades of near-white yellow and near-white blue      
        for (int i = 0; i < swatch.length/2; i++){ // first half blue tones
            swatch[i] = Color.YELLOW;
        }
        for (int i = swatch.length/2; i < swatch.length; i++){ // second half yellow tones
            swatch[i] = Color.ORANGE;
        }

        // The temporary image, my canvas for drawing
        GreenfootImage temp = new GreenfootImage (900, 480);

        // Run this loop one time per "density"

        for (int i = 0; i < density; i++){
            for (int j = 0; j < 100; j++){ // draw 100 circles
                int randSize;
                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(swatch.length);
                temp.setColor (swatch[randColor]);

                // random locations for our dot
                int randX = Greenfoot.getRandomNumber (width);
                int randY = Greenfoot.getRandomNumber (height);

                int tempVal = Greenfoot.getRandomNumber(250);
                if (tempVal >= 1){
                    temp.drawRect (randX, randY, 0, 0);
                }else{
                    randSize = Greenfoot.getRandomNumber (2) + 2;
                    temp.fillOval (randX, randY, randSize, randSize);
                }
            }
        }

        return temp;
    }
}