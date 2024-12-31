import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * YellowBalloons is 1 of the 6 balloon types
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class YellowBalloons extends Balloons
{
    /**
     * Constructor for objects of class YellowBalloons.
     */
    public  YellowBalloons(double speedChange)
    {
        image = new GreenfootImage("Yellow.png");
        image.scale(20,40);
        setImage(image);
        speed = 3*speedChange;
        health = 3;
        damage = 4;
        cash=50;
        baseCash = cash;
    }
}
