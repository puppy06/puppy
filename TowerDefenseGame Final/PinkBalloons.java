import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PinkBalloons is 1 of the 6 balloon types
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class PinkBalloons extends Balloons
{
    /**
     * Constructor for objects of class PinkBalloons.
     */
    public PinkBalloons(double speedChange)
    {
        image = new GreenfootImage("Pink.png");
        image.scale(20,40);
        setImage(image);
        speed = 4*speedChange;
        health = 4;
        damage = 5;
        cash=75;
        baseCash = cash;
    }
}
