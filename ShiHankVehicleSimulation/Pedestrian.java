import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Pedestrian that tries to walk across the street
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    // Instance variables that Car, Bus, Ambulance and Police inherit
    protected double speed;
    protected double maxSpeed;
    protected int direction; // direction is always -1 or 1, for moving down or up, respectively
    protected boolean awake;
    public Pedestrian(int direction) {
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        this.direction = direction;
    }    
}
