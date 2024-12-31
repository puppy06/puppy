import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Rapidly shoots out 8 weak tacks in every direction
* @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public class TackTower extends Towers
{
    private Tack[] tacks;
    private boolean spawnOnce = false;
    private int tackTimer = -1;
    /**
     * Constructor for tack tower class
     */
    public TackTower(){
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
        tackTimer++;
        if(tackTimer%120==0){
            //spawnOnce = true;
            fireProjectile(attackSpeed);
        }
    }
    /**
     * Spawns all 8 projectiles
     * @param double - the speed the projectiles will travel
     */
    public void fireProjectile(double attackSpeed){
        tacks = new Tack[8];
        for(int i = 1;i<=8;i++){
            tacks[i-1] = new Tack(i);
            getWorld().addObject(tacks[i-1],getX(),getY());
        }
    }
    

}