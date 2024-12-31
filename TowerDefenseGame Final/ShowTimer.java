import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shows and updates the time in the menu
 * 
 * @author Hank Shi
 * @version June 2023
 */
public class ShowTimer extends Actor
{
    /**
     * Act - do whatever the ShowTimer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    MyWorld game;
    /**
     * Constructor of ShowTimer class
     * @param int - current time of world
     */
    public ShowTimer(int timer){
        setImage(new GreenfootImage ("Timer: " + timer, 25, Color.WHITE, new Color(0,0,0,0)));
    }
    /**
     * Displays new time every second(60 acts)
     */
    public void updateTimer()
    {
        game = (MyWorld)getWorld();
        setImage(new GreenfootImage ("Timer: " + game.getTimer()/60, 25, Color.WHITE, new Color(0,0,0,0)));  
    }
}
