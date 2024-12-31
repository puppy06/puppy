import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * GreenBalloons is 1 of the 6 balloon types
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class GreenBalloons extends Balloons
{
    /**
     * Constructor for objects of class GreenBalloons.
     */
    public GreenBalloons(double speedChange){
        image = new GreenfootImage("Green.png");
        image.scale(20,40);
        setImage(image);
        speed = 2*speedChange;
        health = 2;
        damage = 3;
        cash=25;
        baseCash = cash;
    }
}
