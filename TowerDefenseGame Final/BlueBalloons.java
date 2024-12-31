import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BlueBalloons is 1 of the 6 balloon types
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class BlueBalloons extends Balloons
{
    /**
     * Constructor for objects of class BlueBalloons.
     */
    public BlueBalloons(double speedChange){
        image = new GreenfootImage("Blue.png");
        image.scale(20,40);
        setImage(image);
        speed = 2*speedChange;
        health = 1;
        damage = 2;
        cash = 10;
        baseCash = cash;
    }
}
