import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effect here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Effect extends Actor
{    
    /**
     * Method that freezes everything on the screen for 3 seconds
     */
    protected void freeze(int duration) {
        Greenfoot.delay(duration);
    }
}
