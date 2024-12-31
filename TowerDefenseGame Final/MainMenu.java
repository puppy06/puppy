import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * The MainMenu world is used as the tower defense game's main menu where the user will be able to change settings, look at instructions and go to the MapSelectionWorld.
 * 
 * @author (Marcus Yeung) 
 * @version June 15 2023
 * 
 * Classical Pop by: Pro Sensory
 * https://opengameart.org/content/classical-pop-chiptune
 * 
 * Theme of Agrual by: Matthew Pablo
 * https://opengameart.org/content/theme-of-agrual
 * 
 * Hitting Wall by: spookymodem
 * https://opengameart.org/content/wall-impact
 * 
 * Chase by: Wolfgang_
 * https://opengameart.org/content/chase
 * 
 * Main Menu image
 * https://www.pinterest.ca/pin/304204149815340510/
 * 
 * Instructions background
 * https://openart.ai/discovery/sd-1007876758525194272
 * 
 * LoseWorld background
 * https://openart.ai/discovery/sd-1006214832086847558
 * 
 * WinWorld background
 * https://search.krea.ai/prompt/24381c1b-cf4e-4a88-a770-d6f7677f46e7
 * 
 * MapSelection Background image
 * https://openart.ai/discovery/sd-1007876758525194272
 * 
 * MoneyBox
 * https://creazilla.com/nodes/1999260-brown-crate-clipart
 * 
 * Permafrost by Scott Buckley
 * https://www.chosic.com/download-audio/45497/ 
 * 
 * SuperTextBox by: 
 * @ author Jordan Cohen
 * 
 * @ version 1.0
 * 
 * SuperSmoothMover by: 
 * @ author Jordan Cohen
 * @ version 3.1.jc
 * 
 * Coordinate by: 
 * @ author Jordan Cohen
 * @ version November 2021
 */
public class MainMenu extends World
{
    private GreenfootSound music;
    private GreenfootSound[] buttonSounds;
    private int buttonSoundIndex;
    private Font titleText;
    private SuperTextBox instructionText;
    private SuperTextBox chooseMapText;
    private SuperTextBox textBox1, textBox2;
    private Button button1, button2, button3, button4;
    private double spawnChange;
    private double speedChange;

    private MouseInfo m;

    private Font boringFont;
    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 900x480 cells with a cell size of 1x1 pixels.
        super(900, 480, 1); 
        spawnChange = 1;
        speedChange = 1;
        music = new GreenfootSound ("ThemeofAgrual.mp3");
        boringFont = new Font ("Times New Roman", false, false, 18);
        String[] tempText1 = {"Balloon Speed: "+speedChange+"X"};
        textBox1 = new SuperTextBox(tempText1, Color.BLACK, Color.YELLOW, boringFont, true, 160, 3, Color.YELLOW);
        addObject(textBox1, 175, 300);
        String[] tempText2 = {"Obstruct chance: "+spawnChange+"X"};
        textBox2 = new SuperTextBox(tempText2, Color.BLACK, Color.YELLOW, boringFont, true, 170, 3, Color.YELLOW);
        addObject(textBox2, 650, 300);
        button1 = new Button();
        addObject(button1, 280, 300); //increase speed
        button2 = new Button();
        button2.setRotation(180);
        addObject(button2, 70, 300); //decrease speed
        button3 = new Button();
        addObject(button3, 760, 300); // decrease spawn chance
        button4 = new Button();
        button4.setRotation(180);
        addObject(button4, 540, 300);//increase spawn chance
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

    public void act () {
        started();
        //Changes to MyWorld when the user presses enter
        if (Greenfoot.isKeyDown("enter")){
            stopped();
            //Greenfoot.setWorld(new MyWorld(spawnChange, speedChange));
            Greenfoot.setWorld(new MapSelectionWorld(spawnChange, speedChange));
        }
        //Changes to InstructionWorld when the user presses i
        if (Greenfoot.isKeyDown("i")){
            stopped();
            Greenfoot.setWorld(new InstructionWorld());
        }
        buttonClicked();

    }

    /**
     * Allows the user to change settings in the simulation by pressing buttons
     */
    public void buttonClicked(){
        m = Greenfoot.getMouseInfo();
        //Decreases the possibility to spawn balloons and plays a sound effect when the button is pressed
        if(Greenfoot.mouseClicked(button3)){
            if(spawnChange!=0){
                spawnChange+=0.1;
            }
            spawnChange = Math.min(5.0, Math.max(0.1, spawnChange));
            textBox2.update("Obstruct chance: "+(Math.round(spawnChange*100)/100.0)+"X");
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
        }
        //Increases the possibility to spawn balloons and plays a sound effect when the button is pressed
        if(Greenfoot.mouseClicked(button4)){
            if(spawnChange!=5){
                spawnChange-=0.1;
            }
            spawnChange = Math.min(5.0, Math.max(0.1, spawnChange));
            textBox2.update("Obstruct chance: "+(Math.round(spawnChange*100)/100.0+"X"));
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
        }
        //Decreases the movement speed of Balloons and plays a sound effect when the button is pressed
        if(Greenfoot.mouseClicked(button2)){
            if(speedChange!=1){
                speedChange-=1.0;
            }
            textBox1.update("Balloon Speed: "+Math.round(speedChange*100)/100.0+"X");
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
        }
        //Increases the movement speed of Balloons and plays a sound effect when the button is pressed
        if(Greenfoot.mouseClicked(button1)){
            if(speedChange!=5){
                speedChange+=1.0;
            }
            textBox1.update("Balloon Speed: "+Math.round(speedChange*100)/100.0+"X");
            buttonSounds[buttonSoundIndex].play();
            buttonSoundIndex++;
            if(buttonSoundIndex>buttonSounds.length-1){
                buttonSoundIndex=0;
            }
        }
    }
}