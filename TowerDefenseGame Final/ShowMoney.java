import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shows and updates the money in the menu
 * 
 * @author Hank Shi 
 * @version June 2023
 */
public class ShowMoney extends Actor
{
    
    MyWorld game;
    private int originalMoney;
    /**
     * Constructor of ShowMoney class
     * @param int - current money of world
     */
    public ShowMoney(int money)
    {
        originalMoney = money;
        setImage(new GreenfootImage ("Money: " + money, 25, Color.WHITE, new Color(0,0,0,0)));
    }
    /**
     * Displays new money score if it has been updated
     */
    public void updateMoney()
    {
        game = (MyWorld)getWorld();
        if(game.getMoney()>originalMoney){
            game.hasGainedMoney();
        }else if(game.getMoney()<500){
            originalMoney = game.getMoney();
        }
        setImage(new GreenfootImage ("Money: " + game.getMoney(), 25, Color.WHITE, new Color(0,0,0,0)));  
    }
}
