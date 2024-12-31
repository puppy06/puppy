import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * One shots all Balloons in its range
* @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public class SniperTower extends Towers
{
    private GreenfootImage sniperTower = new GreenfootImage("SniperTower.png");
    private GreenfootSound rifleShot = new GreenfootSound("sounds/rifle shot.wav");
    private int direction;
    private SniperRound sniperRound;
    private boolean fired = false;
    private int sniperFrequency = -2;
    /**
     * Constructor for sniper tower class
     */
    public SniperTower()
    {
        sniperTower.mirrorHorizontally();
        setImage(sniperTower);
        attackSpeed = 3;
        range  = 300;
    }
    public void act()
    {
        placing();
        
        if(isPlaced != true){
            return;
        }
        // Add your action code here.
        aim();
        sniperFrequency++;
        if(sniperFrequency<0||sniperFrequency-300>=0){
            fireProjectile(1);
        }
        
        MyWorld world = (MyWorld) getWorld();
        RangeHighlight rh = new RangeHighlight(range);
        
        if(Greenfoot.mouseClicked(this)){
            world.addObject(rh, getX(), getY());
        }
        
    }
    /**
     * Spawns 1 projectile at location of balloon
     * @param double - the speed the projectiles will travel
     */
    public void fireProjectile(double attackSpeed){
        if(balloonInRange!=null){
            sniperFrequency = 0;
            MyWorld world = (MyWorld) getWorld();
            SniperRound sr = new SniperRound(getRotation());
            world.addObject(sr, getX(), getY());
        }
    }
    
}
