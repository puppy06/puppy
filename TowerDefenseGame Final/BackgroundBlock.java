import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BackgroundBlock here.
 * 
 * @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 , 2023
 */
public abstract class BackgroundBlock extends Actor
{
    protected GreenfootImage icon;
    protected static int counter;
    protected int towerPrice;
    protected Font font2;
    private GreenfootSound[] funnyUp;
    private int funnyUpIndex;
    private GreenfootSound firstStartSound;
    private GreenfootSound[] startSound;
    private int startSoundIndex;
    public BackgroundBlock(){
        counter = 0;
        funnyUpIndex = 0;
        startSoundIndex = 0;
        funnyUp = new GreenfootSound[20];
        startSound = new GreenfootSound[20];
        firstStartSound = new GreenfootSound("sounds/start.wav");
        for(int i = 0;i < 20;i++){
            startSound[i] = new GreenfootSound("sounds/start.wav");
            funnyUp[i] = new GreenfootSound("sounds/get your money up not your funny up.wav");
        }
        firstStartSound.play();
    }
    /**
     * Act - do whatever the BackgroundBlock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void scale()
    {
        getImage().scale(60,60);
    }
    public void act()
    {
        isClicked();
    }
    /**
     * Checks if a Tower icon has been clicked
     */
    public void isClicked(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        MyWorld world = (MyWorld) getWorld();
        if(mouse != null ){
            // If mouse has been clicked
            if(Greenfoot.mouseClicked(this)){
                // Checks if game has started
                if(world.getSpawning()){
                    // Checks if user has enough money to purchase Tower
                    if(world.getMoney()>=towerPrice){
                        create(world);
                        counter++;
                        world.addMoney(-towerPrice);
                    }else if(world.getMoney()<towerPrice){
                        // Plays the sound 'Get your money up not your funny up'
                        funnyUp[funnyUpIndex].play();
                        funnyUpIndex++;
                        if(funnyUpIndex>funnyUp.length-1){
                            funnyUpIndex=0;
                        }
                    }
                }else {
                    // Plays start sound effect
                    startSound[startSoundIndex].play();
                    startSoundIndex++;
                    if(startSoundIndex>20){
                        startSoundIndex = 0;
                    }
                }
            }
        }
    }
    /**
     * Gets how much a tower costs
     * @return int returns the price of a Tower
     */
    public int getTowerPrice(){
        return towerPrice;
    }
    /**
     * Allows user to change price of Tower
     * @param theMoney the amount of money in the world
     */
    public void setTowerPrice(int towerPrice){
        this.towerPrice = towerPrice;
    }
    public abstract void create(MyWorld world);

}
