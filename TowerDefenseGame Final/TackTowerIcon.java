import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class IceTowerIcon here.
  * @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public class TackTowerIcon extends BackgroundBlock
{
    private GreenfootImage TackTowerImage;
    /**
     * Constructor for tack tower icon
     */
    public TackTowerIcon(){
        //Create Icon Images
        
        icon = getImage();
        TackTowerImage = new GreenfootImage("TackTower.png");
        TackTowerImage.scale(40,40);
        towerPrice = 2000;
        icon.drawImage(TackTowerImage,5,5);
        //setImage(icon);
    }
    /**
     * creates tack tower
     * @ param World - the world that the object is added to 
     */
    public void create(MyWorld world){
        world.addObject(new TackTower(), -100, -100);
    }
}
