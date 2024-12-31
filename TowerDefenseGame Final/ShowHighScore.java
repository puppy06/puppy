import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shows and updates the high score in the menu
 * @author Hank Shi
 * @version June 15 , 2023
 */
public class ShowHighScore extends Actor
{
    MyWorld game;
    /**
     * Constructor of ShowHighScore class
     * @param int - current high score of world
     */
    public ShowHighScore(int highScore)
    {
        setImage(new GreenfootImage ("High Score: " + highScore, 25, Color.WHITE, new Color(0,0,0,0)));
    }
    /**
     * Displays new high score if it has been updated
     */
    public void updateHighScore()
    {
        game = (MyWorld)getWorld();
        setImage(new GreenfootImage ("High Score: " + game.getHighScore(), 25, Color.WHITE, new Color(0,0,0,0)));  
    }
}
