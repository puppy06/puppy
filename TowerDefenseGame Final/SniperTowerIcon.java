import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IceTowerIcon here.
 * @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public class SniperTowerIcon extends BackgroundBlock
{
    private GreenfootImage sniperImage;
    /**
     * Constructor for sniper tower icon
     */
    public SniperTowerIcon(){
        //Create Icon Images
        
        icon = getImage();
        sniperImage = new GreenfootImage("SniperTower.png");
        sniperImage.scale(40,40);
        towerPrice = 250;
        icon.drawImage(sniperImage,5,5);
        //setImage(icon);
    }
    /**
     * creates sniper tower
     * @ param World - the world that the object is added to 
     */
    public void create(MyWorld world){
        world.addObject(new SniperTower(), -100, -100);
    }
}
