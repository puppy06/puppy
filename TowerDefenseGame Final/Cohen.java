import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * RainbowBalloons is 1 of the 6 balloon types
 * 
 * @author Jude Kuo 
 * 
 * @version (June 15 2023)
 */
public class Cohen extends Balloons
{
    /**
     * Constructor for objects of class Cohen.
     */
    public Cohen(double speedChange){
        isBoss = true;
        image = new GreenfootImage("Rainbow.png");
        //image.scale(80,80);
        setImage(image);
        speed = 2*speedChange;
        health = 5;
        damage = 6;
        cash=100;
        baseCash = cash;
    }
    
}
