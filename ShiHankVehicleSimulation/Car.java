import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Car subclass
 */
public class Car extends Vehicle
{
    
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
    }
    /**
     * Act - Car runs over Citizens. 
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive(); 
        checkHitPedestrian();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * When a Car hit's a Citizen, it should knock it over
     */
    public boolean checkHitPedestrian () {
        Citizen p = (Citizen)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Citizen.class);
        if (p != null){
            p.knockDown();
            return true;
        }
        return false;
    }
}
