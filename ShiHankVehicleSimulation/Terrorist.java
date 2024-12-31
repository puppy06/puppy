import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Write a description of class Terrorist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Terrorist extends Pedestrian
{
    // Gets closest bus in range
    private Bus closestBus; 
    private int terroristBusDistance;
    private GreenfootSound teleportation = new GreenfootSound("teleportationsound.wav");
    private GreenfootSound explosionSound = new GreenfootSound("explosionsound.wav");
    public Terrorist(int direction) {
        super(direction);
        GreenfootImage terrorist = new GreenfootImage("images/terrorist.png");
        terrorist.scale(terrorist.getWidth()/10, terrorist.getHeight()/10);
        setImage(terrorist);
        // start as awake 
        awake = false;
        this.direction = direction;
    }
    /**
     * Act - Check for Buses and teleport to them to blow them up. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // check if terrorist has found bus          
        trackDown();
        if (closestBus!=null) {
            awake = true;
            if (Math.abs(getX()-closestBus.getX())<=10) {
                teleportation.setVolume(100);
                explosionSound.setVolume(100);
                teleportation.play();
                explosionSound.play();
                setLocation(closestBus.getX(),closestBus.getY()+60);
                Explosion e = new Explosion();
                getWorld().addObject(e,closestBus.getX(),closestBus.getY());
                e.playExplosion();
            }
            if (closestBus.getTerroristHere()){
                getWorld().removeObject(this);
            }
        }        
    }
      
    /**
     * Method to find closest bus 
     */
    public boolean trackDown() {
        // Get all buses in range of terrorist
        ArrayList<Bus> busesInRange = (ArrayList<Bus>)getObjectsInRange(313,Bus.class);
        if (busesInRange==null) {
            return false;
        } else {           
            // Getting the bus with the closest x-position
            int xDistDiff = Integer.MAX_VALUE;
            for (Bus b: busesInRange){
                if (b.getX()< getX()){
                    int diff = Math.abs(getX()-b.getX());
                    if (diff<xDistDiff){
                        closestBus = b;                          
                        xDistDiff=diff;
                    }                    
                }                        
            }
            // Terrorist has chosen its target bus 
            if (closestBus!=null){
                awake = true;
                return true;
            } 
            else
                return false;           
        }
    }
}   
