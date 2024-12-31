import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a textbox that is used to store text in the InstrcutionWorld
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class TextShower extends Actor
{
    /**
     * Creates a box to draw a number into to show the map number in the MapSelectionWorld
     * @param String - the text
     * @param int - the size of the text
     */
    public TextShower(String str, int fontSize)
    {
        GreenfootImage box = new GreenfootImage (900, 100);
        //Draws the number in yellow
        box.setColor(Color.YELLOW);
        //Draws the number with the IMPACT font and is neither bolded or in italics, with a size font that the user can choose
        box.setFont(new Font ("IMPACT", false, false, fontSize));
        box.drawString(str,20,80);
        setImage(box);
    }
}
