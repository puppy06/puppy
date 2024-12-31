import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Police here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Police extends Vehicle
{
    /**
     * Act - Drives on the bottom lane and checks for Terrorists. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage policeCar = new GreenfootImage("police.png");
    private GreenfootSound policeSiren = new GreenfootSound("PoliceSiren.wav");
    public Police(VehicleSpawner origin) {
        super(origin);
        policeCar.scale(policeCar.getWidth()/4,policeCar.getHeight()/4);
        setImage(policeCar);
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
    }
    
    public void act()
    {
        drive();
        checkHitPedestrian();
        if (checkEdge()){
            if (policeSiren.isPlaying())
                policeSiren.stop();
            getWorld().removeObject(this);
        }
    }
    /**
     * In this drive method Police doesn't check for empty lanes since it doesn't lanes
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        if (ahead == null)
        {
            speed = maxSpeed;
        } 
        move (speed * direction);
    }
    /**
     * Method that checks if Police has touched a terrorist. If it does, It will arrest the terrorist.
     */
    public boolean checkHitPedestrian () {
        Terrorist t = (Terrorist)getOneIntersectingObject(Terrorist.class);
        if(t!=null){
            getWorld().removeObject(t);
            policeSiren.play();
        }
        return false;
    }
    
}
