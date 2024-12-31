import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Ambulance subclass
 */
public class Ambulance extends Vehicle
{
    public Ambulance(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        maxSpeed = 2.5;
        speed = maxSpeed;
    }

    /**
     * Act - Ambulance heals Citizens by touching them. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive();
        checkHitPedestrian();
        checkDownCornerPedestrian();
        checkUpCornerPedestrian();
        if (checkEdge()){
            getWorld().removeObject(this);
        }
        
    }
    /**
     * These methods checks if any Citizens are in front or beside the Ambulance. If they are, they will be healed.
     */
    public boolean checkHitPedestrian () {
        Citizen p = (Citizen)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Citizen.class);
        if (p != null){
            p.healMe();
            return true;
        }
        return false;
    }
    public boolean checkDownCornerPedestrian () {
        Citizen p = (Citizen)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 40, Citizen.class);
        if (p != null){
            p.healMe();
            return true;
        }
        return false;
    }
    public boolean checkUpCornerPedestrian () {
        Citizen p = (Citizen)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, -40, Citizen.class);
        if (p != null){
            p.healMe();
            return true;
        }
        return false;
    }
}
