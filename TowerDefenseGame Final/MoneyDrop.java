import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An object that is spawned into the world and moves to its destination
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class MoneyDrop extends Actor
{
    private int speed;
    /**
     * Constructor for objects of class MoneyDrop.
     */
    public MoneyDrop(){
        speed = 1;
    }
    public void act()
    {
        int destination=Greenfoot.getRandomNumber(350)+100;
        setRotation(90);
        move(speed);
        //if at destination, stop moving
        if((Math.abs(getY()-destination))<5){
            speed = 0;
        }
    }
}

