import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Used in the MapSelectionWorld to show the map number above the map.
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class CharShower extends Actor
{
    /**
     * Creates a box to draw a number into to show the map number in the MapSelectionWorld
     */
    public CharShower(int digit)
    {
        GreenfootImage box = new GreenfootImage (30, 20);
        //Draws the number in yellow
        box.setColor(Color.YELLOW);
        //Draws the number with the IMPACT font and is neither bolded or in italics, with a size font of 18
        box.setFont(new Font ("IMPACT", false, false, 18));
        box.drawString(""+digit,0,18);
        setImage(box);
    }
}
