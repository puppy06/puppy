import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Superclass of all Towers 
  * @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public abstract class Towers extends Actor
{
    /**
     * Act - do whatever the Towers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected final int transparency = 150;
    protected GreenfootImage image;
    protected double attackSpeed;
    protected double fireRate;
    protected boolean shooting = true;
    protected int range;
    protected int direction;
    protected boolean isPlaced;
    protected abstract void fireProjectile(double attackSpeed);
    protected GreenfootSound[] nope;
    protected int nopeIndex;
    protected Balloons balloonInRange;
    public Towers(){
        nope = new GreenfootSound[20];
        nopeIndex = 0;
        for(int i = 0;i < 20;i++){
            nope[i] = new GreenfootSound("nope.wav");
        }
    }
    public void act()
    {
        //set domain and range where the tower can attack
        
    }
    
    public void updateTransparency(){
        GreenfootImage tower = getImage();
        tower.setTransparency(255);
        setImage(tower);
    }
    public int getDirection(){
        return direction;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }
    
    public void placing(){
        MyWorld world = (MyWorld) getWorld();
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if(mouse == null){
            return;
        }
        
        if(isPlaced == false){
            setLocation((mouse.getX()/60)*60+30, (mouse.getY()/60)*60+30);
            if(Greenfoot.mouseClicked(null)&&(getOneIntersectingObject(Path.class) != null
            ||getOneIntersectingObject(Obstacle.class) != null||getOneIntersectingObject(Menu.class) != null)){
                nope[nopeIndex].play();
                nopeIndex++;
                
            }else if(Greenfoot.mouseClicked(null)&& getOneIntersectingObject(Path.class) == null && getOneIntersectingObject(Menu.class) == null 
             && getOneIntersectingObject(Obstacle.class) == null ){
                updateTransparency();
                isPlaced = true;
            }
            return;       
        }
    }
    
    public void setTrue(){
        isPlaced = true;
    }
    
    public void aim(){
        balloonInRange = null;
        ArrayList<Balloons> list = (ArrayList<Balloons>) getObjectsInRange(range, Balloons.class);
        if(!list.isEmpty() && list.get(0) != null){
            balloonInRange = list.get(0);
            turnTowards(list.get(0).getX(), list.get(0).getY());
        }
    }
    
    public int getRange(){
        return range;
    }
    
    
}
