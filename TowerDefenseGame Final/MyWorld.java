import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
/**
 * MyWorld is the world at where the user is able to place towers and stop the balloons from reaching the end of their path
 * 
 * @author Jude Kuo
 * @author Marcus Yeung
 * @author Abdullah Qureshi
 * @author Hank Shi
 * @version June 15 2023
 */
public class MyWorld extends World
{
    private int money;
    private GreenfootSound[] crateSounds;
    private int crateSoundIndex;
    private int balloonCount;
    private GreenfootSound[] dababy;
    private int dababyIndex;
    private GreenfootSound gameMusic, rushMusic, saved;
    private int pathXValue=0;
    private boolean spawning;// = false;
    private int enemyCount=10;
    private int Enemies;
    private double spawnChange;
    private double speedChange;
    private boolean gainedMoney;
    private int timer;
    private static int highScore = 0;
    // 2d array of binary values, 1 means that you place a path 
    //on the grid functions exactly like a map
    private HealthBar healthBar;
    private static boolean goldRush, drop;
    private int mapChoice;
    private MoneyDrop crate;
    private ShowMoney moneyDisplay;
    private ShowTimer timeDisplay;
    private ShowHighScore highScoreDisplay;
    private BombTowerIcon bombTowerIcon;
    private SniperTowerIcon sniperTowerIcon;
    private TackTowerIcon tackTowerIcon;
    private Menu menu;
    private TextShower text;
    private Scanner scan;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld(double spawnChange, double speedChange,int[][] map,int mapChoice)
    {    
        // Create a new world with 900x480 cells with a cell size of 1x1 pixels.

        super(900, 480, 1, false); 
        setPaintOrder(TextShower.class, MoneyDrop.class,GoldRush.class, Projectiles.class, Towers.class, Balloons.class, Path.class,ShowMoney.class,ShowTimer.class,ShowHighScore.class,GUI.class, BackgroundBlock.class, Menu.class);
        gameMusic = new GreenfootSound ("ClassicalPop.mp3");
        rushMusic = new GreenfootSound ("Chase.mp3");
        saved = gameMusic;
        text = new TextShower("Press S to start", 30);
        addObject(text,750,400);
        healthBar = new HealthBar();
        goldRush = false;
        drop = false;
        gainedMoney = false;
        crate = new MoneyDrop();
        this.spawnChange = spawnChange;
        this.speedChange = speedChange;
        money = 500;
        timer = 7200;
        spawning = false;
        moneyDisplay = new ShowMoney(money); 
        timeDisplay = new ShowTimer(timer/60);
        highScoreDisplay = new ShowHighScore(highScore);
        // Get high score from text file 
        try {
            scan = new Scanner(new File("HighScore.txt"));
            String score = scan.nextLine();
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
        addObject(moneyDisplay, 820, 270);
        addObject(highScoreDisplay,820, 315);
        addObject(timeDisplay, 820, 360);
        //prepare();
        this.mapChoice = mapChoice;
        if(mapChoice==1){
            setBackground("cell.jpg");
        }else if(mapChoice==2){
            setBackground("corkboard.jpg");
        }else if(mapChoice==3){
            setBackground("weave.jpg");
        }else if(mapChoice==4){
            setBackground("cell.jpg");  
        }else if(mapChoice==5){
            setBackground("corkboard.jpg");
        }else if(mapChoice==6){
            setBackground("weave.jpg");
        }else if(mapChoice==7){
            setBackground("cell.jpg");         
        }else if(mapChoice==8){
            setBackground("corkboard.jpg");
        }
        mapBuilder(map);
        crateSoundIndex = 0;
        dababy = new GreenfootSound[20];
        crateSounds = new GreenfootSound[20];
        for(int i=0;i<crateSounds.length;i++){
            dababy[i] = new GreenfootSound("sounds/dababy.wav");
            crateSounds[i]=new GreenfootSound("Hitting Wall.wav");
        }
        startedMusic();
    }
    /**
     * Stops the GoldRush event
     */
    public static void goldRush () {
        goldRush = false;
    }

    /**
     * Plays rushMusic in a loop
     */
    public void startedRushMusic(){
        rushMusic.playLoop();
    }

    /**
     * Stops rushMusic
     */
    public void stoppedRushMusic(){
        rushMusic.pause();
    }

    /**
     * Plays gameMusic in a loop
     */
    public void startedMusic(){
        gameMusic.playLoop();
    }

    /**
     * Stops playing all music
     */
    public void stopped(){
        gameMusic.pause();
        rushMusic.pause();
    }

    /**
     * Plays the saved music in a loop
     */
    public void started () {
        saved.playLoop();
    }

    public void act () {

        //Press "s" to start spawning balloons
        if (Greenfoot.isKeyDown("s")&&spawning == false){
            spawning = true;
            removeObject(text);
        }
        if (spawning){
            startWaves();
            timer--;
            if(timer%60==0){
                timeDisplay.updateTimer();
            }
            //Has a chance to drop a crate that gives 500 money when clicked somewhere on the map
            if(drop == false && Greenfoot.getRandomNumber(150)==0){
                drop = true;
            }
            if(drop == true){
                addObject(crate, Greenfoot.getRandomNumber(650)+50, -60);
                drop = false;
            }
            if(Greenfoot.mouseClicked(crate)){
                addMoney(500);
                gainedMoney = true;
                crateSounds[crateSoundIndex].play();
                crateSoundIndex++;
                removeObject(crate);
            }
            //Has a chance to add the GoldRush event into the world if it is not already true
            if (goldRush==false && Greenfoot.getRandomNumber(450) == 0){
                addObject (new GoldRush(240), 270, 240);
                stopped();
                startedRushMusic();
                saved = rushMusic;
                goldRush = true;
            }
            //If the GoldRush object is not in the world, stop the GoldRush event
            if (goldRush && getObjects(GoldRush.class).size() == 0){
                stoppedRushMusic();
                startedMusic();
                saved = gameMusic;
                goldRush = false;
            }
            // Changes to LoseWorld when pHealth reaches 0
            if(healthBar.getPhealth()<=0){
                stopped();
                Greenfoot.setWorld(new LoseWorld());
            }
            // Changes to WinWorld when the timer reaches 0
            if(timer <=0){
                stopped();
                UserInfo addHighScore = new UserInfo(this);
                Greenfoot.setWorld(new WinWorld(money));
            }
        }
        // Updates money 
        moneyDisplay.updateMoney();

        // Sets high score to highest amount of money gained so far                             
        if(spawning&&highScore<money&&gainedMoney){
            highScore = money;
            highScoreDisplay.updateHighScore();
        }
    }

    
    /**
     * @return boolean returns true if the GoldRush event is in effect
     */
    public static boolean isRushed () {
        return goldRush;
    }

    /**
     * getMoney returns the World's money
     * @return int returns the amount of money in the world
     */
    public int getMoney(){
        return money;
    }

    /**
     * Adds money to the worlds money
     * @param theMoney the amount of money in the world
     * @return int returns the amount of money in the world after adding more money
     */
    public int addMoney(int theMoney){
        money += theMoney;
        return money;
    }
    
    /**
     * getTimer returns the timer variable
     * @return int returns the timer variable
     */
    public int getTimer(){
        return timer;
    }

    /**
     * getHealthBar - returns the World's HealthBar.
     */
    public HealthBar getHealthBar(){
        return healthBar;
    }

    /**
     * Prepare - Adds what should be on the world before the game starts.
     */
    public int getMapChoice() {
        return mapChoice;
    }

    /**
     * Sets up the World's path and obstacles for each map using a 2d array
     * @param takes in the value of i or j which can be 0,1,2,3 , each
     * placing a different object. Rest of coded just places object without params
     */
    private void mapBuilder(int[][] map)
    {
        for(int i =0; i< 12; i++)
        {
            for (int j=0 ;j<8; j++)
            {
                if(map[j][i] == 1)
                {
                    addObject(new Path(), 30 + i * 60, 30+ j *60);
                }
                if(map[j][i] == 2)
                {
                    addObject(new Tree(), 30 + i * 60, 30+ j *60);
                }
                if(map[j][i] == 3)
                {
                    addObject(new Rock(), 30 + i * 60, 30+ j *60);
                }
            }
        }
        bombTowerIcon = new BombTowerIcon();
        addObject(bombTowerIcon,815,80);

        tackTowerIcon = new TackTowerIcon();
        addObject(tackTowerIcon,815,140);

        sniperTowerIcon = new SniperTowerIcon();
        addObject(sniperTowerIcon,815,200);

        addObject(healthBar,810,410);
        menu = new Menu(bombTowerIcon.getTowerPrice(),tackTowerIcon.getTowerPrice(),sniperTowerIcon.getTowerPrice());
        addObject(menu,810,270);
    }

    
    // Mr.Cohen's code below
    public static float getDistance (Actor a, Actor b)
    {
        double distance;
        double xLength = a.getX() - b.getX();
        double yLength = a.getY() - b.getY();
        distance = Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        return (float)distance;
    }

    /**
     * Has a chance to spawn a balloon depending on the spawnChange variable and spawns a type of balloon depending on the number generated by spawnNumber
     */
    public void startWaves(){
        // Has a chance to spawn a balloon depending on the spawnChange variable
        //Spawns a type of balloon depending on the number generated by spawnNumber
        int spawnRate = Greenfoot.getRandomNumber ((int)(100*spawnChange));
        int spawnNumber = Greenfoot.getRandomNumber (24);
        if(spawnRate == 0){
            if (spawnNumber == 5 || spawnNumber == 6|| spawnNumber == 13|| spawnNumber == 19) {
                BlueBalloons blueBalloons2 = new BlueBalloons(speedChange);
                addObject(blueBalloons2,90,3);
            }
            if (spawnNumber == 4|| spawnNumber == 7|| spawnNumber == 14|| spawnNumber == 20){
                GreenBalloons greenBalloons2 = new GreenBalloons(speedChange);
                addObject(greenBalloons2,90,3);
            }
            if (spawnNumber == 3|| spawnNumber == 9|| spawnNumber == 15|| spawnNumber == 21){
                PinkBalloons pinkBalloons2 = new PinkBalloons(speedChange);
                addObject(pinkBalloons2,90,3);
            }
            if (spawnNumber == 2){
                Cohen rainbowBalloons2 = new Cohen(speedChange);
                addObject(rainbowBalloons2,90,3);
                dababy[dababyIndex].play();
                dababyIndex++;
            }
            if (spawnNumber == 1|| spawnNumber == 11|| spawnNumber == 17|| spawnNumber == 23){
                RedBalloons redBalloons2 = new RedBalloons(speedChange);
                addObject(redBalloons2,90,3);
            }
            if (spawnNumber == 0|| spawnNumber == 12 || spawnNumber == 18|| spawnNumber == 24){
                YellowBalloons yellowBalloons2 = new YellowBalloons(speedChange);
                addObject(yellowBalloons2,90,3);
            }
        }
    }

    /**
     * getHighScore returns the highscore
     * @return int returns the highscore variable
     */
    public int getHighScore(){
        return highScore;
    }

    /**
     * getSpawning returns spawning
     * @return boolean returns whether spawning is true or false
     */
    public boolean getSpawning(){
        return spawning;
    }

    /**
     * gainedMoneyYet returns the gainedMoney variable
     * @return boolean returns whether or not money has been gained
     */
    public boolean gainedMoneyYet(){
        return gainedMoney;
    }

    /**
     * hasGainedMoney sets the gained money variable to true
     */
    public void hasGainedMoney(){
        gainedMoney = true;
    }
}
