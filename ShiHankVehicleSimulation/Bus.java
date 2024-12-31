import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * The Bus subclass
 */
public class Bus extends Vehicle implements Cloneable
{
    // Bus instance variables and objects
    private int stopTimer = -1;
    private int citizenCount = 0;
    private boolean checkOnce = false;
    private Citizen citizenAhead;
    private Vehicle vehicleAhead;
    private Terrorist terroristAhead;
    private boolean terroristHere = false;
    private GreenfootSound[] brakeSounds;
    private int brakeSoundsIndex;
    private static GreenfootImage[] bus = {new GreenfootImage("bus01.png"),
    new GreenfootImage("bus02.png"),new GreenfootImage("bus03.png"),new GreenfootImage("bus04.png"),new GreenfootImage("bus05.png"),
    new GreenfootImage("bus06.png"),new GreenfootImage("bus07.png"),new GreenfootImage("bus08.png")};
    
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        //Set up values for Bus
        setImage(bus[0]);
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 15;
        
        brakeSoundsIndex = 0;
        brakeSounds = new GreenfootSound[20];
        for(int i = 0;i < brakeSounds.length;i++){
            brakeSounds[i] = new GreenfootSound("carbrake.wav");
            brakeSounds[i].setVolume(90);
        }
    }
    
    /**
     * Act - Bus calls drive method and disappears when it reaches the edge. 
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive();
        if (checkEdge()){
            if (null != this)
            getWorld().removeObject(this);
            return;
        }
        
               
    }
    /**
     * In this drive method Bus also checks for citizens to pick up
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        checkPoliceCarBack();
        vehicleAhead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        // Gets current citizen bus is picking up
        citizenAhead = (Citizen) getOneIntersectingObject(Citizen.class);
        // Creates list of citizens bus is touching
        List<Citizen> citizenAheadList = (ArrayList<Citizen>) getIntersectingObjects(Citizen.class);               
        // Check if bus has reached its limit
        if (citizenCount<7&&vehicleAhead == null)
            {           
            if (citizenAheadList!=null&&citizenAheadList.size()>0){
                // Bus encounters citizen while moving
                if (stopTimer==-1&&citizenAhead.getRotation()==0){
                    speed = 0;
                    checkHitPedestrian();
                // Bus picks up citizen
                } else if (stopTimer==0){
                    stopTimer = -1;
                    citizenCount++;
                    removeTouching(Citizen.class);
                    speed = maxSpeed;
                    checkOnce = false;                
                } else if (stopTimer>0) 
                    checkHitPedestrian();
            } else if (citizenAhead==null)
                speed = maxSpeed;
        // Bus changes lane if it's behind a Vehicle
        } else if (vehicleAhead !=null){
            if (citizenAhead==null){
                speed = vehicleAhead.getSpeed();
                changeLane();       
            }
        }     
        // Adds citizens to sit in bus 
        if (citizenCount<=7)
            setImage(bus[citizenCount]);
        move (speed * direction);   
    }
    /**
     * When a Bus hits a Citizen, it should pause for 1 second for the Citizen to get on a bus
     */    
    public boolean checkHitPedestrian () {
        // Citizen stops, Bus plays brake sound and sets timer for 1 second
        if (!checkOnce&&citizenCount<8){
            citizenAhead.pause();
            brakeSounds[brakeSoundsIndex].play();
            brakeSoundsIndex++;
            if (brakeSoundsIndex>brakeSounds.length-1){
                brakeSoundsIndex = 0;
            }
            stopTimer = 60;
            checkOnce = true;
            return true; 
        // Makes sure Bus doesn't check while paused
        } else if(checkOnce)
            stopTimer--;        
        return false;
    }
    /**
     * When police is behind the bus it changes lane
     */
    public void checkPoliceCarBack(){
        Police police = (Police) getOneObjectAtOffset (-direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Police.class);
        if (police!=null){
            setLocation(getX(),getY()-54);
        }
    }
    public int getCitizenCount(){
        return this.citizenCount;
    }
    public boolean getTerroristHere(){
        return this.terroristHere;
    }
    public Citizen getCitizenAhead(){
        return citizenAhead;
    }
    public Vehicle getVehicleAhead(){
        return vehicleAhead;
    }
}