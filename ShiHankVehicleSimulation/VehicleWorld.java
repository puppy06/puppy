import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Credits:
 * Sources:
 * https://www.kindpng.com/imgv/hwxRJTw_explosion-clipart-png-png-download-cartoon-explosion-transparent/
 * https://www.youtube.com/watch?v=Ni26LQOUc3Q
 * https://www.youtube.com/watch?v=SMILHbbADJw
 * https://www.youtube.com/watch?v=jX-0Wb_wQsY
 * https://www.youtube.com/watch?v=6OjUE5FAN0E
 * https://www.videvo.net/sound-effect/police-siren-variou-te2027601/253680/
 * https://www.youtube.com/watch?v=RYoGew1CRkE
 * https://www.youtube.com/watch?v=hcS-DAvsgyk
 * https://static.vecteezy.com/system/resources/previews/008/138/368/non_2x/set-of-different-city-skyscraper-buildings-free-vector.jpg
 * Description:
 * Terrorist tracks closest bus and sets off its bomb, destroying everything in its range and freezing the world for 3 seconds
 * Explosion inherits from the Effect class, which provides the freeze effect
 * Terrorist spawns from the bottom and citizen spawns from the top
 * It waits until a bus has the same x position as the terrorist before it teleports to the bus and blows it up
 * A police car travels on the bottom lane and arrests terrorists before they can blow up buses
 * If a slower vehicle is in front of the police car that vehicle will change lanes so the police can pass
 * Bus waits and picks up citizens and plays a brake sound effect everytime it stops
 * Car runs over citizens and plays a hit and run sound effect
 * Ambulance heals citizens that have been run over and those citizens play a sound effect after being healed
 * All vehicles except the police car change lanes if they're directly behind a slower vehicle
 * If a vehicle is on the top lane it will check if the lane below it is clear 
 * If a vehicle is on the bottom lane it will check if the lane above it is clear
 * If a vehicle is in the middle 3 lanes it will check if both lanes are clear
 * To be clear means the space adjacent to a blocked vehicle and the spaces behind and ahead of it are clear
 * 
 */
public class VehicleWorld extends World
{
    private GreenfootImage background;

    
    // Color Constants
    public static Color GREY_BORDER = new Color (108, 108, 108);
    public static Color GREY_STREET = new Color (88, 88, 88);
    public static Color YELLOW_LINE = new Color (255, 216, 0);

    // Instance variables / Objects
    private boolean twoWayTraffic, splitAtCenter;
    private int laneHeight, laneCount, spaceBetweenLanes;
    private int[] lanePositionsY;
    private VehicleSpawner[] laneSpawners;
    
    private GreenfootSound cityAmbience = new GreenfootSound("cityambience.mp3");
    int stopwatch = 0;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public VehicleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 
    
        setPaintOrder ( Citizen.class, Terrorist.class,Police.class,Explosion.class, Bus.class, Car.class, Ambulance.class);

        // set up background
        background = new GreenfootImage ("background01.png");
        setBackground (background);

        // Set critical variables
        laneCount = 6;
        laneHeight = 48;
        spaceBetweenLanes = 6;
        splitAtCenter = false;
        twoWayTraffic = false;

        
        // Init lane spawner objects 
        laneSpawners = new VehicleSpawner[laneCount];

        // Prepare lanes method - draws the lanes
        lanePositionsY = prepareLanes (this, background, laneSpawners, 222, laneHeight, laneCount, spaceBetweenLanes, twoWayTraffic, splitAtCenter);
    }

    public void act () {
        spawn();
        cityAmbience.playLoop();
    }

    private void spawn () {
        // Chance to spawn a car,ambulance or bus
        if (Greenfoot.getRandomNumber (60) == 0){
            int lane = Greenfoot.getRandomNumber(laneCount);
            if (!laneSpawners[lane].isTouchingVehicle()){
                int vehicleType = Greenfoot.getRandomNumber(3);
                if (vehicleType == 0){
                    addObject(new Car(laneSpawners[lane]), 0, 0);
                } else if (vehicleType == 1){
                    addObject(new Bus(laneSpawners[lane]), 0, 0);
                } else if (vehicleType == 2){
                    addObject(new Ambulance(laneSpawners[lane]), 0, 0);
                } 
            }
        }
        stopwatch++;
        // Police spawns at bottom every 1000 runs
        if(stopwatch%1000==0){
            addObject(new Police(laneSpawners[5]), 0, 0);  
        }
        // Citizen spawns at top every 50 runs
        if(stopwatch%50==0){
            int xSpawnLocation = Greenfoot.getRandomNumber (600) + 100; // random between 99 and 699, so not near edges
            addObject (new Citizen (1), xSpawnLocation, 175);
        }
        // 1 Terrorist spawns at the bottom one at the time every 500 runs
        if(stopwatch%500==0&&getObjects(Terrorist.class).isEmpty()){
            int xSpawnLocation = Greenfoot.getRandomNumber (600) + 100; // random between 99 and 699, so not near edges
            addObject(new Terrorist(-1), xSpawnLocation, 550);
        }
    }

    /**
     *  Given a lane number (zero-indexed), return the y position
     *  in the centre of the lane. (doesn't factor offset, so 
     *  watch your offset, i.e. with Bus).
     *  
     *  @param lane the lane number (zero-indexed)
     *  @return int the y position of the lane's center, or -1 if invalid
     */
    public int getLaneY (int lane){
        if (lane < lanePositionsY.length){
            return lanePositionsY[lane];
        } 
        return -1;
    }
    
    /**
     * Given a y-position, return the lane number (zero-indexed).
     * Note that the y-position must be valid, and you should 
     * include the offset in your calculations before calling this method.
     * For example, if a Bus is in a lane at y=100, but is offset by -20,
     * it is actually in the lane located at y=80, so you should send
     * 80 to this method, not 100.
     * 
     * @param y - the y position of the lane the Vehicle is in
     * @return int the lane number, zero-indexed
     * 
     */
    public int getLane (int y){
        for (int i = 0; i < lanePositionsY.length; i++){
            if (y == lanePositionsY[i]){
                return i;
            }
        }
        return -1;
    }
     /**
     * Given a y-position, return the lane number (zero-indexed).
     * rewrite the getLane(), check the vehicle's range instead of exact location to check the lane
     *
     * @param y - the y position of the lane the Vehicle is in
     * @return int the lane number, zero-indexed
     *
     */
    public int checkCurrentLane (int y){
        for (int i = 0; i < lanePositionsY.length; i++){
            if (y <= lanePositionsY[i]+10 && y>=lanePositionsY[i]-10){
                return i;
            }
        }
        return -1;
    }
      /**
     *
     *
     * @return int the y difference between two neigbour lines, this method can be used in change line function
     */
    public int getLaneDifference(){
        return this.laneHeight+this.spaceBetweenLanes;
    }
    public static int[] prepareLanes (World world, GreenfootImage target, VehicleSpawner[] spawners, int startY, int heightPerLane, int lanes, int spacing, boolean twoWay, boolean centreSplit, int centreSpacing)
    {
        // Declare an array to store the y values as I calculate them
        int[] lanePositions = new int[lanes];
        // Pre-calculate half of the lane height, as this will frequently be used for drawing.
        // To help make it clear, the heightOffset is the distance from the centre of the lane (it's y position)
        // to the outer edge of the lane.
        int heightOffset = heightPerLane / 2;

        // draw top border
        target.setColor (GREY_BORDER);
        target.fillRect (0, startY, target.getWidth(), spacing);

        // Main Loop to Calculate Positions and draw lanes
        for (int i = 0; i < lanes; i++){
            // calculate the position for the lane
            lanePositions[i] = startY + spacing + (i * (heightPerLane+spacing)) + heightOffset ;
            
            // draw lane
            target.setColor(GREY_STREET); 
            // the lane body
            target.fillRect (0, lanePositions[i] - heightOffset, target.getWidth(), heightPerLane);
            // the lane spacing - where the white or yellow lines will get drawn
            target.fillRect(0, lanePositions[i] + heightOffset, target.getWidth(), spacing);

            // Place spawners and draw lines depending on whether its 2 way and centre split
            if (twoWay && centreSplit){
                // first half of the lanes go rightward (no option for left-hand drive, sorry UK students .. ?)
                if ( i < lanes / 2){
                    spawners[i] = new VehicleSpawner(false, heightPerLane);
                    world.addObject(spawners[i], target.getWidth(), lanePositions[i]);
                } else { // second half of the lanes go leftward
                    spawners[i] = new VehicleSpawner(true, heightPerLane);
                    world.addObject(spawners[i], 0, lanePositions[i]);
                }

                // draw yellow lines if middle 
                if (i == lanes / 2){
                    target.setColor(YELLOW_LINE);
                    target.fillRect(0, lanePositions[i] - heightOffset - spacing, target.getWidth(), spacing);

                } else if (i > 0){ // draw white lines if not first lane
                    for (int j = 0; j < target.getWidth(); j += 120){
                        target.setColor (Color.WHITE);
                        target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                    }
                } 

            } else if (twoWay){ // not center split
                if ( i % 2 == 0){
                    spawners[i] = new VehicleSpawner(false, heightPerLane);
                    world.addObject(spawners[i], target.getWidth(), lanePositions[i]);
                } else {
                    spawners[i] = new VehicleSpawner(true, heightPerLane);
                    world.addObject(spawners[i], 0, lanePositions[i]);
                }

                // draw Grey Border if between two "Streets"
                if (i > 0){ // but not in first position
                    if (i % 2 == 0){
                        target.setColor(GREY_BORDER);
                        target.fillRect(0, lanePositions[i] - heightOffset - spacing, target.getWidth(), spacing);

                    } else { // draw dotted lines
                        for (int j = 0; j < target.getWidth(); j += 120){
                            target.setColor (YELLOW_LINE);
                            target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                        }
                    } 
                }
            } else { // One way traffic
                spawners[i] = new VehicleSpawner(true, heightPerLane);
                world.addObject(spawners[i], 0, lanePositions[i]);
                if (i > 0){
                    for (int j = 0; j < target.getWidth(); j += 120){
                        target.setColor (Color.WHITE);
                        target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                    }
                }
            }
        }
        // draws bottom border
        target.setColor (GREY_BORDER);
        target.fillRect (0, lanePositions[lanes-1] + heightOffset, target.getWidth(), spacing);

        return lanePositions;
    }

    /**
     * <p>The prepareLanes method is a static (standalone) method that takes a list of parameters about the desired roadway and then builds it.</p>
     * 
     * <p><b>Note:</b> So far, Centre-split is the only option, regardless of what values you send for that parameters.</p>
     *
     * <p>This method does three things:</p>
     * <ul>
     *  <li> Determines the Y coordinate for each lane (each lane is centered vertically around the position)</li>
     *  <li> Draws lanes onto the GreenfootImage target that is passed in at the specified / calculated positions. 
     *       (Nothing is returned, it just manipulates the object which affects the original).</li>
     *  <li> Places the VehicleSpawners (passed in via the array parameter spawners) into the World (also passed in via parameters).</li>
     * </ul>
     * 
     * <p> After this method is run, there is a visual road as well as the objects needed to spawn Vehicles. Examine the table below for an
     * in-depth description of what the roadway will look like and what each parameter/component represents.</p>
     * 
     * <pre>
     *                  <=== Start Y
     *  ||||||||||||||  <=== Top Border
     *  /------------\
     *  |            |  
     *  |      Y[0]  |  <=== Lane Position (Y) is the middle of the lane
     *  |            |
     *  \------------/
     *  [##] [##] [##| <== spacing ( where the lane lines or borders are )
     *  /------------\
     *  |            |  
     *  |      Y[1]  |
     *  |            |
     *  \------------/
     *  ||||||||||||||  <== Bottom Border
     * </pre>
     * 
     * @param world     The World that the VehicleSpawners will be added to
     * @param target    The GreenfootImage that the lanes will be drawn on, usually but not necessarily the background of the World.
     * @param spawners  An array of VehicleSpawner to be added to the World
     * @param startY    The top Y position where lanes (drawing) should start
     * @param heightPerLane The height of the desired lanes
     * @param lanes     The total number of lanes desired
     * @param spacing   The distance, in pixels, between each lane
     * @param twoWay    Should traffic flow both ways? Leave false for a one-way street (Not Yet Implemented)
     * @param centreSplit   Should the whole road be split in the middle? Or lots of parallel two-way streets? Must also be two-way street (twoWay == true) or else NO EFFECT
     * 
     */
    public static int[] prepareLanes (World world, GreenfootImage target, VehicleSpawner[] spawners, int startY, int heightPerLane, int lanes, int spacing, boolean twoWay, boolean centreSplit){
        return prepareLanes (world, target, spawners, startY, heightPerLane, lanes, spacing, twoWay, centreSplit, spacing);
    }

}
