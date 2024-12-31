import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Class of Bomb that detonates within the closest balloon and affects all balloons in that range
 * @author Marcus Yeung
 * @author Hank Shi
 * @author Abdullah Qureshi
 * @version June 15 , 2023
 */
public class Bomb extends Projectiles
{
    private GreenfootImage bomb = new GreenfootImage("images/Bomb.png");
    private GreenfootImage explosionImage = new GreenfootImage("images/explosion.png");
    private GreenfootSound explosionsound = new GreenfootSound("sounds/explosionsound.wav");
    private int timer = 0;
    protected boolean projectiled, canAttack;
    /**
     * Constructor of Bomb class
     */
    public Bomb() {
        projectiled = false;
        canAttack = true;
        setImage(bomb);
        explosionImage.scale(60,60);
    }

    public void act() {
        //ArrayList<Balloons> impactedObjects = (ArrayList<Balloons>)getObjectsInRange(60,Balloons.class);
        //move(projectileSpeed);
        //getWorld().removeObject(this);
        detonate();
    }
    /**
     * Detonates and removes 1 health of all balloons in blast radius
     */
    public void detonate() {
        MyWorld w = (MyWorld)getWorld();
        // Bomb's explosion lasts for 200 acts
        if(timer==0){
            timer++;
            ArrayList<Balloons> impactedObjects = (ArrayList<Balloons>)getIntersectingObjects(Balloons.class);
            // Check if Balloons are in range
            if(impactedObjects.size()>0){
                setImage(explosionImage);
                for(int i = 0;i < impactedObjects.size();i++) {
                    Balloons object = impactedObjects.get(i);
                    // Bomb decreases health of each Balloon in range except Cohen
                    object.decreaseHealth();
                    if(!object.getBoss()){
                        // Bomb removes all Red Balloons in range
                        if(object.getHealth()<0&&!object.isReachedEnd()){
                            object.pop();
                            Balloons temp = impactedObjects.remove(i);
                            if(canAttack == true){
                                w.addMoney(temp.getCash());
                                projectiled = true;
                                canAttack = false;
                            }
                            if(projectiled == true && getIntersectingObjects(Balloons.class)==null){
                                canAttack = true;
                            }
                            i--;
                            getWorld().removeObject(object);
                        }else{
                            object.setImage(object.getBalloonsList()[(int)object.getHealth()]);
                            if(canAttack == true){
                                w.addMoney(impactedObjects.get(i).getCash());
                                projectiled = true;
                                canAttack = false;
                            }
                            if(projectiled == true && getIntersectingObjects(Balloons.class)==null){
                                canAttack = true;
                            }
                        }
                    }
                }
                explosionsound.play();
            }
        // Bomb's detonation period is finished
        }else if(timer==200){
            timer = 0;
            getWorld().removeObject(this);
        }else{
            timer++;
        }
    }
}
