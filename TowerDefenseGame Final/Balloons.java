import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Balloons is the superclass for the enimies that will spawn in MyWorld and will move in a set path until they have reached the end or have been popped
 * 
 * @author (Jude Kuo, Marcus Yeung) 
 * @version (June 15 2023)
 */
public abstract class Balloons extends SuperSmoothMover
{
    protected static boolean slowedDown = false;
    protected static boolean rushed = false;
    protected boolean projectiled, canAttack;
    protected int cash;
    protected int baseCash;
    protected double health;
    protected double speed;
    protected int damage;
    protected int balloonX;
    protected int balloonY; 
    private Coordinate currentDestination;
    private ArrayList<Coordinate> destinations;
    private int actCount, redrawFrequency;
    private boolean targeted = false;
    protected GreenfootImage image;
    protected boolean isDestroyed = false;
    private GreenfootSound[] popSound;
    private int popSoundIndex;
    private GreenfootSound babyGiggle;
    private int babyGiggleIndex;
    private int mapChoice;
    protected boolean isBoss;
    protected GreenfootImage[] balloonsOrder = {new GreenfootImage("images/Red.png"),new GreenfootImage("images/Blue.png"),
    new GreenfootImage("images/Green.png"),new GreenfootImage("images/Yellow.png"),new GreenfootImage("images/Pink.png"),
    new GreenfootImage("images/Rainbow.png")}; 
    
    /**
     * Constructor for subclasses of class Balloons.
     * 
     */
    public Balloons(){
        actCount = 0;
        redrawFrequency = 8;
        projectiled = false;
        canAttack = true;
        isBoss = false;
        destinations = new ArrayList<Coordinate>();  
        popSoundIndex = 0;
        babyGiggleIndex = 0;
        popSound = new GreenfootSound[20];
        babyGiggle = new GreenfootSound("sounds/babygiggle.wav");
        for(int i = 0;i < 20;i++){
            popSound[i] = new GreenfootSound("sounds/pop.wav");
        }
        
    }

    /**
     * Add the map the user chose to the world
     * @ param World - the world that the object is added to
     */
    public void addedToWorld(World w){
        MyWorld world = (MyWorld)w;
        this.mapChoice = world.getMapChoice();
        if(mapChoice==1){
            destinations.add(new Coordinate(90,30));
            destinations.add(new Coordinate(90,90));
            destinations.add(new Coordinate(150,90));
            destinations.add(new Coordinate(150,390));
            destinations.add(new Coordinate(270,390));
            destinations.add(new Coordinate(270,30));
            destinations.add(new Coordinate(390,30));
            destinations.add(new Coordinate(390,90));
            destinations.add(new Coordinate(510,90));
            destinations.add(new Coordinate(510,270));
            destinations.add(new Coordinate(630,270));
            destinations.add(new Coordinate(630,330));
            destinations.add(new Coordinate(690,330));
            destinations.add(new Coordinate(690,510));
        }else if(mapChoice==2){
            destinations.add(new Coordinate(1*60 - 30,1*60- 30));
            destinations.add(new Coordinate(1*60 - 30,3*60- 30));
            destinations.add(new Coordinate(3*60 - 30,3*60- 30));
            destinations.add(new Coordinate(3*60 - 30,1*60- 30));
            destinations.add(new Coordinate(5*60 - 30,1*60- 30));
            destinations.add(new Coordinate(5*60 - 30,5*60- 30));
            destinations.add(new Coordinate(1*60 - 30,5*60- 30));
            destinations.add(new Coordinate(1*60 - 30,7*60- 30));      
            destinations.add(new Coordinate(10*60 - 30,7*60- 30));
            destinations.add(new Coordinate(10*60 - 30,4*60- 30));
            destinations.add(new Coordinate(12*60 - 30,4*60- 30));
            destinations.add(new Coordinate(690, 4200));
        }else if(mapChoice==3){
            destinations.add(new Coordinate(1*60 - 30,1*60- 30));
            destinations.add(new Coordinate(5*60 - 30,1*60- 30));
            destinations.add(new Coordinate(5*60 - 30,3*60- 30));
            destinations.add(new Coordinate(7*60 - 30,3*60- 30));
            destinations.add(new Coordinate(7*60 - 30,5*60- 30));
            destinations.add(new Coordinate(5*60 - 30,5*60- 30));
            destinations.add(new Coordinate(5*60 - 30,7*60- 30));
            destinations.add(new Coordinate(7*60 - 30,7*60- 30));
            destinations.add(new Coordinate(7*60 - 30,8*60- 30));
            destinations.add(new Coordinate(10*60 - 30,8*60- 30));
            destinations.add(new Coordinate(10*60 - 30,6*60- 30));
            destinations.add(new Coordinate(12*60 - 30,6*60- 30));
            destinations.add(new Coordinate(690, 4200));
        }else if(mapChoice==4){
            destinations.add(new Coordinate(1*60 - 30,1*60- 30));
            destinations.add(new Coordinate(1*60 - 30,2*60- 30));
            destinations.add(new Coordinate(2*60 - 30,2*60- 30));
            destinations.add(new Coordinate(2*60 - 30,8*60- 30));
            destinations.add(new Coordinate(4*60 - 30,8*60- 30));
            destinations.add(new Coordinate(4*60 - 30,7*60- 30));
            destinations.add(new Coordinate(10*60 - 30,7*60- 30));
            destinations.add(new Coordinate(10*60 - 30,5*60- 30));
            destinations.add(new Coordinate(4*60 - 30,5*60- 30));
            destinations.add(new Coordinate(4*60 - 30,3*60- 30));
            destinations.add(new Coordinate(12*60 - 30,3*60- 30));
            destinations.add(new Coordinate(690, 550));
        }else if(mapChoice==5){
            destinations.add(new Coordinate(1*60 - 30,1*60- 30));
            destinations.add(new Coordinate(1*60 - 30,2*60- 30));
            destinations.add(new Coordinate(4*60 - 30,2*60- 30));
            destinations.add(new Coordinate(4*60 - 30,4*60- 30));
            destinations.add(new Coordinate(1*60 - 30,4*60- 30));
            destinations.add(new Coordinate(1*60 - 30,8*60- 30));
            destinations.add(new Coordinate(3*60 - 30,8*60- 30));
            destinations.add(new Coordinate(3*60 - 30,6*60- 30));
            destinations.add(new Coordinate(5*60 - 30,6*60- 30));
            destinations.add(new Coordinate(5*60 - 30,8*60- 30));
            destinations.add(new Coordinate(7*60 - 30,8*60- 30));
            destinations.add(new Coordinate(7*60 - 30,1*60- 30));
            destinations.add(new Coordinate(9*60 - 30,1*60- 30));
            destinations.add(new Coordinate(9*60 - 30,8*60- 30));
            destinations.add(new Coordinate(11*60 - 30,8*60- 30));
            destinations.add(new Coordinate(11*60 - 30,2*60- 30));
            destinations.add(new Coordinate(69420, 90));
        }else if(mapChoice==6){
            destinations.add(new Coordinate(1*60 - 30,1*60- 30));
            destinations.add(new Coordinate(1*60 - 30,2*60- 30));
            destinations.add(new Coordinate(11*60-30,90));
            destinations.add(new Coordinate(11*60-30,69420));
        }else if(mapChoice==7){
            destinations.add(new Coordinate(1*60 - 30,1*60- 30));
            destinations.add(new Coordinate(2*60 - 30,1*60- 30));
            destinations.add(new Coordinate(2*60 - 30,2*60- 30));
            destinations.add(new Coordinate(3*60 - 30,2*60- 30));
            destinations.add(new Coordinate(3*60 - 30,3*60- 30));
            destinations.add(new Coordinate(4*60 - 30,3*60- 30));
            destinations.add(new Coordinate(4*60 - 30,4*60- 30));
            destinations.add(new Coordinate(5*60 - 30,4*60- 30));
            destinations.add(new Coordinate(5*60 - 30,5*60- 30));
            destinations.add(new Coordinate(6*60 - 30,5*60- 30));
            destinations.add(new Coordinate(6*60 - 30,6*60- 30));
            destinations.add(new Coordinate(7*60 - 30,6*60- 30));
            destinations.add(new Coordinate(7*60 - 30,7*60- 30));
            destinations.add(new Coordinate(8*60 - 30,7*60- 30));
            destinations.add(new Coordinate(8*60 - 30,8*60- 30)); 
            destinations.add(new Coordinate(8*60 - 30,69420)); 
        }else if(mapChoice==8){
            destinations.add(new Coordinate(1*60 - 30,1*60- 30));
            destinations.add(new Coordinate(11*60 - 30,1*60- 30));
            destinations.add(new Coordinate(11*60 - 30,3*60- 30));
            destinations.add(new Coordinate(2*60 - 30,3*60- 30));
            destinations.add(new Coordinate(2*60 - 30,5*60- 30));
            destinations.add(new Coordinate(11*60 - 30,5*60- 30));
            destinations.add(new Coordinate(11*60 - 30,7*60- 30));
            destinations.add(new Coordinate(2*60 - 30,7*60- 30));
            destinations.add(new Coordinate(90, 550));
        }

    }

    public void act()
    {
        // Add your action code here.
        actCount++;

        // If I don't have a destination, and there are destinations
        // in my list, set my current destination to the next one in my list
        if (currentDestination == null && destinations.size() > 0){
            currentDestination = getNextDestination();
        }
        
        move (speed);
        //Every balloon that is on the screen while rushed is true will drop double the money
        if(rushed){
            cash=baseCash*2;
        }
        else{
            cash = baseCash;
        }

        // If I have a destination, move towards it
        if (currentDestination != null){
            moveTowardsDestination();
        }

        // Call the redrawPath method in World and pass it my current coordinate and list
        // of destinations
        //setLocation (570, getY());
        reachedEnd();

    }

    /**
     * If the SnowStorm event is happening slow down all balloons
     */
    public static void stormMe(){
        if(isStorming()==true){
            slowedDown = true;
        }
        if(isStorming()==false){
            slowedDown = false;
        }
    }

    /**
     * returns whether the Snowstorm event is currently happening
     * @return boolean returns true if the Snowstorm event is happening or false if it isn't happening
     */
    public static boolean isStorming(){
        return slowedDown;
    }

    /**
     * if the GoldRush event is happening apply rushed to all balloons
     */
    public static void rushMe(){
        rushed = true;
        if(isDoubled()==false){
            rushed = false;
        }
    }

    /**
     * returns whether the GoldRush event is currently happening
     * @return boolean returns true if the GoldRush event is happening or false if it isn't happening
     */
    public static boolean isDoubled(){
        return rushed;
    }

    /**
     * returns the cash variable that all balloons have
     * @return int returns the cash variable
     */
    public int getCash(){
        return cash;
    }

    private void moveTowardsDestination(){
        // Use method to figure out exact distance between self and destination
        double distanceToDestination = getPathDistance (new Coordinate(getX(), getY()), currentDestination);
        // If I'm so close to my destination that I'm about to overshoot it, set my
        // location to the exact destination location instead of using calculated movement
        if (distanceToDestination < speed){
            setLocation (currentDestination.getX(), currentDestination.getY());
            destinations.remove(currentDestination);
            currentDestination = null;
        } else { // as long as I'm not close
            // Turn towards and move towards my destination
            turnTowards (currentDestination.getX(), currentDestination.getY());

        }
    }

    /**
     * Accessor to allow the World to get the Player's path to redraw it
     */
    public ArrayList<Coordinate> getPath(){
        return destinations;
    }

    /**
     * Mutator to allow the World to add a destination to the Player's path
     * (which is triggered by a mouse click in the World)
     * 
     * @param c     The Coordinate to add to the player's destinations
     */
    public void addDestination (Coordinate c){
        destinations.add(c);
    }

    /**
     * Helper method to get the next destination in the List. In a separate method
     * in case we want to add other things to happen when getting next destination
     */
    private Coordinate getNextDestination () {
        return destinations.get(0);
    }

    public void checkHit(int damage){
        if(isTouching(Projectiles.class)){
            health-=damage;
            this.damage --;
        }

    }

    /**
     * plays the popSound
     */
    public void pop(){
        popSound[popSoundIndex].play();
        popSoundIndex++;
        if(popSoundIndex>popSound.length-1){
            popSoundIndex=0;
        }
        getWorld().removeObject(this);
    }

    /**
     * Checks if balloon makes it through world
     */
    public void reachedEnd(){
        MyWorld world = (MyWorld) getWorld();
        if(getY() > world.getHeight()+getImage().getHeight()){
            HealthBar hb = world.getHealthBar();
            hb.damage(damage);
            hb.update();
            babyGiggle.play();
            //babyGiggleIndex++;
            
            world.removeObject(this);
            return;
        }
    }

    public boolean isReachedEnd() {
        MyWorld world = (MyWorld) getWorld();
        if(getY()>world.getHeight()+getImage().getHeight()){
            return true;
        }
        return false;
    }

    public void hitEnemy (int damage) {
        health-=damage;
        if(health<=0){
            MyWorld w = (MyWorld)getWorld();
            //Will have a 20% chance to drop an item for the player to pick up when destroyed
            w.removeObject(this);
        }
    }

    public static double getPathDistance (Coordinate a, Coordinate b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return distance;
    }

    public double getHealth(){
        return health;
    }

    public void decreaseHealth(){
        health--;
    }

    public GreenfootImage[] getBalloonsList(){
        return balloonsOrder;
    }
    public boolean getBoss(){
        return isBoss;
    }
}
