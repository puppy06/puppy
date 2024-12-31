import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The WinWorld will appear on the screen when the timer variable decreases all the way to 0
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */

public class WinWorld extends World
{
    private GreenfootSound music;
    private GreenfootSound win;
    private Font font;
    /**
     * Constructor for objects of class WinWorld.
     * 
     */
    public WinWorld(int money)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 480, 1); 
        font = new Font("Impact", true, false, 30);
        addObject(new MrKrabs(), 175,200);
        getBackground().setColor(Color.WHITE);
        getBackground().setFont(font);
        getBackground().drawString("When you win "+money+" dollars",25,320);
        win = new GreenfootSound("congratulations.wav");
        music = new GreenfootSound ("ThemeofAgrual.mp3");
        win.play();
        
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
