import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.FileWriter;

import java.io.PrintWriter;

import java.io.IOException;
/**
 * UserInfo class will upload the highscore of this game to a textfile
 * 
 * @author (Marcus Yeung) 
 * @version (June 15 2023)
 */
public class UserInfo
{
    MyWorld world;
    public UserInfo(World w){
        world = (MyWorld)w;
        try{
            FileWriter out = new FileWriter("HighScore.txt");
            PrintWriter output = new PrintWriter (out);
            output.println(world.getHighScore());
            output.close();
        }
        catch(IOException e){
            System.out.println("Error: "+ e);
        }
    }
    
}
