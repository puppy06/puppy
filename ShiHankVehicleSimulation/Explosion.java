import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Effect
{
    // image of explosion
    private GreenfootImage explosionImage = new GreenfootImage("explosion.png");
    public Explosion()
    {
        explosionImage.scale(200,200);
        setImage(explosionImage);        
    }
    /**
     * Method to display explosion image and remove every Actor in range of explosion
     */
    public void playExplosion(){
        
        // removes list of objects in range of explosion
        getWorld().removeObject((Terrorist)getOneIntersectingObject(Terrorist.class));
        ArrayList<SuperSmoothMover> impactedObjects = (ArrayList<SuperSmoothMover>)getObjectsInRange(125,SuperSmoothMover.class);
        for(SuperSmoothMover object: impactedObjects) {
            getWorld().removeObject(object);
        }
        // pauses everything for 3 seconds
        freeze(180);
        getWorld().removeObject(this);
    }
}
