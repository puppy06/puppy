import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Effetcs here.
 * @author Marcus Yeung
 * @version June 15 , 2023
 */
public class Effects extends Actor
{
    protected GreenfootImage image;
    
    protected void fade (int timeLeft, int fadeTime){
        double percent = timeLeft / (double)fadeTime;
        int newTransparency = (int)(percent * 255);
        image.setTransparency (newTransparency);
        
    }
    
}
