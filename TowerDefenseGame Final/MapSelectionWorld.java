import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A world that is used to select the map the user want to play on
 * 
 * @author Jude Kuo
 * @author Marcus Yeung
 *
 * @version June 15 2023
 */
public class MapSelectionWorld extends World
{
    private GreenfootSound[] buttonSounds;
    private int buttonSoundIndex;
    private FirstMap mapOne;
    private SecondMap mapTwo;
    private ThirdMap mapThree;
    private FourthMap mapFour;
    private FifthMap mapFive;
    private SixthMap mapSix;
    private SeventhMap mapSeven;
    private EighthMap mapEight;
    private double spawnChange;
    private double speedChange;
    private int userChoice;
    private Button button1, button2;
    private CharShower char1,char2,char3,char4,char5,char6,char7,char8;
    private GreenfootSound music;
    
    /**
     * Constructor for objects of class MapSelectionWorld.
     * @ param spawnChange changes the chance a balloon has to spawn
     * @ param speedChange changes the speed at which the balloon moves
     */
    public MapSelectionWorld(double spawnChange,double speedChange)
    {    
        // Create a new world with 900x480 cells with a cell size of 1x1 pixels.
        super(900, 480, 1);
        this.spawnChange = spawnChange;
        this.speedChange = speedChange;
        mapOne = new FirstMap();
        mapTwo = new SecondMap();
        mapThree = new ThirdMap();
        mapFour = new FourthMap();
        mapFive = new FifthMap();
        mapSix = new SixthMap();
        mapSeven = new SeventhMap();
        mapEight = new EighthMap();
        userChoice = 1;
        //Adds buttons to switch out maps on the screen
        button1 = new Button();
        button1.setRotation(180);
        button2 = new Button();
        addObject(button1, 400, 400);
        addObject(button2, 500, 400);
        char1 = new CharShower(1);
        char2 = new CharShower(2);
        char3 = new CharShower(3);
        char4 = new CharShower(4);
        char5 = new CharShower(5);
        char6 = new CharShower(6);
        char7 = new CharShower(7);
        char8 = new CharShower(8);
        music = new GreenfootSound ("ThemeofAgrual.mp3");
        buttonSoundIndex = 0;
        buttonSounds = new GreenfootSound[20];
        for(int i=0;i<buttonSounds.length;i++){
            buttonSounds[i]=new GreenfootSound("mouseClick.wav");
        }
    }

    /**
     * Starts the music 
     */
    public void started(){
        music.playLoop();
    }
    
    /**
     * Stops the music
     */
    public void stopped(){
        music.stop();
    }

    public void act(){
        started();
        //Allows the user to switch maps depending on the userChoice variable which changes based on the button pressed
        if(userChoice>1&&Greenfoot.mouseClicked(button1)){
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
            userChoice--;
        }
        if(userChoice<8&&Greenfoot.mouseClicked(button2)){
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
            userChoice++;
        }
        if(userChoice==1&&Greenfoot.mouseClicked(button1)){
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
            userChoice=8;
        }
        if(userChoice==8&&Greenfoot.mouseClicked(button2)){
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
            userChoice=1;
        }
        if(userChoice == 1){
            clearAll();
            addObject(char1, 150, 120);
            addObject(char2, 450, 120);
            addObject(char3, 750, 120);
            addObject(mapOne,150,240);
            addObject(mapTwo,450,240);
            addObject(mapThree,750,240);
        }
        if(userChoice == 2){
            clearAll();
            addObject(char2, 150, 120);
            addObject(char3, 450, 120);
            addObject(char4, 750, 120);
            addObject(mapTwo,150,240);
            addObject(mapThree,450,240);
            addObject(mapFour,750,240);
        }
        if(userChoice == 3){
            clearAll();
            addObject(char3, 150, 120);
            addObject(char4, 450, 120);
            addObject(char5, 750, 120);
            addObject(mapThree,150,240);
            addObject(mapFour,450,240);
            addObject(mapFive,750,240);
        }
        if(userChoice == 4){
            clearAll();
            addObject(char4, 150, 120);
            addObject(char5, 450, 120);
            addObject(char6, 750, 120);
            addObject(mapFour,150,240);
            addObject(mapFive,450,240);
            addObject(mapSix,750,240);
        }
        if(userChoice == 5){
            clearAll();
            addObject(char5, 150, 120);
            addObject(char6, 450, 120);
            addObject(char7, 750, 120);
            addObject(mapFive,150,240);
            addObject(mapSix,450,240);
            addObject(mapSeven,750,240);
        }
        if(userChoice == 6){
            clearAll();
            addObject(char6, 150, 120);
            addObject(char7, 450, 120);
            addObject(char8, 750, 120);
            addObject(mapSix,150,240);
            addObject(mapSeven,450,240);
            addObject(mapEight,750,240);
        }
        if(userChoice == 7){
            clearAll();
            addObject(char7, 150, 120);
            addObject(char8, 450, 120);
            addObject(char1, 750, 120);
            addObject(mapSeven,150,240);
            addObject(mapEight,450,240);
            addObject(mapOne,750,240);
        }
        if(userChoice == 8){
            clearAll();
            addObject(char8, 150, 120);
            addObject(char1, 450, 120);
            addObject(char2, 750, 120);
            addObject(mapEight,150,240);
            addObject(mapOne,450,240);
            addObject(mapTwo,750,240);
        }
        if(Greenfoot.mouseClicked(mapOne)) {
            // Handle mouse click if needed
            stopped();
            int map [] [] ={{1,1,0,0,1,1,1,0,0,0,2,0},
                    {0,1,1,2,1,0,1,1,1,0,3,0},
                    {0,0,1,0,1,0,0,0,1,0,0,0},
                    {0,0,1,0,1,0,0,0,1,0,0,0},
                    {0,0,1,0,1,0,0,0,1,1,1,2},
                    {0,2,1,0,1,0,0,0,0,0,1,1},
                    {0,0,1,1,1,0,0,2,0,0,0,1},
                    {0,0,0,0,0,0,0,0,0,3,0,1},};
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map,1));
        }else if(Greenfoot.mouseClicked(mapTwo)) {
            stopped();
            int map2[][] = {{1,0,1,1,1,0,0,0,0,0,0,0}, 
                    {1,0,1,2,1,0,0,0,3,0,2,0}, 
                    {1,1,1,0,1,0,0,0,0,0,2,0},
                    {0,0,0,0,1,0,0,0,0,1,1,1},
                    {1,1,1,1,1,0,0,0,0,1,0,1},
                    {1,0,0,0,0,0,0,0,0,1,0,1},
                    {1,1,1,1,1,1,1,1,1,1,0,1},
                    {0,0,0,0,0,0,0,0,0,0,0,1},};
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map2,2));           
        }else if(Greenfoot.mouseClicked(mapThree)) {
            stopped();
            int map3[][] = {{1,1,1,1,1,0,0,0,0,0,0,0}, 
                    {0,0,0,0,1,0,0,0,2,0,0,0}, 
                    {0,0,0,0,1,1,1,0,0,0,3,0},
                    {0,3,0,2,0,0,1,0,0,0,0,0},
                    {0,0,0,0,1,1,1,0,0,0,0,0},
                    {0,0,0,0,1,0,0,0,0,1,1,1},
                    {0,3,0,0,1,1,1,0,0,1,0,1},
                    {0,0,0,0,0,0,1,1,1,1,0,1},};
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map3,3));            
        }else if(Greenfoot.mouseClicked(mapFour)) {
            stopped();
            int map4[][] = 
                {{1,0,3,0,0,2,0,0,0,0,0,2}, 
                    {1,1,0,0,0,0,0,0,0,3,0,0}, 
                    {0,1,0,1,1,1,1,1,1,1,1,1},
                    {0,1,2,1,0,0,2,0,0,0,0,1},
                    {0,1,0,1,1,1,1,1,1,1,0,1},
                    {0,1,0,0,0,0,0,0,0,1,0,1},
                    {0,1,0,1,1,1,1,1,1,1,3,1},
                    {3,1,1,1,0,0,0,0,0,0,0,1},};
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map4,4));            
        }else if(Greenfoot.mouseClicked(mapFive)) {
            stopped();
            int map5[][] = 
                {{1,0,0,2,0,0,1,1,1,3,0,0}, 
                    {1,1,1,1,0,0,1,0,1,0,1,1}, 
                    {0,0,0,1,0,0,1,0,1,0,1,0},
                    {1,1,1,1,0,2,1,0,1,0,1,0},
                    {1,0,0,0,0,0,1,0,1,0,1,0},
                    {1,0,1,1,1,0,1,0,1,2,1,0},
                    {1,3,1,0,1,0,1,0,1,0,1,3},
                    {1,1,1,0,1,1,1,0,1,1,1,0},};
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map5,5));            
        }else if(Greenfoot.mouseClicked(mapSix)) {
            stopped();
            int map6[][] = {{1,0,0,0,0,0,2,0,0,0,0,0},
                    {1,1,1,1,1,1,1,1,1,1,1,0},
                    {0,0,0,0,0,0,2,0,0,0,1,2},
                    {0,0,3,0,0,0,0,0,0,0,1,0},
                    {0,0,0,0,3,0,0,0,0,0,1,0},
                    {0,0,0,0,0,0,0,0,3,0,1,0},
                    {0,0,0,2,0,0,0,0,0,0,1,3},
                    {0,0,0,0,0,0,0,0,0,3,1,0},
                };
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map6,6));            
        }else if(Greenfoot.mouseClicked(mapSeven)) {
            stopped();
            int map7[][] = {{1,1,0,2,0,3,0,0,0,0,0,2},
                    {0,1,1,0,0,0,0,0,0,0,0,0},
                    {0,0,1,1,0,0,0,0,2,0,0,0},
                    {0,3,0,1,1,0,0,0,0,0,0,2},
                    {0,0,0,0,1,1,0,0,3,0,0,0},
                    {0,0,0,2,0,1,1,2,0,0,0,0},
                    {0,2,0,0,0,0,1,1,0,0,0,2},
                    {0,0,0,2,0,3,0,1,0,0,0,0},
                };
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map7,7));            
        }else if(Greenfoot.mouseClicked(mapEight)) {
            stopped();
            int map8[][] = {{1,1,1,1,1,1,1,1,1,1,1,0},
                    {0,0,0,2,0,0,0,0,0,2,1,2},
                    {0,1,1,1,1,1,1,1,1,1,1,0},
                    {0,1,0,3,0,0,0,0,3,0,0,0},
                    {0,1,1,1,1,1,1,1,1,1,1,0},
                    {0,0,0,3,0,0,0,0,2,0,1,3},
                    {0,1,1,1,1,1,1,1,1,1,1,0},
                    {0,1,0,0,0,0,0,0,0,0,0,0},
                };
            Greenfoot.setWorld(new MyWorld(spawnChange, speedChange,map8,8));            
        }
        //}
        //fm.shrink();
        /*(sm.isMouseHovering()){

        }
        while(tm.isMouseHovering()){

        }*/
    }

    /**
     * Clears the images and texts relating to the maps off of the screen
     */
    public void clearAll(){
        removeObject(char1);
        removeObject(char2);
        removeObject(char3);
        removeObject(char4);
        removeObject(char5);
        removeObject(char6);
        removeObject(char7);
        removeObject(char8);
        removeObject(mapOne);
        removeObject(mapTwo);
        removeObject(mapThree);
        removeObject(mapFour);
        removeObject(mapFive);
        removeObject(mapSix);
        removeObject(mapSeven);
        removeObject(mapEight);
    }
}
