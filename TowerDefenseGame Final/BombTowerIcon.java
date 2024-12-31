import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IceTowerIcon here.
 * 
  * @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public class BombTowerIcon extends BackgroundBlock
{
    private GreenfootImage cannonImage;
    /**
     * Constructor for bomb tower icon
     */
    public BombTowerIcon(){
        //Create Icon Images
        icon = getImage();
        font2 = new Font("SANS_SERIF", true, false, 20);
        cannonImage = new GreenfootImage("BombTower.png");
        cannonImage.scale(40,40);
        towerPrice = 150;
        icon.drawImage(cannonImage,5,5);
    }
    /**
     * creates bomb tower
     * @ param World - the world that the object is added to 
     */
    public void create(MyWorld world){
        world.addObject(new BombTower(), -100, -100);
        
    }
}
