import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * RedBalloons is 1 of the 6 balloon types
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class RedBalloons extends Balloons
{
    /**
     * Constructor for objects of class RedBalloons.
     */
    public RedBalloons(double speedChange){
        image = new GreenfootImage("Red.png");
        image.scale(20,40);
        setImage(image);
        speed = 2*speedChange;
        health = 1;
        damage = 1;
        cash=5;
        baseCash = cash;
    }
}
