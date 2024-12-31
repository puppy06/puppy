import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends GUI
{
    //Total health of the player
    private int phealth;
    
    //Image Dimensions
    private int width;
    private int height;
    
    private int inc;
    

    
    /**
     * Contructor for health bar.
     */
    public HealthBar(){
        //Setting values
        phealth = 60;
        width = 150;
        height = 40;
        
        inc = width/height;
        
        //Loading Image
        update();
        
        
    }
    /**
     * Act - do whatever the Healthbar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        update();
    }
    
    /**
     * getHealth - Get the health value.
     */
    public int getPhealth(){
        return phealth;
    }
    
    /**
     * update - Updates the appearance of the health bar.
     */
    public void update(){
        image = new GreenfootImage(width, height);
        image.setColor(Color.RED);
        image.fillRect(1, 1, width, height-1);
        image.setColor(Color.GREEN);
        image.fillRect(0, 1, phealth*inc, height-1);
        setImage(image);
    }
    
    /**
     * @param damage - Reduces the health.
     */
    public void damage(int health){
        phealth -= health;
    }
}
