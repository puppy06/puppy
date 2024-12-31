import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The LoseWorld will appear on the screen when the user's health decreases all the way to 0
 * 
 * @author (Marcus Yeung) 
 * @version June 15 2023
 */

public class LoseWorld extends World
{
    private GreenfootSound music;
    private GreenfootSound fail;
    /**
     * Constructor for objects of class LoseWorld.
     * 
     */
    public LoseWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 480, 1); 
        fail = new GreenfootSound("epicfail.wav");
        music = new GreenfootSound ("ThemeofAgrual.mp3");
        fail.play();
    }

    /**
     * Starts the music 
     */
    public void started(){
        music.playLoop();
    }
    
    /**
     * Stops the music
     */
    public void stopped(){
        music.stop();
    }
    
    public void act () {
        //Changes to GameWorld when the user presses enter
        if (Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
