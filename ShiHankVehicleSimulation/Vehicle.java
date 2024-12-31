import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the superclass for Vehicles.
 * 
 */
public abstract class Vehicle extends SuperSmoothMover
{
    protected double maxSpeed;
    protected double speed;
    protected int direction; // 1 = right, -1 = left
    protected boolean moving;
    protected int yOffset;
    protected VehicleSpawner origin;  
    private final int firstLane =0;
    private final int lastLane = 5;
    //private final int lineDifference = 54;
    
    protected abstract boolean checkHitPedestrian ();

    public Vehicle (VehicleSpawner origin) {
        this.origin = origin;
        moving = true;
        
        if (origin.facesRightward()){
            direction = 1;
            
        } else {
            direction = -1;
            getImage().mirrorHorizontally();
        }
    }
    public void addedToWorld (World w){
        setLocation (origin.getX() - (direction * 100), origin.getY() - yOffset);
    }

    /**
     * A method used by all Vehicles to check if they are at the edge.
     * 
     * Note that this World is set to unbounded (The World's super class is (int, int, int, FALSE) which means
     * that objects should not be stopped from leaving the World. However, this introduces a challenge as there
     * is the potential for objects to disappear off-screen but still be fully acting and thus wasting resources
     * and affecting the simulation even though they are not visible.
     */
    protected boolean checkEdge() {
        if (direction == 1)
        { // if moving right, check 200 pixels to the right (above max X)
            if (getX() > getWorld().getWidth() + 200){
                return true;
            }
        } 
        else 
        { // if moving left, check 200 pixels to the left (negative values)
            if (getX() < -200){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
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

        } else if (ahead != null)
        {
            speed = ahead.getSpeed();
        }
        checkPoliceCarBack();
        changeLane();
        move (speed * direction);
    } 
    /** Method get current lane number of vehicle
     *  @param y - the y position of the lane the Vehicle is in
     * @return int the lane number, zero-indexed
     */
    private int getCurrentVehicleLane(int y){
        VehicleWorld     vw = (VehicleWorld) getWorld();
        return vw.checkCurrentLane (y+yOffset);
    }
    /**
     * Method to check if a Police car is behind the vehicle. If yes, then vehicle will switch lanes 
     */
    protected void checkPoliceCarBack(){
        Police police = (Police) getOneObjectAtOffset (-direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Police.class);
        if(police!=null){
            setLocation(getX(),getY()-54);
        }
    }
   
    /**
     *  Method get Lane width;
     * @return int difference between two neigbour lanes
     */
   
    private int getLaneDifference(){
        VehicleWorld     vw = (VehicleWorld) getWorld();
        return vw.getLaneDifference();
    }
   
    /**
     * Method that deals with change line.
     * First find current lane of vehicle and each lane's space, then check if ahead has vehicle or not
     * If the vehicle ahead is not null, then check if its neighbour lanes has a vehicle or not, if not then change lanes
     * Order of checking neighboring lanes: If down lane: ahead, down and back are not null, change to the down line, otherwise check upper line, if ahead and back
     * are null which means the upper lane is clear, then change to the upper lane. 
     * If lane number is 0(first lane) check only if its bottom lane is clear
     * If lane number is 5(last lane) check only if its top lane is clear
     */
    protected void changeLane(){
        int lane = getCurrentVehicleLane(getY());
        int laneDifference = getLaneDifference();
        Vehicle ahead = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        Vehicle down = (Vehicle) getOneObjectAtOffset (0, laneDifference, Vehicle.class);
        Vehicle downBack = (Vehicle) getOneObjectAtOffset (-direction * (int)(speed + getImage().getWidth()/2 + 4), laneDifference, Vehicle.class);
        Vehicle up = (Vehicle) getOneObjectAtOffset (0, -laneDifference, Vehicle.class);
        Vehicle upBack = (Vehicle) getOneObjectAtOffset (-direction * (int)(speed + getImage().getWidth()/2 + 4), -laneDifference, Vehicle.class);
        Vehicle aheadDown = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), laneDifference, Vehicle.class);
        Vehicle aheadUp = (Vehicle) getOneObjectAtOffset (direction * (int)(speed + getImage().getWidth()/2 + 4), -laneDifference, Vehicle.class);
        
        // Check if there's a vehicle ahead
        if(ahead !=null ){
            if(lane ==firstLane){//check current vehicle's line
                if(aheadDown ==null && down==null && downBack ==null){
                    setLocation(getX(),getY()+laneDifference);
                }//else exit
            }else if(lane ==lastLane){
                // Since police car is always in the bottom lane, it's not possible to have a police car above
                if(aheadUp instanceof Police) {
                    aheadUp =null; 
                }
                if(up instanceof Police) {
                    up =null;
                }
                if(upBack instanceof Police) {
                    upBack =null;
                }
                if(aheadUp ==null && up==null && upBack ==null){
                    setLocation(getX(),getY()-laneDifference);
                }// else exit, can not change line
            }else if(aheadDown ==null && down==null && downBack ==null&& aheadUp ==null && up==null && upBack==null ){
                int chance = (int)Math.random();
                if(chance%2==0){
                    setLocation(getX(),getY()+laneDifference);
                }else if(chance%2==1){
                    setLocation(getX(),getY()-laneDifference);
                }
            }else if(aheadDown ==null && down==null && downBack ==null){
                setLocation(getX(),getY()+laneDifference);
            }else if( aheadUp ==null && up==null && upBack==null ){
                setLocation(getX(),getY()-laneDifference);
            }//else exit, can not change line            
        }
        
    }

    /**
     * An accessor that can be used to get this Vehicle's speed. Used, for example, when a vehicle wants to see
     * if a faster vehicle is ahead in the lane.
     */
    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
}
