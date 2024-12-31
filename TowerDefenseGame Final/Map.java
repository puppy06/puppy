import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MapSelections here.
 * @author Jude kuo
 * @author Hank Shi
 * @version June 15 , 2023
 */
public abstract class Map extends Actor
{
    /**
     * Act - do whatever the MapSelections wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean isMouseHovering(){
        if(Greenfoot.mouseMoved(this)){
            return true;
        }else return false;
    }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
    public void enlarge(){
        System.out.println("Selected"+getImage().toString().substring(17,25));
        //setImage("Selected"+getImage().toString().substring(17,25));
    }
    public void shrink(){
        setImage(getImage().toString().substring(25));
    }
}
