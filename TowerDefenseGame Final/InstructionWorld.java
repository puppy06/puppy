import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The InstructionWorld shows how to play the game on the screen using text to the user playing this game
 * 
 * @author Marcus Yeung
 * @version June 15 2023
 */

public class InstructionWorld extends World
{
    private GreenfootSound music;
    private double healthChange;
    private double speedChange;
    private TextShower title,text1,text2,text3,text4,text5,text6, text7, text8;
    /**
     * Constructor for objects of class InstructionWorld.
     * 
     */
    public InstructionWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 480, 1); 
        title = new TextShower("INSTRUCTIONS", 30);
        addObject(title,750,50);
        text1 = new TextShower("This tower defense game consists of 3 towers, the attack tower, bomb tower, and sniper tower", 15);
        addObject(text1,550,90);
        text2 = new TextShower("As the balloons spawn, the player will be able to place towers as long as you have the money to purchase them", 15);
        addObject(text2,550,130);
        text3 = new TextShower("The player will be able to earn money by popping the balloons", 15);
        addObject(text3,550,170);
        text5 = new TextShower("Crates will also fall from the sky which will drop money when the user clicks on them", 15);
        addObject(text5,550,210);
        text5 = new TextShower("Each balloon will give a different amount of money when popped", 15);
        addObject(text5,550,250);
        text6 = new TextShower("The player will win if they survive for 1 minute", 15);
        addObject(text6,550,290);
        text7 = new TextShower("However, the player will lose if the health bar loses all of its health", 15);
        addObject(text7,550,330);
        text8 = new TextShower("PRESS Esc TO RETURN TO THE MAIN MENU", 30);
        addObject(text8,550,370);
        music = new GreenfootSound ("ThemeofAgrual.mp3");
        this.healthChange = healthChange;
        this.speedChange = speedChange;
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
            //Greenfoot.setWorld(new MyWorld(healthChange,speedChange));
        }
        if (Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
