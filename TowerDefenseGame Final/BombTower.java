import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Detects the nearest Balloon and plants a bomb near it, decreasing all Balloons in its range
 * @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public class BombTower extends Towers
{
    private GreenfootImage bombTower = new GreenfootImage("images/BombTower.png");
    private GreenfootImage bombTowerFired = new GreenfootImage("images/BombTowerFired.png");
    private int bombFrequency = -2;
    /**
     * Constructor for bomb tower class
     */
    public BombTower(){
        bombTower.rotate(90);
        bombTowerFired.rotate(90);
        bombTower.mirrorHorizontally();
        bombTowerFired.mirrorHorizontally();
        setImage(bombTower);
        attackSpeed = 3;
        range  = 100;     
    }
    public void act()
    {
        placing();
        
        if(isPlaced != true){
            return;
        }
        
        // Add your action code here.
        //detectBalloon();
        //setImage(bombTower);
        aim();
        bombFrequency++;
        
        if(bombFrequency<0||bombFrequency-300>=0){
            fireProjectile(attackSpeed);
        }
    }
    /**
     * Spawns Bomb at closest balloon
     * @param double - the speed the projectiles will travel
     */
    public void fireProjectile(double attackSpeed){
        if(balloonInRange!=null &&!balloonInRange.isReachedEnd()){
            bombFrequency = 0;
            Bomb bomb = new Bomb();
            getWorld().addObject(bomb,balloonInRange.getX(),balloonInRange.getY());
            
        }
    }
    /**
     * Spawns Bomb at closest balloon
     * @param double - the speed the projectiles will travel
     */
     private void shoot(double attackSpeed){
        Bomb bomb = new Bomb();
        getWorld().addObject(bomb,getX(),getY());
        bomb.rotate(getRotation());
    }
}
