import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays price of Towers
 * @author Jude kuo
 * @author Marcus Yeung
 * @version June 15 , 2023
 */
public class Menu extends Actor
{
    private GreenfootImage image;
    private MyWorld world;
    private Font font;
    private Font font2;
    /**
     * Constructor of the Menu class
     * @param bombTowerPrice - how much a bomb tower costs
     * @param tacktowerPrice - how much a tack tower costs
     * @param sniperTowerrice - how much a sniper tower costs
     */
    public Menu(int bombTowerPrice, int tackTowerPrice, int sniperTowerPrice)
    {
        // Displays Tower prices on menu
        getImage().scale(180,560);
        image = getImage();
        font = new Font("SANS_SERIF", true, false, 30);
        font2 = new Font("SANS_SERIF", true, false, 20);
        image.setColor(Color.WHITE);
        
        image.setFont(font);
        image.drawString("Menu", getImage().getWidth()/4,45);
        image.setFont(font2);
        image.drawString("$"+bombTowerPrice, getImage().getWidth()/4-35,100);
        image.drawString("$"+tackTowerPrice, getImage().getWidth()/4-35,160);
        image.drawString("$"+sniperTowerPrice, getImage().getWidth()/4-35,220);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}
