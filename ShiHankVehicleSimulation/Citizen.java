import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Citizen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Citizen extends Pedestrian
{
    private GreenfootSound[] thankYouSounds;
    private int thankYouSoundsIndex;
    private GreenfootSound[] carHitSounds;
    private int carHitSoundsIndex;
    public Citizen(int direction) {
        super(direction);
        
        thankYouSoundsIndex = 0;
        thankYouSounds = new GreenfootSound[20];
        carHitSounds = new GreenfootSound[20];
        for (int i = 0;i < thankYouSounds.length;i++){
            thankYouSounds[i] = new GreenfootSound("thankyou.wav");
            carHitSounds[i] = new GreenfootSound("hitandrun.wav");
        }
        
    }
    /**
     * Act - Pauses to let non-Bus Vehicles go. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // If there is a non-bus vehicle in front it will pause
        if (awake){
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null) {
                setLocation (getX(), getY() + (int)(speed*direction));
            }
            if (getY() > 550) {
                getWorld().removeObject(this);
            }
        } else if (!awake){
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null) {
                setLocation (getX(), getY() + (int)(speed*direction));
            }           
        }
    }
    /**
     * Method to cause this Citizen to become knocked down - stop moving, turn onto side
     */
    public void knockDown () {
        if (getRotation()==0){
            carHitSounds[carHitSoundsIndex].play();
            carHitSoundsIndex++;
            if (carHitSoundsIndex>carHitSounds.length-1) {
                carHitSoundsIndex = 0;
            }
            speed = 0;
            setRotation (90);
            awake = false;
        }
    }

    /**
     * Method to allow a downed Citizen to be healed
     */
    public void healMe () {
        speed = maxSpeed;
        if (getRotation()==90){
            thankYouSounds[thankYouSoundsIndex].play();
            thankYouSoundsIndex++;
            if (thankYouSoundsIndex>thankYouSounds.length-1){
                thankYouSoundsIndex = 0;
            }
            setRotation (0);
            awake = true;         
        }
    }
    public void pause () {
        speed = 0;
    }
    public void resume () {
        speed = maxSpeed;
    }
    public boolean isAwake () {
        return awake;
    }
}
